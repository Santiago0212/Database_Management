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
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		pruebaTree();
		launch(args);
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
			send(op);
		}while(op!=0);
	}

	private static void send(int op) {
		switch(op) {
			case 1:
				add();
				break;
			case 2:
				delete();
				break;
			case 3:
				print();
				break;
			case 4:
				prube();
				break;
		}
		
	}

	private static void prube() {
		tree.triggerInorder();
		//System.out.println(tree.weight(tree.getRoot()));
		
	}

	private static void print() {
		tree.print();
		
	}

	private static void delete() {
		System.out.print("Write the key for the node you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		tree.triggerDelete(key);
		
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
