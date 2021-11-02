package com.example.demoLogAPI.log;

import org.springframework.data.jpa.domain.JpaSort;

public class LogPage {

	private int pageNumber = 0;
	private int pageSize = 3;
	private JpaSort.Direction sortDirection = JpaSort.Direction.ASC;
	private String sortBy = "dateTime";
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public JpaSort.Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(JpaSort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	
	
	
	
}
