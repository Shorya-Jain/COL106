public class MyStack<T> {
  public T[] arr;
  public int size = -1;
  private int cap = 2;      
    //Make Constructor
    @SuppressWarnings("unchecked")
    public  MyStack(){
        arr = (T[]) new Object[2];
    }
    
    //push
  @SuppressWarnings("ManualArrayToCollectionCopy")
    public void push(T value){
        if(size < cap-1){
            arr[++size]= value;
            
        }
        else{
            cap= (cap+1)*2;
            T[] tarray;
            tarray = (T[]) new Object[cap];
            for(int i = 0; i <=size; i++){
                tarray[i] = arr[i];
            }
            tarray[++size] = value;
            this.arr = tarray;
        }
    }
    
    //pop
    public T pop() throws EmptyStackException{
      if(size !=-1){
          return arr[size--];
      }
      else{
          throw new EmptyStackException("Empty Stack!");
      }
      }
    
    //TOP
    public T top() throws EmptyStackException{
    if(size==-1){
        throw new EmptyStackException("Empty Stack!!");
    }
    else{
        return arr[size];
    }
    }
    //isEmpty
    public boolean isEmpty(){
        if(size == -1){
            return true;
        }
        else{
            return false;
        }
    }
}

class EmptyStackException extends Exception {
        public EmptyStackException(String s) {
            super(s);
        }
    }
    
 