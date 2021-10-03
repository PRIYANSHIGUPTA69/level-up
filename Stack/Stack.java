import java.util.*;
public class Stack {

    private int[] arr;
    private int tos;
    private int noOfElements;
    private int MaxCapacity;

    Stack(int size){
        initialize(size);
    }

    Stack(){
        this(10);
    }
    public class StackException extends Exception {
      public StackException( String message )  {
   	        super( message );  // Calls Exception's constructor    
      }
   }

    protected void initialize(int size){
        this.noOfElements = 0;
        this.MaxCapacity = size;
        this.tos = 0;
        this.arr = new int[this.MaxCapacity];
    }
    public void overFlowExcepton () throws Exception{
        if(this.noOfElements ==  this.MaxCapacity){
           throw 'StackOverflow' ;
        }
        
    }
    public void underFlowExcepton () throws Exception{
        if(this.noOfElements ==  0){
           throw new Exception('StackUnderflow');
        }
        
    }
    public void push(int data) throws StackException{
        if ( this.noOfElements  == this.MaxCapacity ){
   	        throw new StackException("Stack overflow");
   	    }
        this.arr[this.noOfElements] = data;
        this.noOfElements++;
    }
    public int peek() throws StackException{
        if ( this.noOfElements  == 0 ){
            throw new StackException("Stack underflow");
        }
        return this.arr[this.noOfElements -1];
    }
    public int pop() throws StackException{
        if ( this.noOfElements  == 0 ){
            throw new StackException("Stack underflow");
        }
        int removeItem = this.arr[this.noOfElements -1];
        this.noOfElements--;
        return removeItem;
    }
    public static void main(String[] args) {
        Stack st  = new Stack();
        try
        {
          st.push(10);
         int peek = st.peek();
         System.out.println(peek);
         int pop = st.pop();
         System.out.println(pop);
         int secondPop = st.pop();
        }
        catch ( StackException e )
        {
           System.out.println("Error detected: " + e.getMessage() );
           System.exit(1);
        }
     }
}