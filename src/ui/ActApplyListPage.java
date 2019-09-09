package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dcl.DBHelper;
import model.ActList;
import model.TeachingManager;
import mycompo.CheckButtonEditor;
import mycompo.CheckButtonRenderer;

public class ActApplyListPage extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�����������
	JTable jTable;//������
	Vector<String> name;//������
	Vector<Vector<String>> data;//������
	Box boxH;//��ʽ��
	Box boxOne, boxTwo, boxThree;//��ʽ��
	JLabel title;//����
	List<ActList> list;//��б����ڽ������ݿ�Ļ�б�
	
	JButton jButton1;//jButton2;//��ҳ��ť
	//���췽��
	public ActApplyListPage(TeachingManager manager) {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		init(manager);//��ǰ��¼�������Ա
	}
	private void init(TeachingManager manager) {
		// TODO Auto-generated method stub
		boxH = Box.createVerticalBox();//ˮƽ��ʽ����
		boxOne = Box.createHorizontalBox();//��ֱ��ʽ����
		boxTwo = Box.createHorizontalBox();
		boxThree = Box.createHorizontalBox();

		//��ʼ���ؼ�
		title = new JLabel("����˻�б�");
		jButton1 = new JButton("ˢ���б�");
		//��Ӽ���
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
//		jButton2 = new JButton("��һҳ");
		name = new Vector<String>();
		data = new Vector<Vector<String>>();
//		name.add("����");
		name.add("�����");
		name.add("��������");
		name.add("�����");
		name.add("�״̬");
		name.add("���");

		list = new ArrayList<ActList>();//��ʼ����б�
		try {
			//��ȡ��б�
			list = DBHelper.ActQuery();
			//������б��ȡvector�����
			for (int i = 0; i < list.size(); i++) {

				ActList act = list.get(i);//���ڹ���vector�������ݵĸ�������
				//�����б��в���ʾ����˵Ļ��Ŀ
				if(!act.getState().equals("���ύ")) {
					continue;
				}
				
				Vector<String> vec = new Vector<String>();//���ݱ��е�ÿһ�ж�Ӧһ��
				
//				vec.add(rs.getString(1).toString());
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
		
		jTable = new JTable(data, name);
		jTable.getColumnModel().getColumn(4).setCellEditor(new CheckButtonEditor(list, manager));
		jTable.getColumnModel().getColumn(4).setCellRenderer(new CheckButtonRenderer());
		jTable.setRowSelectionAllowed(false);
		boxOne.add(title);
		boxTwo.add(new JScrollPane(jTable));//��ʾ����б���
		boxThree.add(jButton1);
//		boxThree.add(Box.createHorizontalStrut(20));
//		boxThree.add(jButton2);
		
		boxH.add(Box.createVerticalStrut(5));
		boxH.add(boxOne);
		boxH.add(Box.createVerticalStrut(25));
		boxH.add(boxTwo);
		boxH.add(Box.createVerticalStrut(10));
		boxH.add(boxThree);
		add(boxH);
	}

}