package com.example.demoLogAPI.log;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class LogDefaultRecord {
	
	@Id
	private LocalDateTime dateTime;
	private String level;
	private String className;
	private String content;
	
	
	public LogDefaultRecord() {
		
	}
	
	public LogDefaultRecord(LocalDateTime dateTime, String level, String className, String content) {
		super();
		this.dateTime = dateTime;
		this.level = level;
		this.className = className;
		this.content = content;
	}


	public LogDefaultRecord(String level, String className, String content) {
		super();
		this.level = level;
		this.className = className;
		this.content = content;
	}
	
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
