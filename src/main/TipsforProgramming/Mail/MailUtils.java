package Mail;

import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
/**
 * Created by john_liu on 2017/7/30.
 */
@Setter
@Getter
public class MailUtils {

     public static MailUtils mailutils;
      String myEmailAccount = "";
      String myEmailPassword = "";
      String myEmailSMTPHost = "";
      Properties MailContentProperties;
      Session    session;

    public MailUtils(Properties properties) {

        MailContentProperties = properties;
        updateAccount();

    }
    public MailUtils(String account,String password,String SMTPhost){
        this.myEmailAccount = account;
        this.myEmailPassword = password;
        this.myEmailSMTPHost = SMTPhost;
        updateProperties();
    }

    private void updateAccount() {
        this.myEmailAccount = MailContentProperties.getProperty("SenderEmailAccount");
        this.myEmailPassword =MailContentProperties.getProperty("SenderEmailPassword");
        this.myEmailSMTPHost =MailContentProperties.getProperty("SenderEmailSMTPHost");
    }

    private void updateProperties()
    {
        MailContentProperties.setProperty("SenderEmailAccount",myEmailAccount);
        MailContentProperties.setProperty("SenderEmailPassword",myEmailPassword);
        MailContentProperties.setProperty("SenderEmailSMTPHost",myEmailSMTPHost);
    }
//    public static void main(String[] args) {
//        Properties props = new Properties();
//
//    }
    public MimeMessage createMail(Properties props) throws UnsupportedEncodingException, MessagingException {
        if(session == null) {
            Properties properties = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");
            this.session = Session.getDefaultInstance(properties);
            session.setDebug(true);
        }
        MimeMessage message = new MimeMessage(session);
        String[] receivers =props.getProperty("Receiver","").split(",");
        String[] carboncopys =props.getProperty("CarbonCopy","").split(",");
        String[] blindcopys  = props.getProperty("BlindCopy","").split(",");
        String   subject     = props.getProperty("Subject","");
        String   content     = props.getProperty("Content","");

        loopRecivers(message,receivers,"R");
        loopRecivers(message,carboncopys,"C");
        loopRecivers(message,blindcopys,"B");
        message.setSentDate(new Date());
        message.setContent(content,"text/html;charset=UTF-8");
        message.setSubject(subject,"UTF-8");
        return message;
    }

    private void loopRecivers(MimeMessage message,String[] receivers,String type) throws UnsupportedEncodingException, MessagingException {

     switch (type){
         case "R":{
             for(int i=0 ;i<receivers.length;i++)
             {   String [] array = receivers[i].split(":");
                 message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(array[0], array[1], "UTF-8"));
             }

         }
         case "C":{
             for(int i=0 ;i<receivers.length;i++)
             {   String [] array = receivers[i].split(":");
                 message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(array[0], array[1], "UTF-8"));
             }
         }
         case "B":{
             for(int i=0 ;i<receivers.length;i++)
             {   String [] array = receivers[i].split(":");
                 message.addRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(array[0], array[1], "UTF-8"));
             }
         }
     }
    }

    public  void sendMail(Message message) throws MessagingException {
      Transport transport = session.getTransport();
      transport.connect(myEmailAccount,myEmailPassword);
      transport.sendMessage(message,message.getAllRecipients());
      transport.close();

    }
}
