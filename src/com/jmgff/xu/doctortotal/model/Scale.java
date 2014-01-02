package com.jmgff.xu.doctortotal.model;

import java.util.ArrayList;
import java.util.Date;

import android.net.sip.SipRegistrationListener;

/*
 * 量表vo
 */
public class Scale implements java.io.Serializable {

	private Integer sid;
	private Integer stype;
	private Integer pid;
	private String sname;
	private String content;
	private Float spoint;
	private Date screatetime;
	private Date smodifytime;
	private String sremarks;
	private String description;
	private ArrayList<Question> questions;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getStype() {
		return stype;
	}

	public void setStype(Integer stype) {
		this.stype = stype;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
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

	public Float getSpoint() {
		return spoint;
	}

	public void setSpoint(Float spoint) {
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
		return "Scale [sid=" + sid + ", stype=" + stype + ", pid=" + pid
				+ ", sname=" + sname + ", content=" + content + ", spoint="
				+ spoint + ", screatetime=" + screatetime + ", smodifytime="
				+ smodifytime + ", sremarks=" + sremarks + ", description="
				+ description + ", questions=" + questions + "]";
	}

	

}
