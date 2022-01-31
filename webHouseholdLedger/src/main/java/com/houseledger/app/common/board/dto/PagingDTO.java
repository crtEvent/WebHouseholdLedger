package com.houseledger.app.common.board.dto;

public class PagingDTO {
	
	// input value by SelectListDTO
	private int currentPage; // 현재 페이지 번호
	private int numberPostsPerPage; // 페이지 당 출력할 게시글 수
	private int pagingBlockSize; // 페이징 블록의 크기
	private int startPositionOnCurrentPage; // 현재 페이지의 첫 번째 글 위치(=SQL LIMIT절에 들어갈 숫자)
	
	// input value by SQL 
	private int totalNumberPosts; // 전체 게시글 개수
	
	// value to be calculated
	private int lastPage; // 마지막 페이지 번호
	private int firstPageOfpagingBlock; // 페이지 블록의 첫번 째 페이지 번호
	private int lastPageOfpagingBlock; // 페이지 블록의 마지막 페이지 번호
	private boolean hasPreviousBlock; // 이전 블록 존재 여부
	private boolean hasNextBlock; // 다음 블록 존재 여부
	
	public PagingDTO(SelectListDTO dto, int totalNumberPosts) {
		
		// [input value by SelectListDTO]
		this.currentPage = dto.getCurrentPage();
		this.numberPostsPerPage = dto.getNumberPostsPerPage();
		this.pagingBlockSize = dto.getPagingBlockSize();
		this.startPositionOnCurrentPage = dto.getStartPositionOnCurrentPage();
		
		// [input value by SQL]
		this.totalNumberPosts = totalNumberPosts;
		if(Integer.valueOf(this.totalNumberPosts) == null) {
			this.totalNumberPosts = 0;
		}
		
		// [value to be calculated]
		// 전체 페이지 개수(=마지막 페이지 번호)
		this.lastPage = ((this.totalNumberPosts - 1) / dto.getNumberPostsPerPage()) + 1;
		if (dto.getCurrentPage() > this.lastPage) {
			// 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 개수(=마지막 페이지 번호)를 저장
			this.currentPage = this.lastPage;
			dto.setCurrentPage(this.lastPage);
		}

		// 페이지 블록의 첫번 째 페이지 번호
		this.firstPageOfpagingBlock = (((dto.getCurrentPage() - 1) / this.pagingBlockSize)
				* this.pagingBlockSize) + 1;

		// 페이지 블록의 마지막 페이지 번호
		this.lastPageOfpagingBlock = this.firstPageOfpagingBlock + this.pagingBlockSize - 1;
		if (this.lastPageOfpagingBlock > this.lastPage) {
			this.lastPageOfpagingBlock = this.lastPage;
		}

		// 이전 블록 존재 여부
		this.hasPreviousBlock = dto.getCurrentPage() > this.pagingBlockSize;

		// 다음 블록 존재 여부
		this.hasNextBlock = dto.getCurrentPage() <= lastPage - this.pagingBlockSize;
	}
	
	// Getter
	
	public int getTotalNumberPosts() {
		return totalNumberPosts;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getPagingBlockSize() {
		return pagingBlockSize;
	}

	public int getStartPositionOnCurrentPage() {
		return startPositionOnCurrentPage;
	}

	public int getNumberPostsPerPage() {
		return numberPostsPerPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}

	public int getFirstPageOfpagingBlock() {
		return firstPageOfpagingBlock;
	}

	public int getLastPageOfpagingBlock() {
		return lastPageOfpagingBlock;
	}

	public boolean isHasPreviousBlock() {
		return hasPreviousBlock;
	}

	public boolean isHasNextBlock() {
		return hasNextBlock;
	}
	
	public String toString() {
		return "\ncurrentPage: "+currentPage
				+"\nnumberPostsPerPage: "+numberPostsPerPage
				+"\npagingBlockSize: "+pagingBlockSize
				+"\nstartPositionOnCurrentPage: "+startPositionOnCurrentPage
				+"\ntotalNumberPosts: "+totalNumberPosts
				+"\nlastPage: "+lastPage
				+"\nfirstPageOfpagingBlock: "+firstPageOfpagingBlock
				+"\nlastPageOfpagingBlock: "+lastPageOfpagingBlock
				+"\nhasPreviousBlock: "+hasPreviousBlock
				+"\nhasNextBlock: "+hasNextBlock;
	}
}
