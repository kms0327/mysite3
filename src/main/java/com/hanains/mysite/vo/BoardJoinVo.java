package com.hanains.mysite.vo;

public class BoardJoinVo {
	private long no;
	private String title;
	private long mem_no;
	private String mem_name;
	private long view_cnt;
	private String date;
	
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
	public long getMem_no() {
		return mem_no;
	}
	public void setMem_no(long mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public long getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(long view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BoardJoinVo [no=" + no + ", title=" + title + ", mem_no=" + mem_no + ", mem_name=" + mem_name
				+ ", view_cnt=" + view_cnt + ", date=" + date + "]";
	}
	
}
