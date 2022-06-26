package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<Genes, DefaultWeightedEdge> graph;
	public Map<String, Genes> idMap;
	
	public Model() {
		this.dao = new GenesDao();
	}
	
	public void creaGrafo() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap = new HashMap<>();
		for(Genes genes : dao.getEssentialGenes())
			idMap.put(genes.getGeneId(), genes);
		
		Graphs.addAllVertices(this.graph, dao.getEssentialGenes());
		
		List<Arco> archi = this.dao.getArchi(idMap);
		for(Arco arco : archi) {
			if(arco.getG1().getChromosome() == arco.getG2().getChromosome())
				Graphs.addEdge(this.graph, arco.getG1(), arco.getG2(), Math.abs(arco.getPeso() * 2.0));
			else 
				Graphs.addEdge(this.graph, arco.getG1(), arco.getG2(), Math.abs(arco.getPeso()));
		}
	}
	
	public String getGraph() {
		return "Grafo creato con " + this.graph.vertexSet().size() + " vertici e " + this.graph.edgeSet().size() + " archi!\n";
	}

	public List<Genes> getEssentialGenes(){
		return this.dao.getEssentialGenes();
	}
	
	public List<Adiacenti> getAdiacenti(Genes g){
		List<Adiacenti> result = new ArrayList<>();
		
		for(Genes genes : Graphs.neighborListOf(this.graph, g)) {
			result.add(new Adiacenti(genes, this.graph.getEdgeWeight(this.graph.getEdge(g, genes))));
		}
		
		Collections.sort(result);
		return result;
	}
	
}
