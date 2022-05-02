package model;

public class Tree<K extends Comparable <K>,V> {

	protected Node<K,V> root;
	private final static int COUNT = 15;

	public void insert(K key, V value) {
		Node<K, V> node = new Node<K, V>(key,value);
		
		if(root == null) {
			root = node;
		} else {
			insert(node, root);
			autoBalance(node);
		}

	}
	
	private void insert(Node<K, V> node, Node<K, V> current) {
		
		if(node.compareTo(current)<=-1) {
			if(current.getLeft() == null) {
				current.setLeft(node);
				node.setDad(current);
				return;
			}
			insert(node,current.getLeft());
		} else if(node.compareTo(current)>=1) {
			if(current.getRight() == null) {
				current.setRight(node);
				node.setDad(current);
				return;
			}
			insert(node,current.getRight());
		} else {
			throw new IllegalArgumentException("The key is already in the tree");
		}
	}

	// El activador del método recursivo
	public void triggerInorder() {
		inorder(root);
	}

	// Recursivo
	public void inorder(Node<K, V> node) {
		// Caso base
		if (node == null) {
			return;
		}
		// Recursivo

		inorder(node.getLeft());
		System.out.println(node.getKey());
		inorder(node.getRight());
	}

	public Node<K, V> triggerSearch(int key) {
		return search(root, key);
	}

	// Recursivo
	public Node<K, V> search(Node<K, V> node, int key) {
		// Caso base
		if (node == null) {
			return null;
		}

		if ((Integer)key == node.getKey()) {
			return node;
		}
		// Procedimiento recursivo
		if ((Integer) key < (Integer) node.getKey()) {
			return search(node.getLeft(), key);
		} else {
			return search(node.getRight(), key);
		}

	}

	
	public void triggerMaxLevel() {
		int level = getMaxLevel(root, 1);
		System.out.println(level);
	}

	public int getMaxLevel(Node<K, V> node, int level) {

		if (node == null) {
			return level-1;
		} else {

			int A = getMaxLevel(node.getLeft(), level + 1);
			int B = getMaxLevel(node.getRight(), level + 1);

			return Math.max(A, B);
		}

	}
	
	public void print() {
		print(root,1);
	}
	
	private void print(Node<K, V> root, int space)
	{
	    // Base case
	    if (root == null)
	        return;
	 
	    // Increase distance between levels
	    space += COUNT;
	 
	    // Process right child first
	    print(root.getRight(), space);
	 
	    // Print current node after space
	    // count
	    System.out.print("\n");
	    for (int i = COUNT; i < space; i++)
	        System.out.print(" ");
	    System.out.print(root.getKey() + "\n");
	 
	    // Process left child
	    print(root.getLeft(), space);
	}//codigo tomado de : https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
	
	public Node<K, V> getMin(Node<K, V> current){
		if (current.getLeft() == null) {
			return current;
		} else {
			return getMin(current.getLeft());
		}
	}
	
	public Node<K, V> getMax(Node<K, V> current) {
		if (current.getRight() == null) {
			return current;
		}else {
			return getMax(current.getRight());
		}
	}
	
	public void triggerDelete(int key) {
		if (root != null){
			root = delete(root, key);
		}
	}
	
	public Node<K, V> delete(Node<K, V> current, int key){
		
		if ((Integer) current.getKey() == key){
			if (current.getLeft() == null && 
					current.getRight() == null){
				return null;
			} else if (current.getLeft() != null && 
					current.getRight() != null) {
				Node<K, V> succesor = getMin(current.getRight());
				Node<K, V> newRightTree = delete(current.getRight(), (Integer)succesor.getKey());
				
				succesor.setLeft(current.getLeft());
				succesor.setRight(newRightTree);

				return succesor;
			} else if (current.getLeft() != null) {
				return current.getLeft();
			} else {
				return current.getRight();
			}
			
		} else if (key < (Integer)current.getKey()){
			Node<K, V> newLeftTree = delete(current.getLeft(), key);
			current.setLeft(newLeftTree);
		} else {
			Node<K, V> newRightTree = delete(current.getRight(), key);
			current.setRight(newRightTree);
		}
		
		return current;
	}
	
	/*public String print() {
		triggerInorder();
		String out = " "+root.getValue()+"\n"+print(root);
		return out;
	}*/
	
	public String print(Node<K, V> node) {
		String out = "";
		boolean left = false;
		boolean right = false;
		
		if(node.getLeft()!=null) {	
			out += node.getLeft().getKey()+" ";
			left=true;
		}else {
			out+="  ";
		}
		
		if(node.getRight()!=null) {
			out += " "+node.getRight().getKey();
			right = true;
		}else {
			out+="  ";
		}
			
		if(right&&left) {
				out += "\n"+print(node.getLeft());
				out += "\n"+print(node.getRight());
		}else if(right) {
			out += "\n"+print(node.getRight());
		}else if(left){
			out += "\n"+print(node.getLeft());
		}
		
		return out;
	}
	
	public Node<K, V> getRoot() {
		return root;
	}

	public void setRoot(Node<K, V> root) {
		this.root = root;
	}

	public int height(Node<K, V> root2) {
		return root2.calculateHeightR();
	}

	public void autoBalance(Node<K, V> current) {
		
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
 

		
		autoBalance(current.getDad());
	}
	
	public Node<K, V> balance(Node<K, V> node) {
		
		int nodeBalance = getBalance(node);
		System.out.println("Key: "+node.getKey()+" Balance: "+nodeBalance);
		
		if(nodeBalance == 2) {
			Node<K, V> nodeRight = node.getRight();
			int nodeRightBalance = getBalance(nodeRight);
			

			if(nodeRightBalance == 1 || nodeRightBalance == 0) {
				node = leftRotate(node);
			} else if(nodeRightBalance == -1) {
				node.setRight(rightRotate(nodeRight));
				node = leftRotate(node);
			}
			
		} else if(nodeBalance == -2) {
			Node<K, V> nodeLeft = node.getLeft();
			int nodeLeftBalance = getBalance(nodeLeft);


			if(nodeLeftBalance == -1 || nodeLeftBalance == 0) {
				node = rightRotate(node);
			} else if(nodeLeftBalance == 1) {
				node.setLeft(leftRotate(nodeLeft));
				node = rightRotate(node);
			}
		}
		
		return node;
	}
	
	protected Node<K, V> leftRotate(Node<K, V> node) {
		
		Node<K, V> right = node.getRight();
		
		if(node == root) {
			root = right;
			
		}
		
		node.setRight(right.getLeft());
		right.setLeft(node);
		right.setDad(node.getDad());
		node.setDad(right);
		
		return right;
	}
	
	protected Node<K, V> rightRotate(Node<K, V> node) {
		
		Node<K, V> left = node.getLeft();
		
		if(node == root) {
			root = left;
		}
		
		node.setLeft(left.getRight());
		left.setRight(node);
		left.setDad(node.getDad());
		node.setDad(left);
		
		
		
		return left;
	}
	
	public int getHeight(Node<K, V> node) {
		if(node == null) {
			return 0;
		}
		return 1 + (Math.max(getHeight(node.getLeft()),getHeight(node.getRight())));
	}
	
	public int getBalance(Node<K, V> node) {
		Node<K, V> left = node.getLeft();
		Node<K, V> right = node.getRight();
		
		return getHeight(right) - getHeight(left);
	}

}
