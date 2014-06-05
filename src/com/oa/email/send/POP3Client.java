package com.oa.email.send;

import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
//利用POP3来读取邮件
//主要用来检测消息Message的基本信息，如发送者，收信者，时间

public class POP3Client {
	public static void main(String[] args) {
        Properties props = System.getProperties();
        String host = "pop3.163.com";
        String username = "15518977794@163.com";
        String password = "song5438";
        String provider = "pop3";
        try {
            // 连接到POP3服务器
            Session ss = Session.getDefaultInstance(props, null);
            ss.setDebug(false);
            // 向回话"请求"一个某种提供者的存储库，是一个POP3提供者
            Store store = ss.getStore(provider);
            // 连接存储库，从而可以打开存储库中的文件夹，此时是面向IMAP的
            store.connect(host, username, password);
            // 打开文件夹，此时是关闭的，只能对其进行删除或重命名，无法获取关闭文件夹的信息
            // 从存储库的默认文件夹INBOX中读取邮件
            Folder inbox = store.getFolder("INBOX");
            if (inbox == null) {
                System.out.println("NO INBOX");
                System.exit(1);
            }
            // 打开文件夹，读取信息
            inbox.open(Folder.READ_ONLY);
            System.out.println("TOTAL EMAIL:" + inbox.getMessageCount());
            // 获取邮件服务器中的邮件
            Message[] messages = inbox.getMessages();
            for (int i = 0; i < messages.length; i++) {
                System.out.println("------------Message--" + (i + 1)
                        + "------------");
                // 解析地址为字符串
                String from = InternetAddress.toString(messages[i].getFrom());
                if (from != null) {
                    String cin = getChineseFrom(from);
                    System.out.println("From:" + cin);
                }
                String replyTo = InternetAddress.toString(messages[i]
                        .getReplyTo());
                if (replyTo != null){
                    String rest = getChineseFrom(replyTo);
                    System.out.println("Reply To" + rest);
                }
                String to = InternetAddress.toString(messages[i]
                        .getRecipients(Message.RecipientType.TO));
                if (to != null) {
                    String tos = getChineseFrom(to);
                    System.out.println("To:" + tos);
                }
                String subject = messages[i].getSubject();
                if (subject != null)
                    System.out.println("Subject:" + subject);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date sent = messages[i].getSentDate();
                if (sent != null)
                    System.out.println("Sent Date:" + sdf.format(sent));
                Date ress = messages[i].getReceivedDate();
                if (ress != null)
                    System.out.println("Receive Date:" + sdf.format(ress));
         
//                Multipart multipart = (Multipart)messages[i].getContent();
//                for (int j=0, n=multipart.getCount(); j<n; j++) {
//                Part part = multipart.getBodyPart(j); 
//                String disposition = part.getDisposition(); 
//                if ((disposition != null) && (disposition.equals(Part.ATTACHMENT)) ||(disposition.equals(Part.INLINE))) {   
////                	saveFile(part.getFileName(), part.getInputStream());
//                 }
//                }
            }
            // 关闭连接，但不删除服务器上的消息
            // false代表不是删除
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

	// 解决中文乱码问题
	public static String getChineseFrom(String res) {
		String from = res;
		try {
			if (from.startsWith("=?GB") || from.startsWith("=?gb")
					|| from.startsWith("=?UTF")) {
				from = MimeUtility.decodeText(from);
			} else {
				from = new String(from.getBytes("ISO8859_1"), "GBK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return from;
	}
}
