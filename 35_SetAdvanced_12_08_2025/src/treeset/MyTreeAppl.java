package treeset;

public class MyTreeAppl {
	public static void main(String[] args) {
		MyTree tree = new MyTree();
		tree.add(10);
		tree.add(15);
		tree.add(2);
		tree.add(19);
		tree.add(25);
		tree.add(39);
		tree.add(1);
		tree.add(4);
		tree.add(11);
		tree.add(16);
		tree.add(20);
		tree.add(7);
		tree.add(0);
        tree.add(17);
		tree.traverse();
		
		System.out.println(tree.contains(4));
		System.out.println(tree.contains(3));

        tree.remove(4);
        tree.traverse();
	}
}
