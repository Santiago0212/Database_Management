package test;

/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;*/

import model.Node;
import model.Tree;

public class Testing {
	
	private Tree<?, ?> tree = new Tree();
	private Node<?, ?>  node;
	
	/*public void setupStage1() {
		node = new Node(5,"w");
		tree.add(5,"w");
	}
	
	public void setupStage2() {
		node = new Node(7,"g");
		tree.add(7,"g");
	}
	
	public void setupStage3() {
		tree.triggerDelete(7);
	}
	
	@Test
	public void Test() {
		
		setupStage1();
		assertEquals(node.getValue(), tree.getRoot().getValue());
		assertEquals(node.getKey(), tree.getRoot().getKey());
		setupStage2();
		assertNotNull(tree.triggerSearch((int) node.getKey()));
		setupStage3();
		assertNull(tree.triggerSearch((int) node.getKey()));
	}*/
}
