package Generos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	
	public ArrayList<Arco> secuenciaMasLarga(String Origen){
		ArrayList<Arco> camino = new ArrayList<>();
		ArrayList<Arco> todosLosArcos = this.obtenerArcos();
		for (Arco arco : todosLosArcos) {
			arco.setColor("BLANCO");
		}
		ArrayList<Arco> adyacentes = this.estructura.get(Origen);
		Collections.sort(adyacentes, Collections.reverseOrder());
		if (adyacentes!=null) {
			Arco arco = adyacentes.get(0);
		
			while(todosLosArcos.size()!=0 && arco.getColor()=="Blanco") {
				todosLosArcos.remove(arco);
				camino.add(arco);
				arco.setColor("AMARILLO");
				ArrayList<Arco> adyacentes2 = this.estructura.get(arco.getDestino());
				Collections.sort(adyacentes2, Collections.reverseOrder());
				boolean tieneAdyacentes=false;
				int i=0;
				while (tieneAdyacentes=false && i<adyacentes2.size()) {
					tieneAdyacentes=this.estructura.get(adyacentes2.get(i).getDestino())!=null;
					i++;
					//me fijo que el proximo vertice tenga adyacentes
				}
				if (tieneAdyacentes)
					arco = adyacentes2.get(i);
			}
			if(arco.getColor()=="AMARILLO") 
				return camino;
			//falta sacar los arcos que no forman parte del ciclo
		}
		return null;
		
		
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
