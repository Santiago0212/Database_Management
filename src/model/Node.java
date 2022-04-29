package model;

public class Node <K,V>{
	
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
	
	public void insert(Node node) {
		if((Integer) node.getKey()< (Integer)this.key) {
			//insertar a la izquierda
			if(this.left == null) {
				this.left = node;
				node.setDad(this);
			}else {
				this.left.insert(node);
			}
			
		}else {
			//insertar a la derecha
			if(this.right == null) {
				this.right = node;
				node.setDad(this);
			}else {
				this.right.insert(node);
			}
		}
	}
	
	public Node<K, V> getDad() {
		return dad;
	}

	private void setDad(Node<K, V> node) {
		// TODO Auto-generated method stub
		
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
	
	public int calculateWeightR() {
		if(right==null) {
			return 0;
		}
		
		return 1+right.calculateWeightR();
	}
	
	public int calculateWeightL() {
		if(left==null) {
			return 0;
		}
		
		return 1+left.calculateWeightL();
	}
	
	public int balanced() {
		return calculateWeightL()-calculateWeightR();
	}
}
