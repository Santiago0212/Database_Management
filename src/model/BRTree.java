package model;

public class BRTree<K extends Comparable<K>,V> extends Tree<K,V>{
	BRNode<K,V> root;
	@Override
	public void insert(K key, V value) {
		BRNode<K,V> node = new BRNode<K,V>(key,value);
		
		if(root == null) {
			root = node;
			root.setColor(Color.RED);
		} else {
			insertBR(node, root);
			autoBalanceBR(node.getDad());
		}

	}
	protected void insertBR(BRNode<K, V> node, BRNode<K, V> current) {
		
		if(node.compareTo(current)<=-1) {
			if(current.getLeft() == null) {
				current.setLeft(node);
				node.setDad(current);
				return;
			}
			insertBR(node,current.getLeft());
		} else if(node.compareTo(current)>=1) {
			if(current.getRight() == null) {
				current.setRight(node);
				node.setDad(current);
				return;
			}
			insertBR(node,current.getRight());
		} else {
			throw new IllegalArgumentException("The key is already in the tree");
		}
	}
	
	public void autoBalanceBR(BRNode<K, V> current) {
		
		if(current == null) {
			return;
		} if(current.getDad() == null) {
			balance(root);
			return;
		} 
		
		if(current.getDad().getLeft() != null) {
			if(current == current.getDad().getLeft()) {
				current.getDad().setLeft(balance(current));
			} 
		} if(current.getDad().getRight() != null) {
			if(current == current.getDad().getRight()) {
				current.getDad().setRight(balance(current));
			}
		}
 

		
		autoBalanceBR(current.getDad());
	}
	@Override
	public BRNode<K, V> triggerSearch(K key) {
		return searchBR(root, key);
	}

	// Recursivo
	public BRNode<K, V> searchBR(BRNode<K, V> node, K key) {
		// Caso base
		if (node == null) {
			return null;
		}

		if (key == node.getKey()) {
			return node;
		}
		// Procedimiento recursivo
		if (key.compareTo(node.getKey())>1) {
			return searchBR(node.getLeft(), key);
		} else {
			return searchBR(node.getRight(), key);
		}

	}
	public BRNode<K, V> balanceBR(BRNode<K, V> node) {
		
		//System.out.println("Key: "+node.getKey()+" Balance: "+nodeBalance);
		if(node==null) {
			return null;
		}
		
		BRNode<K, V> dad = node.getDad();
		BRNode<K, V> uncle = node.getUncle();
		
		if(dad.getColor()==Color.RED) {
			if(node.getUncle()!=null&uncle.getColor()==Color.RED) {
				dad.setColor(Color.BLACK);
				uncle.setColor(Color.BLACK);
				dad.getDad().setColor(Color.RED);
				balanceBR(dad.getDad());
			}else {
				if(dad.getRight()==node) {
					leftRotateBR(dad);
					balanceBR(dad.getLeft());
				}
				else {
					rightRotateBR(dad);
					balanceBR(dad.getRight());
				}
			}
		}
		
		return node;
	}
	
protected BRNode<K, V> leftRotateBR(BRNode<K, V> node) {
		
	BRNode<K, V> right = node.getRight();
		
		if(node == root) {
			root = right;
			root.setColor(Color.BLACK);
		}
		
		node.setRight(right.getLeft());
		right.setLeft(node);
		right.setDad(node.getDad());
		node.setDad(right);
		
		return right;
	}
	
	protected BRNode<K, V> rightRotateBR(BRNode<K, V> node) {
		
		BRNode<K, V> left = node.getLeft();
		
		if(node == root) {
			root = left;
			root.setColor(Color.BLACK);
		}
		
		node.setLeft(left.getRight());
		left.setRight(node);
		left.setDad(node.getDad());
		node.setDad(left);
		
		
		
		return left;
	}
	
	@Override
	public void print() {
		printBR(root,1);
	}
	
	protected void printBR(Node<K, V> root, int space)
	{
	    // Base case
	    if (root == null)
	        return;
	 
	    // Increase distance between levels
	    space += 10;
	 
	    // Process right child first
	    printBR(root.getRight(), space);
	 
	    // Print current node after space
	    // count
	    System.out.print("\n");
	    for (int i = 10; i < space; i++)
	        System.out.print(" ");
	    System.out.print(root.getKey() + "\n");
	 
	    // Process left child
	    printBR(root.getLeft(), space);
	}
}
