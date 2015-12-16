package com.hanains.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private long memberNo;
	private long viewCount;
	private long groupNo;
	private long orderNo;
	private long depth;
	private String regdate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", memberNo=" + memberNo
				+ ", viewCount=" + viewCount + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth
				+ ", regdate=" + regdate + "]";
	}
	
	
}
