package model;

public class Tree<K,V> {

	private Node<K,V> root;
	private final static int COUNT = 15;

	public void insert(K key, V value) {
		Node node = new Node(key,value);
		
		if(root == null) {
			root = node;
		} else {
			insert(node, root);
			autoBalance(node);
		}

	}
	
	private void insert(Node node, Node current) {
		
		if((Integer) node.getKey() < (Integer) current.getKey()) {
			if(current.getLeft() == null) {
				current.setLeft(node);
				node.setDad(current);
				return;
			}
			insert(node,current.getLeft());
		} else if((Integer) node.getKey() > (Integer) current.getKey()) {
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
	public void inorder(Node node) {
		// Caso base
		if (node == null) {
			return;
		}
		// Recursivo
		System.out.println(node.getValue());

		inorder(node.getLeft());
		inorder(node.getRight());
	}

	public Node triggerSearch(int key) {
		return search(root, key);
	}

	// Recursivo
	public Node search(Node node, int key) {
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

	public int getMaxLevel(Node node, int level) {

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
	
	private void print(Node root, int space)
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
	
	public Node getMin(Node current){
		if (current.getLeft() == null) {
			return current;
		} else {
			return getMin(current.getLeft());
		}
	}
	
	public Node getMax(Node current) {
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
	
	public Node delete(Node current, int key){
		
		if ((Integer) current.getKey() == key){
			if (current.getLeft() == null && 
					current.getRight() == null){
				return null;
			} else if (current.getLeft() != null && 
					current.getRight() != null) {
				Node succesor = getMin(current.getRight());
				Node newRightTree = delete(current.getRight(), (Integer)succesor.getKey());
				
				succesor.setLeft(current.getLeft());
				succesor.setRight(newRightTree);

				return succesor;
			} else if (current.getLeft() != null) {
				return current.getLeft();
			} else {
				return current.getRight();
			}
			
		} else if (key < (Integer)current.getKey()){
			Node newLeftTree = delete(current.getLeft(), key);
			current.setLeft(newLeftTree);
		} else {
			Node newRightTree = delete(current.getRight(), key);
			current.setRight(newRightTree);
		}
		
		return current;
	}
	
	/*public String print() {
		triggerInorder();
		String out = " "+root.getValue()+"\n"+print(root);
		return out;
	}*/
	
	public String print(Node node) {
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
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int height(Node root2) {
		return root2.calculateHeightR();
	}

	public void autoBalance(Node current) {
		if(current == null) {
			return;
		}
		balance(current);
		autoBalance(current.getDad());
	}
	
	public Node balance(Node node) {
		
		int nodeBalance = getBalance(node);
		System.out.println("Balance: "+nodeBalance);
		
		if(nodeBalance == 2) {
			Node nodeRight = node.getRight();
			int nodeRightBalance = getBalance(nodeRight);
			

			if(nodeRightBalance == 1 || nodeRightBalance == 0) {
				node = leftRotate(node);
			} else if(nodeRightBalance == -1) {
				node.setRight(rightRotate(nodeRight));
				node = leftRotate(node);
			}
			
		} else if(nodeBalance == -2) {
			Node nodeLeft = node.getLeft();
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
	
	private Node leftRotate(Node node) {
		
		Node right = node.getRight();
		
		if(node == root) {
			root = right;
		}
		
		node.setRight(right.getLeft());
		right.setLeft(node);

		return right;
	}
	
	private Node rightRotate(Node node) {
		
		Node left = node.getLeft();
		
		if(node == root) {
			root = left;
		}
		
		node.setLeft(left.getRight());
		left.setRight(node);
		
		
		
		return left;
	}
	
	public int getHeight(Node node) {
		if(node == null) {
			return 0;
		}
		return 1 + (Math.max(getHeight(node.getLeft()),getHeight(node.getRight())));
	}
	
	public int getBalance(Node node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
		return getHeight(right) - getHeight(left);
	}

}
