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
import model.Applicant;
import model.AppliedRoom;
import mycompo.AppliedRoomEditor;
import mycompo.AppliedRoomRenderer;

public class AppliedRoomListPage extends JPanel{
	
	//�����������
	JTable jTable;//������
	Vector<String> name;//������
	Vector<Vector<String>> data;//������
	Box boxH;//��ʽ��
	Box boxOne, boxTwo, boxThree;//��ʽ��
	JLabel title;//����
	List<AppliedRoom> list;//��б����ڽ������ݿ�Ļ�б�
//	JButton jButton1, jButton2;//��ҳ��ť
	//���췽��
	public AppliedRoomListPage(Applicant stu) {
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
		title = new JLabel("����������б�");
		name = new Vector<String>();
		data = new Vector<Vector<String>>();
		name.add("���ұ��");
		name.add("ʹ������");
		name.add("ʹ��ʱ��");
		name.add("��������");
		
//		jButton1 = new JButton("��һҳ");
//		jButton2 = new JButton("��һҳ");
		
		list = new ArrayList<AppliedRoom>();//��ʼ����б�
		try {
			//��ȡ��б�
			list = DBHelper.AppliedRoom(stu);
			//������б��ȡvector�����
			for (int i = 0; i < list.size(); i++) {

				AppliedRoom room = list.get(i);//���ڹ���vector�������ݵĸ�������

				//���ݱ��е�ÿһ�ж�Ӧһ��
				Vector<String> vec = new Vector<String>();
				vec.add(room.getBuildingId()+"-"+room.getRoomNum());
				vec.add(room.getNeedDate());
				vec.add(room.getUseTime());
				data.add(vec);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������
		jTable = new JTable(data, name);
		//��������ӳ�����ť
		jTable.getColumnModel().getColumn(3).setCellEditor(new AppliedRoomEditor(list, stu));
		jTable.getColumnModel().getColumn(3).setCellRenderer(new AppliedRoomRenderer());
		jTable.setRowSelectionAllowed(false);
		
		boxOne.add(title);
		boxTwo.add(new JScrollPane(jTable));//��ʾ����б���
//		boxThree.add(jButton1);
//		boxThree.add(Box.createHorizontalStrut(20));
//		boxThree.add(jButton2);
		
		boxH.add(Box.createVerticalStrut(5));
		boxH.add(boxOne);
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(boxTwo);
//		boxH.add(Box.createVerticalStrut(10));
//		boxH.add(boxThree);
		add(boxH);
	}

}
