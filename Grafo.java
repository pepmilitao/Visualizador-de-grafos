package entities2;
import java.util.*;

// outra possivel implementação:
// https://www.youtube.com/watch?v=jq0N1LDOTlw

public class Grafo<T> {
	private Map<T, List<T>> map = new HashMap<>(); 
	
	public void adicionarVertice(T vertice) {
		
		map.put(vertice, new LinkedList<T>()); 
	
	}
	
	public void adicionarAresta(T vertice1, T vertice2) {
		
		if(!map.containsKey(vertice1)) { //Se o vertice 1 não existe, temos que adicioná-lo. 
			adicionarVertice(vertice1); //Adicionando o vértice ao grafo
		}
		if(!map.containsKey(vertice2)) { //Se o vertice 2 não existe, temos que adicioná-lo. 
			adicionarVertice(vertice2); //Adicionando o vértice ao grafo
		}
		map.get(vertice1).add(vertice2); // adiciona vertice 2 à lista associada com vertice 1
		map.get(vertice2).add(vertice1); // adiciona vertice 1 à lista associada com vertice 2
		
	}
	
	public boolean temAresta(T vertice1, T vertice2) {
		
		if (map.get(vertice1).contains(vertice2)) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public ArrayList<T> vizinhos(T vertice) {
		ArrayList<T> vizinhos = new ArrayList<T>(); 
		if(!map.containsKey(vertice)) {
			return null; 
		}
		for(T u:map.get(vertice)) {
			vizinhos.add(u);
		}
		return vizinhos; 
	}
}
