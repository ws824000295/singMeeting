package com.boot.common.util;

import org.springframework.data.domain.PageRequest;

public class Pagination extends PageRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Pagination(Integer page, int size) {
		super(null==page?0:page, size);
	}
}
