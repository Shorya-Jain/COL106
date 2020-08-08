
import java.util.*;

public class quickSort {
public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    System.out.println("Enter array size");
    int n = in.nextInt();
    
    ArrayList<Integer> ar = new ArrayList<>();
    
    
    for(int i =0; i < n; i++){
       ar.add(in.nextInt()); 
    }
    ArrayList ans = quick(ar);
    
    for(int i = 0; i< ans.size(); i++){
        System.out.print(ans.get(i)+ " ");
    }   
}
public static ArrayList<Integer> quick(ArrayList<Integer> ar){
    
    int len = ar.size();
    if(len == 0 || len == 1)
        return(ar);
    
    int piv = ar.get(len/2);
    
    ArrayList<Integer> l = new ArrayList<>();
    ArrayList<Integer> g = new ArrayList<>();
    ArrayList<Integer> p = new ArrayList<>();
    
    for(int i =0; i < len; i++){
        
        if(ar.get(i)<piv)
            l.add(ar.get(i));
        else if(ar.get(i)==piv)
            p.add(ar.get(i));
        else
            g.add(ar.get(i));
        
    }
    
    if(!g.isEmpty()){
        g = quick(g);
        p.addAll(g);
    }
    if(!l.isEmpty()){
        l = quick(l);
        l.addAll(p);
        return(l);
    }
    else
        return(p);
    
    
}

}
