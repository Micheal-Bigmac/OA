package com.oa.email.receiver;

/**
 * 邮件测试类
 * 
 * @author athena
 * 
 */
public class test {

	public static void main(String[] args) {
		MessageParser.parse(SimpleMailReceiver.fetchInbox(HostType.NETEASE.getProperties(),
				AuthenticatorGenerator.getAuthenticator("15518977794@163.com", "song5438")));
	}
}