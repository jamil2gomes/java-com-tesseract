package com.lp3.modelo;

public class Placa {

	private Integer id;
	private String placa;

	
	public Placa() {
		
	}
	
	public Placa(String placa) {
		this.placa = placa;
		
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Placa other = (Placa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Placa [id=" + id + ", placa=" + placa + ", ipvaAtrasado="+ "]";
	}
	
	
	
}
