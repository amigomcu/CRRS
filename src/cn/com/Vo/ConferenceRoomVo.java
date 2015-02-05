package cn.com.Vo;

public class ConferenceRoomVo {
	private int conferenceId;
	private int conferenceCapital;
	private String conferenceLocation;
	private int[][] freeTimeSegement = new int[14][12]; // 两周 ,每天朝九晚五记录状态

	public ConferenceRoomVo() {
		super();
	}

	public ConferenceRoomVo(int conferenceId, int conferenceCapital,
			String conferenceLocation) {
		super();
		this.conferenceId = conferenceId;
		this.conferenceCapital = conferenceCapital;
		this.conferenceLocation = conferenceLocation;
		freeTimeSegement = new int[14][12];
	}

	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	public int getConferenceCapital() {
		return conferenceCapital;
	}

	public void setConferenceCapital(int conferenceCapital) {
		this.conferenceCapital = conferenceCapital;
	}

	public String getConferenceLocation() {
		return conferenceLocation;
	}

	public void setConferenceLocation(String conferenceLocation) {
		this.conferenceLocation = conferenceLocation;
	}

	public int[][] getFreeTimeSegement() {
		return freeTimeSegement;
	}

	public void setFreeTimeSegement(int[][] freeTimeSegement) {
		this.freeTimeSegement = freeTimeSegement;
	}

	public int getFreeTimeSegement(int row, int col) {
		if (row < 0 || row >= 14) {
			return -1;
		}
		return this.freeTimeSegement[row][col];
	}

	public void setFreeTimSegement(int row, int col, int val) {
		if (row < 0 || row >= 14 || (val != 0 && val != 1)) {
			return;
		}

		// System.out.println(row + " " + col + " " + val);

		this.freeTimeSegement[row][col] = val;
	}

	public String toString() {
		return this.getConferenceId() + "-" + this.getConferenceCapital() + "-"
				+ this.getConferenceLocation();
	}

	/*
	 * 
	 * 读取 map PS:存储时完全严格按照朝九晚五的模式且半小时为一个单位, 所以读取 map 直接处理即可
	 * 
	 * 对应处理每一个会议室未来两周的空闲时间记录表 map
	 * 
	 * map[i][j] 表示其相应的会议室距离今天第 i 天的第 j 个时间段状态 map[i][j] = 1 表示被占用 map[i][j] = 0
	 * 表示该会议室该时间段空闲
	 * 
	 * 下标均从 0 开始。。。
	 * 
	 * val = 1 :添加纪录 val = 0: 删除记录
	 */
	@SuppressWarnings("unused")
	public void setFreeTimeMap(ConferenceRecordVo recordVo, int val) {

		int day = recordVo.getDifferDay(); // 记录超出当前时间几天
		// System.out.println(day);

		int startCol = 0, endCol = 0;

		int startHour = recordVo.getStartHour();
		int endHour = recordVo.getEndHour();
		int startMinute = recordVo.getStartMinute();
		int endMinute = recordVo.getEndMinute();

		int x1, y1, x2, y2;
		int start, end;
		boolean isAm;

		if (startHour <= 12) {
			start = (startHour - 9) * 60 + startMinute;
			end = (endHour - 9) * 60 + endMinute;
			isAm = true;

		} else {
			start = (startHour - 14) * 60 + startMinute;
			end = (endHour - 14) * 60 + endMinute;
			isAm = false;
		}

		x1 = start / 30;
		y1 = start % 30;

		x2 = end / 30;
		y2 = end % 30;

		startCol = x1;
		if (y2 == 0)
			endCol = x2 - 1;
		else
			endCol = x2;

		if (isAm == false) {
			startCol += 6;
			endCol += 6;
		}

		// System.out.println(day + " " + startCol + " " + endCol);

		for (int j = startCol; j <= endCol; j++) {
			this.setFreeTimSegement(day, j, val);
			// this.setFreeTimSegement(1, 1, 1);
		}
	}

}
