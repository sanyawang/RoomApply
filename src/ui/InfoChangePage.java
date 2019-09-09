package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dcl.DBHelper;
import model.TeachingManager;

//�޸���Ϣ�࣬�����޸���Ϣ��ҳ�沼��, �޸Ľ�����Ա�ĸ�����Ϣ
public class InfoChangePage extends JPanel implements ActionListener{
	//��������
	Box boxH;//��ʽ��
	Box boxOne, boxTwo, boxThree, boxFour, boxFive, boxSix;//��ʽ��
	JTextField email, phone;//�����ı���
	JLabel title, emailL, phoneL;//���ݱ�ǩ
	JButton jButton;//�ύ��ť
	TeachingManager teach;//����Ա����
	//���췽��
	public InfoChangePage(TeachingManager teach) {
		
		this.teach = teach;
		setLayout(new FlowLayout());//���õ�ǰ���Ϊ��ʽ����
		
		setBounds(400, 100, 600, 600);
		setVisible(true);
		init();//ҳ���ʼ������
	}
	//�Զ���ҳ���ʼ������
	void init() {
		
		boxH = Box.createVerticalBox();//ˮƽ��ʽ����
		boxOne = Box.createHorizontalBox();//��ֱ��ʽ����
		boxTwo = Box.createHorizontalBox();
		boxThree = Box.createHorizontalBox();
		boxFour = Box.createHorizontalBox();
		boxFive = Box.createHorizontalBox();
		boxSix = Box.createHorizontalBox();
		
		email = new JTextField();//��ʼ���ı���ͱ�ǩ�ؼ�
		phone = new JTextField();
		
		//�����ı����ʼ������Ϣ
		email.setText(teach.getEmail());
		phone.setText(teach.getPhone());
		
		jButton = new JButton("�޸�");
		//Ϊ��ť��Ӽ���
		jButton.addActionListener(this);
		
		title = new JLabel("������Ϣ");
		emailL = new JLabel("�����ʼ�");
		phoneL = new JLabel("�ֻ�����");
		
		boxOne.add(title);//��ˮƽ��ʽ��������ı���ͱ�ǩ
		boxTwo.add(emailL);
		boxTwo.add(Box.createHorizontalStrut(5));//���ˮƽ֧��
		boxTwo.add(email);
		boxThree.add(phoneL);
		boxThree.add(Box.createHorizontalStrut(5));
		boxThree.add(phone);
		boxSix.add(jButton);
		
		boxH.add(Box.createVerticalStrut(100));//�ڲ����ӵ�����
		boxH.add(boxOne);
		boxH.add(Box.createVerticalStrut(30));
		boxH.add(boxTwo);
		boxH.add(Box.createVerticalStrut(15));
		boxH.add(boxThree);
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(boxSix);
		add(boxH);//�����м������
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ȡ���ĺ����Ϣ
		String emailChange = email.getText();
		String phoneChange = phone.getText();
		//������Ϣ
		try {
				//���������ǽ������Ա
				DBHelper.ChangeInfo(emailChange, phoneChange, "teachingmanager", "employeeid", teach.getEmployeeID());
				//�޸ĳɹ���ʾ
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "������ʾ", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//����ҳ����Ϣ
		email.setText(emailChange);
		phone.setText(phoneChange);
	}
}
