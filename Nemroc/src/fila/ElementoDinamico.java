package fila;

public interface ElementoDinamico extends Comparable {
	public void setKey(Comparable key);

	public Comparable getKey();

	public int compareTo(Object e);

	public static class Helper {
		public static int compareTo(ElementoDinamico e, Object o) {
			if (o instanceof ElementoDinamico)
				return e.getKey().compareTo(((ElementoDinamico) o).getKey());
			else if (o instanceof Comparable)
				return e.getKey().compareTo(o);
			else
				throw new ClassCastException(
						"Tentativa de comparar um Elemento Dinamico com um objeto que nao eh comparavel.");
		}

		public static ElementoDinamico cast(Object o) {
			if (o instanceof ElementoDinamico)
				return (ElementoDinamico) o;
			else
				throw new ClassCastException(
						"Objeto nao implementa a interface DynamicSetElement.");
		}
	}
}