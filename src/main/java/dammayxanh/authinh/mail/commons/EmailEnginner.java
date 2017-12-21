package dammayxanh.authinh.mail.commons;

import dammayxanh.authinh.mail.entity.MailTemplate;
import dammayxanh.authinh.web.model.Authenticator;
import dammayxanh.authinh.web.model.PersonInfo;
import dammayxanh.authinh.web.model.WebConfigMail;
import org.apache.commons.mail.*;

import java.lang.reflect.Field;
import java.net.URL;

public class EmailEnginner {
    private String hostName;
    private Integer smptpPort;
    private Authenticator authenticator;
    private Email email;

    public EmailEnginner() {
        email = new SimpleEmail();

        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(Constants.MY_EMAIL,
                Constants.MY_PASSWORD));
        this.email.setSSLOnConnect(true);
    }

    public EmailEnginner(Email email, String hostName, Integer smptpPort, Authenticator authenticator) {
        this.hostName = hostName;
        this.smptpPort = smptpPort;
        this.authenticator = authenticator;

        this.email = email;

        this.email.setHostName(hostName);
        this.email.setSmtpPort(smptpPort);
        this.email.setAuthenticator(new DefaultAuthenticator(authenticator.getUserName(),
                authenticator.getPassword()));

        this.email.setSSLOnConnect(true);
    }

    public EmailEnginner(Email email, WebConfigMail configMail, Authenticator authenticator) {
        this.email = email;

        this.hostName = configMail.getHostName();
        this.smptpPort = configMail.getSmtpPort();
        this.authenticator = authenticator;

        this.email = email;

        this.email.setHostName(hostName);
        this.email.setSmtpPort(smptpPort);
        this.email.setAuthenticator(new DefaultAuthenticator(authenticator.getUserName(),
                authenticator.getPassword()));

        this.email.setSSLOnConnect(configMail.isSSLOnConnect());
    }

    public void sendSimpleTextEmail(MailTemplate mailTemplate) {
        // Người gửi
        try {
            email.setFrom(authenticator.getMailAdress());

            // Tiêu đề
            email.setSubject(mailTemplate.getSubject());

            // Nội dung email

            email.setMsg(mailTemplate.getContent());

            // Người nhận
            for (PersonInfo personInfo: mailTemplate.getAddressList()
                 ) {
                email.addTo(personInfo.getMailAdress());
            }

            for (PersonInfo personInfo: mailTemplate.getCClist()
                    ) {
                email.addCc(personInfo.getMailAdress());
            }
            email.send();
            System.out.println("Sent!!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void sendHtmlEmail(MailTemplate mailTemplate){
        HtmlEmail email = (HtmlEmail) this.email;
        try {
            email.setFrom(authenticator.getMailAdress());

            // Người nhận
            for (PersonInfo personInfo: mailTemplate.getAddressList()
                    ) {
                email.addTo(personInfo.getMailAdress());
            }

            for (PersonInfo personInfo: mailTemplate.getCClist()
                    ) {
                email.addCc(personInfo.getMailAdress());
            }

            // Tiêu đề
            email.setSubject(mailTemplate.getSubject());

            // Sét nội dung email định dạng HTML.
            email.setHtmlMsg(mailTemplate.getContent());

            // Thiết lập các thông báo thay thế
            // (Trong trường hợp chương trình đọc mail của người nhận ko hỗ trợ đọc HTML Email)
            email.setTextMsg("Your email client does not support HTML messages");

            // Gửi email
            email.send();

            System.out.println("Sent!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String fillContentMailByParameter(PersonInfo personInfo,String content){

        content = content.replace(Constants.FIRST_NAME,personInfo.getFirstName());
        content = content.replace(Constants.MAIL_ADRESS,personInfo.getMailAdress());
        content = content.replace(Constants.LAST_NAME,personInfo.getLastName());
        content = content.replace(Constants.AGE,personInfo.getAge()+"");
        content = content.replace(Constants.TEL,personInfo.getTel()+"");

        if (personInfo.getExtraParameter() ==null){
            return content;
        }
        for ( String map: personInfo.getExtraParameter().split(";") ) {
            String[] param = map.split(":");
            String key = param[0];
            String value = param[1];
            content = content.replace("${"+key+"}",value);
        }
//        content.replaceAll(PersonInfo.class.getFields())
        return content;
    }

}
