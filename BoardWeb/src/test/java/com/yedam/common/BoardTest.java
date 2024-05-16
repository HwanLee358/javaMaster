package com.yedam.common;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class BoardTest {
	public static void main(String[] args) {
		SqlSession session = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(197);
		vo.setReply("si");
		
		mapper.updateReply(vo);
	}
}
