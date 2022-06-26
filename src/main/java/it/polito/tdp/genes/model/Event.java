package it.polito.tdp.genes.model;

public class Event implements Comparable<Event>{
	
	private int tempo;
	private int nIng;
	
	public Event(int tempo, int nIng) {
		super();
		this.tempo = tempo;
		this.nIng = nIng;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getnIng() {
		return nIng;
	}

	public void setnIng(int nIng) {
		this.nIng = nIng;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.tempo-o.tempo;
	}
	
	

}
