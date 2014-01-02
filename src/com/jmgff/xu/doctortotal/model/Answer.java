package com.jmgff.xu.doctortotal.model;

import java.util.Date;

/*
 * 
 * 答案
 */
public class Answer implements java.io.Serializable {
	private int sid;
	private int stype;
	private int pid;
	private String sname;
	private String content;
	private float spoint;
	private Date screatetime;
	private Date smodifytime;
	private String sremarks;
	private String description;
	private boolean isCheck;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getSpoint() {
		return spoint;
	}

	public void setSpoint(float spoint) {
		this.spoint = spoint;
	}

	public Date getScreatetime() {
		return screatetime;
	}

	public void setScreatetime(Date screatetime) {
		this.screatetime = screatetime;
	}

	public Date getSmodifytime() {
		return smodifytime;
	}

	public void setSmodifytime(Date smodifytime) {
		this.smodifytime = smodifytime;
	}

	public String getSremarks() {
		return sremarks;
	}

	public void setSremarks(String sremarks) {
		this.sremarks = sremarks;
	}

	@Override
	public String toString() {
		return "Answer [sid=" + sid + ", stype=" + stype + ", pid=" + pid
				+ ", sname=" + sname + ", content=" + content + ", spoint="
				+ spoint + ", screatetime=" + screatetime + ", smodifytime="
				+ smodifytime + ", sremarks=" + sremarks + ", description="
				+ description + ", isCheck=" + isCheck + "]";
	}

}
