package com.jda.wms.email;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.inject.Inject;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;

public class EmbeddedImageEmailUtil {

	private final Context context;
	private final RequestDetailsRetriever requestDetailsRetriever;
	String envVar = System.getProperty("user.dir");
	private Configuration configuration;

	@Inject
	public EmbeddedImageEmailUtil(Context context, RequestDetailsRetriever requestDetailsRetriever,Configuration configuration) {
		this.context = context;
		this.requestDetailsRetriever = requestDetailsRetriever;
		this.configuration = configuration;
	}

	public void send(String host, String port, final String userName, final String password, String subject,
			String htmlBody, Map<String, String> mapInlineImages) throws AddressException, MessagingException, IOException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("NPS-AUTOMATION"));
		// InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		// msg.setRecipients(Message.RecipientType.TO, toAddresses);
		// **********************

		String[] mailAddressTo = requestDetailsRetriever.getmailAddress(context.getParentRequestId());

//		String[] mailAddressTo2 = new String[1];

//		mailAddressTo2[0] = "Santhaseelan.Shanmugam@mnscorp.net";
//		mailAddressTo2[1] = "Santhaseelan.Shanmugam@mnscorp.net";
//		mailAddressTo2[2] = "Aktharunissa.Abdulrahman@mnscorp.net";
//		mailAddressTo2[3]= "Allen.Eapachen@marks-and-spencer.com";
//		mailAddressTo2[4]= "Nandakumar.Sivalingam@marks-and-spencer.com";
//		mailAddressTo2[5]= "Vignesh.Ganapathy@marks-and-spencer.com";
//		mailAddressTo2[6]= "Vamsi.Krishnareddy@marks-and-spencer.com";
//		mailAddressTo2[7]= "Gokulraj.Matheswaran@marks-and-spencer.com";
//		mailAddressTo2[8]= "Shanmuganathan.Murugan@marks-and-spencer.com";
//		mailAddressTo2[9]= "Kiran.Porwal@marks-and-spencer.com";
//		mailAddressTo2[10]= "Abirami.Sivakumar@marks-and-spencer.com";
//		mailAddressTo2[11]= "Badrinath.Jeeva@marks-and-spencer.com";

		InternetAddress[] mailAddress_TO = new InternetAddress[mailAddressTo.length];

		for (int i = 0; i < mailAddressTo.length; i++) {
			mailAddress_TO[i] = new InternetAddress(mailAddressTo[i]);
		}
		msg.addRecipients(Message.RecipientType.TO, mailAddress_TO);
		// msg.addRecipients(Message.RecipientType.TO, mailAddress_TO);

		// **********************
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(htmlBody, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds inline image attachments
		if (mapInlineImages != null && mapInlineImages.size() > 0) {
			Set<String> setImageID = mapInlineImages.keySet();

			for (String contentId : setImageID) {
				MimeBodyPart imagePart = new MimeBodyPart();
				imagePart.setHeader("Content-ID", "<" + contentId + ">");
				imagePart.setDisposition(MimeBodyPart.INLINE);

				String imageFilePath = mapInlineImages.get(contentId);
				try {
					imagePart.attachFile(imageFilePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(imagePart);
			}
		}

//		String filePath = envVar+"/files";
		String filePath = configuration.getStringProperty("cucumber-zip-path");
		String fileName = "Cucumber_Report.zip";
		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		attachmentBodyPart.attachFile(new File(filePath + "/" + fileName));
		multipart.addBodyPart(attachmentBodyPart);
		
		msg.setContent(multipart);

		Transport.send(msg);
	}
}
