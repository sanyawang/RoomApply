package dcl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ActList;
import model.Applicant;
import model.AppliedRoom;
import model.RoomInfo;
import model.TeachingManager;


/*
 * ���ݿ������
 * 
 * Ϊҳ�����ṩ������Դ��ʹ��ϵͳ�е�ģ�͹�����󷵻ظ�������
 * */
public class DBHelper {

	//���ݿ�������
	private final static String driverName = "com.mysql.jdbc.Driver";
	//Զ�����ݿ��ַ
	private final static String url = "jdbc:mysql://sanyaya-rdb.mysql.rds.aliyuncs.com:3306/roomapply?useSSL=false";
	//���ݿ��¼�û���
	private final static String user = "sanya_root";
	//���ݿ��¼����
	private final static String password = "Wang1234";
	static Connection conn;
	
	//�������ݿ�����
	private static void Conn() {
		try {
			//�������ݿ��������
			Class.forName(driverName).newInstance();
			//�������ݿ�����
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ѯ����˻�б�
	public static List<ActList> ActQuery() throws SQLException {
		
		String sql = "select * from actlist";//SQL��䣬��ѯ��б�
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		List<ActList> list = new ArrayList<ActList>();
		ActList act;
		while(rs.next()) {
			//����˻�������
			if(!rs.getString(6).equals("���ύ")) {
				continue;
			}
			act = new ActList();
			act.setActID(rs.getString(1));
			act.setTheme(rs.getString(2));
			act.setContent(rs.getString(3));
			act.setRoomType(rs.getString(4));
			act.setContain(rs.getInt(5));
			act.setState(rs.getString(6));
			act.setRemark(rs.getString(7));
			act.setApplicant(rs.getString(8));
			list.add(act);
		}
		conn.close();//�ر����ݿ�����
		return list;//���ػ�б�
	}
	
	//��ѯ���ͨ������������ҵĻ�б�
	public static List<ActList> ActRoom(Applicant stu) throws SQLException {
		
		String sql = "select * from actlist";//SQL��䣬��ѯ��б�
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		List<ActList> list = new ArrayList<ActList>();
		ActList act;
		
		while(rs.next()) {
			//���ͨ���Ļ�����������
			if(!rs.getString(6).equals("����׼") || !rs.getString(8).equals(stu.getStuID())) {
				continue;
			}
			
			act = new ActList();
			act.setActID(rs.getString(1));
			act.setTheme(rs.getString(2));
			act.setContent(rs.getString(3));
			act.setRoomType(rs.getString(4));
			act.setContain(rs.getInt(5));
			act.setState(rs.getString(6));
			act.setRemark(rs.getString(7));
			act.setApplicant(rs.getString(8));
			list.add(act);
		}
		conn.close();//�ر����ݿ�����
		return list;//���ػ�б�
	}
	
	//��ѯ������Ϣ��
		public static List<RoomInfo> RoomInfo() throws SQLException {
			
			String sql = "select * from roominfo";//SQL��䣬��ѯ������Ϣ�б�
			Conn();//�������ݿ�
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			List<RoomInfo> list = new ArrayList<RoomInfo>();
			RoomInfo room = new RoomInfo();
			while(rs.next()) {
				room = new RoomInfo();
				room.setBuildingID(rs.getString(1));
				room.setRoomNum(rs.getString(2));
				room.setDateNow(rs.getString(3));
				room.setRoomType(rs.getString(4));
				room.setContain(rs.getInt(5));
				room.setTime1(rs.getString(6));
				room.setTime2(rs.getString(7));
				room.setTime3(rs.getString(8));
				room.setTime4(rs.getString(9));
				room.setTime5(rs.getString(10));
				room.setTime6(rs.getString(11));
				room.setTime7(rs.getString(12));
				list.add(room);
			}
			conn.close();//�ر����ݿ�����
			return list;//���ػ�б�
		}
		
	//��������������б�,�������ѳ���������
		public static List<AppliedRoom> AppliedRoom(Applicant stu) throws SQLException {
			
			String sql = "select * from appliedroom";//SQL��䣬��ѯ�������б��б�
			Conn();//�������ݿ�
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			//����list�洢��ѯ�����б�
			List<AppliedRoom> list = new ArrayList<AppliedRoom>();
			AppliedRoom room = new AppliedRoom();
			//��ȡ��ѯ���
			while(rs.next()) {
				//�ж������Ƿ��ѳ���
				if(rs.getString(7).equals("0") || !rs.getString(8).equals(stu.getStuID())) {
					continue;
				}
				room = new AppliedRoom();
				room.setId(rs.getInt(1));
				room.setBuildingId(rs.getString(2));
				room.setRoomNum(rs.getString(3));
				room.setApplyDate(rs.getString(4));
				room.setNeedDate(rs.getString(5));
				room.setUseTime(rs.getString(6));
				room.setApplicant(rs.getString(8));
				room.setActId(rs.getString(9));
				list.add(room);
			}
			conn.close();//�ر����ݿ�����
			return list;//���ػ�б�
		}
		
	//��ȡ��¼�������˶���
	public static Applicant getApplicant(String s) throws SQLException {
		//���������˵�id��ȡ��������Ϣ
		String sql = "select * from applicant where stuid = '";
		sql = sql + s + "'";
		//�������ݿ�
		Conn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		//����һ�������˶���洢��������Ϣ
		Applicant stu = new Applicant();
		//��ȡ��ѯ���
		while(rs.next()) {
			stu.setStuID(rs.getString(1));
			stu.setStuName(rs.getString(2));
			stu.setStuClass(rs.getString(3));
			stu.setStuCollege(rs.getString(4));
			stu.setEmail(rs.getString(5));
			stu.setPhone(rs.getString(6));
			stu.setPassWord(rs.getString(7));
		}
		conn.close();//�ر����ݿ�����
		return stu;
	}
	
	//��ȡ��¼�Ľ������Ա����
	public static TeachingManager getManager(String s) throws SQLException {
		//���������˵�id��ȡ��������Ϣ
		String sql = "select * from teachingmanager where employeeid = '";
		sql = sql + s + "'";
		//�������ݿ�
		Conn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		//����һ�������˶���洢��������Ϣ
		TeachingManager teach = new TeachingManager();
		//��ȡ��ѯ���
		while(rs.next()) {
			teach.setEmployeeID(rs.getString(1));
			teach.setEmployeeName(rs.getString(2));
			teach.setWorkArea(rs.getString(3));
			teach.setEmail(rs.getString(4));
			teach.setPhone(rs.getString(5));
			teach.setPassWord(rs.getString(6));
		}
		conn.close();//�ر����ݿ�����
		return teach;
	}
	
	//��ȡ���������Ա����
	public static String getEmail() throws SQLException {
		// ƴ�Ӳ�ѯ���
		String sql = "select email from teachingmanager";

		//�������ݿ�
		Conn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		String email = null;//��ȡ���������Ա����
		//��ȡ��ѯ���
		while(rs.next()) {
			email = rs.getString(1);
		}
		conn.close();//�ر����ݿ�����
		return email;
	}
	
	//��ȡ�����������ַ
	public static String getApplyEmail(String s) throws SQLException {
		// ƴ�Ӳ�ѯ���
		String sql = "select email from applicant where stuid='";
		sql = sql + s + "'";
		
		//�������ݿ�
		Conn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		String email = null;//��ȡ����������
		
		//��ȡ��ѯ���
		while(rs.next()) {
			email = rs.getString(1);
		}
		
		conn.close();//�ر����ݿ�����
		return email;
	}
	
	//��ȡ¥������Ա�����ַ
	public static String getBuildEmail(String s) throws SQLException {
		// ƴ�Ӳ�ѯ���
		String sql = "select email from buildmanager where workbuild='";
		sql = sql + s + "'";
		
		//�������ݿ�
		Conn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		String email = null;//��ȡ����������
		
		//��ȡ��ѯ���
		while(rs.next()) {
			email = rs.getString(1);
		}
		
		conn.close();//�ر����ݿ�����
		return email;
	}
	
	//���½��ҿ�����Ϣ
	public static void UpdateRoom(String buildingid, String roomnum, String date, String time) throws SQLException {
		//ƴ�Ӹ������
		String sql = "update roominfo set "+time+"='1' where buildingid='"+buildingid+
				"' and roomnum ='"+roomnum+"' and date='"+date+"'";
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);//ִ�и���
	}
	
	//�������������һ����¼
	public static void UpdateApplied(String buildingid, String roomnum, String date, String time, String stuid, String actid) 
			throws SQLException {
		//��ȡϵͳ�ĵ�ǰ����
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//ƴ�Ӹ������
		String sql = "insert into appliedroom(buildingid, roomnum, applydate, needdate, appliedtime, applicant, actid) values('"+
				buildingid+"','"+roomnum+"','"+sdf.format(dateNow)+"','"+date+"','"+time+"','"+stuid+"','"+actid+"')";
		
		String s = "update actlist set state = '������'  where actid ='"+actid+"'";
		
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);//ִ�в���
		stat.executeUpdate(s);//���»״̬
	}
	
	public static ResultSet Query(String s) throws SQLException {
		
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(s);
		return rs;
	}
	
	//���б����һ����¼
	public static void UpdateList(String theme, String content, String roomType, String contain, String stuid) 
			throws SQLException {
		//ƴ�Ӹ������
		String sql = "insert into actlist(theme, content, roomtype, contain, state, applicant) values('"
				+theme+"','" +content+"', '"+roomType+"', "+contain+",'���ύ','"+ stuid + "')";
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
	}
	
	//���б������˽�������û״̬
	public static void UpdateState(String remark, String check, String employeeid, String actid) throws SQLException {
		//ƴ�Ӹ������
		String sql = "UPDATE actlist SET remark = '"+remark+"',state='"+check+"',reviewer='"+
				employeeid+"'WHERE ActId = '"+actid+"'";
		
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
	}
	
	//���ĸ�����Ϣ
	public static void ChangeInfo(String email, String phone, String table, String type, String id) throws SQLException {
		//ƴ�Ӹ������
		String sql = "update "+table+" set email = '"+email+"',phone='"+phone+"' where "+type+"='"+id+"'";

		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
		
	}
	
	//��������
	public static void ChangeInfo(String pwd, String table, String type, String id) throws SQLException {
		//ƴ�Ӹ������
		String sql = "update "+table+" set password='"+pwd+"' where "+type+"='"+id+"'";
		
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
		
	}
	
	//�����������Ľ�����Ϣ��
	public static void UndoRoom(String buildingid, String roomnum, String date, String time) throws SQLException {
		//ƴ�Ӹ������
		String sql = "update roominfo set "+time+"='0' where buildingid='"+buildingid+
				"' and roomnum ='"+roomnum+"' and date='"+date+"'";
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);//ִ�и���
	}
	
	//��������������������ұ�
	public static void UndoApplied(int id, String actid) 
			throws SQLException {
		//��ȡϵͳ�ĵ�ǰ����
		Date dateNow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//ƴ�Ӹ������
		String sql = "update appliedroom set state = '0' where id ="+id;

		String s = "update actlist set state = '�ѳ���'  where actid ='"+actid+"'";
		
		Conn();//�������ݿ�
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);//ִ�в���
		stat.executeUpdate(s);//���»״̬
	}
}
