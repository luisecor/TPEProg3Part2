package Grafo;

public class Main {

	public static void main(String[] args) {


		GrafoDirigido g1 = new GrafoDirigido();
		
		g1.agregarVertice("Ficcion");
		g1.agregarVertice("Aventura");
	

		g1.agregarArco("Ficcion", "Aventura");
		g1.agregarArco("Ficcion", "Aventura");
		g1.agregarArco("Ficcion", "Aventura");
		g1.agregarArco("Ficcion", "Aventura");
		
		g1.impimir();
		
		
		
	}

}
