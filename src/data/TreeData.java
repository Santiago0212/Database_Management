package data;

import java.util.ArrayList;

import model.AVLTree;

public class TreeData <K extends Comparable<K>,V>{
	ArrayList<K> AVLKeys = new ArrayList<K>();
	ArrayList<V> AVLValues = new ArrayList<V>();
	
	public void saveAVLK(AVLTree <K,V> avl){
		AVLKeys = avl.preorderK();
		AVLValues = avl.preorderV();
		
		
	}
}
