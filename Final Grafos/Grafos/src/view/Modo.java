package view;

public enum Modo {
	
	EDICAO("Editando o grafo"), 
	BUSCA("Remove um vértice"),
	REMOVE_ARESTA("Remove uma aresta");
	private final String descricao; 
	
	Modo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao; 
	}
}
