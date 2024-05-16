package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchVO;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService{
	SqlSession session = DataSource.getInstance().openSession(true);
	ReplyMapper mapper = session.getMapper(ReplyMapper.class);
	
	@Override
	public List<ReplyVO> replyList(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.replyListPaging(search);
	}

	@Override
	public boolean removeReply(int replyNo) {
		// TODO Auto-generated method stub
		return mapper.deleteReply(replyNo) == 1;
	}

	@Override
	public boolean addReply(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return mapper.insertReply(rvo) == 1;
	}

	@Override
	public int getReplyCnt(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.selectCount(boardNo);
	}

	@Override
	public boolean modifyReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return mapper.updateReply(reply) == 1;
	}


}
