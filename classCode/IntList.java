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

	public void skippify() {
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p;
			for (int i = 0; i < n; i ++) {
				if (next.rest == null) {
					break;
				}
				next = next.rest;
			}
			p.rest = next.rest;
			p = p.rest;
			n ++;
		}
	}

	public static void removeDuplicates(IntList p) {
		if (p == null) {
			return;
		}
		IntList current = p.rest;
		IntList previous = p;
		while (current != null) {
			if (current.first != previous.first) {
				previous = current;
			}
			else {
				previous.rest = current.rest;
				// omit the redundant node
			}
			current = current.rest;
		}
	}
	public static void main(String[] args) {
		IntList L = new IntList(1, null);
		for (int i = 2; i < 10; i ++) {
			L.addFirst(i);
		}
		L.skippify();
		IntList p = L;
		while (p != null) {
			System.out.print(p.first+ " ");
			p = p.rest;
		}
		System.out.println();
	}
} 