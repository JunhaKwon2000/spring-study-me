package com.winter.app.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	

	// limit의 시작 인덱스 번호
	private Long startIndex;
	
	// limit의 끝
	private Long endIndex;
	
	// Limit의 끝(페이지당 보여줄 row의 갯수)
	private Long perPage;
	
	// 현재 페이지 번호
	private Long pageNum;
	
	private Long totalPage;
	
	private Long startNum;
	
	private Long endNum;
	
	// 쿼리 계산
	private void makePage() {
		// getter 메서드가 호출되어야함(그래야 null 체크를 하니까)
		this.startIndex = (this.getPageNum() - 1) * this.getPerPage();
		this.endIndex = this.getPerPage();
		
	}
	
	public void makeNum(Long totalCount) {
		// 1번부터 몇번까지 있을까? 를 알아내는 작업
		
		// 1. totalPage: 전체 페이지 갯수
		// totalPage = totalCount / 10
		// if (totalCount % 10 != 0) { totalPage += 1 } 
		this.totalPage = totalCount / this.getPerPage();
		if (totalCount % perPage != 0) totalPage++;
		
		// 2. totalBlock: 전체 블럭의 갯수
		Long perBlock = 5L; // 블럭당 출력할 번호의 갯수
		Long totalBlock = totalPage / perBlock;
		if (totalPage % perBlock != 0) totalBlock++;
		
		// 3. 현재 페이지 번호로 현재 블럭 번호를 계산
		Long curBlock = (this.getPageNum() / perBlock);
		if (pageNum % perBlock != 0) curBlock++;
		
		// 4. 현재 블럭 번호로 시작 번호와 끝 번호 계산
		this.startNum = ((curBlock - 1L) * perBlock) + 1L;
		this.endNum = curBlock * perBlock;
		
		// 5. 마지막 블럭일 경우
		if (curBlock == totalBlock ) {
			this.endNum = totalPage;
		}

		this.makePage();
		
	}
	
	public Long getStartIndex() {
		return startIndex;
	}
	
	public Long getEndIndex() {
		return endIndex;
	}
	
	public Long getPerPage() {
		if (this.perPage == null) this.perPage = 10L;
		return perPage;
	}
	
	public Long getPageNum() {
		if (this.pageNum == null || this.pageNum < 1) this.pageNum = 1L;
		if (this.pageNum > totalPage) this.pageNum = totalPage;
		return pageNum;
	}
		
}
