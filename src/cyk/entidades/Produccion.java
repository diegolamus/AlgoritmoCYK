package cyk.entidades;

public class Produccion {

	private String variable;
	private String[] producciones;
	
	public Produccion(String variable, String[] producciones) {
		super();
		this.variable = variable;
		this.producciones = producciones;
	}
	
	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String[] getProducciones() {
		return producciones;
	}

	public void setProducciones(String[] producciones) {
		this.producciones = producciones;
	}

	
}
