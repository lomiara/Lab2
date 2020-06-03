package TI.PR;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class App {


    App(){
        sendMessage();
    }

    public static void main( String[] args ) {
        new App();
    }

    public void getMessages(){
        String from = "frenzyk123321@gmail.com";
        String host = "pop.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.pop3.host",host);
        properties.put("mail.pop3.port","995");
        properties.put("mail.pop3.starttls.enable","true");
        Session emailSession = Session.getDefaultInstance(properties);
        try {
            Store store = emailSession.getStore("pop3s");
            store.connect(host,from,pas);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---"+messages.length);
            for (int i = 0; i < 5; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }
            emailFolder.close();
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        String to = "andrei.sidletchi@gmail.com";
        String from = "frenzyk123321@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        Session session = Session.getInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("frenzyk123321@gmail.com",pas);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setText("Denis");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("TestMessage");
            System.out.println("Sending..");
            Transport.send(message);
            System.out.println("Sent message successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    String pas = "roocjomfngrfsnbb";
}
