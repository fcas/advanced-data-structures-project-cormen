package conjuntos;

public interface IConjuntoDisjunto {

	public Object makeSet(Object x);

	public void union(Object x, Object y);

	public Object findSet(Object x);
}