package com.ctbu.javateach666.pojo.bo;

public class ToNoticeReqBo_zxy {
	private int page;
	private int rows;
	public String starttime;
	public int id;
	public int tonotid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the tonotid
	 */
	public int getTonotid() {
		return tonotid;
	}
	/**
	 * @param tonotid the tonotid to set
	 */
	public void setTonotid(int tonotid) {
		this.tonotid = tonotid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ToNoticeReqBo_zxy [page=" + page + ", rows=" + rows + ", starttime=" + starttime + ", id=" + id
				+ ", tonotid=" + tonotid + "]";
	}
	
}
