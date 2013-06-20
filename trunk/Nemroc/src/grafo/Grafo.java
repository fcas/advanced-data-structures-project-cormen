package grafo;

import java.util.Iterator;

public interface Grafo {
	public Vertice addVertex(String name);

	public Vertice addVertex(int index, String name);

	public Vertice addVertex(Vertice v);

	public Vertice getVertex(int index);

	public void addEdge(Vertice u, Vertice v);

	public void addEdge(int u, int v);

	public Iterator vertexIterator();

	public Iterator edgeIterator(Vertice u);

	public Iterator edgeIterator(int u);

	public int getCardV();

	public int getCardE();

	public boolean isDirected();
}