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

import model.TeachingManager;

public class ManagerMenu extends JFrame{

	JMenuBar menuBar;//�˵���
	JMenu menu;//�˵�
	JMenu menuInfo;//�޸���Ϣ�˵�
	JMenu exit;//�˳��˵�
	JMenuItem item1;//�˵���
	JMenuItem item2;
	JMenuItem item3;
	JMenuItem item4;
	
	TeachingManager manager;//��ǰ��¼�Ľ������Ա
	
	JPanel jPanel;//�������
	
	public ManagerMenu(String s, TeachingManager manager) {
		
		this.manager = manager;
		init(s);
		setBounds(400, 100, 600, 600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init(String s) {
		
		setTitle(s);//���ô��ڱ���
		setLayout(new BorderLayout());
		menuBar = new JMenuBar();//�����˵���
		menu = new JMenu("�˵���Ŀ");//�����˵�
		menuInfo = new JMenu("�޸���Ϣ");
		exit = new JMenu("�˳�ϵͳ");
		item1 = new JMenuItem("������б�");//��Ӳ˵���
		item2 = new JMenuItem("������Ϣ");
		item3 = new JMenuItem("��¼����");
		item4 = new JMenuItem("�˳�ϵͳ");

		jPanel = new JPanel();//��ʼ�����������
		
		//����˻�б�����¼�
		item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				ActApplyListPage applyList = new ActApplyListPage(manager);
				jPanel.add(applyList, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		//�޸ĸ�����Ϣ����
		item2.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				InfoChangePage infoChange = new InfoChangePage(manager);
				jPanel.add(infoChange, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		
		//�޸ĵ�¼����
		item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jPanel.removeAll();//�����������е����
				PasswordChangePage pwdChange = new PasswordChangePage(manager);
				jPanel.add(pwdChange, BorderLayout.CENTER);
				validate();//ˢ�´���
			}
		});
		
		//�˳�ϵͳ����
		item4.addActionListener(new ActionListener() {
			
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
		
		//�˵�����ӵ��˵�
		menu.add(item1);
		menuInfo.add(item2);
		menuInfo.add(item3);
		exit.add(item4);
		//�˵���ӵ��˵���
		menuBar.add(menu);
		menuBar.add(menuInfo);
		menuBar.add(exit);
		//���˵������õ�����
		setJMenuBar(menuBar);
		add(jPanel, BorderLayout.CENTER);//���������ӵ�center��
	}
}
