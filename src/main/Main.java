package main;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import control.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BRNode;
import model.BRTree;
import model.Node;
import model.Person;
import model.Sex;
import model.Tree;

public class Main extends Application{

	static Tree<Integer, String> tree = new Tree<Integer, String>();
	static Tree<Character,BRTree<String,Person>> abecedaryTree = new Tree <Character,BRTree<String,Person>>();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		//pruebaTree();
		createTree();		
		launch(args);
	}
	
	private static void createTree() {
		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};
		for(Character c: alphabet)
		abecedaryTree.insert(c,new BRTree<String,Person>());
		int op;
		do {
			op=menu();
			send(op,abecedaryTree);
		}while(op!=0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		loader.setController(new MainWindow());
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void pruebaTree() {
		System.out.println("Select an option to do in your tree:");
		int op;
		do {
			op=menu();
			send(op,tree);
		}while(op!=0);
	}

	private static void send(int op,Tree<?,?> t) {
		switch(op) {
			case 1:
				add();
				break;
			case 2:
				delete(t);
				break;
			case 3:
				print(t);
				break;
			case 4:
				prube(t);
				break;
		}
		
	}

	private static void prube(Tree<?,?> t) {
		t.triggerInorder();
		//System.out.println(tree.weight(tree.getRoot()));
		
	}

	private static void print(Tree<?,?> t) {
		System.out.println("write 0 for print persons tree");
		int ans = sc.nextInt();
		sc.nextLine();
		if(ans==0) {
			System.out.println("write the Capital Letter");
			Node<Character,BRTree<String,Person>> node =abecedaryTree.triggerSearch(sc.next().charAt(0));
			node.getValue().print();
			return;
		}
		t.print();
		
	}

	private static void delete(Tree<?,?> t) {
		System.out.print("Write the key for the node you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		t.triggerDelete(key);
		
	}

	private static void add() {
		System.out.println("Write 0 if you want add a person");
		int op = sc.nextInt();
		sc.nextLine();
		if(op==0) {
			System.out.println("Write the name");
			String name= sc.nextLine();
			Node<Character,BRTree<String,Person>> node = abecedaryTree.triggerSearch(name.charAt(0));
			node.getValue().insert(name, new Person(name,null, null, new Date(), 0, null));
		}else {
			System.out.print("Write the key for your node\n");
			int key = sc.nextInt();
			sc.nextLine();
			System.out.print("Write the value for the node\n");
			String value = sc.nextLine();
			
			tree.insert(key,value);
		}
	}
	
	public static int getRandom(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	private static int menu() {
		System.out.println("1: For add a node\n"+"2: For delete a node\n"+
	"3: For print the values in your tree\n");
		return sc.nextInt();
	}

	

}
