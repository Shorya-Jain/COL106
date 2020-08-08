/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shorya
 */
import java.util.*;



public class mergeSort {
    
public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    System.out.println("Enter array size");
    int n = in.nextInt();
    int ar[] = new int[n];
    for(int i =0; i < n; i++){
       ar[i] = in.nextInt(); 
    }
    int ans[] = merge(ar);
    
    for(int i = 0; i< ans.length; i++){
        System.out.print(ans[i]+ " ");
    }   
}
   public static int[] merge(int[] ar){
       
       if(ar.length == 1){
           return ar;
       }
       else{
           int l[] = Arrays.copyOfRange(ar, 0, (int)ar.length/2);
           int r[] = Arrays.copyOfRange(ar, (int)ar.length/2, (int)ar.length);
           l = merge(l);
           r = merge(r);
           int ans[] = combine(l,r);
           return ans;
       }
   }
   
   private static int[] combine(int[] l, int[] r){
       int len = l.length+ r.length;
       int ans[] = new int[len];
       
       int lind = 0;
       int rind = 0;
       int ansind = 0;
       
       while(lind<l.length && rind< r.length){
           if(l[lind]<r[rind]){
               ans[ansind++] = l[lind++];
           }
           else
               ans[ansind++] = r[rind++];
       }
       
       if(lind == l.length){
           while(rind<r.length){
               ans[ansind++]=r[rind++];
           }
       }
       else if(rind == r.length){
           while(lind<l.length){
               ans[ansind++]=l[lind++];
           }
       }
       return ans;
   }
}

