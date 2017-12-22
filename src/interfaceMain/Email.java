package interfaceMain;

import java.util.ArrayList;

/**
 * 
 * @author nunocamilo
 * @author andrerouiller
 * @author tomascostapedro
 *
 */

public class Email {
	
	private String name;
	private ArrayList<Rule> rulesList;
	private String tipo;

	public Email(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		rulesList = new ArrayList<Rule>();
	}

	public String getName() {
		return name;
	}
	
	public void addRule (String ruleName){
		Rule rule = new Rule(ruleName, 0.0);
		rulesList.add(rule);
	}
/**
 * 	
 * @return Lista de rules de cada mail
 */
	public ArrayList<Rule> getRulesList() {
		return rulesList;
	}
	
	public double getPesoMail() {
		double pesoMail = 0;
		for(Rule rule : rulesList) {
			pesoMail = pesoMail + rule.getPeso();
		}
		return pesoMail;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
