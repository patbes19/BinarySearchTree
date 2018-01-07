package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinarySearchTree implements TreeInterface {

    private TreeItem baseItem;
    private ArrayList<LinkedList<TreeItem>> nodesList;
    /*
        nodesList contains all tree elements
        elements from the same tree level are in one ArrayList element as LinkedList elements
     */

    public BinarySearchTree() {
        this.baseItem = null;
        this.nodesList = new ArrayList<LinkedList<TreeItem>>();
    }

    @Override
    public boolean addItem(TreeItem newItem) {
        if(this.baseItem != null) {

            TreeItem currentItem = this.baseItem; //start with baseItem

            while(currentItem != null) {

                if(currentItem.compareTo(newItem) > 0) {
                    //newItem -> currentItem left
                    if(currentItem.getLeft() != null) {
                        //if currentItem has left item then continue iteration with currentItem.left
                        currentItem = currentItem.getLeft();
                    }
                    else {
                        //if currentItem has not left item then add newItem as currentItem.left
                        currentItem.setLeft(newItem);
                        return true;
                    }
                }
                else if(currentItem.compareTo(newItem) < 0) {
                    //newItem -> currentItem right
                    if(currentItem.getRight() != null) {
                        //if currentItem has right item then continue iteration with currentItem.right
                        currentItem = currentItem.getRight();
                    }
                    else {
                        //if currentItem has not right item then add newItem as currentItem.right
                        currentItem.setRight(newItem);
                        return true;
                    }
                }
                else {
                    //item already exists message
                    //System.out.println("Item '" + newItem.getValue() + "' already exists");
                    return false;
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
    public boolean removeItem(TreeItem itemToRemove) {
        if (this.baseItem != null) {

            TreeItem currentItem = this.baseItem; //start with baseItem as current
            TreeItem parentItem = currentItem; //start with baseItem as parent for current

            while(currentItem != null) {
                if(currentItem.compareTo(itemToRemove) > 0) {
                    //itemToRemove -> currentItem left
                    //itemToRemove is not equal to currentItem
                    //continue iteration after changing parentItem and currentItem
                    parentItem = currentItem;
                    currentItem = currentItem.getLeft();
                }
                else if(currentItem.compareTo(itemToRemove) < 0) {
                    //itemToRemove -> currentItem right
                    //itemToRemove is not equal to currentItem
                    //continue iteration after changing parentItem and currentItem
                    parentItem = currentItem;
                    currentItem = currentItem.getRight();
                }
                else {
                    //itemToRemove found - remove
                    return performRemoving(currentItem, parentItem);
                }
            }
            //itemToRemove not found message
            //System.out.println("Item not found");
            return false;
        }
        else {
            //list is empty message
            //System.out.println("Tree is empty");
            return false;
        }
    }

    @Override
    public TreeItem setBaseItem(TreeItem baseItem) {
        this.baseItem = baseItem;
        return this.baseItem;
    }

    @Override
    public void traverse() {
        traverseTreeRecursively(this.baseItem);
        System.out.println();
    }

    public void printTree() {

        this.nodesList.clear(); //clear list
        saveTreeItemInTheList(this.baseItem, 0); //initialize list

        for(int i = 0; i < this.nodesList.size(); i++) {
            System.out.print("| ");
            for(TreeItem currentItem : this.nodesList.get(i)) {
                if(currentItem != null)
                    System.out.print(currentItem.getValue());
                else
                    System.out.print("-");

                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public TreeItem findItem(TreeItem itemToFind) {

        //start elements
        TreeItem currentItem = this.baseItem;

        while(currentItem != null) {

            if(itemToFind.compareTo(currentItem) == 0) {
                //item found
                return itemToFind;
            }
            else if(itemToFind.compareTo(currentItem) > 0){
                //item greater than current - go to the right
                currentItem = currentItem.getRight();
            }
            else {
                //item smaller than current - go to the left
                currentItem = currentItem.getLeft();
            }
        }
        //item doesn't exist
        return null;
    }

    private boolean performRemoving(TreeItem itemToRemove, TreeItem itemToRemoveParent) {

        //case 1 - no children
        if(itemToRemove.getLeft() == null && itemToRemove.getRight() == null) {
            //check if itemToRemove has parent
            if(itemToRemoveParent.getLeft() != null || itemToRemoveParent.getRight() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.getLeft().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(null);
                    return true;
                }
                else if (itemToRemoveParent.getRight().compareTo(itemToRemove) == 0) {
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
        else if(itemToRemove.getLeft() != null && itemToRemove.getRight() == null) {
            //itemToRemove will be replaced with child
            //check if itemToRemove has parent
            if(itemToRemoveParent.getLeft() != null || itemToRemoveParent.getRight() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.getLeft().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(itemToRemove.getLeft());
                    return true;
                }
                else if (itemToRemoveParent.getRight().compareTo(itemToRemove) == 0) {
                    //parentItem right to remove
                    itemToRemoveParent.setRight(itemToRemove.getLeft());
                    return true;
                }
            }
            //if itemToRemove doesn't have parent then itemToRemove is baseItem with one child - left
            setBaseItem(itemToRemove.getLeft());
            return true;
        }

        //case 2b - 1 child - right
        else if(itemToRemove.getLeft() == null && itemToRemove.getRight() != null) {
            //itemToRemove will be replaced with child
            //check if itemToRemove has parent
            if(itemToRemoveParent.getLeft() != null || itemToRemoveParent.getRight() != null) {
                //if itemToRemove has parent then delete item from parentItem left/right field
                if (itemToRemoveParent.getLeft().compareTo(itemToRemove) == 0) {
                    //parentItem left to remove
                    itemToRemoveParent.setLeft(itemToRemove.getRight());
                    return true;
                }
                else if (itemToRemoveParent.getRight().compareTo(itemToRemove) == 0) {
                    //parentItem right to remove
                    itemToRemoveParent.setRight(itemToRemove.getRight());
                    return true;
                }
            }
            //if itemToRemove doesn't have parent then itemToRemove is baseItem with one child - left
            setBaseItem(itemToRemove.getRight());
            return true;
        }

        //case 3 - 2 children
        else if(itemToRemove.getLeft() != null && itemToRemove.getRight() != null) {
            //itemToRemove will be replaced with right nearest element -
            //element from branch 'right' that doesn't have left element

            //PREPARE START ELEMENTS
            TreeItem replacementItem = itemToRemove.getRight(); //start with right child as replacementItem
            TreeItem replacementItemParent = itemToRemove;   //start with itemToRemove as replacementItemParent

            //SEARCH FOR ELEMENT WHICH WILL REPLACE ITEM TO REMOVE
            //search for element from branch 'right' that doesn't have left element
            while(replacementItem.getLeft() != null) {
                replacementItemParent = replacementItem;
                replacementItem = replacementItem.getLeft();
            }

            //CHANGE REPLACEMENT ITEM AND ITS PARENT CHILDREN
            //check if replacementItem is equal to itemToRemove.right
            if(replacementItem.compareTo(itemToRemove.getRight()) != 0) {
                //check if replacementItem has right element
                if(replacementItem.getRight() != null) {
                    //if replacementItem has right element then it will move to its parent left field
                    replacementItemParent.setLeft(replacementItem.getRight());
                }
                else {
                    //if replacementItem doesn't have right element then its parent loses left element
                    replacementItemParent.setLeft(null);
                }
                //copy left/right fields from itemToRemove to replacementItem
                replacementItem.setLeft(itemToRemove.getLeft());
                replacementItem.setRight(itemToRemove.getRight());
            }
            else {
                //if replacementItem is itemToRemove right element then
                //copy itemToRemove left child to replacementItem left child
                replacementItem.setLeft(itemToRemove.getLeft());
            }

            //CHANGE ITEM TO REMOVE PARENT CHILD
            //check if itemToRemoveParent has left element
            if(itemToRemoveParent.getLeft() != null) {
                //check if left element is itemToRemove
                if (itemToRemoveParent.getLeft().compareTo(itemToRemove) == 0) {
                    //replace itemToRemove
                    itemToRemoveParent.setLeft(replacementItem);
                    return true;
                }
            }
            //check if itemToRemoveParent has right element
            if(itemToRemoveParent.getRight() != null) {
                //check if right element is itemToRemove
                if (itemToRemoveParent.getRight().compareTo(itemToRemove) == 0) {
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

    private void traverseTreeRecursively(TreeItem currentItem) {

        if(currentItem != null) {
            traverseTreeRecursively(currentItem.getLeft());
            System.out.print(currentItem.getValue() + " ");
            traverseTreeRecursively(currentItem.getRight());
        }
    }

    private void saveTreeItemInTheList(TreeItem currentItem, int currentTreeLevel) {

        if(currentTreeLevel == 0) {
            //first item
            this.nodesList.add(new LinkedList<TreeItem>()); //add first tree level
            this.nodesList.get(currentTreeLevel).add(currentItem); //add first item
        }
        else {
            //next item
            if(currentTreeLevel == this.nodesList.size()) {
                //create next tree level and fill it with nulls
                this.nodesList.add(new LinkedList<TreeItem>()); //add tree level

                for(int i = 0; i < Math.pow(2, currentTreeLevel); i++) {
                    this.nodesList.get(currentTreeLevel).add(i, null);
                }
            }

            //current item parent index necessary for adding current item in the right place on the list
            TreeItem currentItemParent = findParentItem(currentItem);
            int currentItemParentIndex = getItemIndexFromOneLevelList(this.nodesList.get(currentTreeLevel-1), currentItemParent);

            //check if current item is left or right item of its parent
            //then add current item to the list
            if(currentItemParent.getLeft() != null) {
                if (findParentItem(currentItem).getLeft().compareTo(currentItem) == 0)
                    nodesList.get(currentTreeLevel).set(2 * currentItemParentIndex, currentItem);
            }

            if(currentItemParent.getRight() != null) {
                if (findParentItem(currentItem).getRight().compareTo(currentItem) == 0)
                    nodesList.get(currentTreeLevel).set(2 * currentItemParentIndex + 1, currentItem);
            }
        }

        //save left node recursively
        if(currentItem.getLeft() != null)
            saveTreeItemInTheList(currentItem.getLeft(), currentTreeLevel+1);

        //save right node recursively
        if(currentItem.getRight() != null)
            saveTreeItemInTheList(currentItem.getRight(), currentTreeLevel+1);
    }

    private static int getItemIndexFromOneLevelList(LinkedList<TreeItem> nodesLevel, TreeItem itemToFind) {

        for(int i = 0; i < nodesLevel.size(); i++) {
            if(nodesLevel.get(i) != null) {
                if(nodesLevel.get(i).compareTo(itemToFind) == 0)
                    return i;
            }
        }
        return -1;
    }

    private TreeItem findParentItem(TreeItem childItem) {

        //start elements
        TreeItem currentItem = this.baseItem;
        TreeItem currentItemParent = null;

        while(currentItem != null) {

            if(childItem.compareTo(currentItem) == 0) {
                //child found, return parent
                return currentItemParent;
            }
            else if(childItem.compareTo(currentItem) > 0){
                //child greater than current - go to the right
                currentItemParent = currentItem;
                currentItem = currentItem.getRight();
            }
            else {
                //child smaller than current - go to the left
                currentItemParent = currentItem;
                currentItem = currentItem.getLeft();
            }
        }
        //child doesn't exist
        return null;
    }
}