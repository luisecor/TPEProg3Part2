package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;




public class GrafoDirigido implements Grafo {
	

	
	
	private HashMap<String, ArrayList<Arco>> estructura;
	
	public GrafoDirigido(){
		this.estructura = new HashMap<>();		
	}

	@Override
	public void agregarVertice(String verticeId) {
		if (!this.estructura.containsKey(verticeId)) {
			this.estructura.put(verticeId, new ArrayList<>());
		}

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
			Arco arco = new Arco(destino);
			ArrayList<Arco> arcos = this.estructura.get(origen);
			int indexOf = arcos.indexOf(arco);
			if (indexOf < 0) {
				arcos.add(arco);
			} else {
				arcos.get(indexOf).sumar();
			}
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

	@Override
	public boolean contieneVertice(String verticeId) {
		return this.estructura.containsKey(verticeId);
	}

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

	@Override
	public Arco obtenerArco(String verticeId1, String verticeId2) {
		if (this.estructura.containsKey(verticeId1) && this.estructura.containsKey(verticeId2)){
			ArrayList<Arco> arcos = this.estructura.get(verticeId1);
			for (Arco arco : arcos) {
				if (arco.getVerticeDestino() == verticeId2)
					return arco;
			}
			
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.estructura.size();
	}

	@Override
	public int cantidadArcos() {
		int cant = 0;
		Iterator<ArrayList<Arco>> it = this.estructura.values().iterator(); 
		while (it.hasNext()) {
			cant+= it.next().size();
		}
		return cant;
	}

	@Override
	public Iterator<String> obtenerVertices() {
		if (!this.estructura.isEmpty()) {
			
			return estructura.keySet().iterator();
		}
		return null;
	}

	@Override
	public Iterator<String> obtenerAdyacentes(String verticeId) {
		if (this.estructura.containsKey(verticeId)) {
			ArrayList<Arco> listArcos = estructura.get(verticeId);
			ArrayList<String> adyasentes = new ArrayList<>();
			for (Arco arco : listArcos ) {
				adyasentes.add(arco.getVerticeDestino());
			}
			return adyasentes.iterator();
		}
		
		return null;
	}

	@Override
	public Iterator<Arco> obtenerArcos() {
		if (!estructura.isEmpty()) {
			ArrayList<Arco> arcos = new ArrayList<>();
			Iterator<String> it = estructura.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				arcos.addAll(estructura.get(key));
			}
			return arcos.iterator();
			/*Collection<ArrayList<Arco<T>>> ColectionArcos = estructura.values();
			ArrayList<Arco<T>> lista = new ArrayList<>();
			for (ArrayList<Arco<T>> subListaArco : ColectionArcos)
				for (Arco<T> arco : subListaArco)
					lista.add(arco);
			return lista.iterator();*/
		}
		return null;
	}

	@Override
	public Iterator<Arco> obtenerArcos(String verticeId) {
		if (estructura.containsKey(verticeId)) {
			ArrayList<Arco> arcos = estructura.get(verticeId);
			return arcos.iterator();			
		}
		return null;
	}
	
	public void impimir() {
		estructura.forEach((k,v) -> {
			System.out.print(k + " --> ");
			System.out.print("[ ");
			v.forEach((a) -> {
				System.out.print(a.getVerticeDestino() + " ");
			});
			System.out.println("]");
		});
		
	}

	



}
