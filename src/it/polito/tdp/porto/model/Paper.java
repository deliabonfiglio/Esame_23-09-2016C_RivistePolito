package it.polito.tdp.porto.model;

public class Paper extends Nodo {

	private int eprintid;
	private String title;
	private String issn;
	private String publication;
	private String type2;
	private String types;

	public Paper(int tipo, int eprintid, String title, String issn, String publication, String type, String types) {
		super(tipo, eprintid);
		this.eprintid = eprintid;
		this.title = title;
		this.issn = issn;
		this.publication = publication;
		this.type2 = type;
		this.types = types;
	}

	public int getEprintid() {
		return eprintid;
	}

	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String pubblication) {
		this.publication = pubblication;
	}
	

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Paper [eprintid=" + eprintid + ", title=" + title + ", issn=" + issn + ", publication=" + publication
				+ ", type=" + type2 + ", types=" + types + "]";
	}

}
