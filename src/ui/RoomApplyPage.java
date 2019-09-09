package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dcl.DBHelper;
import model.ActList;
import model.Applicant;
import model.RoomInfo;
import mycompo.ApplyRoomEditor;
import mycompo.ApplyRoomRenderer;

public class RoomApplyPage extends JFrame{
	
	//�����������
	JTable jTable;//������
	Vector<String> name;//������
	Vector<Vector<String>> data;//������
	Box boxH;//��ʽ��
	Box boxOne, boxTwo, boxThree;//��ʽ��
	JLabel title;//����
//	JButton jButton1, jButton2;//��ҳ��ť
	JButton close;//�ر�ҳ�水ť
	JScrollPane scrollPane;
	List<RoomInfo> list;//��б����ڽ������ݿ�Ļ�б�
	
	//���췽��
	public RoomApplyPage(ActList act, Applicant stu) {
		// TODO Auto-generated constructor stub

		setLayout(new FlowLayout());
		setVisible(true);
		setBounds(200, 150, 1000, 500);
		init(act, stu);//���¼����, �͵�¼�����˶���
	}
	private void init(ActList act, Applicant stu) {
		// TODO Auto-generated method stub
		boxH = Box.createVerticalBox();//ˮƽ��ʽ����
		boxOne = Box.createHorizontalBox();//��ֱ��ʽ����
		boxTwo = Box.createHorizontalBox();
		boxThree = Box.createHorizontalBox();

		//��ʼ���ؼ�
		title = new JLabel("����������б�");
		
//		jButton1 = new JButton("��һҳ");
//		jButton2 = new JButton("��һҳ");
		close = new JButton("�ر�ҳ��");
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		name = new Vector<String>();//��ʼ���б�������
		data = new Vector<Vector<String>>();//��ʼ����������
		name.add("���ұ��");//����б���
		name.add("��        ��");
		name.add("��������");
		name.add("��������");
		name.add("8:00-10:00");
		name.add("10:00-12:00");
		name.add("12:00-14:00");
		name.add("14:00-16:00");
		name.add("16:00-18:00");
		name.add("18:00-20:00");
		name.add("20:00-22:00");
		
		list = new ArrayList<RoomInfo>();//��ʼ����б�
		try {
			//��ȡ��б�
			list = DBHelper.RoomInfo();
			//������б��ȡvector�����
			for (int i = 0; i < list.size(); i++) {

				RoomInfo room = list.get(i);//���ڹ���vector�������ݵĸ�������

				//���ݱ��е�ÿһ�ж�Ӧһ��
				Vector<String> vec = new Vector<String>();
				vec.add(room.getBuildingID()+"-"+room.getRoomNum());
				vec.add(room.getDateNow());
				vec.add(room.getRoomType());
				vec.add(room.getContain()+"");
				vec.add(room.getTime1());
				vec.add(room.getTime2());
				vec.add(room.getTime3());
				vec.add(room.getTime4());
				vec.add(room.getTime5());
				vec.add(room.getTime6());
				vec.add(room.getTime7());
				data.add(vec);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jTable = new JTable(data, name);
		for (int i = 4; i < 11; i++) {
			jTable.getColumnModel().getColumn(i).setCellEditor(new ApplyRoomEditor(list, act, stu));
			jTable.getColumnModel().getColumn(i).setCellRenderer(new ApplyRoomRenderer());
			jTable.setRowSelectionAllowed(false);
		}
		
		
		scrollPane = new JScrollPane(jTable) {
			//��д�������ù������Ĵ�С
			@Override
			public Dimension getPreferredSize() {
				// TODO Auto-generated method stub
				return new Dimension(900, 350);
			}
			
		};//jTable��Ϊһ�������ͼ���ڹ��췽�������
//		scrollPane.setSize(900, 450);���table��ʹ����Ч��δ���tableʱʹ���޷���ʾ����
		boxOne.add(title);
		boxTwo.add(scrollPane);//��ʾ����б���
		boxThree.add(close);
//		boxThree.add(jButton1);
//		boxThree.add(Box.createHorizontalStrut(20));
//		boxThree.add(jButton2);
		//�ڲ����ӵ����
		boxH.add(Box.createVerticalStrut(5));
		boxH.add(boxOne);//����
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(boxTwo);//���ݱ�
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(boxThree);//�رհ�ť
		add(boxH);
	}

}
