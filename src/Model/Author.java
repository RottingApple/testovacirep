package Model;

public class Author {
	private int author_id;
	private String firstname;
	private String lastname;
	private String nationality;
	private String born;
	public Author(int author_id, String firstname, String lastname, String nationality, String born) {
		this.author_id = author_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.nationality = nationality;
		this.born = born;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}

	
}
