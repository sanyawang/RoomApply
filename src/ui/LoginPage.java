package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dcl.DBHelper;
import model.Applicant;
import model.TeachingManager;

public class LoginPage extends JFrame implements ActionListener{

	/**
	 * ��¼������
	 * 1����֤�û�����ݣ������û��Ĳ�ͬ������ʾ��ͬ�ĵ�¼�˵�
	 * 2�������û�����ϵͳ�л�ȡ�û���Ϣ������
	 */
	private static final long serialVersionUID = 1L;
	Box boxH;//��ʽ��
	Box boxVOne, boxVTwo, boxVThree;//��ʽ��
	JTextField textField;//�û����ı���
	JPasswordField passWord;//�����
	JLabel user;//�û���
	JLabel pwd;//����
	JButton jButton;//��¼��ť
	
	public LoginPage(String s) {
		
		setLayout(new FlowLayout());//���õ�ǰ����Ϊ��ʽ����
		init(s);
		setBounds(500, 200, 300, 300);//��¼����
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init(String s) {
		
		setTitle(s);
		boxH = Box.createVerticalBox();//��ֱ��ʽ����
		boxVOne = Box.createHorizontalBox();//ˮƽ��ʽ����
		boxVTwo = Box.createHorizontalBox();
		boxVThree = Box.createHorizontalBox();
		user = new JLabel("�û���");//�û�����ǩ
		textField = new JTextField(10);//�û����ı�
		pwd = new JLabel("��    ��");//�����ǩ
		passWord = new JPasswordField(10);//�����
		jButton = new JButton("��¼");//��¼��ť
		jButton.addActionListener(this);
		
		boxVOne.add(user);//����û���ǩ
		boxVOne.add(Box.createHorizontalStrut(5));//���ˮƽ֧��
		boxVOne.add(textField);//����û��ı���
		boxVTwo.add(pwd);//��������ǩ
		boxVTwo.add(Box.createHorizontalStrut(5));
		boxVTwo.add(passWord);//��������
		boxVThree.add(jButton);//��ӵ�¼��ť
		
		boxH.add(Box.createVerticalStrut(70));
		boxH.add(boxVOne);//����ʽ��ʽ������ӵ�ˮƽ��ʽ����
		boxH.add(Box.createVerticalStrut(15));//��Ӵ�ֱ֧��
		boxH.add(boxVTwo);
		boxH.add(Box.createVerticalStrut(20));
		boxH.add(boxVThree);
		add(boxH);
	}

	@Override//��¼��ť�ļ����¼�
	public void actionPerformed(ActionEvent e) {
		// �����¼��Ϣ��ѯ���
		String sql = "select * from login where user='"+textField.getText()+"'";
		try {
			//��������
			ResultSet rs = DBHelper.Query(sql);
			
			//���û����У��
			while(rs.next()) {
				//��ȡ����������
				String pass = new String(passWord.getPassword());
				
				if (rs.getString("password").equals(pass)) {//У������
					//ʹ��equals������ʹ��==ʱ����
					if(rs.getString("type").equals("stu")) {//�ж��û�����
						//���쵱ǰ��¼�û�����
						Applicant stu = DBHelper.getApplicant(rs.getString("user"));
						JOptionPane.showMessageDialog(null, "��¼�ɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						dispose();//�رյ�¼����
						ApplicantMenu appMenu = new ApplicantMenu("��������ϵͳ", stu);//���������˲˵�
						
					}else if(rs.getString("type").equals("teach")) {//�ж��û�����
						//���쵱ǰ��¼�û�����
						TeachingManager manager = DBHelper.getManager(rs.getString("user"));
						JOptionPane.showMessageDialog(null, "��¼�ɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						dispose();//�رյ�¼����
						ManagerMenu manaMenu = new ManagerMenu("���ҹ���ϵͳ", manager);//����������Ա�˵�
					}	
				} else {
					//������ʾ,�û������������
					JOptionPane.showMessageDialog(null, "�û������������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
