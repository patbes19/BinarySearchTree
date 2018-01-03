package com.company;

public class TreeItem implements Comparable<TreeItem> {

    private TreeItem left;
    private TreeItem right;
    private int value;

    public TreeItem(int value) {
        this.value = value;
    }

    public TreeItem getRight() {
        return right;
    }

    public TreeItem getLeft() {
        return left;
    }

    public int getValue() {
        return value;
    }

    public TreeItem setLeft(TreeItem leftItem) {
        this.left = leftItem;
        return this.left;
    }

    public TreeItem setRight(TreeItem rightItem) {
        this.right = rightItem;
        return this.right;
    }

    @Override
    public int compareTo(TreeItem treeItem) {
        if(this.value > treeItem.getValue())
            return 1;
        else if(this.value < treeItem.getValue())
            return -1;
        else
            return 0;
    }
}
