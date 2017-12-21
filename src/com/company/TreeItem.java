package com.company;

public class TreeItem extends ListItem {

    public TreeItem(int value) {
        super(value);
    }

    @Override
    public ListItem left() {
        return this.left;
    }

    @Override
    public ListItem right() {
        return this.right;
    }

    @Override
    public ListItem setLeft(ListItem leftItem) {
        this.left = leftItem;
        return this.left;
    }

    @Override
    public ListItem setRight(ListItem rightItem) {
        this.right = rightItem;
        return this.right;
    }
}
