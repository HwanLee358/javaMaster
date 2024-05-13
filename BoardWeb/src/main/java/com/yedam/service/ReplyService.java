package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo); // 목록.
	boolean removeReply(int replyNo); // 삭제.
	boolean addReply(ReplyVO rvo);
}
