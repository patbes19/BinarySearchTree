package com.company;

public abstract class ListItem implements Comparable<ListItem> {

    protected ListItem left;
    protected ListItem right;
    protected int value;

    public ListItem(int value) {
        this.value = value;
    }

    public ListItem getRight() {
        return right;
    }

    public ListItem getLeft() {
        return left;
    }

    public int getValue() {
        return value;
    }

    public abstract ListItem left();
    public abstract ListItem right();
    public abstract ListItem setLeft(ListItem leftItem);
    public abstract ListItem setRight(ListItem rightItem);

    @Override
    public int compareTo(ListItem listItem) {
        if(this.value > listItem.getValue())
            return 1;
        else if(this.value < listItem.getValue())
            return -1;
        else
            return 0;
    }
}