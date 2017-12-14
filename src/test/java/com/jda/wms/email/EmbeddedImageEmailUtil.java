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

//		msg.setFrom(new InternetAddress("NPS-AUTOMATION"));
		msg.setFrom(new InternetAddress("y0137206@mnscorp.net"));

		String[] mailAddressTo = requestDetailsRetriever.getmailAddress(context.getParentRequestId());


		InternetAddress[] mailAddress_TO = new InternetAddress[mailAddressTo.length];

		for (int i = 0; i < mailAddressTo.length; i++) {
			mailAddress_TO[i] = new InternetAddress(mailAddressTo[i]);
		}
		msg.addRecipients(Message.RecipientType.TO, mailAddress_TO);

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

//		File reportZipPath = new File(configuration.getStringProperty("cucumber-zip-path"));
//		File[] listOfFiles = reportZipPath.listFiles();
//		if (reportZipPath.listFiles().length!=0){
//			for(int r=0;r<listOfFiles.length;r++){
//				if(listOfFiles[r].getName().contains(".zip")){
//					String filePath = configuration.getStringProperty("cucumber-zip-path");
//					String fileName = "Cucumber_Report.zip";
//					MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//					attachmentBodyPart.attachFile(new File(filePath + "/" + fileName));
//					multipart.addBodyPart(attachmentBodyPart);
//				}
//			}
//		}
		msg.setContent(multipart);
		Transport.send(msg);
	}
}
