package interfaceMain;

import java.util.ArrayList;

public class Email {
	
	private String name;
	private ArrayList<Rule> rulesList;

	public Email(String name, ArrayList<Rule> rulesList) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.rulesList = rulesList;
	}

	public String getName() {
		return name;
	}
	
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
	
	
	
}
