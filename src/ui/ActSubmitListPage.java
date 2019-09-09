package ui;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dcl.DBHelper;
import model.ActList;
import model.Applicant;
import mycompo.SubmitButtonEditor;
import mycompo.SubmitButtonRenderer;

public class ActSubmitListPage extends JPanel{

		//�����������
		JTable jTable;//������
		Vector<String> name;//������
		Vector<Vector<String>> data;//������
		Box boxH;//��ʽ��
		Box boxOne, boxTwo, boxThree;//��ʽ��
		JLabel title;//����
		List<ActList> list;//��б����ڽ������ݿ�Ļ�б�
//		JButton jButton1, jButton2;//��ҳ��ť
		//���췽��
		public ActSubmitListPage(Applicant stu) {
			// TODO Auto-generated constructor stub
			setLayout(new FlowLayout());
			init(stu);
		}
		private void init(Applicant stu) {
			// TODO Auto-generated method stub
			boxH = Box.createVerticalBox();//ˮƽ��ʽ����
			boxOne = Box.createHorizontalBox();//��ֱ��ʽ����
			boxTwo = Box.createHorizontalBox();
			boxThree = Box.createHorizontalBox();

			//��ʼ���ؼ�
//			jButton1 = new JButton("��һҳ");
//			jButton2 = new JButton("��һҳ");
			title = new JLabel("���ύ��б�");
			name = new Vector<String>();//��ʼ���б�������
			data = new Vector<Vector<String>>();//��ʼ����������
//			name.add("����");//����б���
			name.add("�����");
			name.add("��������");
			name.add("�����");
			name.add("�״̬");
			name.add("�������");
			
			list = new ArrayList<ActList>();//��ʼ����б�
			try {
				//��ȡ��б�
				list = DBHelper.ActRoom(stu);
				//������б��ȡvector�����
				for (int i = 0; i < list.size(); i++) {

					ActList act = list.get(i);//���ڹ���vector�������ݵĸ�������
					//�����б��в���ʾ����˵Ļ��Ŀ
//					if(!act.getState().equals("�ύ")) {
//						continue;
//					}
					
					Vector<String> vec = new Vector<String>();//���ݱ��е�ÿһ�ж�Ӧһ��
					
//					vec.add(rs.getString(1).toString());
					vec.add(act.getTheme());
					vec.add(act.getRoomType());
					vec.add(act.getContain()+"");
					vec.add(act.getState());
					data.add(vec);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jTable = new JTable(data, name);//��ʼ����
			//Ϊ��Ԫ����Ӱ�ť
			jTable.getColumnModel().getColumn(4).setCellEditor(new SubmitButtonEditor(list, stu));
			jTable.getColumnModel().getColumn(4).setCellRenderer(new SubmitButtonRenderer());
			jTable.setRowSelectionAllowed(false);
			
			boxOne.add(title);//��ʾ�б�����
			boxTwo.add(new JScrollPane(jTable));//��ʾ����б���
//			boxThree.add(jButton1);
//			boxThree.add(Box.createHorizontalStrut(20));
//			boxThree.add(jButton2);
			//�ڲ����ӵ�����
			boxH.add(Box.createVerticalStrut(10));
			boxH.add(boxOne);//����
			boxH.add(Box.createVerticalStrut(25));
			boxH.add(boxTwo);//���ݱ�
//			boxH.add(Box.createVerticalStrut(10));
//			boxH.add(boxThree);//�رհ�ť
			add(boxH);
		}

}
