package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.AVLTree;
import model.BRTree;

public class TreeData <K extends Comparable<K>,V>{
	ArrayList<K> AVLKeys = new ArrayList<K>();
	ArrayList<V> AVLValues = new ArrayList<V>();
	
	ArrayList<K> RBKey = new ArrayList<K>();
	ArrayList<V> RBValues = new ArrayList<V>();
	
	public void saveAVL(AVLTree <K,V> avl){
		AVLKeys = avl.preorderK();
		AVLValues = avl.preorderV();
	}
	
	public void save() {
		File file = new File("data/data.temp");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void load() {
		File file = new File("data/data.temp");
		try {
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object ob = ois.readObject();
			TreeData<K,V> data = (TreeData<K, V> ) ob;
			this.AVLKeys = data.AVLKeys;
			this.AVLValues = data.AVLValues;
			this.RBKey = data.RBKey;
			this.RBValues=data.RBValues;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
