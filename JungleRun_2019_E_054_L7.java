/* 2019/E/054 */

import java.util.*;

/*Class for point coordinates and distance from source*/
class Node
{
	int i;	//row
	int j;	//column
	int distance;		//distance from source

	/*Constructor*/
	public Node(int i, int j,int distance)
	{
		this.i = i;
		this.j = j;
		this.distance = distance;
	}
}


class BreadthFirstSearch
{	
	/* Directions for moving in the 4 sides of matrix */
	static int rowNum[] = {-1, 0, 0, 1}; 
	static int colNum[] = {0, -1, 1, 0};
	static int N;

	/* Function to check whether given cell (row, col)
	   is a valid cell or not. */
	static boolean isValid(int row, int col)
	{
	return (row >= 0) && (row < N) && (col >= 0) && (col < N);
	}
	
	/* Function for calculate the shortest path between
	   a given source cell to a destination cell and return it */	   
   int getShortestDistance(char[][] squareArray,int starti,int startj,int endi,int endj)
   {
	N = squareArray.length;
	/*If source and destination are same, return -2*/
	if ((starti == endi) && (startj == endj))
	{
		return -2;
	}
	
	boolean [][]visited = new boolean[N][N]; //Create a boolean array to keep track of visited cells
	
	visited[starti][startj] = true; //Started cell is visited
	
	Queue<Node> q = new LinkedList<>(); //Make a queue for get shortest path
	
	Node s = new Node(starti,startj, 0);
	q.add(s); 										
	
	int iCoor;
	int jCoor;

	/*loop until queue is empty*/
	while (!q.isEmpty())
	{
		Node current = q.peek();
		iCoor = current.i;
		jCoor = current.j;
		
		/*If destination is found, return distance*/
		if (iCoor == endi && jCoor == endj)
		{
			return current.distance;
		}
		/*If cannot found destination, remove front cell from queue*/
		q.remove();

		/*Loop through all 4 sides of checking cell of matrix*/
		for (int i = 0; i < 4; i++)
		{
			int row = iCoor + rowNum[i];
			int col = jCoor + colNum[i];
			
			/*If cell is valid and not visited and cell character is equal to P or E,
				update the boolean matrix and to the queue */
			if ((isValid(row,col)) && (!visited[row][col]) 
			&&  ((squareArray[row][col] == 'E') ||(squareArray[row][col] == 'P')) )
			{				
				visited[row][col] = true;
				Node Adjcell = new Node(row,col,current.distance + 1) ;
				q.add(Adjcell);
			}
			/*If cell is valid and not visited and cell character is equal to T,
				update the boolean matrix */
			else if ((isValid(row,col)) && (squareArray[row][col] == 'T') && (!visited[row][col]))
			{
				visited[row][col] = false;
			}
		}
	}
	/*If destination is not found, return -1*/
	return -1;	
   }     
}

class JungleRun_2019_E_054_L7 {
    public static void main(String[] args) 				
	{
		Scanner sc = new Scanner(System.in); //Create a scanner object	
		BreadthFirstSearch BFS = new BreadthFirstSearch(); //Create a BreadthFirstSearch object

		/* get user input for size of the jungle */	
		System.out.print("Enter the size of the matrix N x N (N) : " );	
		int N = sc.nextInt(),shortestDistance;

		/* Maximum size of the jungle 30 */
		if(N>30)
		{
			System.out.println("Size of matrix should be less than 30");
			System.exit(0);
		}	

		int starti = 0,startj = 0,endi = 0,endj = 0;
		char[][] squareArray = new char[N][N];
		
		System.out.println("Enter the matrix like as below" + 
			"\n\tSPPPP\n\tTPTPP\n\tTPPPP\n\tPTETT\n\tPTPTT" );	

		System.out.println("Matrix : " );	
	
		/*get user inputs for jungle matrix*/
		for(int i = 0;i < N; i++)		
		{
			String A = sc.next();
			char[] charArray= A.toCharArray();
			
			for(int j = 0;j < N; j++)		
			{
				if(charArray[j] == 'S'){
					starti = i;
					startj = j;
				}
				if(charArray[j] == 'E'){
					endi = i;
					endj = j;
				}
				squareArray[i][j] = charArray[j];			
			}
		}		

		/*User output according to the return values*/
		shortestDistance = BFS.getShortestDistance(squareArray,starti,startj,endi,endj);
		if (shortestDistance == -1) 
		{
			System.out.println("Destination cannot be reached from source");            
        }
		else if (shortestDistance == -2) 
		{
			System.out.println("Destination in source port");            
        }
		else 
		{
            System.out.println("The shortest ditance : " + shortestDistance);
        }	
	}
}