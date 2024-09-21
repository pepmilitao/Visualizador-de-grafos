package entities;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import view.MainFrame;


@SuppressWarnings("serial")
public class Vertice extends JPanel /* implements Comparable<Vertice> */ {	
	
	protected static final int TAMANHO = 50;
    protected static final Color COR_VERTICE = Color.white;
    protected static final Map<String, Vertice> vertices = new HashMap<>();
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
        G.setColor(Vertice.COR_VERTICE);
        G.fillOval(0, 0, Vertice.TAMANHO, Vertice.TAMANHO);
    }

    private void setLabel() {
        this.label.setText(this.id);
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setSize(Vertice.TAMANHO, Vertice.TAMANHO);
        this.add(this.label);
    }
    /*
    @Override
    public boolean equals(Object Vertice2) {
        if (this == Vertice2) return true;
        if (!(Vertice2 instanceof Vertice)) return false;
        Vertice outroVertice = (Vertice) Vertice2;

        return Objects.equals(this.id, (outroVertice).getId()) &&
                this.coordX == outroVertice.getCoordX() &&
                this.coordY == outroVertice.getCoordY();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + coordX;
        result = 31 * result + coordY;

        return result;
    }

    @Override
    public int compareTo(Vertice outroVertice) {
        return String.valueOf(this.id).compareTo(outroVertice.getId());
    }
	*/
}
