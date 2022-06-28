package Generos;

public class Main {

	public static void main(String[] args) {


		GrafoDirigido g1 = new GrafoDirigido();
		
		LeeYEscribe.cargarCSVGeneros(g1);
		
		g1.impimir();
		
//		System.out.println("Busqueda Investigacion , los primeros 4");
//		
//		System.out.println(g1.generosMasBuscados("investigacion", 4));
		
		System.out.println("Secuencia mas larga -------> investigacion ");
		
		System.out.println(g1.secuenciaMasLarga("investigacion"));
		
		g1.secuenciaMasLarga("investigacion").forEach((k) ->{
			System.out.println( k.getVerticeDestino() );
		});;
		
		
	}

}
