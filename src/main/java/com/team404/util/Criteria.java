package com.team404.util;

import lombok.Data;

@Data //게터, 세터 자동생성
public class Criteria {
	
	//화면에서 전달한 값들을 가지고 다닐 클래스
	private int	pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
	

}
