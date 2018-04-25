package com.ctbu.javateach666.pojo.bo;

import java.util.List;

public class LKGetSubmitClassWorkDataRspBO {
	private String teaname;
	private String couname;
	private List<LKSubmotsWorkBO> submits;
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public List<LKSubmotsWorkBO> getSubmits() {
		return submits;
	}
	public void setSubmits(List<LKSubmotsWorkBO> submits) {
		this.submits = submits;
	}
}
