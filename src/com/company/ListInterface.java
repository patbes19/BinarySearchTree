package com.company;

public interface ListInterface {

    boolean addItem(ListItem newItem);
    boolean removeItem(ListItem itemToRemove);
    ListItem setBaseItem(ListItem baseItem);
    void printList();
}
