package dammayxanh.authinh.mail.entity;

import dammayxanh.authinh.web.model.PersonInfo;

import java.util.ArrayList;
import java.util.List;

public class MailTemplate {
    private String subject;
    private List<PersonInfo> addressList;
    private List<PersonInfo> CClist;
    private String content;
    private String singture;

    public MailTemplate() {
        addressList = new ArrayList<PersonInfo>();
        CClist = new ArrayList<PersonInfo>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<PersonInfo> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<PersonInfo> addressList) {
        this.addressList = addressList;
    }

    public List<PersonInfo> getCClist() {
        return CClist;
    }

    public void setCClist(List<PersonInfo> CClist) {
        this.CClist = CClist;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSingture() {
        return singture;
    }

    public void setSingture(String singture) {
        this.singture = singture;
    }
}
