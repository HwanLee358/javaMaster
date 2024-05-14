package com.yedam.common;

import lombok.Data;

@Data
public class SearchVO {
	// 글 검색
	private int page;
	private String searchCondition;
	private String keyword;
	
	// 댓글관련
	private int boardNo;
	private int rpage;
}
