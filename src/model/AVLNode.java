package model;

public class AVLNode <K extends Comparable <K>,V>implements Comparable<AVLNode<K,V>>{
	
	private K key;
	private V value;
	private boolean looked;
	

	//Enlaces
	private AVLNode<K, V> dad;
	private AVLNode<K, V> left;
	private AVLNode<K, V> right;
	
	
	public AVLNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	
	public AVLNode<K, V> getDad() {
		return dad;
	}

	public void setDad(AVLNode<K, V> node) {
		dad = node;
		
	}

	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public AVLNode<K, V> getLeft() {
		return left;
	}
	public void setLeft(AVLNode<K, V> left) {
		this.left = left;
	}
	public AVLNode<K, V> getRight() {
		return right;
	}
	public void setRight(AVLNode<K, V> right) {
		this.right = right;
	}
	
	public boolean isLooked() {
		return looked;
	}



	public void setLooked(boolean looked) {
		this.looked = looked;
	}
	
	public int calculateHeightR() {
		if(right==null) {
			return 0;
		}
		
		return 1+right.calculateHeightR();
	}
	
	public int calculateHeightL() {
		if(left==null) {
			return 0;
		}
		
		return 1+left.calculateHeightL();
	}
	
	public int getBalance() {
		return calculateHeightR()-calculateHeightL();
	}
	
	@Override
	public int compareTo(AVLNode<K,V> o) {
		// TODO Auto-generated method stub
		AVLNode<K,V> A = this;
		AVLNode<K,V> B = o;
		
		//int output = A.lastName.compareTo(B.lastName);
		int output = A.getKey().compareTo(B.getKey());
		return output;
	}
}
