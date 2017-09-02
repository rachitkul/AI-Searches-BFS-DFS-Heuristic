import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Node implements Comparable<Node>
{
	public String state;
	public String parent;
	public String action;
	public int pathCost;
	public int heuristic;
	
	public int compareTo(Node other)
	{
	 if(homework.algorithm.equals("BFS")==true || homework.algorithm.equals("DFS")==true)
	 {
		if(state.compareTo(other.state)==0)
		{
			return parent.compareTo(other.parent);
		}
		return state.compareTo(other.state);
	 }
	 else
	 {
		 if(homework.flag==0)
		 {
			 if(state.compareTo(other.state)==0)
				{
					return parent.compareTo(other.parent);
				}
				return state.compareTo(other.state);
		 }
		 else 
		 {
			 /*Used for Priority Queue*/
			if(pathCost<other.pathCost)
				return -1;
			else if(pathCost>other.pathCost)
				return 1;
			else 
				return 0;
				
		 }
	 }
	}
	Node()
	{
		state="";
		parent=null;
		action="left";
		pathCost=0;
		
	}
	
	Node(String state1,String parent1,int pathCost1)
	{
		state=state1;
		parent= parent1;
		//action=action1;
		pathCost=pathCost1;
		heuristic=0;
	}
	
	Node(String state1,String parent1,int pathCost1,int heuristic1)
	{
		state=state1;
		parent= parent1;
		//action=action1;
		pathCost=pathCost1;
		heuristic=heuristic1;
	}
	
	
	
}

public class homework {
	public String line;
	public static String algorithm;
	public static int flag;
	public String start;
	public String goal;
	public int iterator;
	public int iteratorHeuristic;
	public static File file;
	public static PrintWriter printWriter;
	

	
///////////////////Breadth first search Function
	public int BFS(ArrayList<Node> problem,homework obj)
	{
		
		homework obj1=new homework();
		LinkedList<Node> solution=new LinkedList<>();
		LinkedList<Node> frontier=new LinkedList<Node>();
		LinkedList<Node> explored=new LinkedList<Node>();
		
		
		//Creating Start node
		Node tempNode=new Node(obj.start,null,0);
		
		
		
		if (obj.start.equals(obj.goal)==true)
		{
			printWriter.println(tempNode.state+" "+tempNode.pathCost);
			return 1;
		}
		
		frontier.addLast(tempNode);
		//int i=1;
		while((frontier.isEmpty())!=true)
		{
			
			Node workNode=new Node();
			workNode=frontier.removeFirst();
			explored.addFirst(workNode);
			
			for(Node x:problem)
			{
				
				if(x.parent.equals(workNode.state)== true)
				{
					
					if((obj1.frontierContains(frontier, x) == false) && (obj1.exploredContains(explored, x)==false) )
					{
						x.pathCost=workNode.pathCost+1;
						//Goal found
						if(obj.goal.equals(x.state)== true )
						{
							
							explored.addFirst(x);
							
							
							
							Node t=explored.peekFirst();
							solution.addFirst(t);
							
							for(Node z:explored)
							{
								if(t.parent.equals(z.state))
								{
							
									solution.addFirst(z);
									t=z;
								}
								
							}
							for(Node z:solution)
							{
								printWriter.println(z.state+" "+z.pathCost);
								
							}
							return 1;
							
						}
						
						frontier.addLast(x);
					}
				}	
				
			}
			
		}	
		
			
		
		return -1;
	}
///////////End of Breadth first search	
	
//Start of DFS function
	
	public int DFS(ArrayList<Node> problem,homework obj)
	{
		
		homework obj1=new homework();
		LinkedList<Node> solution= new LinkedList<Node>();
		LinkedList<Node> frontier=new LinkedList<Node>();
		LinkedList<Node> explored=new LinkedList<Node>();
		
		
		//Creating Start node
		Node tempNode=new Node(obj.start,null,0);
		
		
		
		if (obj.start.equals(obj.goal)==true)
		{
			printWriter.println(tempNode.state+" "+tempNode.pathCost);
			return 1;
		}
		
		frontier.addFirst(tempNode);
		
		while((frontier.isEmpty())!=true)
		{
			
			Node workNode=new Node();
			workNode=frontier.removeFirst();//  removeFirst();
			explored.addLast(workNode);			
			if(obj.goal.equals(workNode.state))
			{
				
				//Make solution array-----------**********Remaining
				//printWriter.println(workNode.state+" "+workNode.pathCost);
				solution.addFirst(workNode);
				flag=1;
				while (flag==1)
				{
					for(Node y:explored)
					{
						if(workNode.parent.equals(y.state))
						{
							//printWriter.println(y.state+" "+y.pathCost);
							solution.addFirst(y);
							workNode=y;
							if(workNode.pathCost==0)
							{
								flag=0;
								break;
							}
						}
					
					}
				}
				for(Node y:solution)
					printWriter.println(y.state+" "+y.pathCost);
				return 1;
				
			}
			int size=problem.size();
			//for(Node x:problem)
			for(int itera=size-1;itera>=0;itera--)
			{
				
				if(problem.get(itera).parent.equals(workNode.state)== true)
				{
					
					if((obj1.frontierContains(frontier, problem.get(itera))== false) && (obj1.exploredContains(explored, problem.get(itera))==false) )
					{
						problem.get(itera).pathCost=workNode.pathCost+1;   /*Logical Error of i*/ 
							
						frontier.addFirst(problem.get(itera));	/*Add in first position*/
		
		
					}
					
				}	
				
			}
			
			
		}	
		

		
		return -1;
	}
//End of DFS Function
	
	//Frontier Contains Function
	public boolean frontierContains(PriorityQueue<Node> frontier, Node x)
	{
		for (Node a:frontier)
		{
			if(a.state.equals(x.state)==true)
			{
				return true;
			}
		}
		return false;
	}
	//End Frontier Contains Function
	//Frontier Contains Function
		public boolean frontierContains(LinkedList<Node> frontier, Node x)
		{
			for (Node a:frontier)
			{
				if(a.state.equals(x.state)==true)
				{
					return true;
				}
			}
			return false;
		}
		//End Frontier Contains Function
	
	//Explored Contains Function
	public boolean exploredContains(LinkedList<Node> explored, Node x)
	{
		for (Node a:explored)
		{
			if(a.state.equals(x.state)==true)
			{
				return true;
			}
		}
		return false;
	}
	//End Explored Contains Function
	
////Start of Uniform Cost Search
	public int UCS(ArrayList<Node> problem,homework obj)
	{
		
		homework obj1=new homework();
		ArrayList<Node> solution=new ArrayList<Node>(); 
		PriorityQueue<Node> frontier=new PriorityQueue<Node>();	//to implement Priority queue
		LinkedList<Node> explored=new LinkedList<Node>();
		
		
		//Creating Start node
		Node tempNode=new Node(obj.start,null,0);
		
		
		
		if (obj.start.equals(obj.goal)==true)
		{
			printWriter.println(tempNode.state+" "+tempNode.pathCost);
			return 1;
		}
		
		frontier.add(tempNode);
		
								
		while((frontier.isEmpty())!=true)
		{
			
			Node workNode=new Node();
												//To sort the Queue according to Path cost*(will happen automatically)
												//Goal Checking*
			workNode=frontier.remove();
			if(obj.goal.equals(workNode.state)== true )		
			{
				
				explored.addFirst(workNode);
				
																		//Prepare solution then go for Printing(Write a method)*
							//Preparing solution Method
				solution.add(workNode);
				//int size=explored.size();
				for (Node z:explored)									//Logical error
				{
					//For debugging
					//System.out.println(z.state + " " +z.pathCost+" "+z.parent);   /*logical Error of duplicate entries being inserted*/
						if(z.state.equals(workNode.parent))
						{
							solution.add(z);
							workNode=z;
						}
						
				}
				
				
				int size=solution.size();
				
				//just to see explored set
				for(int b=size-1;b>=0;b--)									//change explored with solution*
				{
					printWriter.println(solution.get(b).state+" "+solution.get(b).pathCost);
				}
				return 1;
				
			}
			
			explored.addFirst(workNode);  //Change addLast to addFirst*
			
			for(Node g:problem)
			{
				Node x=new Node(g.state,g.parent,g.pathCost,g.heuristic);
				
				if(x.parent.equals(workNode.state)== true)
				{
					
					
					if((obj1.frontierContains(frontier, x) == false) && (obj1.exploredContains(explored, x)==false) )
					{
						x.pathCost=x.pathCost+workNode.pathCost;	//PathCost=x.pathCost+workNode.pathCost*
						frontier.add(x);
					}					
					//else if (frontier.contains(x) == true && x.pathCost < that node in frontier.pathCost)*  then replace
					else if(obj1.frontierContains(frontier, x)==true )
					{
						x.pathCost=x.pathCost+workNode.pathCost;
						//System.out.println(x.state+" "+x.pathCost);
						int f1=0;
						
						for(Iterator<Node> iter=frontier.iterator();iter.hasNext();)
						{
							Node y=iter.next();
							
							if((y.state.equals(x.state))&&( x.pathCost < y.pathCost))
							{
								
								//System.out.println("Entered replace section for"+ " " +x.state+" "+x.pathCost);
								//System.out.println("Replacing "+y.state+" "+y.pathCost);
								//replace the state and parent
								iter.remove();
								f1=1;
								break;
							
							}
							
						}
						if(f1==1)
						{
							frontier.add(x);
						}
					}
					else if(obj1.exploredContains(explored, x)== true)
					{
						x.pathCost=x.pathCost+workNode.pathCost;
						int f1=0;
						for(Iterator<Node> iter = explored.iterator(); iter.hasNext();)  //Node y:frontier
						{
							Node y=iter.next();
							
							if(y.state.equals(x.state)==true && y.pathCost > x.pathCost)
							{
								
								//replace the state and parent
								iter.remove();
								f1=1;
								break;
								//frontier.add(x);
							
							}
							
						}
						if(f1==1)
						{
							frontier.add(x);
						}
					}
				}	
				
			}
			
		}	
		
			
		
		return -1;
	}

///End of Uniform Cost search

////Start of A-star algorithm
	public int Astar(ArrayList<Node> problem,homework obj)
	{
		
		homework obj1=new homework();
		ArrayList<Node> solution=new ArrayList<Node>(); 
		PriorityQueue<Node> frontier=new PriorityQueue<Node>();	//to implement Priority queue
		LinkedList<Node> explored=new LinkedList<Node>();
		
		
		//Creating Start node
		Node tempNode=new Node(obj.start,null,0);
		
		
		
		if (obj.start.equals(obj.goal)==true)
		{
			printWriter.println(tempNode.state+" "+tempNode.pathCost);
			return 1;
		}
		
		frontier.add(tempNode);
		
		while((frontier.isEmpty())!=true)
		{
			
			Node workNode=new Node();
												//To sort the Queue according to Path cost*(will happen automatically)
												//Goal Checking*
			workNode=frontier.remove();
			
				
			if(obj.goal.equals(workNode.state)== true )		
			{
				
				explored.addFirst(workNode);
				workNode.pathCost=workNode.pathCost-workNode.heuristic;
																		//Prepare solution then go for Printing(Write a method)*
							//Preparing solution Method
				solution.add(workNode);
				for (Node z:explored)
				{
					//For debugging
					//System.out.println(z.state + " " +z.pathCost+" "+z.parent);   /*logical Error of duplicate entries being inserted*/
						if(z.state.equals(workNode.parent))
						{
							z.pathCost=z.pathCost-z.heuristic;
							solution.add(z);
							workNode=z;
						}
						
				}
				
				
				int size=solution.size();
				
				
				for(int b=size-1;b>=0;b--)									//Printing Solution
				{
					printWriter.println(solution.get(b).state+" "+solution.get(b).pathCost);
				}
				return 1;
				
			}
			
			explored.addFirst(workNode);  //Change addLast to addFirst*
			
			
			for(Node g:problem)
			{
				Node x=new Node(g.state,g.parent,g.pathCost,g.heuristic);
				
				if(x.parent.equals(workNode.state)== true)
				{
				
					
					if((obj1.frontierContains(frontier, x) == false) && (obj1.exploredContains(explored, x)==false) )
					{
						x.pathCost=x.pathCost+x.heuristic+(workNode.pathCost-workNode.heuristic);	//PathCost=x.pathCost+workNode.pathCost*
						
						frontier.add(x);
					}					
					else if(obj1.frontierContains(frontier, x)==true )
					{
						x.pathCost=x.pathCost+x.heuristic+(workNode.pathCost-workNode.heuristic);
						
						int f1=0;
						for(Iterator<Node> iter = frontier.iterator(); iter.hasNext();)  //Node y:frontier
						{
							Node y=iter.next();
							
							if(y.state.equals(x.state)==true && y.pathCost > x.pathCost)
							{
								
								//replace the state and parent
								iter.remove();
								f1=1;
								break;
								//frontier.add(x);
							
							}
							
						}
						if(f1==1)
						{
							frontier.add(x);
						}
					}
					else if(obj1.exploredContains(explored, x)== true)
					{
						x.pathCost=x.pathCost+x.heuristic+(workNode.pathCost-workNode.heuristic);
						int f1=0;
						for(Iterator<Node> iter = explored.iterator(); iter.hasNext();)  //Node y:frontier
						{
							Node y=iter.next();
							
							if(y.state.equals(x.state)==true && y.pathCost > x.pathCost)
							{
								
								//replace the state and parent
								iter.remove();
								f1=1;
								break;
								//frontier.add(x);
							
							}
							
						}
						if(f1==1)
						{
							frontier.add(x);
						}
					}
				}	
				
			}
			
		}	
		
			
		
		return -1;
	}
///End of A-star algorithm	
	
/////////////////Start of Main function	
	public static void main(String[] args) {
		
		homework hw=new homework();
		BufferedReader bufferedReader=null;
		ArrayList<Node> arrayList= new ArrayList<Node>();
		file=new File("output.txt");
		
		
		
		//Printing solution into a output file
		
			try 
			{
					file.createNewFile();
					
					
					printWriter= new PrintWriter(file);
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Node tempNode;
		
		try
		{
			bufferedReader = new BufferedReader(new FileReader("input.txt"));
			
		//Reading the file input	
			algorithm=bufferedReader.readLine();
			hw.start = bufferedReader.readLine();
			hw.goal=bufferedReader.readLine();
			hw.iterator=Integer.parseInt(bufferedReader.readLine());
				
		//Reading and inserting the problem set in an ArrayList	
			while(hw.iterator>0)
			{
				String[] str=bufferedReader.readLine().split("\\s", 3);
				tempNode= new Node(str[1],str[0],Integer.parseInt(str[2]));
				arrayList.add(tempNode);
				hw.iterator--;
				//System.out.println(line);
			}
			hw.iteratorHeuristic=Integer.parseInt(bufferedReader.readLine());
			while(hw.iteratorHeuristic>0)
			{
				String[] str1=bufferedReader.readLine().split("\\s", 2);
				for(Node b:arrayList)
				{
					if(b.state.equals(str1[0]))
					{
						b.heuristic=Integer.parseInt(str1[1]);
					}
				
				}
				hw.iteratorHeuristic--;
			}
			bufferedReader.close();
			//Sorting the ArrayList
			flag=0;
			
			/*No need for sorting as live traffic information is ordering*/
			//if(algorithm.equals("DFS")==true)
			//	Collections.sort(arrayList,Collections.reverseOrder());
			/*
			else
				Collections.sort(arrayList);
				*/
			switch (algorithm)
			{
				case "BFS": hw.BFS(arrayList,hw);break;
				
				case "DFS": 
							hw.DFS(arrayList,hw);break;
				
				case "UCS":
							flag=1;
							hw.UCS(arrayList,hw);break;
				
				case "A*": flag=1;
							hw.Astar(arrayList,hw);
							break;
				
			}
			
		}
		catch(IOException e	)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				bufferedReader.close();
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
/////////////////End of Main Function

}
