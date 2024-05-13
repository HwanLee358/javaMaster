package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int page;
	private int startPage, endPage;
	private boolean prev, next;
	
	public PageDTO(int page, int totalCnt) {
		this.page = page;
		int readlEnd = (int) Math.ceil(totalCnt / 5.0);
		
		this.endPage = (int) Math.ceil(page / 5.0) * 5;
		this.startPage = this.endPage - 4;
		this.endPage = this.endPage > readlEnd ? readlEnd : this.endPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < readlEnd ? true : false;
	}
}
