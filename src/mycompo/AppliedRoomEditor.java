package mycompo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import bll.SendMail;
import dcl.DBHelper;
import model.Applicant;
import model.AppliedRoom;
import model.RoomInfo;

public class AppliedRoomEditor extends AbstractCellEditor implements TableCellEditor{

	private JPanel jPanel;
	private Button button;
	List<AppliedRoom> list;
	Applicant stu;
	
	public AppliedRoomEditor(List<AppliedRoom> list, Applicant stu) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.stu = stu;
		initButton();
		initPanel();
		jPanel.add(button, BorderLayout.CENTER);
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
	}

	private void initButton() {
		// TODO Auto-generated method stub
		button = new Button("����");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ȡ��ǰ�еĽ�����Ϣ
				AppliedRoom room = list.get(button.getRow());
				
				//��ȡ�������ʱ���
				String time1 =null;//�洢�����ʱ���
				switch(room.getUseTime()) {
				
					case "8:00-10:00" :
						time1 = "time1";
						break;
					case "10:00-12:00" :
						time1 = "time2";
						break;
					case "12:00-14:00" :
						time1 = "time3";
						break;
					case "14:00-16:00" :
						time1 = "time4";
						break;
					case "16:00-18:00" :
						time1 = "time5";
						break;
					case "18:00-20:00" :
						break;
					case "20:00-22:00" :
						time1 = "time7";
						break;
					default :
						break;
				}
				
				
				//�������ݿⲢ֪ͨ¥��������
				try {
					//����roominfo���ʱ��״̬
					DBHelper.UndoRoom(room.getBuildingId(), room.getRoomNum(), room.getNeedDate(), time1);
					
					//��appliedroom�����һ�������¼
					DBHelper.UndoApplied(room.getId(), room.getActId());
					
					//���¥�������������ַ
					String email = DBHelper.getBuildEmail(room.getBuildingId());
					
					//��¥�������˷����ʼ�
					String message = "���ã�������"+stu.getStuName()+"��ѧ��Ϊ"+stu.getStuID()+"������������Ľ��ң�"+room.getBuildingId()+
							"-"+room.getRoomNum()+"��ʹ�����ڣ�"+room.getNeedDate()+"��ʹ��ʱ�䣺"+
							room.getUseTime()+"ʹ�á��ʼ����Ի��������ϵͳ��";
					SendMail sendMail = new SendMail();
					sendMail.sendMail(email, message);
					
					//��ʾ��Ϣ
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "���볷���ɹ�����֪ͨ¥�������ˣ�");
					button.setText("�ѳ���");
					button.setEnabled(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		button.setRow(row);
		button.setColumn(column);
		return jPanel;
	}

}
