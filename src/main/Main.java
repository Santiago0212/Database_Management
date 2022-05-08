package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import java.util.concurrent.ThreadLocalRandom;

import model.BRTree;


public class Main extends Application{

	static AVLTree<Character,BRTree<Integer,Person>> abecedaryTree = new AVLTree <Character,BRTree<Integer,Person>>();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		
		createTree();
		new Thread(()->{
			try {
				createCombinations();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		
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
	
	public static void createCombinations() throws IOException {
		String[] names = importNames();
		String[] lastNames = importLastNames();

		int numI=1000;
		int numJ=1000;
		
		int total=numI*numJ;
		
		String[ ] paises = new String[] {"Canada","Estados Unidos","Mexico","Belice","Costa rica","El Salvador",
	    		"Guatemala","Honduras","Nicaragua","Panamá","Argentina","Bolivia","Brasil","Chile","Colombia","Ecuador",
	    		"Paraguay","Peru","Surinam","Trinidad y Tobago","Uruguay","Venezuela","Antigua y Barbuda","Bahamas","Barbados",
	    		"Cuba","Dominica","Granada","Guyana","Haiti","Jamaica","República Dominicana","San Cristóbal y Nieves","San Vicente y las Granadinas","Santa Lucia"};
		double[] poblacionesDouble=new double[] {0.03*total,0.25*total,0.10*total,0.01*total,0.01*total,0.01*total,0.02*total,0.01*total,0.01*total,0.01*total,0.04*total,0.01*total,0.18*total,0.02*total,0.05*total,0.02*total,0.01*total,0.03*total,0.01*total,0.01*total,0.01*total,0.02*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total,0.01*total};
	    int [] poblaciones=new int[poblacionesDouble.length];
	    
	    for(int i=0;i<poblaciones.length;i++) {
	    	poblaciones[i]=(int) poblacionesDouble[i];
	    }
	   
		int k = 0;
		
		for(int i = 0; i<numI; i++) {
			for(int j = 0; j<numJ; j++) {
				String name = names[i].split(",")[0];
				String sexString = names[i].split(",")[1];
				String lastName = lastNames[j];
				String code = k+"";
				
				//System.out.println(name);
				
				if(name!=null && sexString!=null && lastName!=null && code!=null) {
					
					Sex sex = null;
					if(sexString.equalsIgnoreCase("BOY")) {
						sex = Sex.MALE;
					} else if(sexString.equalsIgnoreCase("GIRL")) {
						sex = Sex.FEMALE;
					}
					
					LocalDate startDate = LocalDate.of(1990, 1, 1);
				    long start = startDate.toEpochDay();
				    LocalDate endDate = LocalDate.now();
				    long end = endDate.toEpochDay();
				    ZoneId defaultZoneId = ZoneId.systemDefault();
				    long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
				    Date date = Date.from(LocalDate.ofEpochDay(randomEpochDay).atStartOfDay(defaultZoneId).toInstant());
				    
				    double randomHeight=getRandom(100,210);
				    randomHeight=randomHeight/100;
				    String nationality="";
				    if(total>100) {
				    	boolean out=false;
					    while(!out) {
					    	int randomNationality=getRandom(1, 35);
					    	if(poblaciones[randomNationality]>0) {
					    		nationality=paises[randomNationality];
					    		out=true;
					    	}
					    }
				    }else {
				    	int randomNationality=getRandom(1, 35);
				    	nationality=paises[randomNationality];
				    }
				    
					
					Person person = new Person(k+"", name, lastName, sex, date, randomHeight, nationality);
					
					Character initialName = person.getName().charAt(0); 

					
					BRTree<Integer, Person> addingTree = abecedaryTree.triggerSearch(initialName).getValue();
					
					

					addingTree.insert(k, person);

		
					k++;
				}
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

	private static void add() {
		System.out.println("Write the name");
		String name= sc.nextLine();
		AVLNode<Character,BRTree<Integer,Person>> AVLNode = abecedaryTree.triggerSearch(name.charAt(0));
	/*	AVLNode.getValue().insert(, new Person("",name,null, null, new Date(), 0, null));*/
		
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
