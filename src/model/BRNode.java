package model;

public class BRNode<K extends Comparable<K>,V> extends AVLNode<K,V>{
	
	private Color color;
	private BRNode<K, V> dad;
	private BRNode<K, V> uncle;
	private BRNode<K, V> left;
	private BRNode<K, V> right;
	
	public BRNode(K key, V value) {
		super(key, value);
		color = Color.RED;
	}
	
	@Override
	public BRNode<K, V> getDad() {
		return dad;
	}
	
	public void setDad(BRNode<K, V> dad) {
		this.dad = dad;
		if(dad!=null) {
			if(dad.getDad()!=null) {
				if(dad.getDad().getRight()==dad)
					this.uncle = dad.getDad().getLeft();
				else
					this.uncle= dad.getDad().getRight();
			}
		}
	}

	public BRNode<K, V> getUncle() {
		return uncle;
	}

	public void setUncle(BRNode<K, V> uncle) {
		this.uncle = uncle;
	}

	public BRNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BRNode<K, V> left) {
		this.left = left;
	}

	public BRNode<K, V> getRight() {
		return right;
	}

	public void setRight(BRNode<K, V> right) {
		this.right = right;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}