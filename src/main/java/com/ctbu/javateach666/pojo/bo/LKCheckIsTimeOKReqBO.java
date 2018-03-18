package com.ctbu.javateach666.pojo.bo;

public class LKCheckIsTimeOKReqBO {
	private int stuid;
	private int teacourseid;
	private int couyear;
	private int semester;
	/*private int coutime;
	private int couhour;
	private int coufhour;*/
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
	public int getTeacourseid() {
		return teacourseid;
	}
	public void setTeacourseid(int teacourseid) {
		this.teacourseid = teacourseid;
	}
	public int getCouyear() {
		return couyear;
	}
	public void setCouyear(int couyear) {
		this.couyear = couyear;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
}
