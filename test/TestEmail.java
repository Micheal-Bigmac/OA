
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.oa.email.send.MyAuthenticator;


//import classes.MyAuthenticator;
public class TestEmail {
	public static void main(String[] args) {

		String email = "2452064683@qq.com";// 发送到的邮箱;
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());// 可以自己得到系统日期;
		System.out.println(date);
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");// 邮件服务器地址
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "false");
		props.put("mail.smtp.port", "25");//25;
		props.put("mail.smtp.user", "fly_mz");// 发送方的发送名;
		props.put("mail.smtp.from", "15518977794@163.com");// 发送邮箱地址;
		props.put("mail.debug", "true");
		MyAuthenticator auth = new MyAuthenticator("15518977794@163.com",
				"song5438");
		Session session = Session.getInstance(props, auth);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		try {
			InternetAddress[] addresses = { new InternetAddress(email) };
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setSentDate(Date.valueOf(date));
			// 设置邮件的标题
			String subject = "5.1 日去泰山旅游吧";
			msg.setSubject(subject);
			// 设置邮件的内容
			String msgtext = "亲爱的";
			msg.setText(msgtext);
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}