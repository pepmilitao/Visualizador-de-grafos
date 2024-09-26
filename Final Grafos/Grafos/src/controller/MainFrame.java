package controller;

import javax.swing.*;

import model.Aresta;
import model.Grafo;
import model.Vertice;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Grafo grafo;
	private GrafoView view;
	
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
		setMenu();
		this.grafo = grafo;
		this.view = view;

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
			}
		});

		setVisible(true); 	
		setBackground(Color.black);
	}

	private void setMenu(){
		JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton edgeButton = new JButton("Adicionar Aresta");
		JButton removeButton = new JButton("Remover");
        JButton resetButton = new JButton("Limpar");
        JButton randomButton = new JButton("Gerar grafo aleatório");
        JButton buscaButton = new JButton("Executar busca em profundidade");

		edgeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Digite os vértices da aresta (Separados por um espaço)");

				String[] destinos = input.split("\\s+");

				 Vertice vertice1 = null;
				 Vertice vertice2 = null;
				if (destinos.length != 2) {
					JOptionPane.showMessageDialog(null, "ERRO: input inválido");
				}
				else{
					for (Component v : view.getComponents()) {
						if (v instanceof Vertice && ((Vertice)v).getId().equals(destinos[0])) {
							vertice1 = (Vertice)v;
						}
	
						if (v instanceof Vertice && ((Vertice)v).getId().equals(destinos[1])) {
							vertice2 = (Vertice)v;
						}
					}
					if (vertice1 != null && vertice2 != null) {
						grafo.adicionarAresta(vertice1, vertice2);
						view.refresh();
					}
					else{
						JOptionPane.showMessageDialog(null, "ERRO: vértices inválidos");
					}
				}
			}
		});

		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Digite o nome de um vértice, para removê-lo, ou dois (separados por um espaço) para remover uma aresta");

				if (input != null && input.matches("^[a-zA-Z0-9]+( [a-zA-Z0-9]+)?$")) {
					try {
						String[] parts = input.split(" ");
						if (parts.length == 1) {
							grafo.removerVertice(input);
							view.refresh();
						} else if (parts.length == 2) {
							String id1 = parts[0];
							String id2 = parts[1];
								
							grafo.removerAresta(grafo.acharVerticePorId(id1), grafo.acharVerticePorId(id2));
							view.refresh();
						}
					} catch (Exception err) {
						JOptionPane.showMessageDialog(null, err.getMessage());
					}
				} else {
					// Caso não seja uma entrada válida, mostrar mensagem de erro
					JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Insira um vértice ou dois vértices separados por um espaço.");
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grafo.resetGrafo();
				view.refresh();
			}
		});

		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grafo.resetGrafo();
				String input = JOptionPane.showInputDialog("Digite o número de vértices do grafo");
		
				try {
					int numVertices = Integer.parseInt(input);
					if (numVertices < 0) {
						throw new IllegalArgumentException("O número de vértices deve ser maior que -1.");
					}
					
					grafo.grafoAleatorio(numVertices);
					view.refresh();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erro: Digite um número inteiro válido.");
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		menuPanel.add(edgeButton);
		menuPanel.add(removeButton);
        menuPanel.add(resetButton);
        menuPanel.add(randomButton);
        menuPanel.add(buscaButton);

        this.add(menuPanel, BorderLayout.SOUTH);
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