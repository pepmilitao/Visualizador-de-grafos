package model;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import controller.MainFrame;

@SuppressWarnings("serial")
public class Aresta extends JComponent {
	
    protected Color cor_aresta = Color.white;
    private Vertice vertice1;
    private Vertice vertice2;
    private JLabel label;
    private boolean topEqualsLeft;
    protected static ArrayList<Aresta> arestas = new ArrayList<>();

    public Aresta(Vertice vertice1, Vertice vertice2) {
        this.setName("Aresta <" + vertice1.getId() + " -> " + vertice2.getId() + ">");
        this.setBackground(MainFrame.COR_DE_FUNDO);
        this.setLayout(null);
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;

        this.topEqualsLeft = setCoordAresta(); // dá os limites da aresta
        setLabel();

        arestas.add(this);
    }

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.setColor(this.cor_aresta);
        G.drawLine(0, topEqualsLeft ? 0 : this.getHeight(),
                this.getWidth(), topEqualsLeft ? this.getHeight() : 0);
    }

    private void setLabel() {
        this.label = new JLabel();
        this.label.setName("EdgeLabel <" + vertice1.getId() + " -> " + vertice2.getId() + ">");
        this.label.setLocation(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2);
        this.label.setSize(this.label.getPreferredSize());
        this.label.setForeground(this.cor_aresta);
    }

    private boolean setCoordAresta() {
        // X e Y
        Vertice coordY;   // determina coord y
        Vertice coordX;  // determina coord x
        //operadores ternários!
        coordY = this.vertice1.getCoordY() < this.vertice2.getCoordY() ? vertice1 : vertice2; // pega o menor y
        coordX = this.vertice1.getCoordX() < this.vertice2.getCoordX() ? vertice1 : vertice2; // pega o menor x
        int extra = Vertice.TAMANHO / 2;

        int largura = Math.abs(this.vertice1.getCoordX() - this.vertice2.getCoordX()) - extra*2;
        int altura = Math.abs(this.vertice1.getCoordY() - this.vertice2.getCoordY());

        if (largura <= 0) {
            largura += extra*2;
        }

        this.setBounds(coordX.getCoordX() + extra,
                coordY.getCoordY(),
                largura,
                altura);

        return coordY == coordX;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public Vertice getVertice1() {
        return this.vertice1;
    }

    public Vertice getVertice2() {
        return this.vertice2;
    }

    public boolean getTopEqualsLeft() {
        return this.topEqualsLeft;
    }
    void setCorAresta(Integer Identificador) {
    	// identificador de cores: 0 é branco, 1 é cinza, 2 é preto
    	if (Identificador == 0) {
    		this.cor_aresta = Color.white; 
    	}
    	else if (Identificador == 1){
    		this.cor_aresta = Color.gray; 
    	}
    	else if (Identificador == 2) {
    		this.cor_aresta = Color.black;
    	}
}
}
