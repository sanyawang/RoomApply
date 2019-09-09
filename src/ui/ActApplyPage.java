package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bll.SendMail;
import dcl.DBHelper;
import model.Applicant;

//���������࣬���ػ����ҳ��
public class ActApplyPage extends JPanel implements ActionListener{

	//�������
	Box boxH;//��ʽ��
	Box boxOne, boxTwo, boxThree, boxFour, boxFive, boxSix;//��ʽ��
	JTextField themeT, containT;//�����ı���
	JTextArea contentT;
	JLabel titleL, themeL, contentL, roomTypeL, containL;//���ݱ�ǩ
	JButton jButton;//�ύ��ť
	ButtonGroup group;//��ѡ��ť��
	JRadioButton radioO, radioM;//��ѡ��ť
	
	Applicant stu;//��¼�û�����
	//���췽��
	public ActApplyPage(Applicant stu) {
		
		this.stu = stu;
		setLayout(new FlowLayout());//���õ�ǰ���Ϊ��ʽ����
		setBounds(400, 100, 600, 600);//��������С
		init();//ҳ���ʼ����
	}
	//�Զ���ҳ���ʼ������
	private void init() {
		//����ÿ�������ݵ����з�ʽ
		boxH = Box.createVerticalBox();//��ֱ����
		boxOne = Box.createHorizontalBox();//ˮƽ����
		boxTwo = Box.createHorizontalBox();
		boxThree = Box.createHorizontalBox();
		boxFour = Box.createHorizontalBox();
		boxFive = Box.createHorizontalBox();
		boxSix = Box.createHorizontalBox();
		//��ʼ���ı���ͱ�ǩ�ؼ�
		themeT = new JTextField(20);
		contentT = new JTextArea(3, 20);
		containT = new JTextField(20);
		jButton = new JButton("�ύ");
		jButton.addActionListener(this);
		titleL = new JLabel("�����");//����ҳ���С����
		themeL = new JLabel("�����");
		contentL = new JLabel("�����");
		roomTypeL = new JLabel("��������");
		containL = new JLabel("�����");
		group = new ButtonGroup();//��ѡ��ť�飬ѡ���������
		radioO = new JRadioButton("��ͨ����");
		radioM = new JRadioButton("��ý�����");
		group.add(radioO);//��ѡ��ť����
		group.add(radioM);
		//���С����
		boxOne.add(titleL);
		//��ӻ�����ǩ���ı�
		boxTwo.add(themeL);
		boxTwo.add(Box.createHorizontalStrut(5));//ˮƽ֧��
		boxTwo.add(themeT);
		//��ӻ���ݱ�ǩ���ı�
		boxThree.add(contentL);
		boxThree.add(Box.createHorizontalStrut(5));
		boxThree.add(contentT);
		//��ӻ�������ͱ�ǩ���ı�
		boxFour.add(roomTypeL);
		boxFour.add(Box.createHorizontalStrut(20));
		boxFour.add(radioO);
		boxFour.add(Box.createHorizontalStrut(40));
		boxFour.add(radioM);
		//��ӻ������ǩ���ı�
		boxFive.add(containL);
		boxFive.add(Box.createHorizontalStrut(5));
		boxFive.add(containT);
		//��ť��ӵ�һ��ˮƽ��
		boxSix.add(jButton);
		//�ڲ����ӵ�����
		boxH.add(Box.createVerticalStrut(15));//��ֱ֧��
		boxH.add(boxOne);
		boxH.add(Box.createVerticalStrut(35));
		boxH.add(boxTwo);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(boxThree);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(boxFour);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(boxFive);
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(boxSix);
		//������ӵ����
		add(boxH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String theme = themeT.getText();
		String content = contentT.getText();
		String roomType = radioO.isSelected() ? "��ͨ����" : "��ý�����";
		String contain = containT.getText();
		
		
		//���һ�����¼
		try {
			//�������ݿ�
			DBHelper.UpdateList(theme, content, roomType, contain, stu.getStuID());
			
			//��ȡ����Ա����
			String email = DBHelper.getEmail();
			
			//��������Ա�����ʼ�
			String message = "���ã��������˷������µĻ���룬�뼰ʱ��¼���������ϵͳ��ˡ��ʼ����Ի��������ϵͳ��";
			SendMail sendMail = new SendMail();
			sendMail.sendMail(email, message);
			
			//������ʾ��
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), "�ύ�ɹ�����֪ͨ�������Ա����", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
			
			//����ı�������
			themeT.setText("");
			contentT.setText("");
			group.clearSelection();
			containT.setText("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("error");
		}
		
	}
}
