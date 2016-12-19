//=============================================================================================================================================//
//	  							*** Edge: Class that represents an edge in a graph ***    																								   //
//=============================================================================================================================================//
/*
 	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-1016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
 */
//=============================================================================================================================================//


public class Edge {
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-
	 *
	 *  @memberVariable: 	-variableName_dataType:					accessSpecifier:		description:	
	 *  					-from_Vertex:							private					head vertex
	 *  					-to Vertex:								private					tail vertex
	 *  					-weight_int:							private					weight of edge
	 *  					-flow_int:		 						private					flow on the edge
	 *  					-capacity_int:							private					remaining capacity on the edge
	 *  					-flow_int:								private					flow along the edge
	 *  					-rev_boolean:							private					flag to know if the edge was a part of the graph or not
	 *  
	 *  @constructor: 		-constructorSignature:											description:
	 *  					-Edge(Vertex u, Vertex v, int w):								Parameterized constructor
	 *  
	 *  @memberFunction: 	-methodSignature:												description:
	 *  					-public String toString():										returns the string associated with the name of the vertex
	 *					
	 *  --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	 */
	/*---------------------------------------------------------------
	* @memberVariable:
	* ---------------------------------------------------------------
	*/
    Vertex from; 
    Vertex to;
    int weight;
    int flow;
    int capacity;
    boolean rev;

    
    /*---------------------------------------------------------------
   	* @constructor function
   	* ---------------------------------------------------------------
   	*/
    Edge(Vertex u, Vertex v, int w) {
	   from = u;
	   to = v;
	   weight = w;
	   flow=0;
	   capacity=w;
	   rev=false;
    }
    
    
    public Edge(Vertex v, Vertex u, int w, boolean b) {
       from = u;
 	   to = v;
 	   weight = w;
 	   flow=0;
 	   capacity=w;
 	   rev=true;
	}


	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-equals(..) is an overriden method from Java.lang.Object 
	 *  					-This method is used to check if two edge objects are equal
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-object_Object: 								Edge Object to be compared
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-e_Edge:										Edge to be compared
	 *  					
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-result_boolean:								result of comparison
	 *  																	true if 'from' of both the edges and 'to of both the edges are equal,
	 *  																	false, otherwise
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    @Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Edge e = (Edge) object;
			if ((this.from == e.from)&&(this.to==e.to)) {
				result = true;
			}
		}
		return result;
	}

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  @dateCreated:		-September-25-2016
   	 *  @dateLastModified:	-September-29-2016
   	 *  @author: 			-Nevhetha
   	 *  @source:			-Dr.Balaji Raghavachari
   	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
   	 *  
   	 *  @comment:			-equals(..) is an overriden method from Java.lang.Object 
   	 *  					-This method is used to create hashcode for the edge object
   	 *
   	 *  @param: 			-variableName_dataType:							description:	
   	 *  
   	 *  @localVariables: 	-variableName_dataType:							description:
   	 *  						
   	 *  @return:			-variableName_dataType:							description:
   	 *  					-hash_int:										hashCode generated for the edge object
   	 *  
   	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
   	 *  
   	 *  	
   	 */
	@Override
	public int hashCode() {
		int hash=from.hashCode();
		hash= hash+to.hashCode();
		return hash;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateCreated:		-September-25-2016
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-otherEnd(...) is a method to find the other end end of an edge, given a vertex reference
	 *  					-This method is used for undirected graphs
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-u_Vertex: 										Vertex whose other end vertex of the edge is to be found
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  						
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_Vertex:										Vertex on the other end of the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public Vertex otherEnd(Vertex u) {
	if (from == u) {
	    return to;
	} if(to ==u)
	    return from;
	return null;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-toString() is a function that represents the vertex by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_String:										String associated with the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
 
    public String toString() {
	return "(" + from + "," + to + ")";
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  @dateLastModified:	-September-29-2016
	 *  @author: 			-Nevhetha
	 *  @source:			-Dr.Balaji Raghavachari
	 *-------------------------------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  @comment:			-toString() is a function that represents the vertex by its name
	 *
	 *  @param: 			-variableName_dataType:							description:	
	 *  					-
	 *  
	 *  @localVariables: 	-variableName_dataType:							description:
	 *  					-
	 *  
	 *  @return:			-variableName_dataType:							description:
	 *  					-X_String:										String(with white spaces) associated with the edge being considered
	 *  
	 *  -----------------------------------------------------------------------------------------------------------------------------------------------------	
	 *  
	 *  	
	 */
    public String stringWithSpaces() {
	return from + " " + to + " " + weight;
    }
}
