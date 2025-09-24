package vn.iostar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private int bookid;
    private int isbn;
    private String title;
    private String publisher;
    private double price;
    private String description;
    private Date publishDate;
    private String coverImage;
    private int quantity;
}
