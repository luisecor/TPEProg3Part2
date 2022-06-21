package Generos;

public class Main {

	public static void main(String[] args) {


		GrafoDirigido g1 = new GrafoDirigido();
		
		LeeYEscribe.cargarCSVGeneros(g1);
		
		g1.impimir();
		
		
		
	}

}
