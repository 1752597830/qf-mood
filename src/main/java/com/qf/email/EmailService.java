package com.qf.email;

/**
 * @Auther: sin
 * @Date: 2023/1/22 - 01 - 22 - 14:19
 * @Description: com.qf.email
 * @version: 1.0
 */


public interface EmailService {
    public String getRegisterEmailVerificationCode(String toAddress, String template);
}
