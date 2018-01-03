package com.company;

public interface ListInterface {

    boolean addItem(TreeItem newItem);
    boolean removeItem(TreeItem itemToRemove);
    TreeItem setBaseItem(TreeItem baseItem);
    void printList();
}
