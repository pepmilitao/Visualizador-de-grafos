package view;

import java.awt.*;
import java.util.Set;
import java.util.List;

import javax.swing.JPanel;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public class GrafoView extends JPanel{
    private Grafo grafo;

    public GrafoView(Grafo grafo){
        this.grafo = grafo;
        setPreferredSize(new Dimension(600, 450));
        setBackground(Color.pink);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderGrafo(g);
    }

    private void renderGrafo(Graphics g){
        Set<Vertice> vertices = grafo.getVertices().keySet();
        List<Aresta> arestas = grafo.getArestas();

        for (Aresta e : arestas) {
            add(e);
        }
        
        for (Vertice v : vertices) {
            add(v);
        }
    }

    public void refresh(){
        repaint();
    }
}
