/**
 *
 * @author shorya
 */
public class StackSort {
    
    public static String[] sort(int[] nums) {
        int len = nums.length;
        String[] ans = new String[len*2];
        //int notp = 0;
        MyStack<Integer> sta = new MyStack<>(); 
        MyStack<Integer> popped = new MyStack<>(); 
        //First element must be pushed so push, and update ans accordingly
        sta.push(nums[0]);
        ans[0]="PUSH";
        
        
        //int lp = nums[0];
        //lp will track the last popped value. Incase the next value we want to pop is less than lp, then return NOT POSSIBLE
        int j = 1; //keep track of ans element to fill
        for(int i = 1; i< len; i++){
            try {
                if(!sta.isEmpty() && nums[i]>sta.top()){
                    //int k = 0;
                    while(!sta.isEmpty() && sta.top() < nums[i]){
                        int temp = sta.pop();
                        ans[j++]="POP";
//                        System.out.println("temp is :"+temp);
//                          System.out.println("top of popped is :"+popped.top());
                        
                        //System.out.println("Flag1");
                        //Check for Not Possible
                        if(!popped.isEmpty() && temp < popped.top()){
                            //System.out.println(popped.top());
                            String[] fl = new String[1];
                            fl[0] = "NOTPOSSIBLE";
                            return fl;
                        }
                        popped.push(temp);
                       // lp = temp;
                        //System.out.println("Loop  iteration finished");
                        //System.out.println("Loop  iteration finished");
                       // System.out.println(sta.top());
                    }
                    //System.out.println("Flag2");
                    sta.push(nums[i]);
                    ans[j++]="PUSH";
                    
                }
                else{
                    sta.push(nums[i]);
                    ans[j++]="PUSH";
                    if(!popped.isEmpty() && sta.top() < popped.top()){
                          //  System.out.println(popped.top());
                            String[] fl = new String[1];
                            fl[0] = "NOTPOSSIBLE";
                            return fl;
                        }
                    
                    
                    //System.out.println("Flag3");
                } 
            } catch (EmptyStackException ex) {}
        }
        
        while(!sta.isEmpty()){
            try{
            sta.pop();
            ans[j++]="POP";
           // System.out.println("Flag4");
            }
            catch(EmptyStackException ex){}
        }
        
        return ans;
    }
     public String[][] kSort(int[] nums){
        return null;
    }
}
