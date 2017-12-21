package dammayxanh.authinh.mail;

import dammayxanh.authinh.mail.commons.Constants;
import dammayxanh.authinh.mail.commons.EmailEnginner;
import dammayxanh.authinh.mail.entity.MailTemplate;
import dammayxanh.authinh.web.model.Authenticator;
import dammayxanh.authinh.web.model.PersonInfo;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class SimpleTextEmail {
    public static void main(String[] args) {
        MailTemplate mailTemplate = new MailTemplate();
        String subject = "day la tieu de ${firstName}";
        String content = "<b>dear ${firstName}, </b>\n <h1> ten toi la ${firstName} ${lastName}</h1> \n nam lay toi ${age}, so dien thoai cua toi la ${tel}";


        Authenticator authenticator = new Authenticator();
        authenticator.setUserName("thinhavse04295@fpt.edu.vn");
        authenticator.setPassword("vlboy@96");
        authenticator.setMailAdress("thinhavse04295@fpt.edu.vn");

        PersonInfo personInfo = new PersonInfo();
        personInfo.setMailAdress("auvanthinh@hotmail.com");
        personInfo.setAge(10);
        personInfo.setFirstName("Au");
        personInfo.setLastName("Thinh");
        personInfo.setTel(766566546);
       // personInfo.setExtraParameter("");


        mailTemplate.setSubject(EmailEnginner.fillContentMailByParameter(personInfo, subject));
        mailTemplate.setContent(EmailEnginner.fillContentMailByParameter(personInfo, content));
        mailTemplate.getAddressList().add(personInfo);


       // EmailEnginner emailEnginner = new EmailEnginner(new SimpleEmail(), "smtp.googlemail.com", 465, authenticator);

        //emailEnginner.sendSimpleTextEmail(mailTemplate);

       EmailEnginner emailEnginner = new EmailEnginner(new HtmlEmail(),"smtp.googlemail.com",465,authenticator);
//
//
      emailEnginner.sendHtmlEmail(mailTemplate);
    }
}
