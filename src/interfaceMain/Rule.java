package interfaceMain;

/**
 * 
 * @author franciscopicao
 * @author tomascostapedro
 * @author andrerouiller
 *
 */

public class Rule {
	
	private double peso;
	private String name;
/**
 * 	
 * @param name
 * @param peso
 * 
 * Construtor de regra
 */
	public Rule(String name, double peso) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.peso = peso;
	}
	
	public Rule(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return getName() + "	" + getPeso();
	}
	

}
