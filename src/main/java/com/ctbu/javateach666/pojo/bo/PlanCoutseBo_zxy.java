package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class PlanCoutseBo_zxy {
	private int page;
	private int rows;
	public String planfile;
	public int id;
	
	public int teaid;
	
	public int semester;
	
	public int couyear;
	
	public String couname;
	
	public String plantitle;
	
    public String plangoal;
	
	public String plancontent;
	
	public String planclass;
	
	public Date plantime1;
	
	public Date plantime2;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the teaid
	 */
	public int getTeaid() {
		return teaid;
	}

	/**
	 * @param teaid the teaid to set
	 */
	public void setTeaid(int teaid) {
		this.teaid = teaid;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * @return the couyear
	 */
	public int getCouyear() {
		return couyear;
	}

	/**
	 * @param couyear the couyear to set
	 */
	public void setCouyear(int couyear) {
		this.couyear = couyear;
	}

	/**
	 * @return the couname
	 */
	public String getCouname() {
		return couname;
	}

	/**
	 * @param couname the couname to set
	 */
	public void setCouname(String couname) {
		this.couname = couname;
	}

	/**
	 * @return the plantitle
	 */
	public String getPlantitle() {
		return plantitle;
	}

	/**
	 * @param plantitle the plantitle to set
	 */
	public void setPlantitle(String plantitle) {
		this.plantitle = plantitle;
	}

	/**
	 * @return the plangoal
	 */
	public String getPlangoal() {
		return plangoal;
	}

	/**
	 * @param plangoal the plangoal to set
	 */
	public void setPlangoal(String plangoal) {
		this.plangoal = plangoal;
	}

	/**
	 * @return the plancontent
	 */
	public String getPlancontent() {
		return plancontent;
	}

	/**
	 * @param plancontent the plancontent to set
	 */
	public void setPlancontent(String plancontent) {
		this.plancontent = plancontent;
	}

	/**
	 * @return the planclass
	 */
	public String getPlanclass() {
		return planclass;
	}

	/**
	 * @param planclass the planclass to set
	 */
	public void setPlanclass(String planclass) {
		this.planclass = planclass;
	}

	/**
	 * @return the plantime1
	 */
	public Date getPlantime1() {
		return plantime1;
	}

	/**
	 * @param plantime1 the plantime1 to set
	 */
	public void setPlantime1(Date plantime1) {
		this.plantime1 = plantime1;
	}

	/**
	 * @return the plantime2
	 */
	public Date getPlantime2() {
		return plantime2;
	}

	/**
	 * @param plantime2 the plantime2 to set
	 */
	public void setPlantime2(Date plantime2) {
		this.plantime2 = plantime2;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlanCoutseBo_zxy [teaid=" + teaid + ", semester=" + semester + ", couyear=" + couyear + ", couname="
				+ couname + ", plantitle=" + plantitle + ", plangoal=" + plangoal + ", plancontent=" + plancontent
				+ ", planclass=" + planclass + ", plantime1=" + plantime1 + ", plantime2=" + plantime2 + "]";
	}

	/**
	 * @return the planfile
	 */
	public String getPlanfile() {
		return planfile;
	}

	/**
	 * @param planfile the planfile to set
	 */
	public void setPlanfile(String planfile) {
		this.planfile = planfile;
	}
	
	
}
