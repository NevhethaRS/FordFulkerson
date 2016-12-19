//=============================================================================================================================================================================================//
//	  							*** FordFulkerson:This program computes the maximum flow in the given graph using Ford Fulkerson algorithm ***    												   //
//=============================================================================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
 */
//=============================================================================================================================================================================================//
import java.util.ArrayList;
import java.util.Scanner;

public class FordFulkerson {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:							description:	
	 *  					
	 *  @constructor: 		-constructorSignature:																description:
	 *  
	 *  @memberFunction: 	-methodSignature:																	description:
	 *  					-public int fordFulkerson(Graph g,Scanner in):										finds the maximum flow for the graph
	 *  					-private void fordFulkerson(Graph g, Vertex source, Vertex sink):					finds an augmented flow for the residual graph
	 *  					-private void reset(Graph g):														resets the flags from previous bfs
	 *  					-private int minimumCapcityEdge(ArrayList<Vertex> path):							finds the edge with minimum capacity along the path
	 *  					-private void assignFlow(Graph g, ArrayList<Vertex> path, int minAlongPath):		Augments the new flow to previous flow
	 *  					-private void assignReverseFlow(Graph g, int minAlongPath, ArrayList<Vertex> path): updates the residual graph
	 *						-private int maximumFlow(Graph g,Vertex src):										calculates the maximum flow from the source	
	 *						-private ArrayList<Vertex> buildPath(Vertex src, Vertex sink):						builds a path from source to sink according to bfs
	 *					
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-fordFulkerson(...) is a function to find an augmented flow for the residual graph
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										residual graph for which the augmented flow is to be found
	 *  					-in_Scanner:									to read input from console
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-source_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					-sink_Vertex:									Vertex from which an augmenting flow is to be found
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_int:											maximum flow possible in the graph from source to sink
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	public int fordFulkerson(Graph g,Scanner in) {
		Vertex source;
		Vertex sink;
		System.out.println("Source:");
		source=g.getVertex(in.nextInt());
		System.out.println("Sink:");
		sink=g.getVertex(in.nextInt());
		in.close();
		System.out.println();
		fordFulkerson(g,source,sink);
		return maximumFlow(g,source);		
	}
	
	 
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-fordFulkerson(...) is a function to find an augmented flow for the residual graph
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										residual graph for which the augmented flow is to be found
	 *  					-source_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					-sink_Vertex:									Vertex from which an augmenting flow is to be found
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-isPath_Boolean									Flag that helps to check if there is an augmenting path
	 *  					-path_ArrayList<Vertex>:						Augmenting Path
	 *  					-v_Vertex:										vertex in the graph
	 *  					-minAlongPath_int:								minimum capacity along the augmenting path
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Vertex:										vertex whose number(index) is n
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private void fordFulkerson(Graph g, Vertex source, Vertex sink) {
		boolean ispath=false;
		ispath=g.isPath(source,sink);
		if(!ispath)
			return ;
		else{
			ArrayList<Vertex> path=new ArrayList<>();
			path=buildPath(source,sink);
			reset(g);
			int minAlongPath=minimumCapcityEdge(path);
			assignFlow(path,minAlongPath);
		}
		fordFulkerson(g,source,sink);
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-reset(...) is a function to find to reset the flags of the vertices after a bfs
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-g_Graph:										residual graph for which the augmented flow is to be found
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					-v_Vertex:										vertex in the graph
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Vertex:										vertex whose number(index) is n
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private void reset(Graph g) {
		for(Vertex v:g){
			v.seen=false;
			v.parent=null;
		}
		
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-buildPath(...) is a function to build path from source to sink after a bfs
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *    					-src_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					-sink_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					
	 *  @return:			-variableName_dataType:							description:
	 *  					-path_ArrayList<Vertex>:						Augmenting path from source to sink
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private ArrayList<Vertex> buildPath(Vertex src, Vertex sink) {
		ArrayList<Vertex> path=new ArrayList<>();
		path.add(0,sink);
		Vertex v=sink;
		while(v!=src){
			path.add(0,v.parent);
			v=v.parent;
		}
		return path;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-minimumCapacityEdge(...) is a function to find a minimum capacity along the augmenting path
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *    					-src_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					-sink_Vertex:									Vertex from which an augmenting flow is to be found
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					
	 *  @return:			-variableName_dataType:							description:
	 *  					-path_ArrayList<Vertex>:						Augmenting path from source to sink
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private int minimumCapcityEdge(ArrayList<Vertex> path) {
		int minCapacity=Integer.MAX_VALUE;
		Vertex u,v=null;
		int i=0;
		u=path.get(i);
		while(i<path.size()-1){
			for(Edge e:u.adj){
				v=e.otherEnd(u);
				if(v==path.get(i+1)){
					if(e.capacity>0&&minCapacity>e.capacity)
						minCapacity=e.capacity;
					break;
				}
			}
			u=v;
			i++;
		}
		return minCapacity;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-assignFlow(...) is a function to assign the flow along the augmenting path
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *    					-path_ArrayList<Vertex>:						path for which the flow is to be assigned
	 *  					-minAlongPath_int:								flow value
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					-i_int:											index of the current vertex
	 *  					-v_Vertex;										Next vertex along the path
	 *  					-u_Vertex:										current vertex along the path
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private void assignFlow(ArrayList<Vertex> path, int minAlongPath) {
		Vertex u,v=null;
		int i=0;
		while(i<path.size()-1){
			u=path.get(i);
			for(Edge e:u.adj){
				v=e.otherEnd(u);
				if(v==path.get(i+1)){
					e.flow+=minAlongPath;
					e.capacity-=minAlongPath;
					break;
				}
			}
			i++;
		}
		assignReverseFlow(minAlongPath,path);
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-assignFlow(...) is a function to calculate the residual capacities
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *    					-path_ArrayList<Vertex>:						path for which the flow is to be assigned
	 *  					-minAlongPath_int:								flow value that was assigned
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					-i_int:											index of the current vertex
	 *  					-v_Vertex;										current vertex along the path
	 *  					-u_Vertex:										Next vertex along the path
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private void assignReverseFlow(int minAlongPath, ArrayList<Vertex> path) {
		Vertex v=null;
		Vertex u=null;
		int i=0;
		while(i<path.size()-1){
			int flag=0;
			u=path.get(i+1);
			for(Edge e:u.adj){
				v=e.otherEnd(u);
				if(v==path.get(i)){
					e.capacity+=minAlongPath;
					flag=1;
					break;
				}
			}
			if(flag==0){
				//u.adj.add(new Edge(u,path.get(i),minAlongPath,true));
			}
			i++;
		}
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-amaximumFlow(...) is a function to calculate the sum of flows from the source
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *    					-g_Graph:										graph on which the maximum flow is to be found
	 *  					-src_Vertex:									source vertex
	 *  					
	 *  @localVariables: 	-variableName_dataType:							description
	 *  					-e_Edge:										current edge
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-flow_int:										sum of flows from the source
	 *  					
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
	private int maximumFlow(Graph g,Vertex src) {
		int flow=0;
		for(Edge e:src.adj){
			flow+=e.flow;
		}
		return flow;
	}
}
