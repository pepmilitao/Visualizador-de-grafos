package model;
import java.awt.Color;
import java.util.*;

import javax.swing.Timer;

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

	public void buscaProfundidade(Vertice verticeInicial, GrafoView view) {
		Stack<Vertice> pilha = new Stack<>(); // Usar uma pilha para controlar a busca
		pilha.push(verticeInicial);
		verticeInicial.setCorVertice(1); // Pinta o vértice como visitado
		busca.add(verticeInicial); // Adiciona à lista de busca
	
		Timer timer = new Timer(500, null); // Cria um timer com intervalo de 500ms
		timer.addActionListener(e -> {
			if (!pilha.isEmpty()) {
				Vertice atual = pilha.peek();
				List<Vertice> vizinhos = vizinhos(atual);
				boolean encontrouVizinhoBranco = false;
	
				// Tenta explorar todos os vizinhos
				for (Vertice vizinho : vizinhos) {
					if (vizinho.getColor().equals(Color.white)) {
						vizinho.setCorVertice(1); // Marca como visitado (cinza)
						busca.add(vizinho); // Adiciona à lista de busca
						pilha.push(vizinho); // Adiciona o vizinho à pilha para ser processado depois
	
						// Pinta a aresta conectada
						String arestaPintar1 = ("Aresta <" + verticeInicial.getId() + " -> " +vizinho.getId() + ">"); 
						String arestaPintar2 = ("Aresta <" + vizinho.getId() + " -> " + verticeInicial.getId() + ">");
						for (Aresta aresta:arestas){
						if (aresta.getName().equals(arestaPintar1) || aresta.getName().equals(arestaPintar2)) {
							aresta.setCorAresta(1); // Aresta visitada
							busca.add(aresta);
						}}
	
						encontrouVizinhoBranco = true;
						break; // Sai do loop para processar um vizinho de cada vez
					}
				}
	
				if (!encontrouVizinhoBranco) {
					atual.setCorVertice(2); // Pinta o vértice como completamente processado (preto)
					busca.add(atual);
					pilha.pop(); // Remove o vértice atual da pilha
				}
	
				view.refresh();
			} else {
				timer.stop(); // Para o timer quando não há mais vértices a processar
			}
		});
	
		timer.start(); // Inicia o timer
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
