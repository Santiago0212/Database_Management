package model;

public class BRTree<K extends Comparable<K>,V> extends Tree<K,V>{
	
	@Override
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
	
	@Override
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
}
