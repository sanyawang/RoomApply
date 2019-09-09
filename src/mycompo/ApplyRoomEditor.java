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
import model.ActList;
import model.Applicant;
import model.RoomInfo;

public class ApplyRoomEditor extends AbstractCellEditor implements TableCellEditor{

	private JPanel jPanel;
	private Button button;
	
	public ApplyRoomEditor(List<RoomInfo> list, ActList act, Applicant stu) {
		// TODO Auto-generated constructor stub
		initButton(list, act, stu);
		initPanel();
		jPanel.add(button, BorderLayout.CENTER);
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
	}

	private void initButton(List<RoomInfo> list, ActList act, Applicant stu) {
		// TODO Auto-generated method stub
		button = new Button();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ȡ��ǰ�еĽ�����Ϣ
				RoomInfo room = list.get(button.getRow());
				
				//��ȡ�������ʱ���
				String time1 =null;//�洢�����ʱ���
				String time2 = null;//�洢ʱ����ڱ��е��б���
				switch(button.getColumn()) {
				
					case 4 :
						time1 = "8:00-10:00";
						time2 = "time1";
						break;
					case 5 :
						time1 = "10:00-12:00";
						time2 = "time2";
						break;
					case 6 :
						time1 = "12:00-14:00";
						time2 = "time3";
						break;
					case 7 :
						time1 = "14:00-16:00";
						time2 = "time4";
						break;
					case 8 :
						time1 = "16:00-18:00";
						time2 = "time5";
						break;
					case 9 :
						time1 = "18:00-20:00";
						time2 = "time6";
						break;
					case 10 :
						time1 = "20:00-22:00";
						time2 = "time7";
						break;
					default :
						break;
				}
				
				//�������ݿⲢ֪ͨ¥��������
				try {
					//����roominfo���ʱ��״̬
					DBHelper.UpdateRoom(room.getBuildingID(), room.getRoomNum(), room.getDateNow(), time2);
					
					//��appliedroom�����һ�������¼
					DBHelper.UpdateApplied(room.getBuildingID(), room.getRoomNum(), room.getDateNow(), time1, stu.getStuID(), act.getActID());
					
					//���¥�������������ַ
					String email = DBHelper.getBuildEmail(room.getBuildingID());
					
					//��¥�������˷����ʼ�
					String message = "���ã�������"+stu.getStuName()+"��ѧ��Ϊ"+stu.getStuID()+"�����˽���"+room.getBuildingID()+
							"-"+room.getRoomNum()+"���������ڣ�"+room.getDateNow()+"��ʱ�䣺"+
							time1+"ʹ�ã��뼰ʱ����׼���������ʼ����Ի��������ϵͳ��";
					SendMail sendMail = new SendMail();
					sendMail.sendMail(email, message);
					
					//��ʾ��Ϣ
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), "������ҳɹ�����֪ͨ¥�������ˣ�");
					button.setText("������");
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
		if (value.equals("0")) {
			button.setText("������");
		} else {
			button.setText("������");
//			button.setEnabled(false);
		}
		button.setRow(row);
		button.setColumn(column);
		return jPanel;
	}

}
