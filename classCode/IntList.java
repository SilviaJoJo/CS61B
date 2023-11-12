public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the first item of this IntList*/
	public void addFirst(int x) {
		IntList curr = this;
		int temp = x;
		while (curr.rest != null) {
			int helper = curr.first;
			curr.first = temp;
			temp = helper;
			curr = curr.rest;
		}
		int helper = curr.first;
		curr.first = temp;
		curr.rest = new IntList(helper, null);
	}
	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}

	public static IntList incrList (IntList L, int x) {
		if (L.rest == null) {
			return new IntList(L.first + x, null);
		}
		return new IntList(L.first + x, incrList(L.rest, x));
	}

	public static IntList dincrList (IntList L, int x) {
		if (L.rest == null) {
			L.first = L.first + x;
			return L;
		}
		L.first = L.first + x;
		dincrList(L.rest, x);
		return L;
	}
	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		//L = new IntList(5, L);
		L.addFirst(5);
		//L = dincrList(L, 1);
		System.out.println(L.get(0));
		System.out.println(L.get(1));
		System.out.println(L.get(2));
	}
} 