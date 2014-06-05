package com.oa.email.send;
/**
 *Module:       MailInfo.java
 *Description:  邮件授权类
 *Company:      
 *Author:       ptp
 *Date:         Mar 6, 2012
 */
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends javax.mail.Authenticator {
    private String strUser;
    private String strPwd;

    public MyAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }

    public  PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
    }
}