package sesoc.global.library.vo;

public class BookList {
	private String imageurl;
	private String title;
	private String publisher;
	private String author;
	private String status;
	
	public BookList(){}
	
	public BookList(String imageurl, String title, String publisher, String author, String status) {
		super();
		this.imageurl = imageurl;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.status = status;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookList [imageurl=" + imageurl + ", title=" + title + ", publisher=" + publisher + ", author=" + author
				+ ", status=" + status + "]";
	}
	
	
}
