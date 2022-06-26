package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {
	
//	Dati in input
	private Genes startingGenes;
	private int nIng;
	private Graph<Genes, DefaultWeightedEdge> graph;
	private int TMAX = 36;
	
//	Dati in output li dedurremo dal geneStudiato
	
	
//	Modello del mondo
	private List<Genes> genesStudiato;
	
//	Coda degli eventi
	private PriorityQueue<Event> queue;
	
	public Simulator(Genes start, int n, Graph<Genes, DefaultWeightedEdge> graph) {
		this.startingGenes = start;
		this.nIng = n;
		this.graph = graph;
		
		if(this.graph.degreeOf(this.startingGenes) == 0) {
			throw new IllegalArgumentException("Vertice di partenza isolato!");
		}
		
		this.queue = new PriorityQueue<>();
		for(int i = 0; i < this.nIng; i++)
			this.queue.add(new Event(0, i));
		
		this.genesStudiato = new ArrayList<>();
		for(int i = 0; i < this.nIng; i++)
			this.genesStudiato.add(this.startingGenes);
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			
			int t = e.getTempo();
			int nIng = e.getnIng();
			Genes g = this.genesStudiato.get(nIng);
			
			if(t < this.TMAX) {
				if(Math.random() < 0.3) {
//					Mantieni il gene
					this.queue.add(new Event(t+1, nIng));
				}
				else {
//					Cambia gene
					double s = 0.0;
					for(DefaultWeightedEdge edge : this.graph.edgesOf(g)) {
						s += this.graph.getEdgeWeight(edge);
					}
					
					double R = Math.random()*s;
					Genes nuovo = null;
					double somma = 0;
					for(DefaultWeightedEdge edge : this.graph.edgesOf(g)) {
						somma += this.graph.getEdgeWeight(edge);
						if(somma > R) {
							nuovo = Graphs.getOppositeVertex(this.graph, edge, g);
							break;
						}
					}
					
					this.genesStudiato.set(nIng, nuovo);
					this.queue.add(new Event(t+1, nIng));
				}
			}
		}
	}
	
	public Map<Genes, Integer> getGeniStudiati(){
		Map<Genes, Integer> studiati = new HashMap<>();
		
		for(int i = 0; i < this.nIng; i++) {
			Genes g = this.genesStudiato.get(nIng);
			if(studiati.containsKey(g))
				studiati.put(g, studiati.get(g)+1);
			else {
				studiati.put(g, 1);
			}
		}
		return studiati;
	}
	
}
