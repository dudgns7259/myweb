package com.team404.freeboard.service;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardService {
	
	public void regist(FreeBoardVO vo); //글 등록
	public ArrayList<FreeBoardVO> getList(Criteria cri); //글 목록
	public FreeBoardVO getContent(int bno); //글 상세보기
	public int update(FreeBoardVO vo);  //글 수정
	public int delete(FreeBoardVO vo); //글 삭제
	public int getTotal(); //총 게시글 수 
	
	

}
