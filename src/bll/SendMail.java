package bll;

public class SendMail {

	public void sendMail(String email, String message) {
		// TODO Auto-generated constructor stub
		Mail mail = new Mail();
		mail.setHost("smtp.126.com"); // �����ʼ�������
		mail.setPort("465"); // �����ʼ��������˿ں�,Ĭ��25
		mail.setSender("apply_classroom@126.com"); // ������
		mail.setName("���������ϵͳ"); // �������ǳ�
		mail.setReceiver(email); // ������
		mail.setUserName("apply_classroom@126.com"); // ��¼�˺�,һ�㶼�Ǻ�������һ��
		mail.setPassWord("roomapply666"); // QQ�����¼�������ͻ���ʱ,����������롰��Ȩ�롱������֤���������������鿴�ʼ���������˵��
		mail.setSubject("֪ͨ�ʼ�");
		mail.setMessage(message);
		MailUtil mailUtil = new MailUtil();
		mailUtil.send(mail);
	}

}
