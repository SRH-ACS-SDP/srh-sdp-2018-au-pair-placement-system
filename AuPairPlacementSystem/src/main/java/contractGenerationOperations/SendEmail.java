package contractGenerationOperations;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.exolab.castor.mapping.MappingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void main(String[] args) throws AddressException, MessagingException {
		
		String to = "selesab17@gmail.com";
		//String to = "jagtarsinghtandon@gmail.com ";
		//String to = "neev689@gmail.com ";
		
		

		String from = "aupairsystem@gmail.com";

		String host = "localhost";

		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");

		//Session session = Session.getDefaultInstance(properties);
		
		Session session = Session.getDefaultInstance(properties,
			    new Authenticator() {
			        protected PasswordAuthentication  getPasswordAuthentication() {
			        return new PasswordAuthentication(
			                    "aupairsystem@gmail.com", "Pass123$$");
			                }
			    });


		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("Au-Pair contract generated");

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("****** THIS IS A SYSTEM GENERATED EMAIL . PLEASE DO NOT REPLY. ******"
					+ "Please find attached the contract generated between Au pair and Host. "
					+ "This is a binding contract.");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			String currentUsersHomeDir = System.getProperty("user.home");

			String filename = currentUsersHomeDir + "/ContractForHostAndAuPair.pdf";

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (Exception mex) {
			mex.printStackTrace();
		}

	}
}
