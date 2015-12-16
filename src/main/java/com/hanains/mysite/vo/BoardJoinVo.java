package com.hanains.mysite.vo;

public class BoardJoinVo {
	private long no;
	private String title;
	private long memNo;
	private String memName;
	private long depth;
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	private long viewCnt;
	private String regdate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public long getMemNo() {
		return memNo;
	}
	public void setMemNo(long memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public long getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(long viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardJoinVo [no=" + no + ", title=" + title + ", memNo=" + memNo + ", memName=" + memName + ", depth="
				+ depth + ", viewCnt=" + viewCnt + ", regdate=" + regdate + "]";
	}
	
	
}
