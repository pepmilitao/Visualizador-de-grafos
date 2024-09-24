package model;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import view.MainFrame;


@SuppressWarnings("serial")
public class Vertice extends JPanel {	
	
	protected static final int TAMANHO = 50;
    protected Color cor_vertice = Color.white;
    public static final Map<String, Vertice> vertices = new HashMap<>();
    private String id;
    private JLabel label;
    private int coordX;
    private int coordY;
    
    public Vertice(int x, int y, String id) {
        this.id = id;
        this.coordX = x;
        this.coordY = y;
        vertices.put(this.id, this);
        this.label = new JLabel();
        this.label.setName("VertexLabel " + this.id);
        this.setLabel();

        
        //Inicializa um Label para o vértice
        this.setName("Vertex " + this.id);
        this.setBackground(MainFrame.COR_DE_FUNDO);
        this.setOpaque(false);
        this.setLayout(null);
        this.setBounds(x, y, Vertice.TAMANHO, Vertice.TAMANHO);
    }

    public int getCoordX() {
        return this.coordX;
    }

    public int getCoordY() {
        return this.coordY;
    }

    public String getId() {
        return this.id;
    }

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.setColor(this.cor_vertice);
        G.fillOval(0, 0, Vertice.TAMANHO, Vertice.TAMANHO);
    }

    private void setLabel() {
        this.label.setText(this.id);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setSize(Vertice.TAMANHO, Vertice.TAMANHO);
        this.add(this.label);
    }
    void setCorVertice(Integer Identificador) {
    	// identificador de cores: 0 é branco, 1 é cinza, 2 é preto
    	if (Identificador == 0) {
    		this.cor_vertice = Color.white; 
    	}
    	else if (Identificador == 1){
    		this.cor_vertice = Color.gray; 
    	}
    	else if (Identificador == 2) {
    		this.cor_vertice = Color.black;
    	}
    }
    Color getColor() {
    	return this.cor_vertice;
    }
}
