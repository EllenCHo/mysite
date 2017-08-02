package com.javaex.vo;

public class PagingVo {
	int totalCount;		//총 게시글 갯수
	int pageCnt;		//화면에 비쳐질 페이지 갯수
	int pageNo;			//한 페이지에 보여질 게시글 갯수
	int totalPage;		//총 페이지 수
	int endPage;		//a
	
	public PagingVo(int totalCount) {
		this.totalCount = totalCount;		//총 게시글 갯수
		pageCnt = 5;		//화면에 비쳐질 페이지 갯수
		pageNo = 5;			//한 페이지에 보여질 게시글 갯수
		totalPage = (int)Math.ceil(totalCount/(double)pageCnt);		//총 페이지 수
	}

	public PagingVo(int totalCount, int pageCnt, int pageNo) {
		this.totalCount = totalCount;
		
		if(pageCnt == 0) {
			this.pageCnt = 5;
		} else {
			this.pageCnt = pageCnt;
		}
		
		if(pageNo == 0) {
			this.pageNo = 5;
		} else {
			this.pageNo = pageNo;
		}
		
		totalPage = (int)Math.ceil(totalCount/(double)pageCnt);		//총 페이지 수
	}
	
	
}
