package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private UndirectedGraph<Nodo, DefaultEdge> graph;
	private Map<Integer, Paper> map;
	private List<Author> authors;
	private List<Paper> papers;
	private AuthorIdMap mapA;
	
	public Model(){
		this.mapA= new AuthorIdMap();
	}
	
	public List<Author> getAuthors(){
		if(authors ==null){
			PortoDAO dao =new PortoDAO();
			authors = dao.getAutori(mapA);
		}
		//System.out.println("----"+authors.size()+"\n");
		return authors;
	}
	public List<Paper> getPapers(){
		if(papers==null){
			PortoDAO dao =new PortoDAO();
			papers= dao.getPapers();
			map = new HashMap<>();
			for(Paper p: papers){
				map.put(p.getEprintid(), p);
			}
		}
		return papers;
	}
	
	
	public void creaGrafo(){
		if(graph == null)
			graph = new SimpleGraph<>(DefaultEdge.class);
		
		List<Nodo> vertici = new ArrayList<>();
		vertici.addAll(this.getAuthors());
		vertici.addAll(this.getPapers());
		
		PortoDAO dao = new PortoDAO();
		Graphs.addAllVertices(graph, vertici);
		
		for(Author a1: this.getAuthors()){	
			for(Paper p2: this.getPapers()){
					//System.out.println(a1.toString()+" "+p2.toString());
					if(dao.articoloDellAutore(a1, p2)==1){
						graph.addEdge(a1, p2);
					}
				}
		}
		System.out.println(graph.toString());
		
	}

	public Map<String, Integer> getFrequenzeRiviste() {
		PortoDAO dao = new PortoDAO();
		
		return dao.getRiviste();
	}

	public List<String> getRivisteMin() {
		List<String> parziale = new ArrayList<>(this.getFrequenzeRiviste().keySet());
		List<String> best = new ArrayList<>();
		
		this.recursive(parziale,best);
		
		return best;
	}

	private void recursive(List<String> parziale, List<String>best) {
		//cond terminazione== esploro tutte le soluzioni possibili
		PortoDAO dao = new PortoDAO();
		
		System.out.println(parziale.toString()+"\n");
		
		System.out.println("--"+best.toString()+"\n");
		
		
		Set<Author> setAutori = new HashSet<Author>(this.getAuthors());
		
		for (String r : parziale) {	
			Set<Author> autorini= dao.getAutoriDellaRivista(r);
			setAutori.removeAll(autorini);
		}
		System.out.println(setAutori.size());
		
		if (setAutori.isEmpty()) {
			if (best.isEmpty())
				best.addAll(parziale);
			
			if (parziale.size() < best.size()){
				best.clear();
				best.addAll(parziale);
			}
		}
		
		for (String rivista  : this.getFrequenzeRiviste().keySet()) {
			if (parziale.isEmpty() || rivista.compareTo(parziale.get(parziale.size()-1)) > 0){
				parziale.add(rivista);
				recursive(parziale, best);
				parziale.remove(rivista);
			}
		}
	}

}
