package com.houseledger.app.common.board.dto;

public class SelectListDTO {
	
	private String search_type;
	private String keyword;
	private int currentPage; // 현재 페이지 번호
	private int numberPostsPerPage; // 페이지 당 출력할 게시글 수
	private int startPositionOnCurrentPage; // 현재 페이지의 첫 번째 글 위치(=SQL LIMIT절에 들어갈 숫자)
	private int pagingBlockSize; // 페이징 블록의 크기
	
	public SelectListDTO() {
		this.currentPage = 1;
		this.numberPostsPerPage = 15;
		this.startPositionOnCurrentPage = (currentPage - 1) * numberPostsPerPage;
		this.pagingBlockSize = 10;
	}
	
	public SelectListDTO(int pagingBlockSize) {
		this.currentPage = 1;
		this.numberPostsPerPage = 15;
		this.startPositionOnCurrentPage = (currentPage - 1) * numberPostsPerPage;
		this.pagingBlockSize = pagingBlockSize;
	}
	
	public String getSearch_type() {
		return search_type;
	}
	
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		if(currentPage < 1) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
		this.numberPostsPerPage = 15;
		this.startPositionOnCurrentPage = (currentPage - 1) * numberPostsPerPage;
	}

	public int getNumberPostsPerPage() {
		return numberPostsPerPage;
	}

	public void setNumberPostsPerPage(int numberPostsPerPage) {
		this.numberPostsPerPage = numberPostsPerPage;
	}

	public int getStartPositionOnCurrentPage() {
		return startPositionOnCurrentPage;
	}

	public void setStartPositionOnCurrentPage(int startPositionOnCurrentPage) {
		this.startPositionOnCurrentPage = startPositionOnCurrentPage;
	}

	public int getPagingBlockSize() {
		return pagingBlockSize;
	}

	public void setPagingBlockSize(int pagingBlockSize) {
		this.pagingBlockSize = pagingBlockSize;
	}

	@Override
	public String toString() {
		return "SelectListDTO [search_type=" + search_type + ", keyword=" + keyword + ", currentPage=" + currentPage
				+ ", numberPostsPerPage=" + numberPostsPerPage + ", startPositionOnCurrentPage="
				+ startPositionOnCurrentPage + ", pagingBlockSize=" + pagingBlockSize + "]";
	}
	
}
