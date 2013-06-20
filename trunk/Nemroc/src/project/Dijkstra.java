package project;

import java.util.Iterator;

public class Dijkstra extends SingleSourceShortestPaths {

	public Dijkstra(WeightedAdjacencyListGraph theGraph) {
		super(theGraph);
	}

	public void computeShortestPaths(Vertex s) {
		initializeSingleSource(s);

		MinPriorityQueue q = new MinHeapPriorityQueue();

		int cardV = g.getCardV();
		for (int i = 0; i < cardV; i++) {
			DijkstraInfo info = (DijkstraInfo) getShortestPathInfo(i);
			info.theVertex = g.getVertex(i);
			info.handle = q.insert(info);
		}

		q.decreaseKey(((DijkstraInfo) getShortestPathInfo(s)).handle,
				new Double(0));

		while (!q.isEmpty()) {
			DijkstraInfo uInfo = (DijkstraInfo) q.extractMin();
			uInfo.handle = null;
			Vertex u = uInfo.theVertex;
			double du = getShortestPathInfo(u).getEstimate();

			WeightedEdgeIterator edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertex v = (Vertex) edgeIter.next();
				DijkstraInfo vInfo = (DijkstraInfo) getShortestPathInfo(v);
				double weight = edgeIter.getWeight();
				if (vInfo.relax(u, du, weight)) {

					q.decreaseKey(vInfo.handle, new Double(vInfo.getEstimate()));
				}
			}
		}
	}

	protected ShortestPathInfo newShortestPathInfo() {
		return new DijkstraInfo();
	}

	private static class DijkstraInfo extends ShortestPathInfo implements
			DynamicSetElement {

		public Vertex theVertex;

		public Object handle;

		public DijkstraInfo() {
			super();
		}

		public void setKey(Comparable key) {
			setEstimate(((Double) key).doubleValue());
		}

		public Comparable getKey() {
			return new Double(getEstimate());
		}

		public int compareTo(Object e) {
			return DynamicSetElement.Helper.compareTo(this, e);
		}
	}
}