package main;

import java.util.Scanner;

import model.Node;
import model.Tree;

public class Main {

	static Tree tree = new Tree();
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
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
		System.out.println(tree.weight(tree.getRoot()));
		
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
