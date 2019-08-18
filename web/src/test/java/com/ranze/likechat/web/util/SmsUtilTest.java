package com.ranze.likechat.web.util;

import com.aliyuncs.exceptions.ClientException;
import com.ranze.likechat.web.exception.ExceedSmsLimitException;
import org.junit.Test;

public class SmsUtilTest {

    @Test
    public void sendValidationCode() {
        long start = System.currentTimeMillis();
        String phoneNum = "15261163516";
        boolean ret = false;
        try {
            ret = SmsUtil.sendValidationCode(phoneNum, "1231123");
        } catch (ExceedSmsLimitException | ClientException e) {
            e.printStackTrace();
        }
        System.out.println("Send result = " + ret);
        System.out.println("Time used = " + (System.currentTimeMillis() - start));

    }
}