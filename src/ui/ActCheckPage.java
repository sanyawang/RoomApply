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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bll.SendMail;
import dcl.DBHelper;
import model.ActList;
import model.TeachingManager;

public class ActCheckPage extends JFrame implements ActionListener{
	//�������
		Box boxH;//��ʽ��
		Box boxTwo, boxThree, boxFour, boxFive, boxSix;//��ʽ��
		JTextField themeT, contentT, roomTypeT, containT;//���ݱ�ǩ
		JLabel themeL, contentL, roomTypeL, containL, checkL, remarkL;//���ݱ����ǩ
		JTextField remarkT;
		JButton jButton;//�ύ��ť
		ButtonGroup group;//��ѡ��ť��
		JRadioButton radioAgr, radioDis;//��ѡ��ť
		ActList act;//һ�����¼
		TeachingManager manager;//���񴦹���Ա����
		//���췽��
		public ActCheckPage(ActList act, TeachingManager manager) {//���������к�����������
			
			this.act = act;//�������Ϊȫ�ֱ���
			this.manager = manager;//���񴦹���Ա
			setBounds(500, 200, 300, 300);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(new FlowLayout());//���õ�ǰ���Ϊ��ʽ����
			
			init();//ҳ���ʼ����
		}
		//�Զ���ҳ���ʼ������
		private void init() {
			//���úе��ڲ�����
			boxH = Box.createVerticalBox();//ˮƽ��ʽ����
			boxTwo = Box.createVerticalBox();//��ֱ��ʽ����
			boxThree = Box.createVerticalBox();
			boxFour = Box.createHorizontalBox();
			boxFive = Box.createHorizontalBox();
			boxSix = Box.createHorizontalBox();
			//��ʼ���ı���ͱ�ǩ�ؼ�
			themeT = new JTextField(act.getTheme(), 15);
			contentT = new JTextField(act.getContent(), 15);
			roomTypeT = new JTextField(act.getRoomType(), 15);
			containT = new JTextField(act.getContain()+"", 15);
			//������ʾ�ı��򲻿ɱ༭
			themeT.setEditable(false);
			contentT.setEditable(false);
			roomTypeT.setEditable(false);
			containT.setEditable(false);
			
			remarkT = new JTextField();
			jButton = new JButton("�ύ");
			//����ύ�����¼�
			jButton.addActionListener(this);
			//Ϊ��ǩ�����ı�
			themeL = new JLabel("�����");
			contentL = new JLabel("�����");
			roomTypeL = new JLabel("��������");
			containL = new JLabel("�����");
			checkL = new JLabel("������");
			remarkL = new JLabel("��         ע");
			//�������ĵ�ѡ��ť��
			group = new ButtonGroup();
			radioAgr = new JRadioButton("��׼");
			radioAgr.setSelected(true);//����Ĭ��ѡ�а�ť
			radioDis = new JRadioButton("�ܾ�");
			group.add(radioAgr);//��ѡ��ť����
			group.add(radioDis);
			//��ֱ��ʽ������ӱ�ǩ
			boxTwo.add(Box.createVerticalStrut(5));
			boxTwo.add(themeL);
			boxTwo.add(Box.createVerticalStrut(10));
			boxTwo.add(contentL);
			boxTwo.add(Box.createVerticalStrut(10));
			boxTwo.add(roomTypeL);
			boxTwo.add(Box.createVerticalStrut(10));
			boxTwo.add(containL);
			boxTwo.add(Box.createVerticalStrut(10));
			boxTwo.add(checkL);
			boxTwo.add(Box.createVerticalStrut(10));
			boxTwo.add(remarkL);
			//��ֱ������ı����ݱ�ǩ
			boxThree.add(themeT);
			//boxTwo.add(Box.createVerticalStrut(10));
			boxThree.add(contentT);
			//boxTwo.add(Box.createVerticalStrut(10));
			boxThree.add(roomTypeT);
			//boxTwo.add(Box.createVerticalStrut(10));
			boxThree.add(containT);
			boxTwo.add(Box.createVerticalStrut(5));
				//	��ѡ��ť��ӵ�һ��ˮƽ��
			boxSix.add(radioAgr);
			boxSix.add(Box.createHorizontalStrut(20));
			boxSix.add(radioDis);
			
			boxThree.add(boxSix);
			boxTwo.add(Box.createVerticalStrut(5));
			boxThree.add(remarkT);
			
			//����ǩ�����ݱ�ǩ��ӵ�һ��ˮƽ��
			boxFour.add(boxTwo);
			boxFour.add(Box.createHorizontalStrut(5));//ˮƽ֧��
			boxFour.add(boxThree);
			
			//��ť��ӵ�ˮƽ��
			boxFive.add(jButton);
			//�ڲ����ӵ�����
			boxH.add(Box.createVerticalStrut(20));
			boxH.add(boxFour);
			boxH.add(Box.createVerticalStrut(13));
			boxH.add(boxFive);
			//������ӵ����
			add(boxH);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//��ȡ��˽���ͱ�ע
			String check = radioAgr.isSelected() ? "����׼" : "�Ѿܾ�";
			String remark = remarkT.getText();
			
			try {
				//�������ݿ��еĻ״̬�ͱ�ע��Ϣ
				DBHelper.UpdateState(remark, check, manager.getEmployeeID(), act.getActID());
				
				//��ȡ�����˵������ַ
				String email = DBHelper.getApplyEmail(act.getApplicant());
				
				//�������˷����ʼ�
				String message = "���ã����ύ�Ļ��������ˣ��뼰ʱ��¼���������ϵͳ�鿴������ʼ����Ի��������ϵͳ��";
				SendMail sendMail = new SendMail();
				sendMail.sendMail(email, message);
				
				//���³ɹ�������Ϣ��ʾ��
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), "�����ɣ���֪ͨ�����ˣ�", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				dispose();//�ر���˴���
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
}
