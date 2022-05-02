package main;

import java.util.Scanner;

import control.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Tree;

public class Main extends Application{

	static Tree<Integer, String> tree = new Tree<Integer, String>();
	static Tree<Character,String> abecedaryTree = new Tree <Character,String>();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		//pruebaTree();
		createTree();		
		launch(args);
	}
	
	private static void createTree() {
		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};
		for(Character c: alphabet)
		abecedaryTree.insert(c,"name");
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
		t.print();
		
	}

	private static void delete(Tree<?,?> t) {
		System.out.print("Write the key for the node you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		t.triggerDelete(key);
		
	}

	private static void add() {
		
		System.out.print("Write the key for your node\n");
		int key = sc.nextInt();
		sc.nextLine();
		System.out.print("Write the value for the node\n");
		String value = sc.nextLine();
		
		tree.insert(key,value);
	}

	private static int menu() {
		System.out.println("1: For add a node\n"+"2: For delete a node\n"+
	"3: For print the values in your tree\n");
		return sc.nextInt();
	}

	

}
