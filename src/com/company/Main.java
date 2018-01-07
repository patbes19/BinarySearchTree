package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BinarySearchTree exampleTree = new BinarySearchTree();

        desktop(exampleTree);
    }

    public static void desktop(BinarySearchTree currentTree) {

        Scanner userScannerIn = new Scanner(System.in);
        boolean quit = false;
        int userChoice;
        int userValue;
        TreeItem currentItem;

        while(!quit) {
            System.out.print("Choose option: " +
                    "1 - add item, " +
                    "2 - remove item, " +
                    "3 - traverse tree, " +
                    "4 - find item, " +
                    "5 - print tree, " +
                    "9 - quit : ");
            try {
                userChoice = userScannerIn.nextInt();
                userScannerIn.nextLine(); //clear scanner line
            }
            catch(InputMismatchException e) {
                userChoice = 0;
                userScannerIn.nextLine(); //clear scanner line
            }

            switch(userChoice) {
                //TreeItem currentItem;
                case 0:
                    System.out.println("Error, wrong parameter type");
                    break;
                case 1:
                    System.out.print("Enter item to add: ");
                    try {
                        userValue = userScannerIn.nextInt();
                        userScannerIn.nextLine(); //clear scanner line
                    }
                    catch(InputMismatchException e) {
                        userScannerIn.nextLine(); //clear scanner line
                        System.out.println("Unexpected error");
                        break;
                    }

                    currentItem = new TreeItem(userValue);
                    if(currentTree.addItem(currentItem))
                        System.out.println("Item '" + currentItem.getValue() + "' added");
                    else
                        System.out.println("Item '" + currentItem.getValue() + "' already exists");
                    break;
                case 2:
                    System.out.print("Enter item to remove: ");
                    try {
                        userValue = userScannerIn.nextInt();
                        userScannerIn.nextLine(); //clear scanner line
                    }
                    catch(InputMismatchException e) {
                        userScannerIn.nextLine(); //clear scanner line
                        System.out.println("Unexpected error");
                        break;
                    }

                    currentItem = new TreeItem(userValue);
                    if (currentTree.removeItem(currentItem))
                        System.out.println("Item '" + currentItem.getValue() + "' removed");
                    else
                        System.out.println("Item '" + currentItem.getValue() + "' doesn't exist");
                    break;
                case 3:
                    currentTree.traverse();
                    break;
                case 4:
                    System.out.print("Enter item to find: ");
                    try {
                        userValue = userScannerIn.nextInt();
                        userScannerIn.nextLine(); //clear scanner line
                    }
                    catch(InputMismatchException e) {
                        userScannerIn.nextLine(); //clear scanner line
                        System.out.println("Unexpected error");
                        break;
                    }

                    currentItem = new TreeItem(userValue);
                    if (currentTree.findItem(currentItem) != null)
                        System.out.println("Item '" + currentItem.getValue() + "' found");
                    else
                        System.out.println("Item '" + currentItem.getValue() + "' not found");
                    break;
                case 5:
                    currentTree.printTree();
                    break;
                case 9:
                    quit = true;
                    break;
                default:
                    System.out.println("Wrong parameter");
            }
        }

        userScannerIn.close();
    }
}