package model;

public class ActList {
	//�������
	private String actID;
	private String theme;
	private String content;
	private String roomType;
	private int contain;
	private String state;
	private String remark;
	private String applicant;
	//get��set����
	public String getActID() {
		return actID;
	}
	public void setActID(String actID) {
		this.actID = actID;
	}

	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getContain() {
		return contain;
	}
	public void setContain(int contain) {
		this.contain = contain;
	}

	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	//���췽��
	public ActList() {
		// TODO Auto-generated constructor stub
	}

}
