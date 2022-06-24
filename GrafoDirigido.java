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

	@Override
	public void agregarVertice(String verticeId) {
		if (!this.estructura.containsKey(verticeId)) {
			this.estructura.put(verticeId, new ArrayList<Arco>());
		}

	}
	
	public ArrayList<String> generosMasBuscados(String Origen, int N) {
		ArrayList<String> solucion = new ArrayList<>();
		ArrayList<Arco> adyacentes = this.estructura.get(Origen);
		Collections.sort(adyacentes, Collections.reverseOrder());
		while (solucion.size()<N && !adyacentes.isEmpty()) {
			String gMasBuscado = adyacentes.get(0).getDestino();
			adyacentes.remove(0);
			solucion.add(gMasBuscado);
		}
		return solucion;
	}
	
	
	
	public ArrayList<String> secuenciaMasLarga(String Origen){
		ArrayList<String> camino = new ArrayList<>();
		ArrayList<String> adyacentes = new ArrayList<>();
		ArrayList<Arco> aux = this.estructura.get(Origen);//agarro los arcos que dirigen a mis adyacentes
		Collections.sort(aux, Collections.reverseOrder());
		for (Arco arco : aux) {//agrego los destinos a mis adyacentes
			adyacentes.add(arco.getDestino());
		}
		HashSet<String> visitados = new HashSet<>();
		visitados.add(Origen);
		camino.add(Origen);
		String v = Origen;
		int i=0;
		while (this.estructura.get(v)!= null) {
			v = adyacentes.get(i);
			if(!visitados.contains(v)) {
				visitados.add(v);
				camino.add(v);
				aux = this.estructura.get(v);
				Collections.sort(aux, Collections.reverseOrder());
				adyacentes.clear();
				while(aux.size()>i && visitados.contains(aux.get(i).getDestino())) {
					i++;
				}
				if(aux.size()>i)
					adyacentes.add(aux.get(i).getDestino());				
				i=0;
			} 
		}	
		
		return camino;
	}

//	@Override
//	public void borrarVertice(String verticeId) {
//		if (estructura.containsKey(verticeId)) {
//			estructura.remove(verticeId);
//			Iterator<String> it = estructura.keySet().iterator();
//			while (it.hasNext()) {
//				String key = it.next();
//				if (existeArco(key, verticeId)) {
//					borrarArco(key, verticeId);
//				}
//			}
//		}
//		
//
//	}
	

	@Override
	public void agregarArco(String origen, String destino) {
		
		if (this.estructura.containsKey(origen) && this.estructura.containsKey(destino)) {
			if (this.estructura.get(origen).contains(destino)) {//Los arcos los comparo por destino, basta con poner el String
				int aux = this.estructura.get(origen).indexOf(destino);//Agarro el indice del arco
				this.estructura.get(origen).get(aux).sumar();//Le sumo uno al arco				
			} else
				this.estructura.get(origen).add(new Arco(destino));
		}
		
//		if (this.estructura.containsKey(origen)&& this.estructura.containsKey(destino)) {
//			Arco arco = new Arco(destino);
//			ArrayList<Arco> arcos = this.estructura.get(origen);
//			int indexOf = arcos.indexOf(arco);
//			if (indexOf < 0) {
//				arcos.add(arco);
//			} else {
//				arcos.get(indexOf).sumar();
//			}
//		}

	}
	
//
//	@Override
//	public void borrarArco(String verticeId1, String verticeId2) {
//		if (this.estructura.containsKey(verticeId1)) {
//			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
//			for (Arco arco : arcos) {
//				if (arco.getVerticeDestino() == verticeId2) {
//					arcos.remove(arco);
//					return;
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public boolean contieneVertice(String verticeId) {
//		return this.estructura.containsKey(verticeId);
//	}
//
//	@Override
//	public boolean existeArco(String verticeId1, String verticeId2) {
//		if (this.estructura.containsKey(verticeId1)) {
//			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
//			for (Arco arco : arcos) {
//				if (arco.getVerticeDestino() == verticeId2)
//					return true;
//			}
//		}
//		return false;
//	}
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
	
//	public void impimir() {
//		estructura.forEach((k,v) -> {
//			System.out.print(k + " --> ");
//			System.out.print("[ ");
//			v.forEach((s,i) -> {
//				System.out.print(s + " " + i + " " );
////				System.out.print(s.getVerticeDestino() + " ");
//			});
//			System.out.println("]");
//		});
//		
//	}

	



}
