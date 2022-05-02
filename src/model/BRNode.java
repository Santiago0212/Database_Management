package model;

public class BRNode<K extends Comparable<K>,V> extends AVLNode<K,V>{
	
	private Color color;
	
	public BRNode(K key, V value) {
		super(key, value);
		color = Color.RED;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
