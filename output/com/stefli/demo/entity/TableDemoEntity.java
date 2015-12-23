package com.stefli.demo.entity;

import java.sql.Timestamp;

public class TableDemoEntity {

	private Long id;
	private String name;
	private String entryId;
	private Timestamp entryDt;

	public TableDemoEntity() {

	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEntryId() {
		return this.entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getEntryDt() {
		return this.entryDt;
	}

	public void setEntryDt(String entryDt) {
		this.entryDt = entryDt;
	}
	
}
