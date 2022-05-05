package data;

import model.AVLTree;

public class TreeData <K extends Comparable<K>,V>{
	ArrayList<K> AVLKeys = new ArrayList<>();
	ArrayList<V> AVLValues = new ArrayList<>();
	
	public ArrayList<K> saveAVLK(AVLTree <K,V> avl){
		AVLKeys = avl.preorderK();
	}
}
