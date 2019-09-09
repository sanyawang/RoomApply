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
import model.Applicant;
//�޸���Ϣ��, �޸������˵ĵ�¼����
	public class PasswordChangeApply extends JPanel implements ActionListener{

			//��������
			Box boxH;//��ʽ��
			Box boxOne, boxThree, boxFour, boxFive, boxSix;//��ʽ��
			JTextField pwdOld, pwdNew, pwdCheck;//�����ı���
			JLabel title, oldL, newL, checkL;//���ݱ�ǩ
			JButton jButton;//�ύ��ť
			Applicant stu;//�����˶���
			//���췽��
			public PasswordChangeApply(Applicant stu) {
				
				this.stu = stu;
				setLayout(new FlowLayout());//���õ�ǰ���Ϊ��ʽ����
				init(stu);//ҳ���ʼ������
				setBounds(400, 100, 600, 600);
				setVisible(true);
			}
			//�Զ���ҳ���ʼ������
			void init(Applicant stu) {
				
				boxH = Box.createVerticalBox();//ˮƽ��ʽ����
				boxOne = Box.createHorizontalBox();//��ֱ��ʽ����
				boxThree = Box.createHorizontalBox();
				boxFour = Box.createHorizontalBox();
				boxFive = Box.createHorizontalBox();
				boxSix = Box.createHorizontalBox();
				
				//��ʼ���ı���ͱ�ǩ�ؼ�
				pwdOld = new JTextField(15);
				pwdNew = new JTextField(15);
				pwdCheck = new JTextField(15);
				
				jButton = new JButton("�޸�");
				//Ϊ��ť��Ӽ���
				jButton.addActionListener(this);
				
				title = new JLabel("�޸�����");
				oldL = new JLabel("ԭ  ��  ��");
				newL = new JLabel("��  ��  ��");
				checkL = new JLabel("ȷ������");
				
				boxOne.add(title);//��ˮƽ��ʽ��������ı���ͱ�ǩ
				boxFour.add(oldL);
				boxFour.add(Box.createHorizontalStrut(5));
				boxFour.add(pwdOld);
				boxFive.add(newL);
				boxFive.add(Box.createHorizontalStrut(5));
				boxFive.add(pwdNew);
				boxThree.add(checkL);
				boxThree.add(Box.createHorizontalStrut(5));
				boxThree.add(pwdCheck);
				boxSix.add(jButton);
				
				boxH.add(Box.createVerticalStrut(100));//�ڲ����ӵ�����
				boxH.add(boxOne);
				boxH.add(Box.createVerticalStrut(30));
				boxH.add(boxFour);
				boxH.add(Box.createVerticalStrut(15));
				boxH.add(boxFive);
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
				String pwdChange = pwdNew.getText();

				//������Ϣ
				try {
						//�ж�ԭ�����Ƿ���ȷ
						if(pwdOld.getText().equals(stu.getPassWord())) {
							//�ж��������ȷ�������Ƿ�һ��
							if (pwdNew.getText().equals(pwdCheck.getText())) {
								//���������ǽ������Ա
								DBHelper.ChangeInfo(pwdChange, "applicant", "stuid", stu.getStuID());
								//�޸ĳɹ���ʾ
								JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
							} else {
								//��ʾ��ǰ�����벻һ��
								JOptionPane.showMessageDialog(null, "��������ȷ�����벻һ�£�", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							//��ʾ��ԭ�������
							JOptionPane.showMessageDialog(null, "ԭ�������", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//���ҳ����Ϣ
				pwdOld.setText("");
				pwdNew.setText("");
				pwdCheck.setText("");
			}
}
