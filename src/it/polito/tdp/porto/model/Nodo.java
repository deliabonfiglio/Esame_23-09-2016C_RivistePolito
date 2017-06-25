package it.polito.tdp.porto.model;

public class Nodo {

	private int id;
	private int tipo;

	
	public Nodo(int type, int id) {
		super();
		this.tipo = type;
		this.id=id;
	}

	public int getType() {
		return tipo;
	}

	public void setType(int type) {
		this.tipo = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (id != other.id)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
}
