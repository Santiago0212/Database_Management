package model;

import java.util.ArrayList;

public class BRTree<K extends Comparable<K>,V> extends AVLTree<K,V>{
	BRNode<K,V> root;
	@Override
	public void insert(K key, V value) {
		BRNode<K,V> node = new BRNode<K,V>(key,value);
		
		if(root == null) {
			root = node;
			root.setColor(Color.BLACK);
		} else {
			node.setColor(Color.RED);
			insertBR(node, root);
			//System.out.println(node.getValue());
			autoBalanceBR(node);
		}

	}
	public BRNode<K, V> getRoot() {
		return root;
	}
	public void setRoot(BRNode<K, V> root) {
		this.root = root;
	}
	protected void insertBR(BRNode<K, V> node, BRNode<K, V> current) {
		//System.out.println("Quiere entrar "+node.getKey());
		//System.out.println(current.getKey());
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
		//print();
		if(current == null) {
			return;
		} 
		
		if(current.getDad() == null) {
			 balanceBR(root);
			return;
		} 
		 
		
			
		if(balanceBR(current)==root) {
			return;
		}
			
		
		
			
		autoBalanceBR(current.getDad());
	}
	
	public BRNode<K, V> search(K key) {
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
	
	public ArrayList<V> searchAllNames(String name) {
		ArrayList<V> names = new ArrayList<V>();
		return searchAllNames(root, name,names);
	}
	
	private ArrayList<V> searchAllNames(BRNode<K, V> current, String name, ArrayList<V> names) {
		
		if (current != null) {
		
			searchAllNames(current.getLeft(),name,names);
			Person p= (Person)current.getValue();
			if( p.getName().equals(name))
				names.add((V)p);
			searchAllNames(current.getRight(),name,names);
		}
		return names;
	}
	
	public ArrayList<V> searchAllLastNames(String lastName) {
		ArrayList<V> lastNames = new ArrayList<V>();
		return searchAllLastNames(root, lastName,lastNames);
	}
	
	private ArrayList<V> searchAllLastNames(BRNode<K, V> current, String lastName, ArrayList<V> lastNames) {
		
		if (current != null) {
		
			searchAllLastNames(current.getLeft(),lastName,lastNames);
			Person p= (Person)current.getValue();
			if( p.getLastname().equals(lastName))
				lastNames.add((V)p);
			searchAllLastNames(current.getRight(),lastName,lastNames);
		}
		return lastNames;
	}
	
	public ArrayList<V> searchAllCodes(String code) {
		ArrayList<V> codes = new ArrayList<V>();
		return searchAllLastNames(root, code,codes);
	}
	
	private ArrayList<V> searchAllCodes(BRNode<K, V> current, String code, ArrayList<V> codes) {
		
		if (current != null) {
		
			searchAllCodes(current.getLeft(),code,codes);
			Person p= (Person)current.getValue();
			System.out.println(((Person)current.getValue()).getCode());
			if(p.getCode().equals(code))
				codes.add((V)p);
			searchAllCodes(current.getRight(),code,codes);
		}
		return codes;
	}
	
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
	
	public BRNode<K, V> balanceBR(BRNode<K, V> node) {
		
		//System.out.println("Key: "+node.getKey()+" Balance: "+nodeBalance);
		if(node==null) {
			return null;
		}
		
		if(node==root) {
			root.setColor(Color.BLACK);
			return node;
		}
		
		BRNode<K, V> dad = node.getDad();
		BRNode<K, V> uncle = node.getUncle();
		BRNode<K, V> grandf = node.getDad().getDad();
		
		if(dad.getColor()==Color.RED&&node.getColor()==Color.RED) {
			if(node.getUncle()!=null&&uncle.getColor()==Color.RED) {
				dad.setColor(Color.BLACK);
				uncle.setColor(Color.BLACK);
				grandf.setColor(Color.RED);
				autoBalanceBR(grandf);
			}else {
				if(dad.getRight()==node) {
					if(grandf.getRight()==dad) {
						leftRotateBR(grandf);
						dad.setColor(Color.BLACK);
						balanceBR(dad);
					}else {
						leftRotateBR(dad);
						dad.setColor(Color.BLACK);
						balanceBR(dad.getLeft());
						if(dad.getDad()!=null)
						rightRotateBR(dad.getDad().getDad());
					}
				}
				else {
					if(grandf.getLeft()==dad) {
						rightRotateBR(grandf);
						balanceBR(dad);
					}else {
						rightRotateBR(dad);
						dad.setColor(Color.BLACK);
						balanceBR(dad.getRight());
						if(dad.getDad()!=null)
						leftRotateBR(dad.getDad().getDad());
					}
				}
			}
		}
		
		return node;
	}
	
protected BRNode<K, V> leftRotateBR(BRNode<K, V> node) {
		
	BRNode<K, V> right = node.getRight();
		
		if(node == root&right!=null) {
			root = right;
			root.setColor(Color.BLACK);
		}
		
		if(right==null) {
			return null;
		}
		
		
		node.setRight(right.getLeft());
		right.setLeft(node);
		if(node.getDad()!=null) {
			BRNode<K, V> dad = node.getDad();
			
			if(dad.getRight()==node) {
				dad.setRight(right);
			}else {
				dad.setLeft(right);
			}
		}
		right.setDad(node.getDad());
		node.setDad(right);
		node.setColor(Color.RED);
		
		return right;
	}
	
	protected BRNode<K, V> rightRotateBR(BRNode<K, V> node) {
		
		BRNode<K, V> left = node.getLeft();
		
		if(node == root&left!=null) {
			root = left;
			root.setColor(Color.BLACK);
		}
		if(left==null) {
			return null;
		}
		
		node.setLeft(left.getRight());
		left.setRight(node);
		if(node.getDad()!=null) {
			BRNode<K, V> dad = node.getDad();
			
			if(dad.getRight()==node) {
				dad.setRight(left);
			}else {
				dad.setLeft(left);
			}
		}
			left.setDad(node.getDad());
			node.setDad(left);
			node.setColor(Color.RED);
		
		
		return left;
	}
	
	@Override
	public void print() {
		printBR(root,1);
	}
	
	protected void printBR(BRNode<K, V> root, int space)
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
	    System.out.print(root.getKey()+" "+root.getColor()+ "\n");
	 
	    // Process left child
	    printBR(root.getLeft(), space);
	}
	public ArrayList<Person> findPersons(Character c, int op) {
		ArrayList<Person> persons = new ArrayList<>();
		return findPersons( c,  op, root,persons);
	}
	
	private ArrayList<Person> findPersons(Character c, int op, BRNode<K, V> current,ArrayList<Person> persons) {
		if (current != null) {
			
		
		// Recursivo

		findPersons(c, op,current.getLeft(),persons);
		switch(op) {
		case 1:
			if(((Person)current.getValue()).getName().charAt(0)==c) {
				persons.add(((Person)current.getValue()));
			}
			break;
		case 2:
			if(((Person)current.getValue()).getLastname().charAt(0)==c) {
				persons.add(((Person)current.getValue()));
			}
			break;
		case 3:
			//System.out.println(((Person)current.getValue()).getCode());
			if((changeToWords(((Person)current.getValue()).getCode())).charAt(0)==c) {
				persons.add(((Person)current.getValue()));
			}
	}
		findPersons(c,op,current.getRight(),persons);
		}
		return persons;
		
	}
	public static String changeToWords(String code) {
		String str = "";
		for(int i = 0; i<=code.length()-1;i++) {
			String character = code.substring(i,i+1);
			switch(character) {
				case "0":
					str+="A";
					break;
				case "1":
					str+="B";
					break;
				case "2":
					str+="C";
					break;
				case "3":
					str+="D";
					break;
				case "4":
					str+="F";
					break;
				case "5":
					str+="G";
					break;
				case "6":
					str+="H";
					break;
				case "7":
					str+="I";
					break;
				case "8":
					str+="J";
					break;
				case "9":
					str+="K";
					break;
			}
		}
			return str;
			
		
	}
}
