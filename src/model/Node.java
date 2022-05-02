package model;

public class Node <K extends Comparable <K>,V>implements Comparable<Node<K,V>>{
	
	private K key;
	private V value;
	
	
	//Enlaces
	private Node<K, V> dad;
	private Node<K, V> left;
	private Node<K, V> right;
	
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	
	public Node<K, V> getDad() {
		return dad;
	}

	public void setDad(Node<K, V> node) {
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
	public Node<K, V> getLeft() {
		return left;
	}
	public void setLeft(Node<K, V> left) {
		this.left = left;
	}
	public Node<K, V> getRight() {
		return right;
	}
	public void setRight(Node<K, V> right) {
		this.right = right;
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
	public int compareTo(Node<K,V> o) {
		// TODO Auto-generated method stub
		Node<K,V> A = this;
		Node<K,V> B = o;
		
		//int output = A.lastName.compareTo(B.lastName);
		int output = A.getKey().compareTo(B.getKey());
		return output;
	}
}
