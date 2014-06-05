package com.oa.email.receiver;

import java.util.Properties;

/**
 * 服务器种类：提供了网易和腾讯的企业邮箱(这两种已经测试通过)，和谷歌（测试未通过） 后期有需要可以扩展
 * 
 * @author athena
 */
public enum HostType {

	NETEASE {
		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.163.com");
			defaults.put("mail.imap.host", "imap.163.com");
			defaults.put("mail.store.protocol", "pop3"); // 默认使用pop3收取邮件
			return defaults;
		}

	},
	TENCENT {
		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.exmail.qq.com");
			defaults.put("mail.imap.host", "imap.exmail.qq.com");
			defaults.put("mail.store.protocol", "pop3"); // 默认使用pop3收取邮件
			return defaults;
		}
	},
	GMAIL {

		@Override
		public Properties getProperties() {
			Properties defaults = new Properties();
			defaults.put("mail.pop3.host", "pop.gmail.com");
			defaults.put("mail.pop3.port", "995");
			defaults.put("mail.imap.host", "imap.gmail.com");
			defaults.put("mail.imap.port", "465");
			defaults.put("mail.store.protocol", "pop3"); // 默认使用pop3收取邮件
			return defaults;
		}

	};

	public abstract Properties getProperties();

}