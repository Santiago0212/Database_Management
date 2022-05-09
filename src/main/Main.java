package main;

import control.PrincipalMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Person;
import model.AVLNode;
import model.AVLTree;
import java.util.Scanner;

import model.BRTree;


public class Main extends Application{

	static AVLTree<Character,BRTree<Integer,Person>> abecedaryTree = new AVLTree <Character,BRTree<Integer,Person>>();
	static Scanner sc =new Scanner(System.in);
	static int codeAux;
	
	public static AVLTree<Character, BRTree<Integer, Person>> getAbecedaryTree() {
		return abecedaryTree;
	}

	public static void setAbecedaryTree(AVLTree<Character, BRTree<Integer, Person>> abecedaryTree) {
		Main.abecedaryTree = abecedaryTree;
	}

	public static int getCodeAux() {
		return codeAux;
	}

	public static void setCodeAux(int codeAux) {
		Main.codeAux = codeAux;
	}

	public static void main(String[] args) {
		
		createTree();
		/*new Thread(()->{
			try {
				createCombinations();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();*/
		
		launch(args);
	}
	
	private static void createTree() {
		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};
		for(Character c: alphabet)
		abecedaryTree.insert(c,new BRTree<Integer,Person>());
		/*int op;
		do {
			op=menu();
			send(op);
		}while(op!=0);*/
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/PrincipalMenu.fxml"));
		loader.setController(new PrincipalMenu<Character, BRTree<Integer, Person>>(abecedaryTree));
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void addPerson(int k,Person person) {
		Character initialName = person.getName().charAt(0);
		
		BRTree<Integer, Person> addingTree = abecedaryTree.triggerSearch(initialName).getValue();
		
		addingTree.insert(k, person);
	}

	public static void pruebaTree() {
		System.out.println("Select an option to do in your tree:");
		int op;
		do {
			op=menu();
			send(op);
		}while(op!=0);
	}
	
	private static void send(int op) {
		switch(op) {
			case 1:
				//add();
				break;
			case 2:
				//delete();
				break;
			case 3:
				print();
				break;
			case 4:
				//prube();
				break;
		}
		
	}

	/*private static void prube(Tree<?,?> t) {
		t.triggerInorder();
		//System.out.println(tree.weight(tree.getRoot()));
		
	}*/

	private static void print() {
		System.out.println("write the Capital Letter");
		AVLNode<Character,BRTree<Integer,Person>> AVLNode =abecedaryTree.triggerSearch(sc.next().charAt(0));
		AVLNode.getValue().print();
		return;
		
	}

	/*private static void delete(Tree<?,?> t) {
		System.out.print("Write the key for the AVLNode you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		t.triggerDelete(key);
		
	}*/

	/*private static void add() {
		System.out.println("Write the name");
		String name= sc.nextLine();
		AVLNode<Character,BRTree<Integer,Person>> AVLNode = abecedaryTree.triggerSearch(name.charAt(0));
	/*	AVLNode.getValue().insert(, new Person("",name,null, null, new Date(), 0, null));*/
		
	
	private static int menu() {
		System.out.println("1: For add a AVLNode\n"+"2: For delete a AVLNode\n"+
	"3: For print the values in your tree\n");
		int out = sc.nextInt();
		sc.nextLine();
		return out;
	}

	

}
