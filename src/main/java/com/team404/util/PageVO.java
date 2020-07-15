package com.team404.util;

import lombok.Data;

@Data
public class PageVO {

	//criteria를 받아서 화면에 보여줄 버튼 계산
	
	private int startPage; //첫페이지 번호
	private int endPage; //마지막페이지 번호
	private boolean prev; //이전버튼
	private boolean next; //다음
	
	private int pageNum; //현재페이지번호
	private int amount; //보여질 데이터 개수
	private int total; // 총 게시글 수
	
	
	public PageVO(Criteria cri, int total) {
		
		//번호, 개수 세팅
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		
		//endPage 계산 (pageNum이 11일 때 20이 되어야 한다)
		//(int)(Math.ceil(조회페이지번호 / 보여질 페이지 수)) * 보여질페이지 수
		this.endPage = (int)(Math.ceil(this.pageNum / 10.0)) * 10;
		
		//처음페이지
		//끝페이지 - 보여질 페이지 + 1
		this.startPage = endPage - 10 + 1;
		
		//실제 마지막 번호
		//예) 만약에 총 게시물이 53개 라면  -> 끝 페이지 번호는 6까지 처리 
		//예) 만약에 총 게시물이 112개 라면  -> 끝 페이지 번호는 12까지 처리 
		//(int)Math.ceil(총 게시글 수 / 화면에 보여지는 데이터 수 )
		int realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		//404개 게시물
		//endPage의 증가번호는 10, 20, 30, 40, 50
		//realEnd는 41번
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//다음버튼 확성화여부 
		this.next = realEnd > this.endPage;
		
		//이전버튼
		//startPage는 1, 11, 21, 31, 41 .....
		//시작버튼이 활성화 되는 경우 11번페이지 부터 
		this.prev = startPage > 1;
		
	}//end method
	
	
	
	
	
	
}// end class
