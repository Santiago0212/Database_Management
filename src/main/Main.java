package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import control.PrincipalMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Person;
import model.Sex;
import model.AVLNode;
import model.AVLTree;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import model.BRTree;


public class Main extends Application{

	static AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
	static AVLTree<Character,BRTree<String,Person>> abecedaryTree = new AVLTree <Character,BRTree<String,Person>>();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		
		createTree();
		new Thread(()->{
			try {
				createCombinations();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		/*try {
			createCombinations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		launch(args);
	}
	
	private static void createTree() {
		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};
		for(Character c: alphabet)
		abecedaryTree.insert(c,new BRTree<String,Person>());
		int op;
		/*do {
			op=menu();
			send(op);
		}while(op!=0);*/
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/PrincipalMenu.fxml"));
		loader.setController(new PrincipalMenu());
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
		
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
				
				Sex sex = null;
				if(sexString.equalsIgnoreCase("BOY")) {
					sex = Sex.MALE;
				} else if(sexString.equalsIgnoreCase("GIRL")) {
					sex = Sex.FEMALE;
				}
				
				Person person = new Person(code,name,lastName,sex);
				
				Character initial = person.getName().charAt(0); 
				
				BRTree<String, Person> addingTree = abecedaryTree.triggerSearch(initial).getValue();
				
				addingTree.insert(code, person);
				//addingTree.print();
				

				k++;
			}
			
		}
		abecedaryTree.triggerSearch('A').getValue().print();

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



	/*@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		loader.setController(new MainWindow());
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
	}*/
	
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
		AVLNode<Character,BRTree<String,Person>> AVLNode =abecedaryTree.triggerSearch(sc.next().charAt(0));
		AVLNode.getValue().print();
		return;
		
	}

	/*private static void delete(Tree<?,?> t) {
		System.out.print("Write the key for the AVLNode you want to delete\n");
		int key = sc.nextInt();
		sc.nextLine();
		
		t.triggerDelete(key);
		
	}*/

	private static void add() {
		
		System.out.println("Write the name");
		String name= sc.nextLine();
		AVLNode<Character,BRTree<String,Person>> AVLNode = abecedaryTree.triggerSearch(name.charAt(0));
		AVLNode.getValue().insert(name, new Person(name,null, null, new Date(), 0, null));
		
	}
	
	public static int getRandom(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	private static int menu() {
		System.out.println("1: For add a AVLNode\n"+"2: For delete a AVLNode\n"+
	"3: For print the values in your tree\n");
		int out = sc.nextInt();
		sc.nextLine();
		return out;
	}

	

}
