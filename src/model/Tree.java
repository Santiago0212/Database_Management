package model;

public class Tree {

	private Node root;
	private final static int COUNT = 10;

	public void add(Node node) {
		if (root == null) {
			root =node ;
		} else {
			root.insert(node);
			autobalance(node);
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
	}
	
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

	public int weight(Node root2) {
		return root2.calculateWeightR();
	}

	
	public Node autobalance(Node current) {
		if(current==null) {
			return null;
		}
		int balance = current.balanced();
		
		Node dad=null;

		switch (balance) {
			case 2: 
				
				dad = current.getLeft();
				
				if(dad.getRight()!=null) {
					current.setLeft(dad.getRight());
				}else {
					current.setLeft(null);
				}
				dad.setRight(current);
				if(current==root) {
					root=dad;
				}
				break;
			case -2:
				 dad = current.getRight();
				
				if(dad.getLeft()!=null) {
					current.setRight(dad.getLeft());
				}else {
					current.setRight(null);
				}
				dad.setLeft(current);
				if(current==root) {
					root=dad;
				}
				break;
			}
		return autobalance(current.getDad());
		
	}

}
