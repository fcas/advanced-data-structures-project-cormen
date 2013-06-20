package project;

/**
 * 
 * @author Anderson Rodrigues
 */
public class HeapInfo implements DynamicSetElement{

	String name;
	int key;
	
	public HeapInfo(String name, int key) {
		this.name = name;
		this.key = key;
	}
	
	
	@Override
	public void setKey(Comparable key) {
		this.key = (Integer) key;
	}

	@Override
	public Comparable getKey() {
		return key;
	}

	@Override
	public int compareTo(Object e) {
		return DynamicSetElement.Helper.compareTo(this, e);
	}
	
	@Override
	public String toString() {
		return name + " " + Integer.toString(key);
	}

}
