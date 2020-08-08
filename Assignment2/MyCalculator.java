
public class MyCalculator {
     MyStack<Integer> a = new MyStack<>();
     
     
 public static int calculate(String expression){
      
    //Remove all spaces from expression
    expression = expression.replaceAll(" ","");
    int len = expression.length();
    MyStack<String> exp = new MyStack<>();
    int ans = 0;  
    String temp = "";  
      //Start updating both stacks. When encountering closing parenthesis
      //pop nums and ops till opening parenthesis is encountered. then
      //push into nums the calculated value.
      //when string finishes, create string in similar fashion as above and run cal on this new string.
    for(int i = 0; i<len; i++){
        char c = expression.charAt(i);
          
        if(c == '('){
            
              if(temp.equals("")){
                  exp.push("(");
                  try{
                    System.out.println("wfihu raleiu : " +exp.top());
                  }
                  catch(EmptyStackException e){}
                  
                  //continue;
                  
              }
              else{
                  try{
                  System.out.println(exp.top());
                  }catch(EmptyStackException e){}
                  exp.push(temp);
                  try{
                  System.out.println(exp.top());
                  }catch(EmptyStackException e){}
                  temp = "";
                  exp.push("(");
              }
             
          }
          else if(c == ')'){
              if(!temp.equals("")){
                  //continue;
              //}
              //else{
                  exp.push(temp);
                  temp = "";
              }
              //done dealing with temp when bracket closes.
              try{
                  //get exp to evaluate
                String subexp = "";
                
                
                while(!exp.top().equals("(")){
                    //System.out.println(exp.top());
                    subexp = exp.pop().concat(subexp);
                }
                System.out.println("Reached flag1 with subexp = " +subexp);
                
                //get rid of opening parenthesis
                if(exp.top().equals("(")){
                    exp.pop();
                }
                
                System.out.println("Before pushing in calculated subexp, top of exp is: " +exp.top());
                exp.push(Integer.toString(cal(subexp)));   
              }
              catch(EmptyStackException e){
              }
          }
          else{
              temp = temp + Character.toString(c);
          }
      }
    if(!temp.equals("")){
        exp.push(temp);
    }
    
    try{
       // System.out.println(exp.top());
        String subexp = "";
        while(!exp.isEmpty()&& !(exp.top().equals("("))){
            subexp = exp.pop().concat(subexp);
        }
        System.out.println(subexp);
        if(!subexp.equals("")){
            ans = cal(subexp);
            System.out.println("ans is :"+ans);
        }
        else{
            System.out.println("subexp is"+subexp);
        }
        
    }
    catch(EmptyStackException e){}  
    return ans;
 }        
            //Till here we have pushed every integer into nums.
            //Now we begin to deal with operators.
            //push every operator intostack except )
            //if not close, push into ops, else send to eval

//            if(c != ')'){
//                ops.push(Character.toString(c));
//            }
//            
//            //This else block assumes that the - is not followed by (
//            else{
//                String exp = "";
//                try{
//                    while(ops.top().charAt(0) != '('){
//                        exp = Integer.toString(nums.pop()) + exp;
//                        char tempop = ops.pop().charAt(0);
//                        exp = tempop + exp;
//                }
//                    //Now top of ops is (, which we must pop
//                    ops.pop();
//                    //Now push into num the evaluated expression. But while pushing we need to be careful about - before an opening bracket
//                    nums.push(cal(exp));
//                  
//                }
//                catch(MyStack.EmptyStackException e){
//                  
//                }
//            }
      
      
    
     
     
     

      
public static int cal(String s) throws EmptyStackException{
    int len = s.length();
    String temp = "";
    MyStack <Integer> nums = new MyStack<>() ;
    MyStack <String> ops = new MyStack<>() ;
    
    for(int i = 0; i< len; i++){
        if(priority(s.charAt(i)) == 0){
            temp=temp+s.charAt(i);
        }
        else{
            char c = s.charAt(i);
            //push temp into number stack
            //change - to + (-num)
            
            if(s.charAt(i)=='-'){
                nums.push(Integer.parseInt(temp));
                c = '+';
                temp="-";
            }
            else{
                nums.push(Integer.parseInt(temp));
                temp="";
            }
            
            //System.out.println("added to nums : "+ nums.top());
            //if ops empty, then push operator
            if(ops.isEmpty() == true){
                //System.out.println("empty ops flag");
                ops.push(Character.toString(c));
                //System.out.println("Added to ops: " +ops.top());
            }
            else{
                try {
                    //if priority of new operator is less or equal to previous op, 
                    //then pop two previous nums, and prev operator, eval and push that into nums
                    while(priority(c) > priority(ops.top().charAt(0))){
                      int b = nums.pop();
                      int a = nums.pop();
                      char top = ops.pop().charAt(0);
                      nums.push(eval(a, b, top));
                      //System.out.println("Pushed into nums: "+nums.top());
                      //ops.push(Character.toString(c));
                      //System.out.println("Added to ops: " +ops.top()); 
                    }
                    // if priority is higher or equal, push new operator to Stack
                    //else{
                        ops.push(Character.toString(c));
                       // System.out.println("Added to ops: " +ops.top());
                    //}
                }
                catch (EmptyStackException ex) {
                }
            }
        }
    }
    nums.push(Integer.parseInt(temp));
    
    
    //Here we start evaluating the remaining stack
 while(ops.isEmpty() == false){
        try {
            int b = nums.pop();
            int a = nums.pop();
            char top = ops.pop().charAt(0);
            nums.push(eval(a, b, top));
           // System.out.println("Added to nums: " +nums.top());
        } catch (EmptyStackException ex) {
            
        }
 }
 int ans = nums.pop();
 return ans;

}

static int  eval(int a, int b, char c){
    switch(c){
        case '*':
            return(a*b);
            //break;
        case '+':
            return(a+b);
            //break;
        case '-':
            return(a-b);
            //break;
        default:
            return(0);
    }


}


static int priority(char c){
    switch(c){
        case '*':
            return(1);
            //break;
        case '+':
            return(3);
            //break;
        case '-':
            return(2);
            //break;
        default:
            return(0);
    }
    
         
}
     
}