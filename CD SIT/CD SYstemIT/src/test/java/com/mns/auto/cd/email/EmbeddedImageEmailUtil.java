package com.mns.auto.cd.email;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.memory.KeepInMemory;
import com.mns.auto.cd.utils.DateUtils;

public class EmbeddedImageEmailUtil {

	private final KeepInMemory context;
	private final RequestDetailsRetriever requestDetailsRetriever;
	private final Configuration configuration;
	public static int Min;

	@Inject
	public EmbeddedImageEmailUtil(KeepInMemory context, RequestDetailsRetriever requestDetailsRetriever,
			Configuration configuration) {
		this.context = context;
		this.requestDetailsRetriever = requestDetailsRetriever;
		this.configuration = configuration;
	}

	public void send(String host, String port, final String userName, final String password, String subject,
			String htmlBody, Map<String, String> mapInlineImages)
			throws AddressException, MessagingException, IOException, InterruptedException {
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

		String[] mailAddressTo = requestDetailsRetriever.getmailAddress(context.getParentRequestId());

		// String[] mailAddressTo2 = new String[1];

		// mailAddressTo2[0] = "Santhaseelan.Shanmugam@mnscorp.net";
		// mailAddressTo2[1] = "Santhaseelan.Shanmugam@mnscorp.net";
		// mailAddressTo2[2] = "Aktharunissa.Abdulrahman@mnscorp.net";
		// mailAddressTo2[3]= "Allen.Eapachen@marks-and-spencer.com";
		// mailAddressTo2[4]= "Nandakumar.Sivalingam@marks-and-spencer.com";
		// mailAddressTo2[5]= "Vignesh.Ganapathy@marks-and-spencer.com";
		// mailAddressTo2[6]= "Vamsi.Krishnareddy@marks-and-spencer.com";
		// mailAddressTo2[7]= "Gokulraj.Matheswaran@marks-and-spencer.com";
		// mailAddressTo2[8]= "Shanmuganathan.Murugan@marks-and-spencer.com";
		// mailAddressTo2[9]= "Kiran.Porwal@marks-and-spencer.com";
		// mailAddressTo2[10]= "Abirami.Sivakumar@marks-and-spencer.com";
		// mailAddressTo2[11]= "Badrinath.Jeeva@marks-and-spencer.com";

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

		/*int incrementedMin = DateUtils.getCurrSysTime() + 2;
		int i = 0;
		while (Min < incrementedMin) {
			Min = DateUtils.getCurrSysTime();
			System.out.println(Min + " and " + incrementedMin);
			System.out.println("Loop Count : " + i);
			i++;
		}*/
		File reportZipPath = new File(configuration.getStringProperty("cucumber-zip-path"));
		File[] listOfFiles = reportZipPath.listFiles();
		System.out.println("File Length : " + reportZipPath.listFiles().length);
		for (int r = 0; r < listOfFiles.length; r++) {
			if (reportZipPath.listFiles().length != 0 && listOfFiles[r].getName().contains(".zip")) {
				String filePath = configuration.getStringProperty("cucumber-zip-path");
				String fileName = "Cucumber_Report.zip";
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				System.out.println("File Path : " + filePath + "\\" + fileName);
				attachmentBodyPart.attachFile(new File(filePath + "\\" + fileName));
				multipart.addBodyPart(attachmentBodyPart);
				break;
			} else {
				;
			}
		}

		msg.setContent(multipart);
		Transport.send(msg);
	}
}
