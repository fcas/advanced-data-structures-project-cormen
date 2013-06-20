package fila;

public interface DynamicSetElement extends Comparable {
	public void setKey(Comparable key);

	public Comparable getKey();

	public int compareTo(Object e);

	public static class Helper {
		public static int compareTo(DynamicSetElement e, Object o) {
			if (o instanceof DynamicSetElement)
				return e.getKey().compareTo(((DynamicSetElement) o).getKey());
			else if (o instanceof Comparable)
				return e.getKey().compareTo(o);
			else
				throw new ClassCastException(
						"Tentativa de comparar um Elemento Dinamico com um objeto que nao eh comparavel.");
		}

		public static DynamicSetElement cast(Object o) {
			if (o instanceof DynamicSetElement)
				return (DynamicSetElement) o;
			else
				throw new ClassCastException(
						"Objeto nao implementa a interface DynamicSetElement.");
		}
	}
}