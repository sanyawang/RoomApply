package bll;

import java.io.Serializable;

public class Mail implements Serializable{

	public static final String enCoding = "UTF-8";//�ʼ�����
	private String host;//��������ַ
	private String port;//�������˿ں�
	private String sender;//����������
	private String receiver;//�ռ��˵�ַ
	private String name;//�������ǳ�
	private String userName;//�˺�
	private String passWord;//����
	private String subject;//�ʼ�����
	private String message;//���ݣ�֧��HTML��

		//get��set����
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
