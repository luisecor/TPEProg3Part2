package Generos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;




public class GrafoDirigido implements Grafo {
	

	
	// (V: Vertice origen , K: Vertice destino)
	private HashMap<String, ArrayList<Arco>> estructura;
	
	public GrafoDirigido(){
		this.estructura = new HashMap<>();		
	}
	
	public void ordenarArcos() {
		this.estructura.forEach((k,v) -> {
			Collections.sort(v,Collections.reverseOrder());
		});
	}

	@Override
	public void agregarVertice(String verticeId) {
		if (!this.estructura.containsKey(verticeId)) {
			this.estructura.put(verticeId, new ArrayList<Arco>());
		}

	}
	
	public ArrayList<String> generosMasBuscados(String Origen, int N) {
		ArrayList<String> solucion = new ArrayList<>();
		ArrayList<Arco> adyacentes = this.estructura.get(Origen);
		
		while (solucion.size()<N && !adyacentes.isEmpty()) {
			String gMasBuscado = adyacentes.get(0).getVerticeDestino();
			adyacentes.remove(0);
			solucion.add(gMasBuscado);
		}
		return solucion;
	}
	
	
	
	public ArrayList<Arco> secuenciaMasLarga(String Origen){
		//Guardo en caminos la solucion
		ArrayList<Arco> camino = new ArrayList<>();
		
		//Lista de Arcos Adyacentes al Origen
		ArrayList<Arco> adyacentes = this.estructura.get(Origen);
		
		//Los arcos ya estan ordenados por el peso, por lo que 
		//la condicion GREEDY que tomamos es siempre el vertice 
		//con mayor peso
		
		HashSet<String> visitados = new HashSet<>();
		visitados.add(Origen);
		
		//Tomo el 1er vertice de adyacentes que sera el primer valor de la solucion
		camino.add(adyacentes.get(0));
		
		//Me paro en el siguiente vertice y lo marco como visitado
		Arco siguiente = adyacentes.get(0);
		visitados.add(siguiente.getDestino());
		
		//Greedy---->
		
		Iterator<Arco> it = adyacentes.iterator();
		while (it.hasNext()) {
			Arco arcoTmp = it.next();
			if (!visitados.contains(arcoTmp.getDestino())) {
				visitados.add(arcoTmp.getDestino());
				camino.add(arcoTmp);
				it = this.estructura.get(arcoTmp.getDestino()).iterator();
			}
			
		}
		
		return camino;
	}
	
	public Grafo BacktrackingObtenerSubGrafo(String Origen) {
		GrafoDirigido solucion = new GrafoDirigido();
		HashSet<String> visitados = new HashSet<>();
		solucion.agregarVertice(Origen);
		this.Backtracking(solucion, Origen," ", visitados);//
		return solucion;
	}

	private void Backtracking(GrafoDirigido solucion, String Origen, String actual, HashSet<String> visitados) {
	
		if(Origen==actual) {
			return;
			
		} else {
			ArrayList<Arco> ArcosAdy = this.estructura.get(actual);//FALLA EN LA PRIMERA ITERACION
			ArrayList<String> adyacentes = new ArrayList<>();
			for (Arco arco : ArcosAdy) {//agrego los destinos a mis adyacentes (siempre que no estén visitados)
				if(!visitados.contains(arco.getDestino()) || arco.getDestino()==Origen)	//adyacente no visitado o es el Origen (para formar el ciclo)
					adyacentes.add(arco.getDestino());
			}
			for (String ady : adyacentes) {
				visitados.add(ady);
				solucion.agregarVertice(ady);
				solucion.agregarArco(actual, ady);
				this.Backtracking(solucion, Origen, ady, visitados);
				visitados.remove(ady);
				solucion.borrarArco(actual, ady);
				solucion.borrarVertice(ady);
			}
		}
		return;
		
	}

@Override
	public void borrarVertice(String verticeId) {
		if (estructura.containsKey(verticeId)) {
			estructura.remove(verticeId);
			Iterator<String> it = estructura.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (existeArco(key, verticeId)) {
					borrarArco(key, verticeId);
				}
			}
		}
		

	}
	

	@Override
	public void agregarArco(String origen, String destino) {
		
		if (this.estructura.containsKey(origen) && this.estructura.containsKey(destino)) {
			Arco tmp = new Arco (destino);
			if (this.estructura.get(origen).contains(tmp)) {//Los arcos los comparo por destino, basta con poner el String
				int aux = this.estructura.get(origen).indexOf(tmp);//Agarro el indice del arco
				this.estructura.get(origen).get(aux).sumar();//Le sumo uno al arco				
			} else
				this.estructura.get(origen).add(new Arco(destino));
		}

	}
	


	@Override
	public void borrarArco(String verticeId1, String verticeId2) {
		if (this.estructura.containsKey(verticeId1)) {
			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
			for (Arco arco : arcos) {
				if (arco.getVerticeDestino() == verticeId2) {
					arcos.remove(arco);
					return;
				}
			}
		}

	}
//
//	@Override
//	public boolean contieneVertice(String verticeId) {
//		return this.estructura.containsKey(verticeId);
//	}
//
	@Override
	public boolean existeArco(String verticeId1, String verticeId2) {
		if (this.estructura.containsKey(verticeId1)) {
			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
			for (Arco arco : arcos) {
				if (arco.getVerticeDestino() == verticeId2)
					return true;
			}
		}
		return false;
	}
//
//	@Override
//	public Arco obtenerArco(String verticeId1, String verticeId2) {
//		if (this.estructura.containsKey(verticeId1) && this.estructura.containsKey(verticeId2)){
//			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
//			for (Arco arco : arcos) {
//				if (arco.getVerticeDestino() == verticeId2)
//					return arco;
//			}
//			
//		}
//		return null;
//	}
//
//	@Override
//	public int cantidadVertices() {
//		return this.estructura.size();
//	}
//
//	@Override
//	public int cantidadArcos() {
//		int cant = 0;
//		Iterator<ArrayList<Arco>> it = this.estructura.values().iterator(); 
//		while (it.hasNext()) {
//			cant+= it.next().size();
//		}
//		return cant;
//	}
//
//	@Override
//	public Iterator<String> obtenerVertices() {
//		if (!this.estructura.isEmpty()) {
//			
//			return estructura.keySet().iterator();
//		}
//		return null;
//	}
//
//	@Override
//	public Iterator<String> obtenerAdyacentes(String verticeId) {
//		if (this.estructura.containsKey(verticeId)) {
//			ArrayList<Arco> listArcos = estructura.get(verticeId);
//			ArrayList<String> adyasentes = new ArrayList<>();
//			for (Arco arco : listArcos ) {
//				adyasentes.add(arco.getVerticeDestino());
//			}
//			return adyasentes.iterator();
//		}
//		
//		return null;
//	}
//
//	@Override
	public ArrayList<Arco> obtenerArcos() {
		if (!estructura.isEmpty()) {
			ArrayList<Arco> arcos = new ArrayList<>();
			Iterator<String> it = estructura.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				arcos.addAll(estructura.get(key));
			}
			return arcos;
			/*Collection<ArrayList<Arco<T>>> ColectionArcos = estructura.values();
			ArrayList<Arco<T>> lista = new ArrayList<>();
			for (ArrayList<Arco<T>> subListaArco : ColectionArcos)
				for (Arco<T> arco : subListaArco)
					lista.add(arco);
			return lista.iterator();*/
		}
		return null;
	}
//
//	@Override
//	public Iterator<Arco> obtenerArcos(String verticeId) {
//		if (estructura.containsKey(verticeId)) {
//			ArrayList<Arco> arcos = estructura.get(verticeId);
//			return arcos.iterator();			
//		}
//		return null;
//	}
	
	public void impimir() {
		estructura.forEach((k,v) -> {
			System.out.print(k + " --> ");
			System.out.print("[ ");
			for ( Arco a : v) {
				System.out.print(a.getVerticeDestino()+ " ");				
			}
			
			System.out.println("]");
		});
		
	}

	



}
