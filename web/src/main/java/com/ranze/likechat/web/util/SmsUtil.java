package com.ranze.likechat.web.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ranze.likechat.web.exception.ExceedSmsLimitException;
import com.ranze.likechat.web.result.ResultStatEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsUtil {
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";

    static final String accessKeyId = "LTAIZU58JtcSzcmT";
    static final String accessKeySecret = "cAqbwE3nkrj7hVicYD6T91sumCsgT5";

    public static boolean sendValidationCode(String phoneNum, String code) throws ExceedSmsLimitException, ClientException {
        try {
            SendSmsResponse sendSmsResponse = sendSms(phoneNum, "SMS_150174074", code);
            String respCode = sendSmsResponse.getCode();
            if (respCode.equals("OK")) {
                return true;
            } else if (respCode.equals("isv.BUSINESS_LIMIT_CONTROL")) {
                throw new ExceedSmsLimitException(ResultStatEnum.EXCEED_QPS_LIMIT);
            }
        } catch (ClientException e) {
            log.warn("SendValidationCode exception: {}", e.getMessage());
            throw e;
        }
        return false;
    }

    private static SendSmsResponse sendSms(String phoneNum, String templateCode, String templateParam) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("Like");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":" + templateParam + "}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        log.info("Aliyun Send phone message: RequestId = " + sendSmsResponse.getRequestId()
                + ", Code = " + sendSmsResponse.getCode() + ", Message = " + sendSmsResponse.getMessage()
                + ", BizId = " + sendSmsResponse.getBizId());

        return sendSmsResponse;
    }
}
