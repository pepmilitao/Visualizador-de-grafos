package model;
import java.awt.Color;
import java.util.*;

import view.GrafoView;


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

	public ArrayList<Object> getBusca(){
		return busca;
	}
	
	public void adicionarVertice(Vertice vertice) {
		vertices.put(vertice, new LinkedList<Vertice>()); 
	}
	
	public void adicionarAresta(Vertice vertice1, Vertice vertice2) {
		vertices.get(vertice1).add(vertice2); // adiciona vertice 2 à lista associada com vertice 1
		vertices.get(vertice2).add(vertice1); // adiciona vertice 1 à lista associada com vertice 2
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

	public void removerAresta(Vertice vertice1, Vertice vertice2) throws Exception {
		Iterator<Aresta> iterator = arestas.iterator();
		while (iterator.hasNext()) {
			Aresta aresta = iterator.next();
			if ((aresta.getVertice1().equals(vertice1) || aresta.getVertice1().equals(vertice2)) && 
				(aresta.getVertice2().equals(vertice1) || aresta.getVertice2().equals(vertice2))) {
					
				iterator.remove();
			}
		}
	}

	public void removerVertice(String id) throws Exception {
		try {
			Vertice vertice = acharVerticePorId(id);
			
			// Remover todas as arestas conectadas ao vértice em outros vértices
			for (Vertice vizinho : vertices.keySet()) {
				List<Vertice> vizinhos = vertices.get(vizinho);
				if (vizinhos.contains(vertice)) {
					removerAresta(vizinho, vertice); // Remove a aresta do vizinho para o vértice
				}
			}
	
			// Agora, remover todas as arestas diretamente conectadas ao vértice
			List<Vertice> vizinhos = vertices.remove(vertice);
			if (vizinhos != null) {
				for (Vertice vizinho : vizinhos) {
					removerAresta(vertice, vizinho); // Remove as arestas do vértice removido
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Vertice acharVerticePorId(String id) throws Exception{
		Set<Vertice> verticesSet = vertices.keySet();
		for (Vertice vertice : verticesSet) {
			if (vertice.getId().equals(id)) {
				return vertice;
			}
		}
		throw new Exception("O id não foi encontrado");
	}

	private void pausaExecucao(GrafoView grafo) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		grafo.refresh();
	}

	public List<Object> buscaProfundidade(Vertice verticeInicial) {
		verticeInicial.setCorVertice(1);
		busca.add(verticeInicial);
		Aresta arestaaPintar = null;
		List<Vertice> vizinhos = vertices.get(verticeInicial);
		for (Vertice verticeVizinho:vizinhos){
			if (verticeVizinho.getColor().equals(Color.white)) {
				String arestaPintar1 = ("Aresta <" + verticeInicial.getId() + " -> " + verticeVizinho.getId() + ">"); 
				String arestaPintar2 = ("Aresta <" + verticeVizinho.getId() + " -> " + verticeInicial.getId() + ">");
				// a aresta pode estar no formato 12 ou 21, precisamos considerar ambos os casos para saber quando pintar
				for (Aresta aresta:arestas){
					if (aresta.getName().equals(arestaPintar1)){
						arestaaPintar = aresta; 
						arestaaPintar.setCorAresta(1);
						busca.add(arestaaPintar);
						break;
					}
					else if (aresta.getName().equals(arestaPintar2)){
						arestaaPintar = aresta;
						arestaaPintar.setCorAresta(1); 
						busca.add(arestaaPintar); 
					}
				}
				buscaProfundidade(verticeVizinho);
			}
		}
		verticeInicial.setCorVertice(2);
		arestaaPintar.setCorAresta(2); 
		return busca;
	}

	public Boolean verticeRepetido(String id){
		Set<Vertice> vertices = this.vertices.keySet();

		for (Vertice v : vertices) {
			if (v.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}

	public void resetGrafo() {
		vertices.clear(); 
		arestas.clear();
		busca.clear(); 
	}
	public void resetBusca(ArrayList<Object> busca) {
		busca.clear();
	}

	public void grafoAleatorio(int n){
		Random r = new Random();
		ArrayList<ArrayList<Integer>> valores = new ArrayList<>();
		for (int i = 0; i < n; i++){
			ArrayList<Integer> coords = new ArrayList<>();
			coords.add(50 + r.nextInt(750 - 50));
			coords.add(50 + r.nextInt(500 - 50));
			valores.add(coords);
		}

		Integer i = 1;
		for (ArrayList<Integer> coords : valores) {
			adicionarVertice(new Vertice(coords.get(0), coords.get(1), i.toString()));
			i++;
		}

		Integer nArestas = 1 + r.nextInt((n*(n-1))/2); //no máximo, o grafo será completo. Poderão ter arestas repetidas que não serão mostradas

		Set<Vertice> vertices = getVertices().keySet();
		for (int j = 0; j < nArestas; j++){
			Integer id1 = 1 + r.nextInt(n);
			Integer id2 = 1 + r.nextInt(n);
			Vertice v1 = null;
			Vertice v2 = null;

			if (id1 != id2) {
				for (Vertice vertice : vertices) {
					if (vertice.getId().equals(id1.toString())) {
						v1 = vertice;
					}

					if (vertice.getId().equals(id2.toString())) {
						v2 = vertice;
					}
				}
			}
			if (v1 != null && v2 != null) {
				adicionarAresta(v1, v2);
			}
		}
	}
}
