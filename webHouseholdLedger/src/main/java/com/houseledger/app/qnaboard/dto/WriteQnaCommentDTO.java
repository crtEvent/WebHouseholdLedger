package com.houseledger.app.qnaboard.dto;

public class WriteQnaCommentDTO {
	
	private String board_idx;
	private String user_idx;
	private String depth;
	private String parent_comment_idx;
	private String bundle_idx;
	private String comment;
	
	public String getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getParent_comment_idx() {
		return parent_comment_idx;
	}
	public void setParent_comment_idx(String parent_comment_idx) {
		this.parent_comment_idx = parent_comment_idx;
	}
	public String getBundle_idx() {
		return bundle_idx;
	}
	public void setBundle_idx(String bundle_idx) {
		this.bundle_idx = bundle_idx;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
