package com.company;

public interface TreeInterface {

    boolean addItem(TreeItem newItem);
    boolean removeItem(TreeItem itemToRemove);
    TreeItem setBaseItem(TreeItem baseItem);
    void traverse();
    void printTree();
    TreeItem findItem(TreeItem itemToFind);
}
