package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BinarySearchTree tree = new BinarySearchTree();
        tree.addItem(new TreeItem(8));
        tree.addItem(new TreeItem(10));
        tree.addItem(new TreeItem(3));
        tree.addItem(new TreeItem(14));
        tree.addItem(new TreeItem(1));
        tree.addItem(new TreeItem(6));
        tree.addItem(new TreeItem(4));
        tree.addItem(new TreeItem(7));
        tree.addItem(new TreeItem(13));

        tree.printList();
    }
}
