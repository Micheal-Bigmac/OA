package com.oa.email.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.util.MimeMessageParser;

/**
 * 邮件解析类
 * 
 * @author athena
 * 
 */
public class MessageParser  {
	/**
	 * 邮件附件存放位置
	 */
	private static final String folder = "c:\\upload";

	private static void parse(Message message) {
		try {
			MimeMessageParser parser = new MimeMessageParser((MimeMessage) message).parse();
			String from = parser.getFrom(); // 获取发件人地址
			List<Address> cc = parser.getCc();// 获取抄送人地址
			List<Address> to = parser.getTo(); // 获取收件人地址
			String replyTo = parser.getReplyTo();// 获取回复邮件时的收件人
			String subject = parser.getSubject(); // 获取邮件主题
			String htmlContent = parser.getHtmlContent(); // 获取Html内容
			String plainContent = parser.getPlainContent(); // 获取纯文本邮件内容（注：有些邮件不支持html）

			System.out.println(subject);

			List<DataSource> attachments = parser.getAttachmentList(); // 获取附件，并写入磁盘
			for (DataSource ds : attachments) {
				BufferedOutputStream outStream = null;
				BufferedInputStream ins = null;
				try {
					String fileName = folder + File.separator + ds.getName();
					outStream = new BufferedOutputStream(new FileOutputStream(fileName));
					ins = new BufferedInputStream(ds.getInputStream());
					byte[] data = new byte[2048];
					int length = -1;
					while ((length = ins.read(data)) != -1) {
						outStream.write(data, 0, length);
					}
					outStream.flush();
					System.out.println("附件:" + fileName);
				} finally {
					if (ins != null) {
						ins.close();
					}
					if (outStream != null) {
						outStream.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parse(Message... messages) {
		if (messages == null || messages.length == 0) {
			System.out.println("没有任何邮件");
		} else {
			for (Message m : messages) {
				parse(m);
			}
			// 最后关闭连接
			if (messages[0] != null) {
				Folder folder = messages[0].getFolder();
				if (folder != null) {
					try {
						Store store = folder.getStore();
						folder.close(false);// 参数false表示对邮件的修改不传送到服务器上
						if (store != null) {
							store.close();
						}
					} catch (MessagingException e) {
						// ignore
					}
				}
			}
		}

	}
}