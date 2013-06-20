package grafo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conjuntos.DisjointSetForest;

/**
 * 
 * @author Anderson Rodrigues
 */
public class MainDisjointSet {
	private static final String caminhoArquivo = System.getProperty("user.dir") + "/src/testes/conjuntos/";
	private static List<String> arquivos = new ArrayList<String>();
	static BufferedReader in;
	
	protected DisjointSetForest disjointSet;
	Object handle[];
	
	public MainDisjointSet(DisjointSetForest set, Object handle[]) throws FileNotFoundException {
		
		disjointSet = set;
		this.handle = handle;
		
	}
	
	public void leArquivo() throws IOException{
		String linha = in.readLine();
		while (linha != null) {
			String palavras[] = linha.split(" ");
			
			switch(palavras[0]){
			case "union":
				disjointSet.union(handle[Integer.parseInt(palavras[1])], handle[Integer.parseInt(palavras[2])]);
				System.out.println("-");
				break;
				
			case "compare":
				System.out.println(disjointSet.findSet(handle[Integer.parseInt(palavras[1])])
							.equals(disjointSet.findSet(handle[Integer.parseInt(palavras[2])])));
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
		
		DisjointSetForest set = new DisjointSetForest();
		Object handle[] = new Object[Integer.parseInt(card)];
		for (int i = 0; i < handle.length; i++){
			handle[i] = set.makeSet(i);
		}
		
		System.out.println("-");
		MainDisjointSet a = new MainDisjointSet(set, handle);
		a.leArquivo();
		scanner.close();
	}
	

}
