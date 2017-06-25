package it.polito.tdp.porto.model;

public class Author extends Nodo {

	private int id;
	private String lastname;
	private String firstname;
		
	public Author(int id, String lastname, String firstname, int tipo) {
		super(tipo, id);
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + "]";
	}
	
	
}