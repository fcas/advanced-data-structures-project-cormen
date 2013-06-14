package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainBellmanFord {
	
	private static final String caminhoArquivo = System.getProperty("user.dir") + "/src/testes/bellmanford/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	protected BellmanFord bellmanford;
	
	public MainBellmanFord(WeightedAdjacencyListGraph grafo) throws FileNotFoundException {
		
		bellmanford = new BellmanFord(grafo);
		
	}
	
	public void leArquivo() throws IOException{
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");
			
			switch(palavras[0]){
			case "edge":
				bellmanford.g.addVertex(Integer.parseInt(palavras[1]), palavras[1]);
				bellmanford.g.addVertex(Integer.parseInt(palavras[2]), palavras[2]);
				bellmanford.g.addEdge(Integer.parseInt(palavras[1]), Integer.parseInt(palavras[2]), Double.parseDouble(palavras[3]));
				System.out.println("-");
				break;
				
			case "shortest":
				Stack<Vertex> pilha = new Stack<Vertex>();
				bellmanford.computeShortestPaths(bellmanford.g.getVertex(Integer.parseInt(palavras[1])));
				ShortestPathInfo destino = bellmanford.getShortestPathInfo(bellmanford.g.getVertex(Integer.parseInt(palavras[2])));
				double tamanho = destino.getEstimate();
				Vertex aux = bellmanford.g.getVertex(Integer.parseInt(palavras[2]));
				do {
					pilha.push(aux);
					aux = destino.getPredecessor();
					if (aux != null){
						destino = bellmanford.getShortestPathInfo(aux);
					}
				} while(aux != null);
				
				for (int i=0; i<pilha.size(); i++){
					System.out.print(pilha.pop().getIndex() + " ");
				}
				
				break;
				
			case "hasNegativeCicle":
				bellmanford.computeShortestPaths(bellmanford.g.adj[0].thisVertex);
				System.out.println(!bellmanford.hasNoNegativeWeightCycle());
				break;
			}
			linha = in.readLine();
		}
	}
	
	public static void main(String[] args) throws IOException {
		arquivos.add(caminhoArquivo + "bellmanford1.in");
//		arquivos.add(caminhoArquivo + "bellmanford2.in");
//		arquivos.add(caminhoArquivo + "bellmanford3.in");
		in = new BufferedReader(new FileReader(arquivos.get(0)));
		String card = in.readLine();
		WeightedAdjacencyListGraph grafo = new WeightedAdjacencyListGraph(Integer.parseInt(card), false);
		MainBellmanFord a = new MainBellmanFord(grafo);
		a.leArquivo();
	}
	
	
	

}
