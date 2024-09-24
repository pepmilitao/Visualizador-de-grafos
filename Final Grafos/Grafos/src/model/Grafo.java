package model;
import java.awt.Color;
import java.util.*;


public class Grafo {
	private Map<Vertice, List<Vertice>> vertices = new HashMap<>(); 
	private ArrayList<Aresta> arestas = new ArrayList<>();
	private ArrayList<Object> busca = new ArrayList<>(); 

	public Map<Vertice, List<Vertice>> getVertices(){
		return vertices;
	}

	public ArrayList<Aresta> getArestas(){
		return arestas;
	}
	
	public void adicionarVertice(Vertice vertice) {
		vertices.put(vertice, new LinkedList<Vertice>()); 
	}
	
	public void adicionarAresta(Vertice vertice1, Vertice vertice2) {
		vertices.get(vertice1).add(vertice2); // adiciona vertice 2 à lista associada com vertice 1
		arestas.add(new Aresta(vertice1, vertice2)); 
		
	}
	
	public boolean temAresta(Vertice vertice1, Vertice vertice2) {
		
		if (vertices.get(vertice1).contains(vertice2)) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public ArrayList<Vertice> vizinhos(Vertice vertice) {
		ArrayList<Vertice> vizinhos = new ArrayList<Vertice>(); 
		if(!vertices.containsKey(vertice)) {
			return null; 
		}
		for(Vertice u:vertices.get(vertice)) {
			vizinhos.add(u);
		}
		return vizinhos; 
	}

	public void removerAresta(Vertice vertice1, Vertice vertice2) {
		String arestaRemover = ("Aresta <" + vertice1.getId() + " -> " + vertice2.getId() + ">");
		for (Aresta aresta:arestas){
			if (aresta.getName().equals(arestaRemover)){
				arestas.remove(aresta);
			}
		}
		
	}

	public void removerVertice(Vertice vertice) {
		List<Vertice> vizinhos = vertices.remove(vertice); 
		for (Vertice vertices:vizinhos){
			removerAresta(vertice, vertices); 
		}
	}

	public List<Object> buscaProfundidade(Vertice verticeInicial) {
		verticeInicial.setCorVertice(1);
		busca.add(verticeInicial);
		Aresta arestaaPintar = null;
		List<Vertice> vizinhos = vertices.get(verticeInicial);
		for (Vertice verticeVizinho:vizinhos){
			if (verticeVizinho.getColor().equals(Color.white)) {
				String arestaPintar = ("Aresta <" + verticeInicial.getId() + " -> " + verticeVizinho.getId() + ">");
				for (Aresta aresta:arestas){
					if (aresta.getName().equals(arestaPintar)){
						arestaaPintar = aresta; 
						arestaaPintar.setCorAresta(1);
						busca.add(arestaaPintar);
						break;
					}
				}
				buscaProfundidade(verticeVizinho);
			}
		}
		verticeInicial.setCorVertice(2);
		arestaaPintar.setCorAresta(2); 
		return busca;
	}

}
