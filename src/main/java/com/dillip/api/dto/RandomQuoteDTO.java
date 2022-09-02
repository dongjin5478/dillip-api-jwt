package com.dillip.api.dto;

public class RandomQuoteDTO {
	private String content;
	private String author;

	public RandomQuoteDTO() {
		super();
	}

	public RandomQuoteDTO(String content, String author) {
		super();
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "RandomQuoteDTO [content=" + content + ", author=" + author + "]";
	}

}
