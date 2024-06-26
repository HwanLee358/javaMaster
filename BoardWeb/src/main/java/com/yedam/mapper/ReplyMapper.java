package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	// 댓글목록.
	List<ReplyVO> replyList(int boardNo);
	List<ReplyVO> replyListPaging(SearchVO search);
	
	int selectCount(int boardNo);
	
	// 댓글삭제.
	int deleteReply(int replyNo);
	// 댓글추가
	int insertReply(ReplyVO rvo);
	// 댓글수정
	int updateReply(ReplyVO reply);
}
