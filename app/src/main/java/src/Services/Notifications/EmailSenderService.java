package src.Services.Notifications;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService extends Authenticator {
    private final String user;
    private final String password;
    private final Session session;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public EmailSenderService() {
        this.user = "obras.en.la.calle.tusi@gmail.com";
        this.password = "Obrasenlacalle123";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        String mailhost = "smtp.gmail.com";
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public void sendMail(String message, String emailTo) {
        this.sendMail("Recupera tu contraseña 🕵️", message, emailTo);
    }

    public void sendMail(String subject, String body, String emailTo) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                MimeMessage message = new MimeMessage(session);
                DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
                message.setSender(new InternetAddress("hola@obrasenlacalle.com"));
                message.setSubject(subject);
                message.setDataHandler(handler);
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                Transport.send(message);
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}
