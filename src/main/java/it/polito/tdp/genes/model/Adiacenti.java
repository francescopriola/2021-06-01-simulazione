package it.polito.tdp.genes.model;

public class Adiacenti implements Comparable<Adiacenti>{
	
	private Genes genes;
	private Double peso;
	
	public Adiacenti(Genes genes, double peso) {
		super();
		this.genes = genes;
		this.peso = peso;
	}

	public Genes getGenes() {
		return genes;
	}

	public void setGenes(Genes genes) {
		this.genes = genes;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Adiacenti o) {
		return -(this.peso.compareTo(o.getPeso()));
	}

	
	
	
	

}
