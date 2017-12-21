package com.company;

public class BinarySearchTree implements ListInterface {

    private ListItem baseItem;

    public BinarySearchTree() {
        this.baseItem = null;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.baseItem != null) {

            ListItem currentItem = this.baseItem; //start with baseItem

            while(currentItem != null) {

                if(currentItem.compareTo(newItem) > 0) {
                    //newItem -> currentItem left
                    if(currentItem.left() != null) {
                        //if currentItem has left item then continue iteration with currentItem.left
                        currentItem = currentItem.left();
                    }
                    else {
                        //if currentItem has not left item then add newItem as currentItem.left
                        currentItem.setLeft(newItem);
                        return true;
                    }
                }
                else if(currentItem.compareTo(newItem) < 0) {
                    //newItem -> currentItem right
                    if(currentItem.right() != null) {
                        //if currentItem has right item then continue iteration with currentItem.right
                        currentItem = currentItem.right();
                    }
                    else {
                        //if currentItem has not right item then add newItem as currentItem.right
                        currentItem.setRight(newItem);
                        return true;
                    }
                }
                else {
                    //item already exists message
                    System.out.println("Item '" + newItem.getValue() + "' already exists");
                    return true;
                }
            }
        }
        else {
            //list is empty, set base item on the list
            setBaseItem(newItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem itemToRemove) {
        if (this.baseItem != null) {

            ListItem currentItem = this.baseItem; //start with baseItem as current
            ListItem parentItem = currentItem; //start with baseItem as parent for current

            while(currentItem != null) {
                if(currentItem.compareTo(itemToRemove) > 0) {
                    //itemToRemove -> currentItem left
                    //itemToRemove is not equal to currentItem
                    //continue iteration after changing parentItem and currentItem
                    parentItem = currentItem;
                    currentItem = currentItem.left();
                }
                else if(currentItem.compareTo(itemToRemove) < 0) {
                    //itemToRemove -> currentItem right
                    //itemToRemove is not equal to currentItem
                    //continue iteration after changing parentItem and currentItem
                    parentItem = currentItem;
                    currentItem = currentItem.right();
                }
                else {
                    //itemToRemove found - remove
                    return performRemoving(currentItem, parentItem);
                }
            }
            //itemToRemove not found message
            System.out.println("Item not found");
            return false;
        }
        else {
            //list is empty message
            System.out.println("Tree is empty");
        }
        return false;
    }

    @Override
    public ListItem setBaseItem(ListItem baseItem) {
        this.baseItem = baseItem;
        return this.baseItem;
    }

    @Override
    public void printList() {
        traverseTreeRecursively(this.baseItem);
    }

    private boolean performRemoving(ListItem itemToRemove, ListItem itemToRemoveParent) {
        System.out.println(itemToRemove.getValue() + " from " + itemToRemoveParent.getValue());

        //case 1 - no children
        if(itemToRemove.left() == null && itemToRemove.right() == null) {
            //check if itemToRemove has parent
            if(itemToRemoveParent.left() != null || itemToRemoveParent.right() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.left().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(null);
                    return true;
                }
                else if (itemToRemoveParent.right().compareTo(itemToRemove) == 0) {
                    //parentItem right to remove
                    itemToRemoveParent.setRight(null);
                    return true;
                }
            }
            else {
                //if itemToRemove doesn't have parent then itemToRemove is baseItem without children
                setBaseItem(null);
                return true;
            }
        }

        //case 2a - 1 child - left
        else if(itemToRemove.left() != null && itemToRemove.right() == null) {
            //itemToRemove will be replaced with child
            //check if itemToRemove has parent
            if(itemToRemoveParent.left() != null || itemToRemoveParent.right() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.left().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(itemToRemove.left());
                    return true;
                }
                else if (itemToRemoveParent.right().compareTo(itemToRemove) == 0) {
                    //parentItem right to remove
                    itemToRemoveParent.setRight(itemToRemove.left());
                    return true;
                }
            }
            //if itemToRemove doesn't have parent then itemToRemove is baseItem with one child - left
            setBaseItem(itemToRemove.left());
            return true;
        }

        //case 2b - 1 child - right
        else if(itemToRemove.left() == null && itemToRemove.right() != null) {
            //itemToRemove will be replaced with child
            //check if itemToRemove has parent
            if(itemToRemoveParent.left() != null || itemToRemoveParent.right() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.left().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(itemToRemove.right());
                    return true;
                }
                else if (itemToRemoveParent.right().compareTo(itemToRemove) == 0) {
                    //parentItem right to remove
                    itemToRemoveParent.setRight(itemToRemove.right());
                    return true;
                }
            }
            //if itemToRemove doesn't have parent then itemToRemove is baseItem with one child - left
            setBaseItem(itemToRemove.right());
            return true;
        }

        //case 3 - 2 children
        else if(itemToRemove.left() != null && itemToRemove.right() != null) {
            //itemToRemove will be replaced with right nearest element -
            //element from branch 'right' that doesn't have left element

            //PREPARE START ELEMENTS
            ListItem replacementItem = itemToRemove.right(); //start with right child as replacementItem
            ListItem replacementItemParent = itemToRemove;   //start with itemToRemove as replacementItemParent

            //SEARCH FOR ELEMENT WHICH WILL REPLACE ITEM TO REMOVE
            //search for element from branch 'right' that doesn't have left element
            while(replacementItem.left() != null) {
                replacementItemParent = replacementItem;
                replacementItem = replacementItem.left();
            }

            //CHANGE REPLACEMENT ITEM AND ITS PARENT CHILDREN
            //check if replacementItem is equal to itemToRemove.right
            if(replacementItem.compareTo(itemToRemove.right()) != 0) {
                //check if replacementItem has right element
                if(replacementItem.right() != null) {
                    //if replacementItem has right element then it will move to its parent left field
                    replacementItemParent.setLeft(replacementItem.right());
                }
                else {
                    //if replacementItem doesn't have right element then its parent loses left element
                    replacementItemParent.setLeft(null);
                }
                //copy left/right fields from itemToRemove to replacementItem
                replacementItem.setLeft(itemToRemove.left());
                replacementItem.setRight(itemToRemove.right());
            }
            else {
                //if replacementItem is itemToRemove right element then
                //copy itemToRemove left child to replacementItem left child
                replacementItem.setLeft(itemToRemove.left());
            }

            //CHANGE ITEM TO REMOVE PARENT CHILD
            //check if itemToRemoveParent has left element
            if(itemToRemoveParent.left() != null) {
                //check if left element is itemToRemove
                if (itemToRemoveParent.left().compareTo(itemToRemove) == 0) {
                    //replace itemToRemove
                    itemToRemoveParent.setLeft(replacementItem);
                    return true;
                }
            }
            //check if itemToRemoveParent has right element
            if(itemToRemoveParent.right() != null) {
                //check if right element is itemToRemove
                if (itemToRemoveParent.right().compareTo(itemToRemove) == 0) {
                    //replace itemToRemove
                    itemToRemoveParent.setRight(replacementItem);
                    return true;
                }
            }
            //if itemToRemove doesn't have parent then itemToRemove is baseItem
            //set new base item
            setBaseItem(replacementItem);
            return true;
        }

        return false;
    }

    public void traverseTreeRecursively(ListItem currentItem) {

        if(currentItem != null) {
            traverseTreeRecursively(currentItem.left());
            System.out.println(currentItem.getValue());
            traverseTreeRecursively(currentItem.right());
        }
    }
}
