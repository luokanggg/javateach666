package com.ctbu.javateach666.pojo.bo;

import java.util.Date;

public class InformationBo_zxy {
	public int id;
	
	public String title;
	
	public String publish_person;//编号
	
	public String publish_person_name;//姓名
	
	public String publish_date;
	
	public String infotype;
	
	public String statement;
	
	public String content;
	
	private int page;
	private int rows;
	
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

	public String getPublish_person_name() {
		return publish_person_name;
	}

	public void setPublish_person_name(String publish_person_name) {
		this.publish_person_name = publish_person_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublish_person() {
		return publish_person;
	}

	public void setPublish_person(String publish_person) {
		this.publish_person = publish_person;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
