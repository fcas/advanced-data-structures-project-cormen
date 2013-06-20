package project;

import java.util.Iterator;

public interface Graph {
	public Vertex addVertex(String name);

	public Vertex addVertex(int index, String name);

	public Vertex addVertex(Vertex v);

	public Vertex getVertex(int index);

	public void addEdge(Vertex u, Vertex v);

	public void addEdge(int u, int v);

	public Iterator vertexIterator();

	public Iterator edgeIterator(Vertex u);

	public Iterator edgeIterator(int u);

	public int getCardV();

	public int getCardE();

	public boolean isDirected();
}