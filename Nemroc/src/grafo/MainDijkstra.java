package grafo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import menorcaminho.Dijkstra;
import menorcaminho.MenorCaminhoInfo;

public class MainDijkstra {
	private static final String caminhoArquivo = System.getProperty("user.dir") + "/src/testes/dijkstra/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	protected Dijkstra dijkstra;
	
	public MainDijkstra(GrafoListaAdjacenciaPesada grafo) throws FileNotFoundException {
		
		dijkstra = new Dijkstra(grafo);
		
	}
	
	public void leArquivo() throws IOException{
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");
			
			switch(palavras[0]){
			case "edge":
				dijkstra.g.addEdge(Integer.parseInt(palavras[1]), Integer.parseInt(palavras[2]), Double.parseDouble(palavras[3]));
				System.out.println("-");
				break;
				
			case "shortest":
				Stack<Vertice> pilha = new Stack<Vertice>();
				dijkstra.computeShortestPaths(dijkstra.g.getVertex(Integer.parseInt(palavras[1])));
				MenorCaminhoInfo destino = dijkstra.getShortestPathInfo(dijkstra.g.getVertex(Integer.parseInt(palavras[2])));
				double tamanho = destino.getEstimate();
				Vertice aux = dijkstra.g.getVertex(Integer.parseInt(palavras[2]));
				do {
					pilha.push(aux);
					aux = destino.getPredecessor();
					if (aux != null){
						destino = dijkstra.getShortestPathInfo(aux);
					}
				} while(aux != null);
				
				while(!pilha.isEmpty()){
				
					System.out.print(pilha.pop().getIndex() + " ");
				}
				
				System.out.println("tamanho=" + tamanho );
				
				break;
				
			case "hasNegativeCicle":
				dijkstra.computeShortestPaths(dijkstra.g.adj[0].thisVertex);
				System.out.println(!dijkstra.hasNoNegativeWeightCycle());
				break;
			}
			linha = in.readLine();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		String nome_arquivo="";
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Informe o nome do arquivo");
		nome_arquivo = scanner.nextLine();
		
		
		arquivos.add(caminhoArquivo + nome_arquivo);
		in = new BufferedReader(new FileReader(arquivos.get(0)));
		String card = in.readLine();
		GrafoListaAdjacenciaPesada grafo = new GrafoListaAdjacenciaPesada(Integer.parseInt(card), true);
		for (int i = 0; i < Integer.parseInt(card); i++){
			grafo.addVertex(i, Integer.toString(i));
		}
		MainDijkstra a = new MainDijkstra(grafo);
		a.leArquivo();
		scanner.close();
	}
	

}
