/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package col106.assignment3.BST;

import col106.assignment3.BST.BST;

/**
 *
 * @author shorya
 */
public class test {
 public static void main(String[] args){   
//    BSTNode <Integer, Integer> ab = new BSTNode<>(1, 1);
    BST<Integer, Integer> abc = new BST<>(123,456);
    BSTNode <Integer, Integer> ab = abc.root;
    abc.insert(10,10); 
    BSTNode qwe = abc.searchnode(10, abc.root);
    //System.out.println(qwe.value);
    abc.printBST();
    abc.delete(10);
    abc.printBST();
    
 }
}

