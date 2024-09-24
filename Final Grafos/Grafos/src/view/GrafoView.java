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

    GrafoView(Grafo grafo){
        this.grafo = grafo;
        setPreferredSize(new Dimension(600, 450));
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
            Vertice v1 = e.getVertice1();
            Vertice v2 = e.getVertice2();

            g.drawLine(v1.getCoordX(), v1.getCoordY(), v2.getCoordX(), v2.getCoordY());
        }
        
        for (Vertice v : vertices) {
            g.setColor(Color.BLUE);
            g.fillOval(v.getCoordX(), v.getCoordY(), 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(v.getName(), v.getCoordX() + 5, v.getCoordY() + 15);
        }
    }
}
