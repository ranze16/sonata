package com.ranze.likechat.web.service;

import com.ranze.likechat.web.cons.Constants;
import com.ranze.likechat.web.entity.dataobject.UserInfo;
import com.ranze.likechat.web.entity.viewobject.UserCreate;
import com.ranze.likechat.web.exception.*;
import com.ranze.likechat.web.mapper.UserInfoMapper;
import com.ranze.likechat.web.result.ResultStatEnum;
import com.ranze.likechat.common.RedisUtil;
import com.ranze.likechat.web.util.SmsUtil;
import com.ranze.likechat.web.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    SmsUtil smsUtil;

    @Override
    public void createUser(UserCreate userCreate) throws CellPhoneExistsException, WrongValidationCodeException, ExceedQpsLimitException {
        String cellPhoneNum = userCreate.getCellPhoneNum();
        try {
            UserInfo user = userInfoMapper.selectByCellPhoneNum(cellPhoneNum);
            if (user != null) {
                throw new CellPhoneExistsException(ResultStatEnum.CELL_PHONE_EXISTS);
            }

            String redisKey = "create_user:" + cellPhoneNum;
            if (userCreate.getValidationCode() == 0) {
                int code = (int) ((Math.random() * 9 + 1) * 100000);
                long startSend = System.currentTimeMillis();
                boolean sendSuccess = SmsUtil.sendValidationCode(cellPhoneNum, code + "");
                log.info("Time for send validationCode: {}", System.currentTimeMillis() - startSend);

                if (sendSuccess) {
                    redisUtil.vSet(redisKey, code, Constants.FIVE_MINUTE_IN_SECONDS);
                } else {
                    throw new InnerErrorException(ResultStatEnum.INNER_ERROR);
                }

            } else {
                // 如果键不存在或者已经过期，则validationCode为null，以验证码错误处理
                Integer validationCode = redisUtil.vGet(redisKey);
                redisUtil.del(redisKey);
                if (validationCode != null && validationCode != -1 && validationCode == userCreate.getValidationCode()) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setCellPhoneNum(cellPhoneNum);
                    userInfo.setNickName("");
                    userInfo.setPassword(userCreate.getPassword());
                    // TODO: 2018/11/3 分布式唯一id
                    userInfo.setId(snowflakeIdWorker.nextId());
                    int insertCount = userInfoMapper.insertSelective(userInfo);
                    if (insertCount <= 0) {
                        throw new CellPhoneExistsException(ResultStatEnum.CELL_PHONE_EXISTS);
                    }
                } else {
                    throw new WrongValidationCodeException(ResultStatEnum.WRONG_VALIDATION_CODE);
                }

            }
        } catch (CellPhoneExistsException | WrongValidationCodeException | ExceedQpsLimitException e) {
            throw e;
        } catch (Exception e) {
            throw new LikeChatException(e.getMessage(), e);
        }

    }

}
