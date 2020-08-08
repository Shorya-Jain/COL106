package col106.assignment3.BST;

import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E>  {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	
	//write your code here 
BSTNode<T, E> root;
        
    public void insert(T key, E value) {
        //System.out.println("Inserting: "+key + ", "+ value);
        
		BSTNode current = root;
                BSTNode toadd = new BSTNode(key, value);
                
                if(current == null){
                    root = toadd;
                }
                else{
                    //search where to insert
                    BSTNode temparent = current;
                    while(current!=null){
                        E temp = (E) current.value;
                        //temp is value of the current node being accessed
                        if(temp.compareTo(value) < 0){
                            temparent = current;
                            current = current.right;
                        }
                        else{
                            temparent = current;
                            current = current.left;
                        }
                    }
                    
                    current = temparent;
                    
                    E temp = (E) current.value;
                    
                    if(value.compareTo(temp)<0){
                        current.left = toadd;
                        toadd.parent = current;
                    }
                    if(value.compareTo(temp)>0){
                        current.right = toadd;
                        toadd.parent = current;
                    }
                }           
    }

    public void update(T key, E value) {
        //write your code here
        //BSTNode updated = new BSTNode(key, value);
        //System.out.println("Updating key "+key + " to value "+value + ":");
        this.delete(key);
        this.insert(key, value);        
    }

    public void delete(T key) {
        
        //Current contains the node to be deleted, and par contains the parent of current
	BSTNode current = searchnode(key, root);	
        BSTNode par = current.parent;
        
       // System.out.println("Deleting element with key "+key +":");
        
        if(current == root){
            BSTNode a = current.right;
            int fl = 0;
            while(a.left != null){
                a = a.left;
                fl = 1;
            }
            //Now 'a' stores the left most node of right subtree
            
            //Take care of right child of a if exists
            root = a;
            if(fl == 1 && a.right != null){
                a.parent.left = a.right;
            }
            else if(fl == 1 && a.right == null){
                a.parent.left = null;
            }
            
            
            //update children of a
            if(a!=current.right)
                a.right = current.right;
            if(a!= current.left)
                a.left = current.left;
            
            //update parents for children of current
            if(a!= current.left)
                current.left.parent= a;
            if(a!=current.right)
                current.right.parent = a;
            
            //update parent for a
            a.parent = par;

        }
        
        
        //If node to delete is a leaf:
        else if(current.left == null && current.right== null){    
            if(par.left!= null && par.left.key.equals(current.key)){
                par.left = null;
            }
            if(par.right!= null && par.right.key.equals(current.key)){
                par.right = null;
            }
        }
        
        //If it only has one child
        
        else if(current.left == null){
            if(par.left!= null && par.left.key.equals(current.key)){
                par.left = current.right;
            }
            if(par.right!= null && par.right.key.equals(current.key)){
                par.right = current.right;
            }
        }
        else if(current.right == null){
            if(par.left!= null && par.left.key.equals(current.key)){
                par.left = current.left;
            }
            if(par.right!= null && par.right.key.equals(current.key)){
                par.right = current.left;
            }
        }
        //if both child exist
        // Will move leftmost node of r_subtree in place of current.
        else{
            BSTNode a = current.right;
            int fl = 0;
            while(a.left != null){
                a = a.left;
                fl = 1;
            }
            //Now 'a' stores the left most node of right subtree
            
            //Take care of right child of a if exists
            if(fl == 1 && a.right != null){
                a.parent.left = a.right;
            }
            else if(fl == 1 && a.right == null){
                a.parent.left = null;
            }
            
            
            //update children of a
            if(a!=current.right)
                a.right = current.right;
            if(a!= current.left)
                a.left = current.left;
            
            //update parents for children of current
            if(a!= current.left)
                current.left.parent= a;
            if(a!=current.right)
                current.right.parent = a;
            
            //update parent for a
            a.parent = par;
            
            //Update parent's left/right child
            if(par.left != null && par.left.key.equals(current.key)){
                par.left = a;
            }
            if(par.right != null && par.right.key.equals(current.key)){
                par.right = a;
            }   
        }
    }

    public void printBST () {
        //write your code here
        Queue<BSTNode> temp1 = new LinkedList<>(); 
        Queue<BSTNode> temp2 = new LinkedList<>(); 
        
//        System.out.println("Printing BST in level order:");
        
        if (root == null) 
            return; 
       
        
        temp1.add(root); 
   
        while (!(temp1.isEmpty() && temp2.isEmpty())){ 
            
            //Populate temp2, print temp1
            while (!temp1.isEmpty()){ 
                
                if (temp1.peek().left != null){ 
                    temp2.add(temp1.peek().left);
                }
                if (temp1.peek().right != null){
                    temp2.add(temp1.peek().right); 
                }
                System.out.println(temp1.peek().key + ", "+ temp1.peek().value); 
                temp1.remove(); 
            }
            
            //populate temp1, print temp2
            while (!temp2.isEmpty()){ 
  
                if (temp2.peek().left != null){ 
                    temp1.add(temp2.peek().left);
                }
                if (temp2.peek().right != null) {
                    temp1.add(temp2.peek().right); 
                }
                System.out.println(temp2.peek().key + ", "+ temp2.peek().value);
                temp2.remove(); 
            } 
            //System.out.println(); 
        }
    }
    
    BSTNode searchnode(T key, BSTNode cur){
        if(cur == null)
            return null;
        
        if(cur.key.equals(key)){
            return cur;
        }
                
        else{
            if(cur.left != null){
                BSTNode ans = searchnode(key, cur.left);
                if(ans != null){
                    return ans;
                }
            }
            if(cur.right != null){
                BSTNode ans = searchnode(key, cur.right);
                if(ans != null){
                    return ans;
                }
            }
        }
        return null;
    }   
}

class BSTNode<T extends Comparable, E extends Comparable> {
    public E value;
    public T key;
    BSTNode left;
    BSTNode right;
    BSTNode parent;
    
    public BSTNode(T key1, E value1){
        key = key1;
        value = value1;
        left = null;
        right = null;
        parent = null;
    }
}