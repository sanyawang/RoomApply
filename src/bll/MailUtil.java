package bll;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

//�ʼ����͹���ʵ����
public class MailUtil {

	public boolean send(Mail mail) {
		//����email����
		HtmlEmail email = new HtmlEmail();
		try {
			//smtp���ͷ�����������
			email.setHostName(mail.getHost());
			//
			if(!"".equals(mail.getPort())) {
				email.setSSLOnConnect(true);
				email.setSslSmtpPort(mail.getPort());
			}
			//���ñ����ַ���
			email.setCharset(Mail.enCoding);
			//�ռ��˵�����
			email.addTo(mail.getReceiver());
			//�����˵�����
			email.setFrom(mail.getSender(), mail.getName());
			//������֤��Ϣ
			email.setAuthentication(mail.getUserName(), mail.getPassWord());
			//�ʼ�����
			email.setSubject(mail.getSubject());
			//�ʼ�����
			email.setMsg(mail.getMessage());
			//�����ʼ�
			email.send();
			return true;
		}catch(EmailException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
