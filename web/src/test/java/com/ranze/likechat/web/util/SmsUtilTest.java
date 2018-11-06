package com.ranze.likechat.web.util;

import com.ranze.likechat.web.exception.ExceedQpsLimitException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmsUtilTest {

    @Test
    public void sendValidationCode() {
        long start = System.currentTimeMillis();
        String phoneNum = "15261163516";
        boolean ret = false;
        try {
            ret = SmsUtil.sendValidationCode(phoneNum, "1231123");
        } catch (ExceedQpsLimitException e) {
            e.printStackTrace();
        }
        System.out.println("Send result = " + ret);
        System.out.println("Time used = " + (System.currentTimeMillis() - start));

    }
}