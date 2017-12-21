package dammayxanh.authinh.web.model;

public class WebConfigMail {
    private String hostName;
    private int smtpPort;
    private boolean SSLOnConnect;

    public WebConfigMail() {
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isSSLOnConnect() {
        return SSLOnConnect;
    }

    public void setSSLOnConnect(boolean SSLOnConnect) {
        this.SSLOnConnect = SSLOnConnect;
    }

    @Override
    public String toString() {
        return "WebConfigMail{" +
                "hostName='" + hostName + '\'' +
                ", smtpPort=" + smtpPort +
                ", SSLOnConnect=" + SSLOnConnect +
                '}';
    }
}
