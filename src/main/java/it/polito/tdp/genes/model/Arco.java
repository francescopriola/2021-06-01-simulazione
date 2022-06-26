package it.polito.tdp.genes.model;

public class Arco {
	
	private Genes g1;
	private Genes g2;
	private String type;
	private double peso;
	
	public Arco(Genes g1, Genes g2, String type, double peso) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.type = type;
		this.peso = peso;
	}

	public Genes getG1() {
		return g1;
	}

	public void setG1(Genes g1) {
		this.g1 = g1;
	}

	public Genes getG2() {
		return g2;
	}

	public void setG2(Genes g2) {
		this.g2 = g2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
