package com.boot.common.util;

import java.util.List;

public class Page {

	private Integer number;
	
	private Integer totalPages;
	
	private Integer totalElements;
	
	private Integer size;
	
	private Integer start;
	
	private List<Object> content;
	
	public Page(Integer page, Integer size){
		this.number = (null==page?0:page);
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTotalPages() {
		if (totalElements % size == 0) {
			totalPages = totalElements / size;
		} else {
			totalPages = totalElements / size + 1;
		}
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStart() {
		start = ((number+1) - 1) * size;
		if(start < 0 ){
			start = 0;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public List<Object> getContent() {
		return content;
	}

	public void setContent(List<Object> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Page [number=" + number + ", totalPages=" + totalPages + ", totalElements=" + totalElements + ", size="
				+ size + ", start=" + start + ", content=" + content + "]";
	}
	
}
