package cyk.entidades;

public class Variable {

	private String variable;
	private String[] producciones;
	
	public Variable(String variable, String[] producciones) {
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
