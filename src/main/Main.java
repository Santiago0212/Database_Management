package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import control.PrincipalMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Person;
import model.Sex;
import model.AVLTree;
import model.BRTree;

public class Main extends Application{

	static AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
	static AVLTree<String,BRTree<Integer,Person>> abecedaryTree = new AVLTree <String,BRTree<Integer,Person>>();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		
		try {
			createTree();
			createCombinations();
		} catch (IOException e) {
			e.printStackTrace();
		}


			
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/PrincipalMenu.fxml"));
		loader.setController(new PrincipalMenu(abecedaryTree));
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
		
	}
	
	private static void createTree() {
		String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(String c: alphabet) {
			abecedaryTree.insert(c,new BRTree<Integer,Person>());
		}
		
	}
/*
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
	*/
	public static void pruebaTree() {
		System.out.println("Select an option to do in your tree:");
		int op;
		do {
			op=menu();
			send(op,tree);
		}while(op!=0);
	}

	private static void send(int op,AVLTree<?,?> t) {
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

	private static void prube(AVLTree<?,?> t) {
		t.triggerInorder();
		//System.out.println(tree.weight(tree.getRoot()));
		
	}

	private static void print(AVLTree<?,?> t) {
		t.print();
		
	}

	private static void delete(AVLTree<?,?> t) {
		System.out.print("Write the key for the node you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		//t.triggerDelete(key);
		
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
	
	
	public static void createCombinations() throws IOException {
		String[] names = importNames();
		String[] lastNames = importLastNames();
		
	
		int k = 0;
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				String name = names[i].split(",")[0];
				String sexString = names[i].split(",")[1];
				String lastName = lastNames[j];
				String code = k+"";
				
				Sex sex = Sex.NONE;
				if(sexString.equalsIgnoreCase("BOY")) {
					sex = Sex.MALE;
				} else if(sexString.equalsIgnoreCase("GIRL")) {
					sex = Sex.FEMALE;
				}
				
				Person person = new Person(code,name,lastName,sex);
				
				String initial = person.getName().substring(0,1); 
				
				AVLTree<Integer, Person> addingTree = abecedaryTree.search(initial).getValue();
				
				System.out.println(name);
				
				addingTree.insert(k, person);

				k++;
			}
		}
		
	}
	
	public static String[] importNames() throws IOException {
		String path = "means/babynames-clean.csv";
		
		String[] namesList = new String[1000];
		
		File names = new File(path);
		
		if(names.exists()) {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			int i = 0;
			boolean finished = false;
			
			while((line = br.readLine())!=null && !finished) {
				if(i<1000) {
					namesList[i] = line.toUpperCase();
				}
				i++;
			}
			
			if (br != null)br.close();
            if (fr != null)fr.close();
			
		}
		
		return namesList;
		
	}
	
	public static String[] importLastNames() throws IOException {
		String path = "means/Names_2010Census.csv";
		
		String[] lastNamesList = new String[1000];
		
		File names = new File(path);
		
		if(names.exists()) {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			int i = 0;
			boolean finished = false;
			
			while((line = br.readLine())!=null && !finished) {
				if(i<1000 && i>0) {
					String[] data = line.split(",");
					lastNamesList[i] = data[0];
				}
				i++;
			}
			
			if (br != null)br.close();
            if (fr != null)fr.close();
			
		}
		
		return lastNamesList;
	}
	

}
