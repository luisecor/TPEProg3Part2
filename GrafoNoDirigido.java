package Grafo;

public class GrafoNoDirigido extends GrafoDirigido {

	@Override
	public void agregarArco(String verticeId1, String verticeId2) {
		super.agregarArco(verticeId1, verticeId2);
		super.agregarArco(verticeId2, verticeId1);
	}
	
	@Override
	public void borrarArco(String verticeId1, String verticeId2) {
		super.borrarArco(verticeId1, verticeId2);
		super.borrarArco(verticeId2, verticeId1);
	}
	

}
