package ProteinTracker;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class Emailer implements Notifier{
    final String senderEmailID = "username@gmail.com"; //<-- username here
    final String senderPassword = "PutPasswordHere"; //<-- Password here
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";

    @Override
    public boolean send(String message) {
        sendEmail("mbergdal@gmail.com", "Goal met", message);
        return true;
    }

    public void sendEmail(String aToEmailAddr, String aSubject, String aBody){
        Properties props = new Properties();
        props.put("mail.smtp.user",senderEmailID);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(aBody);
            msg.setSubject(aSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(aToEmailAddr));
            Transport.send(msg);
        }
        catch (MessagingException ex){
            System.err.println("Cannot send email. " + ex);
        }
    }

    public class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }
}