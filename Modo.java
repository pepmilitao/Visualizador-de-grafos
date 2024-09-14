package view;

public enum Modo {
	
	NENHUM("Nenhum"), 
	ADD_VERTICE("Adiciona um vértice"),
	ADD_ARESTA("Adiciona uma aresta"), 
	REMOVE_VERTICE("Remove um vértice"),
	REMOVE_ARESTA("Remove uma aresta");
	private final String descricao; 
	
	Modo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao; 
	}
}
