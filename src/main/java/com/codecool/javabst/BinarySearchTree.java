package com.codecool.javabst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Skeleton for the Binary search tree. Feel free to modify this class.
public class BinarySearchTree {
    private Node mainNode;

    public Node getMainNode() {
        return mainNode;
    }

    public BinarySearchTree(Node mainNode) {
        this.mainNode = mainNode;
    }

    public static BinarySearchTree build(List<Integer> elements) {
        return new BinarySearchTree(createNode(elements));
    }

    public boolean search(Integer toFind) {
        return searchForElement(toFind, mainNode) != null;
    }

    public void add(Integer toAdd) {
        addElement(toAdd, this.mainNode);
    }

    public void remove(Integer toRemove) {
        List<Integer> elementsFromNode;
        if (this.mainNode.getRoot() == toRemove) {
            elementsFromNode = createListFromNode(this.mainNode, new ArrayList<>());;
            this.mainNode = null;
        } else {
            Node foundNode = searchForAndRemoveElement(toRemove, this.mainNode);
            if (foundNode == null) {
                throw new IllegalArgumentException(toRemove + " Not in current tree");
            }
            elementsFromNode = createListFromNode(foundNode, new ArrayList<>());
        }
        elementsFromNode.remove(toRemove);
        Collections.sort(elementsFromNode);
        if (elementsFromNode.size() > 0) {
            Node newNode = createNode(elementsFromNode);
            if (mainNode == null) {
                mainNode = newNode;
            } else {
                addElement(newNode, this.mainNode);
            }
        }
    }

    private static Node createNode(List<Integer> elements) {
        Node node;
        if (elements.size() == 3) {
            node = new Node(elements.get(1));
            node.setLeft(new Node(elements.get(0)));
            node.setRight(new Node(elements.get(2)));
        } else if (elements.size() == 2) {
            node = new Node(elements.get(0));
            node.setRight(new Node(elements.get(1)));
        } else if (elements.size() == 1) {
            node = new Node(elements.get(0));
        } else {
            int midIndex = elements.size() / 2;
            List<Integer> leftSide = elements.subList(0, midIndex);
            List<Integer> rightSide = elements.subList(midIndex + 1, elements.size());

            Node leftNode = createNode(leftSide);
            Node rightNode = createNode(rightSide);
            node = new Node(elements.get(midIndex));
            node.setLeft(leftNode);
            node.setRight(rightNode);
        }
        return node;
    }

    private Node searchForElement(int elem, Node currentNode) {
        if (currentNode.getRoot() == elem) {
            return currentNode;
        } else if(currentNode.getRoot() > elem && currentNode.getLeft() != null) {
            return searchForElement(elem, currentNode.getLeft());
        } else if(currentNode.getRight() != null) {
            return searchForElement(elem, currentNode.getRight());
        } else {
            return null;
        }
    }

    private Node searchForAndRemoveElement(int elem, Node currentNode) {
        if (currentNode.getLeft() != null && currentNode.getLeft().getRoot() == elem) {
            Node returnedNode = currentNode.getLeft();
            currentNode.setLeft(null);
            return returnedNode;
        } else if (currentNode.getRight() != null && currentNode.getRight().getRoot() == elem) {
            Node returnedNode = currentNode.getRight();
            currentNode.setRight(null);
            return returnedNode;
        } else if(currentNode.getRoot() > elem && currentNode.getLeft() != null) {
            return searchForAndRemoveElement(elem, currentNode.getLeft());
        } else if(currentNode.getRight() != null) {
            return searchForAndRemoveElement(elem, currentNode.getRight());
        } else {
            return null;
        }
    }

    private void addElement(int elem, Node currentNode) {
        if (currentNode.getRoot() > elem) {
            if (currentNode.getLeft() != null) {
                addElement(elem, currentNode.getLeft());
            } else {
                currentNode.setLeft(new Node(elem));
            }
        } else {
            if (currentNode.getRight() != null) {
                addElement(elem, currentNode.getRight());
            } else {
                currentNode.setRight(new Node(elem));
            }
        }
    }


    private void addElement(Node node, Node currentNode) {
        if (currentNode.getRoot() > node.getRoot()) {
            if (currentNode.getLeft() != null) {
                addElement(node, currentNode.getLeft());
            } else {
                currentNode.setLeft(node);
            }
        } else {
            if (currentNode.getRight() != null) {
                addElement(node, currentNode.getRight());
            } else {
                currentNode.setRight(node);
            }
        }
    }

    public List<Integer> createListFromNode(Node currentNode, List<Integer> list) {
        if(currentNode == null) {
            return list;
        }
        list.add(currentNode.getRoot());
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            return list;
        } else if (currentNode.getRight() != null && currentNode.getLeft() != null) {
            list = createListFromNode(currentNode.getRight(), list);
            return createListFromNode(currentNode.getLeft(), list);

        } else if (currentNode.getLeft() != null) {
            return createListFromNode(currentNode.getLeft(), list);
        } else {
            return createListFromNode(currentNode.getRight(), list);
        }
    }


}
