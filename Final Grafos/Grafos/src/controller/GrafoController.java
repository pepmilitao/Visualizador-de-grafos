package controller;

import model.*;
import view.MainFrame;

import javax.swing.*;
import java.util.List;

public class GrafoController {

    private Grafo grafo;
    private MainFrame view;

    public GrafoController(Grafo grafo, MainFrame view) {
        this.grafo = grafo;
        this.view = view;
    }

    // Inicializa o programa e exibe a interface principal
    public void iniciar() {
        view.setVisible(true);
    }

    // Processa a adição de vértices no grafo
    public void cadastrarVertice(String id, int x, int y) {
        Vertice vertice = new Vertice(x, y, id);
        grafo.adicionarVertice(vertice);
        view.repaint();  // Atualiza a visualização
    }

    // Processa a adição de arestas no grafo
    public void cadastrarAresta(String vertice1Id, String vertice2Id) {
        Vertice vertice1 = Vertice.vertices.get(vertice1Id);
        Vertice vertice2 = Vertice.vertices.get(vertice2Id);
        if (vertice1 != null && vertice2 != null) {
            grafo.adicionarAresta(vertice1, vertice2);
            view.repaint(); 
        } else {
            JOptionPane.showMessageDialog(view, "Vértices inválidos!");
        }
    }

    // Remove um vértice do grafo
    public void removerVertice(String verticeId) {
        Vertice vertice = Vertice.vertices.get(verticeId);
        if (vertice != null) {
            grafo.removerVertice(vertice);
            view.repaint();  
        } else {
            JOptionPane.showMessageDialog(view, "Vértice não encontrado!");
        }
    }

    // Remove uma aresta entre dois vértices
    public void removerAresta(String vertice1Id, String vertice2Id) {
        Vertice vertice1 = Vertice.vertices.get(vertice1Id);
        Vertice vertice2 = Vertice.vertices.get(vertice2Id);
        if (grafo.temAresta(vertice1, vertice2)) {
            grafo.removerAresta(vertice1, vertice2);
            view.repaint();  // Atualiza a visualização
        } else {
            JOptionPane.showMessageDialog(view, "Aresta não encontrada!");
        }
    }

    // Executa o algoritmo de busca em profundidade
    public void executarBusca(String verticeIdInicial) {
        Vertice verticeInicial = Vertice.vertices.get(verticeIdInicial);
        if (verticeInicial != null) {
            List<Object> resultado = grafo.buscaProfundidade(verticeInicial);
            atualizarVisualizacaoBusca(resultado);
        } else {
            JOptionPane.showMessageDialog(view, "Vértice inicial inválido!");
        }
    }

    // Atualiza a visualização do grafo com o resultado da busca
    private void atualizarVisualizacaoBusca(List<Object> resultado) {
        // Mostra o resultado da busca na interface (implementação visual)
        JOptionPane.showMessageDialog(view, "Busca em profundidade concluída!");
        view.repaint();  // Atualiza a visualização
    }
}
