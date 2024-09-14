package view;

import javax.swing.*;
import java.awt.*; 
import entities.Grafo;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	protected static final int LARGURA = 800; // constante para largura do painel
	protected static final int ALTURA = 600; // constante para altura do painel
	protected static final Color COR_DE_FUNDO = Color.black; // constante para a cor de fundo
	//protected static Modo modo = Modo.ADD_VERT;
	//protected static Algoritmo algoritmo = null; 
	private JLabel modoLabel;
	private static final JLabel DisplaydeAlgoritmo; 
	//private Grafo painelGrafo; 
	
	static {
		DisplaydeAlgoritmo = new JLabel(); 
		DisplaydeAlgoritmo.setName("Display");
		DisplaydeAlgoritmo.setText("Please choose a starting vertex");
		DisplaydeAlgoritmo.setForeground(MainFrame.COR_DE_FUNDO);
		DisplaydeAlgoritmo.setBackground(Color.white);
		DisplaydeAlgoritmo.setHorizontalAlignment(SwingConstants.CENTER);
		DisplaydeAlgoritmo.setVerticalAlignment(SwingConstants.CENTER);
		DisplaydeAlgoritmo.setLayout(new FlowLayout(FlowLayout.TRAILING));
		DisplaydeAlgoritmo.setVisible(false);
	}
	public MainFrame() {
		super("Visualizador de Buscas em Grafos"); // super cria um frame invisivel com o titulo dado
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(MainFrame.LARGURA, MainFrame.ALTURA); 
		setLayout(new BorderLayout());
		setResizable(false); //Permitir ou não mudar o tamanho da tela!
		setModoJLabel(); 
		// TO DO: Setar o modo, dar display no grafo, dar display no display de algoritmo
		setJMenu(); 
		
		setVisible(true); 	
		setBackground(Color.black);

	}
	private void setJMenu() {
		JMenuBar menuBar = new JMenuBar(); 
		this.setJMenuBar(menuBar); 
		
		// na barra de menus, temos que fazer um menu para:
		// 1. começar um grafo do zero (limpar a tela)
		// 2. escolher o modo (add vértice, add aresta, remove vertice, remove aresta)
		// 3. escolher qual algoritmo iremos rodar
	}
	private void setModoJLabel() {
		modoLabel = new JLabel();
		this.add(modoLabel, BorderLayout.NORTH);
		modoLabel.setName("Modo");
		modoLabel.setText("Modo atual: Adicionar Vértice");
		modoLabel.setOpaque(true);
		modoLabel.setBackground(MainFrame.COR_DE_FUNDO);
		modoLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        modoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	
	public static JLabel getDisplaydeAlgoritmo() {
        return DisplaydeAlgoritmo;
    }
	
	
	
	
	
}
