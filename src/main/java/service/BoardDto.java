package service;

import java.sql.Date;

/*
   DTO(Data Tranfer Object):데이타를 전송하는 객체로
                            테이블의 레코드 하나를 저장할 수 있는 자료구조   
 */
public class BoardDto {
	
	private String boardno;
	private String id;
	private String title;
	private String content;
	private String hitCount;
	private java.sql.Date postDate;
	private String name;
	
	//생성자]
	public BoardDto() {}	
	public BoardDto(String boardno, String id, String title, String content, String hitCount, Date postDate, String name) {
		
		this.boardno = boardno;
		this.id = id;
		this.title = title;
		this.content = content;
		this.hitCount = hitCount;
		this.postDate = postDate;
		this.name = name;
	}

	
	public String getBoardno() {
		return boardno;
	}
	public void setBoardno(String boardno) {
		this.boardno = boardno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public java.sql.Date getPostDate() {
		return postDate;
	}
	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
