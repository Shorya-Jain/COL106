package col106.assignment3.Heap;

import java.util.ArrayList;

public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	
    /**
     *arr will store nodes
     */
    public ArrayList<node> arr;
        
        public Heap(){
            this.arr = new ArrayList<>();
        }
        
        
	public void insert(T key, E value) {
           // System.out.println("Inserting: "+key+", "+value);
            //add node add to the end
            node toadd = new node(key, value);
            arr.add(toadd);

            //Bubble Up
            this.bubbleup(arr.size()-1);
	}
        
        public void bubbleup(int i){
            //Here i is the index that needs to be bubbled up
            if(i == 0)
                return;
            
            while(i > 0){
                node a = arr.get(i);
                node par = arr.get((i-1)/2);
                if(a.value.compareTo(par.value)>0){
                    swap(i, (i-1)/2);
                    i = (i-1)/2;
                }
                else
                    return;
            }
        }
        
        public void swap(int i1, int i2){
            node temp = arr.get(i1);
            arr.set(i1, arr.get(i2));
            arr.set(i2, temp);
        }
        
        
	public E extractMax() {
            E ans = (E) arr.get(0).value;
            delete((T) arr.get(0).key);
            return ans;
	}

	public void delete(T key) {
            if (arr.isEmpty())
                return;

            int len = arr.size();
            int ind = 0;
            int i;
            for(i = 0; i < len; i++){
                if (arr.get(i).key== key){
                    ind = i;
                    break;
                }
            }
            if (i==len)
                return;
            // Now we have index of the node to delete in ind

            //Swap last element with element to delete             
            swap(ind, len-1);

            //delete last element
            arr.remove(len-1);
            len = len-1;
            if(ind == len){
                return;
            }
            //if root, then bubbledown
            if(ind == 0)
                bubbledown(0);
            //if leaf then bubble up
            else if(ind> (arr.size()-1)/2)
                bubbleup(ind);
            
            else{
                //if bigger than parent then bubble up
                if(arr.get((ind-1)/2).value.compareTo(arr.get(ind).value)<0)
                    bubbleup(ind);
                //otherwise bubble down
                else
                    bubbledown(ind);
            }
        }

	public void increaseKey(T key, E value) {
            if (arr.isEmpty())
                return;

            int len = arr.size();
            int ind = 0;
            int i;
            for(i=0; i < len; i++){
                if (arr.get(i).key== key){
                    ind = i;
                    break;
                }
            }
            if(i==len)
                return;
            // Now we have index of the node to update

            node a = arr.get(ind);
            a.value = value;
            
            //Since a.value has increased, we might need to bubble it up
            if(ind != 0)
                bubbleup(ind);
	}

	public void printHeap(){            
            for(int i = 0; i < arr.size(); i++){
                node a = arr.get(i);
                System.out.println(a.key + ", "+ a.value);
            }
	}
        
        
        public void bubbledown(int a) {
            
            while((2*a)+1<arr.size()){
                
                //Both children exist
                if(2*a+2<arr.size()){
                    node l = this.arr.get(2*a+1);
                    node r = this.arr.get(2*a+2);
                    node x = this.arr.get(a);
                    
                    //l_child larger
                    if(l.value.compareTo(r.value)>0) {
                        if(l.value.compareTo(x.value)>0) {
                            swap(a, 2*a+1);
                            a = 2*a+1;
                        }
                        else return;
                        }
                    else{
                    //r_child larger   
                        if(l.value.compareTo(r.value)<0) {
                            if(r.value.compareTo(x.value)>0) {
                                swap(a, 2*a+2);
                                a = 2*a +2;
                            }
                            else return;
                        }
                    }
                }
                
                //only left
                else{
                    node l = this.arr.get(2*a+1);
                    node x = this.arr.get(a);
                    if(l.value.compareTo(x.value)>0) {
                            swap(a, 2*a+1);
                            a = 2*a +1;
                    }
                    else return;
                }
            }
        }       
}

class node<T extends Comparable, E extends Comparable> {
    
    public E value;
    public T key;   
    
    public node(T key1, E value1){
        value = value1;
        key = key1;
    }
}