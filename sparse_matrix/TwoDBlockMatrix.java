import java.util.*;
import java.text.DecimalFormat;


public class TwoDBlockMatrix{
    float[][] tarray;
    
//Constructor
    
    public TwoDBlockMatrix(float[][] arr){
        tarray=arr;
    }
    
//Builder Method    
    
    static TwoDBlockMatrix buildTwoDBlockMatrix(java.io.InputStream in){
        Scanner br;
        br = new Scanner(in);
        String text;    //Reading String
        List <String> A = new ArrayList<String>();
        while(br.hasNextLine()){
            A.add(br.nextLine());
        }
        //text = br.nextLine();
        br.close();
        
        
        //Get Dimensions of Array
        int maxrow = 0, maxcol = 0;
        int currow = 0, curcol = 0;
        int i=0;
        int flag=1;
        while(i<A.size()){
            //System.out.println(i)
            if(A.get(i).equals("#")){
                maxrow=Math.max(maxrow,currow-1);
                maxcol=Math.max(maxcol,curcol-1);
                currow=0;
                curcol=0;
                flag=1;
            }
            else{
                if(flag==1){
                    flag=0;
                    currow=Character.getNumericValue(A.get(i).charAt(0));
                    curcol=Character.getNumericValue(A.get(i).charAt(2));
                }
                else{
                    String[] temp=A.get(i).substring(0,A.get(i).length()-1).split(" ");
                    curcol+=temp.length;
                    int j = i;
                    while(!A.get(j).equals("#")){
                        currow++;
                        j++;
                    }
                    i = j-1;
                }    
            }
            i++;
        }
        
        // Populate Matrix and return it into constructor
        float[][] matrix = new float[maxrow][maxcol];
        i=0;
        flag = 1;
        while(i<A.size()){
            if(A.get(i).equals("#")){
                flag = 1;
                i++;
            }
            else{
                if(flag == 1){
                    currow = Character.getNumericValue(A.get(i).charAt(0))-1;
                    curcol = Character.getNumericValue(A.get(i).charAt(2))-1;
                    flag = 0;
                }
                else{
                    int j = i;
                    while(!A.get(j).equals("#")){
                        String[] temp=A.get(j).substring(0,A.get(j).length()-1).split(" "); //was.get(i)
                        for(int k = 0; k<temp.length; k++){
                            matrix[currow][curcol+k] = Float.parseFloat(temp[k]);
                        }
                        j++;
                        currow++;
                    }
                    i = j-1;
                }
            i++;
            }
            //i++;
        }
        
        //Send matrix into constructor
        TwoDBlockMatrix abc = new TwoDBlockMatrix(matrix);
        return abc;
    }
    
    
    //convert matrix into input-format styled matrix
    
   public String toString(){
        int maxrow = tarray.length;
        int maxcol = tarray[0].length;
        
        DecimalFormat format2dec = new DecimalFormat("0.00");
        
        boolean vis[][] = new boolean[maxrow][maxcol];
        int i;
        String s = "";
        for(i=0;i<maxrow;i++)
        {
            int j = 0;
            while(j<maxcol)
            {          
                while(j<maxcol && (vis[i][j] == true || tarray[i][j] == 0))
                   j++;
                if(j == maxcol)
                   break;
                int k = j;
                int start = k;
                while(k < maxcol && tarray[i][k]!=0 && vis[i][k] == false)
                    k++;
                int end = k;
                s = s + Integer.toString(i+1) + " "+ Integer.toString(start+1)+ '\n';
                int l;
                for(l = start;l<end-1;l++)
                {
                    s = s + Float.toString(tarray[i][l]) + " ";
                    vis[i][l] = true;
                }
                s = s + Float.toString(tarray[i][l]) + ";" + '\n';
                vis[i][l] = true;
                int row = i+1;
                while(row < maxrow)
                {
                    int check = 1;
                    for(int col = start;col < end;col++)
                    {
                        if(vis[row][col] == true || tarray[row][col] == 0)
                        {
                            check = 0;
                            break;
                        }
                    }
                    if(check == 0)
                         break;
                    
                    for(l = start;l<end-1;l++)
                    {
                        s = s + format2dec.format(tarray[row][l]) + " ";
                        vis[row][l] = true;
                    }
                    s = s + format2dec.format(tarray[row][l]) + ";" + '\n';
                    vis[row][l] = true;
                    row++;
                }
                s = s + "#" + '\n';
                j = k;    
            }
        }
        return s;
    }

    public TwoDBlockMatrix transpose(){
        
        int maxrow = tarray.length;
        int maxcol = tarray[0].length;
        
        //ret will have opposite dimensions as tarray
        float ret[][] = new float[maxcol][maxrow];
        
        for(int i = 0; i< maxcol; i++){
            for(int j = 0; j< maxrow; j++){
                ret[i][j] = tarray[j][i];
            }
        }
        
        
        
        TwoDBlockMatrix abc = new TwoDBlockMatrix(ret);
        return abc;
    }
    
    
    
    public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException {
        //return tarray * other
        float arr2[][] = other.tarray;
        int row1 = tarray.length;
        int col1 = tarray[0].length;
        int row2 = arr2.length;
        int col2 = arr2[0].length;
        float ans[][] = new float[row1][col2];
        
        if(col1 != row2){
            throw new IncompatibleDimensionException("Incompatible Dimensions");                   
        }
        else{
            for(int i =0; i< row1; i++){
                for(int j =0; j < col2; j++){
                    float temp = 0;
                    for(int k = 0; k < col1; k++){
                        temp = temp + ((tarray[i][k])*arr2[k][j]);
                    }
                ans[i][j] = temp;
                }
            }        
        }
        TwoDBlockMatrix abc = new TwoDBlockMatrix(ans);
        return abc;
    
    }
    
    public TwoDBlockMatrix getSubBlock(int row_start, int col_start, int row_end, int col_end) throws SubBlockNotFoundException{
        int row = tarray.length;
        int col = tarray[0].length;
        if(((row_start >= row_end) || (col_start >=col_end)) || ((row_end > (row+1)) || (col_end >(col+1)))){
            throw new SubBlockNotFoundException("Invalid dimensions for input");
        }
        else{
            try
            {
                int numrow = row_end-row_start;
                int numcol = col_end-col_start;
                float ans[][] = new float[numrow][numcol];
                
                for(int i = 0; i < numrow; i++){
                    for(int j = 0; j < numcol; j++){
                        ans[i][j] = tarray[i+row_start-1][j+col_start-1];
                    }
                }
                TwoDBlockMatrix abc = new TwoDBlockMatrix(ans);
                return abc;
            
            }
            catch(Exception e){
                throw new SubBlockNotFoundException("Subblock doesn't exist");
            }
            
        }
        
    }
    public TwoDBlockMatrix inverse() throws InverseDoesNotExistException{
        int row1 = tarray.length;
        int col1 = tarray[0].length;
        
        if(row1 != col1){
            throw new InverseDoesNotExistException("Not square matrix!");
        }
        else{
//            if(det(this) == 0){
//                throw new InverseDoesNotExistException("Inverse does not exist");
//            }
//            
//            else{
//                        
            try{
            float ans[][] = new float[row1][row1];
            
                //Make cofactors
                for (int i = 0; i < row1; i++)
			for (int j = 0; j < row1; j++)
				ans[i][j] = (float) (Math.pow(-1, i + j)* det(minor(this, i, j)));

		// adjugate and determinant
		float findet = 1 / det(this);
		for (int i = 0; i < row1; i++) {
			for (int j = 0; j <= i; j++) {
				float temp = ans[i][j];
				ans[i][j] = (ans[j][i] * findet);
				ans[j][i] = temp * findet;
			}
		}
            TwoDBlockMatrix abc = new TwoDBlockMatrix(ans);
            return abc; 
            
            
            }
            catch(Exception e){
                throw new InverseDoesNotExistException("No inverse exists ");
            }
            
            }
        
        //}
    }


    
    
    private float det(TwoDBlockMatrix abc) throws InverseDoesNotExistException{
        //only takes square matrix
        try{
        int size = abc.tarray.length;
        
        if(size == 1){
            return abc.tarray[0][0];
        }
        else{
            if(size ==2){
                return ((abc.tarray[0][0])*(abc.tarray[1][1]))- ((abc.tarray[0][1])*(abc.tarray[1][0]));
            }
            else{
                float ans = 0;
                boolean flag = false;
                for(int i = 0; i<size; i++){
                    float submat[][] = new float[size-1][size-1];
                    
                   
                    if(flag = false){
                        
                        ans = ans + (abc.tarray[0][i])*det(minor(abc, 0, i));
                        flag = !flag;
                    }
                    else{
                    
                        ans = ans - (abc.tarray[0][i])*det(minor(abc, 0, i));
                        flag = !flag;
                    }       
                }
            return ans;
            }
        }
        }
        catch(InverseDoesNotExistException e){
            throw new InverseDoesNotExistException("Inverse doesn't exist");
        }
    }

    private TwoDBlockMatrix minor(TwoDBlockMatrix abc, int x, int y) {
        int size = abc.tarray.length;
        float ans[][] = new float[size-1][size-1];
        
        int row1 = 0, col1 = 0;
        
        for(int i = 0; i < size; i++){
            if(i != x){
                for(int j =0; j< size; j++){
                    if(j!= y){
                        ans[row1][col1] = abc.tarray[i][j];
                        row1++;
                        col1++;
                    }
                }
            }
        }
        TwoDBlockMatrix ret= new TwoDBlockMatrix(ans);
        return ret;
    }
   
    
    
    
    
    






}

class InverseDoesNotExistException extends Exception
{
	public InverseDoesNotExistException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
}

class IncompatibleDimensionException extends Exception
{
	public IncompatibleDimensionException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
}

class SubBlockNotFoundException extends Exception
{
	public SubBlockNotFoundException(String s)
	{
	// Call constructor of parent Exception
		super(s);
		}
}



