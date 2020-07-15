package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;


public interface FreeBoardMapper {

	public void regist(FreeBoardVO vo);
	public ArrayList<FreeBoardVO> getList(Criteria cri);
	public FreeBoardVO getContent(int bno);
	public int update(FreeBoardVO vo);  //글 수정
	public int delete(FreeBoardVO vo); //글 삭제
	public int getTotal(); //총 게시글 수 
	
}
