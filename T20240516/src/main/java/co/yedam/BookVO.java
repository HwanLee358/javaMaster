package co.yedam;

import lombok.Data;

@Data
public class BookVO {
	private String bookCode;
	private String bookName;
	private String bookAuthor;
	private String bookPress;
	private int bookPrice;
}
