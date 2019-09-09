package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Applicant;

public class ApplicantMenu extends JFrame {

	JMenuBar menuBar;//�˵���
	JMenu menu;//�˵�
	JMenu menuInfo;//�޸���Ϣ�˵�
	JMenu exit;//�˳��˵�
	JMenuItem item1;//�˵���
//	JMenuItem item2;
	JMenuItem item3;
	JMenuItem item4;
	JMenuItem item5;
	JMenuItem item6;
	JMenuItem item7;
	JPanel jPanel;//�������
	
	Applicant stu;//��¼�û�����
	
	public ApplicantMenu(String s, Applicant stu) {
		
		this.stu = stu;
		init(s);
		setBounds(400, 100, 600, 600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init(String s) {
		
		setTitle(s);//���ô��ڱ���
		setLayout(new BorderLayout());
		menuBar = new JMenuBar();//�����˵���
		menu = new JMenu("�˵�");//�����˵�
		menuInfo = new JMenu("�޸���Ϣ");
		exit = new JMenu("�˳�ϵͳ");
		item1 = new JMenuItem("�����");//��Ӳ˵���
//		item2 = new JMenuItem("��������");
		item3 = new JMenuItem("������");
		item4 = new JMenuItem("���������");
		item5 = new JMenuItem("������Ϣ");
		item6 = new JMenuItem("��¼����");
		item7 = new JMenuItem("�˳�ϵͳ");
		
		jPanel = new JPanel();//��ʼ�����������
		
		//�����������ҳ��
		item1.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				ActApplyPage actApply = new ActApplyPage(stu);
				jPanel.add(actApply, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
//		item2.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				jPanel.removeAll();//�����������е����
//				RoomApplyPage roomApply = new RoomApplyPage();
//				jPanel.add(roomApply, BorderLayout.CENTER);
//				validate();//ˢ�´���
//			}
//		});
		//��������¼���ʾ���ύ��б�
		item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				ActSubmitListPage submitList = new ActSubmitListPage(stu);
				jPanel.add(submitList, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		//��������¼���ʾ������Ľ����б�
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				AppliedRoomListPage appliedRoom = new AppliedRoomListPage(stu);
				jPanel.add(appliedRoom, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		//�����ʾ������Ϣ�޸Ľ���
		item5.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				InfoChangeApply infoChange = new InfoChangeApply(stu);
				jPanel.add(infoChange, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		//��������¼���ʾ�޸ĵ�¼�������
		item6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				PasswordChangeApply pwdChange = new PasswordChangeApply(stu);
				jPanel.add(pwdChange, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		//��������˳�ϵͳ�¼�
		item7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳�");
				if(n == JOptionPane.YES_OPTION) {
					dispose();//�˳�ϵͳ
				}else {
					validate();//ˢ�½���
				}
			}
		});
		menu.add(item1);//���˵�����ӵ��˵�
//		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		
		menuInfo.add(item5);
		menuInfo.add(item6);
		
		exit.add(item7);
		//�˵���ӵ��˵���
		menuBar.add(menu);
		menuBar.add(menuInfo);
		menuBar.add(exit);
		
		setJMenuBar(menuBar);//���˵������õ�����
		add(jPanel, BorderLayout.CENTER);//���������ӵ�center��
	}
}
