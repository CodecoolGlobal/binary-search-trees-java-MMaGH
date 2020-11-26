package com.codecool.javabst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            numbers.add(i * 2 + 5);
        }


        BinarySearchTree myTree = BinarySearchTree.build(numbers);

        System.out.println(numbers);

        myTree.remove(69);

        var arrayFromNode = myTree.createListFromNode(myTree.getMainNode(), new ArrayList<>());
        Collections.sort(arrayFromNode);


        System.out.println(arrayFromNode);

        // write some test code here
        System.out.println(myTree.search(7)); // should be true
        System.out.println(myTree.search(55)); // should be true
        System.out.println(myTree.search(34535)); // should be false

        System.out.println(myTree.search(54));

        myTree.add(54);
        System.out.println("adding 54");

        System.out.println(myTree.search(54));

        System.out.println("done");
    }
}