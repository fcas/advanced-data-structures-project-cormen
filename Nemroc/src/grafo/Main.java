/************************************************************************
 *
 * 1. This software is for the purpose of demonstrating one of many
 * ways to implement the algorithms in Introduction to Algorithms,
 * Second edition, by Thomas H. Cormen, Charles E. Leiserson, Ronald
 * L. Rivest, and Clifford Stein.  This software has been tested on a
 * limited set of test cases, but it has not been exhaustively tested.
 * It should not be used for mission-critical applications without
 * further testing.
 *
 * 2. McGraw-Hill licenses and authorizes you to use this software
 * only on a microcomputer located within your own facilities.
 *
 * 3. You will abide by the Copyright Law of the United States.
 *
 * 4. You may prepare a derivative version of this software provided
 * that your source code indicates that it is based on this software and
 * also that you have made changes to it.
 *
 * 5. If you believe that you have found an error in this software,
 * please send email to clrs-java-bugs@mhhe.com.  If you have a
 * suggestion for an improvement, please send email to
 * clrs-java-suggestions@mhhe.com.
 *
 ***********************************************************************/

// MSTTest.java
// Tests MST algorithms by emulating the example in Figure 23.4 on
// pages 568-569 of Introduction to Algorithms, Second edition.
package grafo;


import java.util.Iterator;

import agm.Kruskal;
import agm.Prim;

public class Main
{
    public static void main(String[] args)
    {
	// Make an undirected graph.
	GrafoListaAdjacenciaPesada graph =
	    new GrafoListaAdjacenciaPesada(9, false);

	Vertice a = new Vertice("a");
	Vertice b = new Vertice("b");
	Vertice c = new Vertice("c");
	Vertice d = new Vertice("d");
	Vertice e = new Vertice("e");
	Vertice f = new Vertice("f");
	Vertice g = new Vertice("g");
	Vertice h = new Vertice("h");
	Vertice i = new Vertice("i");

	graph.addVertex(a);
	graph.addVertex(b);
	graph.addVertex(c);
	graph.addVertex(d);
	graph.addVertex(e);
	graph.addVertex(f);
	graph.addVertex(g);
	graph.addVertex(h);
	graph.addVertex(i);

	graph.addEdge(a, b, 4);
	//graph.addEdge(a, h, 8);
	graph.addEdge(b, c, 3);
	//graph.addEdge(b, h, 11);
	graph.addEdge(c, d, 7);
	//graph.addEdge(c, a, 3);
	graph.addEdge(c, f, 4);
	graph.addEdge(c, i, 2);
	graph.addEdge(d, e, 9);
	graph.addEdge(d, f, 14);
	graph.addEdge(e, f, 2);
	graph.addEdge(f, g, 2);
	//graph.addEdge(g, h, 1);
	graph.addEdge(g, i, 6);
	//graph.addEdge(h, i, 7);

	

	System.out.println("Graph:");
	System.out.println(graph);

	GrafoListaAdjacenciaPesada kruskalMST =
	    (new Kruskal()).computeMST(graph);

	System.out.println("MST computed by Kruskal's algorithm:");
	System.out.println(kruskalMST);

	GrafoListaAdjacenciaPesada primMST =
	    (new Prim()).computeMST(graph);

	System.out.println("MST computed by Prim's algorithm:");
	System.out.println(primMST);
	
	Iterator ite = primMST.edgeIterator(2);
	while (ite.hasNext()) {
		Vertice v = (Vertice) ite.next();
		v.getIndex();
	}
    }
}


