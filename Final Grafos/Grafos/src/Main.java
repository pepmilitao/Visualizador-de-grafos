import controller.MainFrame;
import model.Grafo;
import view.*;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		GrafoView view = new GrafoView(grafo);

		new MainFrame(grafo, view); 

		// Controller...
	}

}