package entities;
import java.util.*;


public class Grafo {
	private Map<Vertice, List<Vertice>> map = new HashMap<>(); 
	private ArrayList<Aresta> arestas = new ArrayList<>(); 
	
	public void adicionarVertice(Vertice vertice) {
		
		map.put(vertice, new LinkedList<Vertice>()); 
	
	}
	
	public void adicionarAresta(Vertice vertice1, Vertice vertice2) {
		map.get(vertice1).add(vertice2); // adiciona vertice 2 à lista associada com vertice 1
		arestas.add(new Aresta(vertice1, vertice2)); 
		
	}
	
	public boolean temAresta(Vertice vertice1, Vertice vertice2) {
		
		if (map.get(vertice1).contains(vertice2)) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public ArrayList<Vertice> vizinhos(Vertice vertice) {
		ArrayList<Vertice> vizinhos = new ArrayList<Vertice>(); 
		if(!map.containsKey(vertice)) {
			return null; 
		}
		for(Vertice u:map.get(vertice)) {
			vizinhos.add(u);
		}
		return vizinhos; 
	}
}
