package cn.com.Vo;

public class UserVo {
	private int userId;
	private String userName;
	private String userPassWord;
	private String userTelphoneNo;
	private String userDepartment;
	private String userPosition;

	public UserVo() {
		super();
	}

	public UserVo(int userId, String userName, String userPassWord,
			String userTelphoneNo, String userDepartment, String userPosition) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassWord = userPassWord;
		this.userTelphoneNo = userTelphoneNo;
		this.userDepartment = userDepartment;
		this.userPosition = userPosition;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public String getUserTelphoneNo() {
		return userTelphoneNo;
	}

	public void setUserTelphoneNo(String userTelphoneNo) {
		this.userTelphoneNo = userTelphoneNo;
	}

	public String getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String toString() {
		return this.getUserId() + "-" + this.getUserName() + "\n"
				+ this.getUserDepartment() + "-" + this.getUserPosition()
				+ this.getUserTelphoneNo() + "-" + this.getUserPassWord();
	}
	

}
