import java.util.function.IntToDoubleFunction;

/** An SLList is a list of integers, which hides the terrible truth
   * of the nakedness within. */
public class SLList {	
	protected static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	} 

	protected IntNode first;

	public SLList(){}

	public void insertFront(int x){}

	/** Returns the index of x in the list, if it exists. Otherwise, return -1*/
	public int indexOf(int x) {return -1;}

 	/** Adds x to the front of the list. */
 	public void addFirst(int x) {
 		first = new IntNode(x, first);
 	}

	public void insert(int item, int position) {
		if (position == 0 || first == null) {
			addFirst(item);
			return;
			// bug1: forget to return for the corner case; otherwise compilation error
		}
		IntNode current = first;
		while (current.next != null && position > 1) {
			// bug2: and or; otherwise always inserted to the end
			current = current.next;
			position -= 1;
		}
		current.next = new IntNode(item, current.next);
	}

	public void reverseIterative() {
		 if (first == null || first.next == null) {
			 return;
		 }
		 IntNode curr1 = first;
		 IntNode curr2 = first.next;
		 curr1.next = null;
		 while (curr2 != null) {
			 IntNode temp = curr2.next;
			 curr2.next = curr1;
			 curr1 = curr2;
			 curr2 = temp;
		 }
		 first = curr1;
	}

	private IntNode reverseHelper(IntNode node) {
		 if (node == null || node.next == null) {
			 first = node;
			 return node;
		 }
		 IntNode last = reverseHelper(node.next);
		 last.next = node;
		 node.next = null;
		 return node;
	}
	public void reverseRecursive() {
		 IntNode end = reverseHelper(first);
	}

	//OMG I don't know why if I use "Run", the result is wrong
	//But if I use the terminal to compile and run, the result is correct
	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10 */
 		SLList L = new SLList();
		 L.addFirst(2);
		 L.addFirst(6);
		 L.addFirst(5);
		 L.insert(10, 1);
		 //L.reverseIterative();
		L.reverseRecursive();
		 SLList.IntNode current = L.first;
		 while (current != null) {
			 System.out.print(current.item + " ");
			 current = current.next;
		 }
 	}
}