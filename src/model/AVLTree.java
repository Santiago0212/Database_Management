package model;

import java.util.ArrayList;

public class AVLTree<K extends Comparable <K>,V> {

	protected AVLNode<K,V> root;
	public int filt;
	private final static int COUNT = 15;
	private Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};

	public void insert(K key, V value) {
		AVLNode<K, V> AVLNode = new AVLNode<K, V>(key,value);
		
		if(root == null) {
			root = AVLNode;
		} else {
			insert(AVLNode, root);
			autoBalance(AVLNode.getDad());
		}

	}
	
	
	public void filt(int op) {
		AVLTree<Character,BRTree<Integer,Person>> newTree = createTree();
		
		int k=0;
		
		filt=op;
			for(Character c : alphabet) {
				BRTree<Integer, Person> addingTree = newTree.triggerSearch(c).getValue();
		
				ArrayList<Person> persons = searchSim(c,root,op);
						
				for(Person p : persons) {
					addingTree.insert(k, p);
					k++;
				}
			}
		
		
		this.root=(AVLNode<K, V>) newTree.getRoot();
				
	}
		
	private ArrayList<Person> searchSim(Character c, AVLNode<K, V> root2,int op) {
		return searchSim( c,  root2, op,new ArrayList<Person>());
	}
	
	private ArrayList<Person> searchSim(Character c, AVLNode<K, V> root2,int op,ArrayList<Person> persons) {
		if (root2 != null) {
		
		// Recursivo

		searchSim(c,root2.getLeft(),op,persons);
		persons.addAll(((BRTree<K, V>)root2.getValue()).findPersons(c,op));
		searchSim(c,root2.getRight(),op,persons);
		}
		return persons;

	}
	
	protected void insert(AVLNode<K, V> AVLNode, AVLNode<K, V> current) {
		
		if(AVLNode.compareTo(current)<=-1) {
			if(current.getLeft() == null) {
				current.setLeft(AVLNode);
				AVLNode.setDad(current);
				return;
			}
			insert(AVLNode,current.getLeft());
		} else if(AVLNode.compareTo(current)>=1) {
			if(current.getRight() == null) {
				current.setRight(AVLNode);
				AVLNode.setDad(current);
				return;
			}
			insert(AVLNode,current.getRight());
		} else {
			throw new IllegalArgumentException("The key is already in the tree");
		}
	}

	// El activador del método recursivo
	public void triggerInorder() {
		inorder(root);
	}

	// Recursivo
	public void inorder(AVLNode<K, V> AVLNode) {
		// Caso base
		if (AVLNode == null) {
			return;
		}
		// Recursivo

		inorder(AVLNode.getLeft());
		System.out.println(AVLNode.getKey());
		inorder(AVLNode.getRight());
	}

	public AVLNode<K, V> triggerSearch(K key) {
		return search(root, key);
	}
	
	private  AVLTree<Character, BRTree<Integer, Person>> createTree() {
		AVLTree<Character,BRTree<Integer,Person>> newTree = new AVLTree<Character,BRTree<Integer,Person>>();
		for(Character c: alphabet)
		newTree.insert(c,new BRTree<Integer,Person>());
		return newTree;
	}
	
	// Recursivo
	public AVLNode<K, V> search(AVLNode<K, V> AVLNode, K key) {
		// Caso base
		if (AVLNode == null) {
			return null;
		}

		if (key == AVLNode.getKey()) {
			return AVLNode;
		}
		// Procedimiento recursivo
		if (key.compareTo(AVLNode.getKey())<=-1) {
			return search(AVLNode.getLeft(), key);
		} else {
			return search(AVLNode.getRight(), key);
		}

	}

	
	public void triggerMaxLevel() {
		int level = getMaxLevel(root, 1);
		System.out.println(level);
	}

	public int getMaxLevel(AVLNode<K, V> AVLNode, int level) {

		if (AVLNode == null) {
			return level-1;
		} else {

			int A = getMaxLevel(AVLNode.getLeft(), level + 1);
			int B = getMaxLevel(AVLNode.getRight(), level + 1);

			return Math.max(A, B);
		}

	}
	
	public void print() {
		print(root,1);
	}
	
	protected void print(AVLNode<K, V> root, int space)
	{
	    // Base case
	    if (root == null)
	        return;
	 
	    // Increase distance between levels
	    space += COUNT;
	 
	    // Process right child first
	    print(root.getRight(), space);
	 
	    // Print current AVLNode after space
	    // count
	    System.out.print("\n");
	    for (int i = COUNT; i < space; i++)
	        System.out.print(" ");
	    System.out.print(root.getKey()+" "+root.getValue() + "\n");
	 
	    // Process left child
	    print(root.getLeft(), space);
	}//codigo tomado de : https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
	
	public AVLNode<K, V> getMin(AVLNode<K, V> current){
		if (current.getLeft() == null) {
			return current;
		} else {
			return getMin(current.getLeft());
		}
	}
	
	public AVLNode<K, V> getMax(AVLNode<K, V> current) {
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
	
	public AVLNode<K, V> delete(AVLNode<K, V> current, int key){
		
		if ((Integer) current.getKey() == key){
			if (current.getLeft() == null && 
					current.getRight() == null){
				return null;
			} else if (current.getLeft() != null && 
					current.getRight() != null) {
				AVLNode<K, V> succesor = getMin(current.getRight());
				AVLNode<K, V> newRightTree = delete(current.getRight(), (Integer)succesor.getKey());
				
				succesor.setLeft(current.getLeft());
				succesor.setRight(newRightTree);

				return succesor;
			} else if (current.getLeft() != null) {
				return current.getLeft();
			} else {
				return current.getRight();
			}
			
		} else if (key < (Integer)current.getKey()){
			AVLNode<K, V> newLeftTree = delete(current.getLeft(), key);
			current.setLeft(newLeftTree);
		} else {
			AVLNode<K, V> newRightTree = delete(current.getRight(), key);
			current.setRight(newRightTree);
		}
		
		return current;
	}
	
	/*public String print() {
		triggerInorder();
		String out = " "+root.getValue()+"\n"+print(root);
		return out;
	}*/
	
	public String print(AVLNode<K, V> AVLNode) {
		String out = "";
		boolean left = false;
		boolean right = false;
		
		if(AVLNode.getLeft()!=null) {	
			out += AVLNode.getLeft().getKey()+" ";
			left=true;
		}else {
			out+="  ";
		}
		
		if(AVLNode.getRight()!=null) {
			out += " "+AVLNode.getRight().getKey();
			right = true;
		}else {
			out+="  ";
		}
			
		if(right&&left) {
				out += "\n"+print(AVLNode.getLeft());
				out += "\n"+print(AVLNode.getRight());
		}else if(right) {
			out += "\n"+print(AVLNode.getRight());
		}else if(left){
			out += "\n"+print(AVLNode.getLeft());
		}
		
		return out;
	}
	
	public AVLNode<K, V> getRoot() {
		return root;
	}

	public void setRoot(AVLNode<K, V> root) {
		this.root = root;
	}

	public int height(AVLNode<K, V> root2) {
		return root2.calculateHeightR();
	}

	public void autoBalance(AVLNode<K, V> current) {
		
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
	
	public AVLNode<K, V> balance(AVLNode<K, V> AVLNode) {
		
		int AVLNodeBalance = getBalance(AVLNode);
		//System.out.println("Key: "+AVLNode.getKey()+" Balance: "+AVLNodeBalance);
		
		if(AVLNodeBalance == 2) {
			AVLNode<K, V> AVLNodeRight = AVLNode.getRight();
			int AVLNodeRightBalance = getBalance(AVLNodeRight);
			

			if(AVLNodeRightBalance == 1 || AVLNodeRightBalance == 0) {
				AVLNode = leftRotate(AVLNode);
			} else if(AVLNodeRightBalance == -1) {
				AVLNode.setRight(rightRotate(AVLNodeRight));
				AVLNode = leftRotate(AVLNode);
			}
			
		} else if(AVLNodeBalance == -2) {
			AVLNode<K, V> AVLNodeLeft = AVLNode.getLeft();
			int AVLNodeLeftBalance = getBalance(AVLNodeLeft);


			if(AVLNodeLeftBalance == -1 || AVLNodeLeftBalance == 0) {
				AVLNode = rightRotate(AVLNode);
			} else if(AVLNodeLeftBalance == 1) {
				AVLNode.setLeft(leftRotate(AVLNodeLeft));
				AVLNode = rightRotate(AVLNode);
			}
		}
		
		return AVLNode;
	}
	
	protected AVLNode<K, V> leftRotate(AVLNode<K, V> AVLNode) {
		
		AVLNode<K, V> right = AVLNode.getRight();
		
		if(AVLNode == root) {
			root = right;
			
		}
		
		AVLNode.setRight(right.getLeft());
		right.setLeft(AVLNode);
		right.setDad(AVLNode.getDad());
		AVLNode.setDad(right);
		
		return right;
	}
	
	protected AVLNode<K, V> rightRotate(AVLNode<K, V> AVLNode) {
		
		AVLNode<K, V> left = AVLNode.getLeft();
		
		if(AVLNode == root) {
			root = left;
		}
		
		AVLNode.setLeft(left.getRight());
		left.setRight(AVLNode);
		left.setDad(AVLNode.getDad());
		AVLNode.setDad(left);
		
		
		
		return left;
	}
	
	public int getHeight(AVLNode<K, V> AVLNode) {
		if(AVLNode == null) {
			return 0;
		}
		return 1 + (Math.max(getHeight(AVLNode.getLeft()),getHeight(AVLNode.getRight())));
	}
	
	public int getBalance(AVLNode<K, V> AVLNode) {
		AVLNode<K, V> left = AVLNode.getLeft();
		AVLNode<K, V> right = AVLNode.getRight();
		
		return getHeight(right) - getHeight(left);
	}

	 public ArrayList<K> preorderK() {
		 ArrayList<K> people= new ArrayList<>();
	       people =  preorderK(root,people);
	       return people;
	    }

	   public ArrayList<K> preorderK(AVLNode<K,V> current,ArrayList<K> people) {
	        if(current != null) {
	        	people.add(current.getKey());
	 	       preorderK(current.getLeft(),people);
	 	       preorderK(current.getRight(),people);
	        }
	        
	        return people;
	       
	    }
	   public ArrayList<V> preorderV() {
			 ArrayList<V> people= new ArrayList<>();
		       people =  preorderV(root,people);
		       return people;
		    }

		   public ArrayList<V> preorderV(AVLNode<K,V> current,ArrayList<V> people) {
		        if(current != null) {
		        	people.add(current.getValue());
		 	       preorderV(current.getLeft(),people);
		 	       preorderV(current.getRight(),people);
		        }
		        
		        return people;
		       
		    }

}