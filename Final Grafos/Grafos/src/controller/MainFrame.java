package controller;

import javax.swing.*;

import model.Aresta;
import model.Grafo;
import model.Vertice;
import view.GrafoView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 

public class MainFrame extends JFrame {
	protected static final int LARGURA = 800; // constante para largura do painel
	protected static final int ALTURA = 600; // constante para altura do painel
	public static final Color COR_DE_FUNDO = Color.black; // constante para a cor de fundo
	//protected static Modo modo = Modo.ADD_VERT;
	//protected static Algoritmo algoritmo = null; 
	private JLabel modoLabel;
	private static final JLabel DisplaydeAlgoritmo; 
	//private Grafo painelGrafo;
	private Vertice verticeSelecionado = null;
	
	static {
		DisplaydeAlgoritmo = new JLabel(); 
		DisplaydeAlgoritmo.setName("Display");
		DisplaydeAlgoritmo.setText("Por favor, escolha um vértice inicial");
		DisplaydeAlgoritmo.setForeground(MainFrame.COR_DE_FUNDO);
		DisplaydeAlgoritmo.setBackground(Color.white);
		DisplaydeAlgoritmo.setHorizontalAlignment(SwingConstants.CENTER);
		DisplaydeAlgoritmo.setVerticalAlignment(SwingConstants.CENTER);
		DisplaydeAlgoritmo.setLayout(new FlowLayout(FlowLayout.TRAILING));
		DisplaydeAlgoritmo.setVisible(false);
	}

	public MainFrame(Grafo grafo, GrafoView view) {
		super("Visualizador de Buscas em Grafos"); // super cria um frame invisivel com o titulo dado
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(MainFrame.LARGURA, MainFrame.ALTURA); 
		setLayout(new BorderLayout());
		setResizable(false); //Permitir ou não mudar o tamanho da tela!
		setModoJLabel(); 
		// TO DO: Setar o modo, dar display no grafo, dar display no display de algoritmo
		setJMenu();

		add(view, BorderLayout.CENTER);

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component clicado =  view.getComponentAt(e.getPoint());
				if (!(clicado instanceof Vertice)) { //se não tiver clicado em vértice nem aresta, ativa o controller de criar um vértice

					String input = JOptionPane.showInputDialog("Digite o nome do vértice:");
					if (input != null && !input.equals("") && !grafo.verticeRepetido(input)) {
						grafo.adicionarVertice(new Vertice(e.getX(), e.getY(), input));
						view.refresh();
					}
					else if (input == null || input.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "ERRO: nome inválido!");
					}
					else if (grafo.verticeRepetido(input)) {
						JOptionPane.showMessageDialog(null, "ERRO: Já existe um vértice com esse nome no grafo!");
					}
					verticeSelecionado = null;

				}
				else if (!(clicado instanceof Aresta)){ //se foi num vértice, ativa o controller de criar aresta

						if (verticeSelecionado == null) {
							verticeSelecionado = (Vertice)clicado;
							System.out.println(verticeSelecionado);
						}
						else{
							grafo.adicionarAresta(verticeSelecionado, (Vertice)clicado);
							view.refresh();
							verticeSelecionado = null;
						}

					}
				}
		});

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