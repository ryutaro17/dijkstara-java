package jp.tfv.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.tfv.network.Link;
import jp.tfv.network.Node;
import jp.tfv.network.dijkstra.Dijkstra;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// Node list
		List<Node> nodeList = new ArrayList<>();
		nodeList.add( new Node("A", 0, 0) );
		nodeList.add( new Node("B", 0, 0) );
		nodeList.add( new Node("C", 0, 0) );
		nodeList.add( new Node("D", 0, 0) );
		nodeList.add( new Node("E", 0, 0) );
		nodeList.add( new Node("F", 0, 0) );

    	// Node Map
		HashMap<String, Node> nodeMap = new HashMap<>();
		for(Node _node : nodeList)	{
			nodeMap.put(_node.getNodeId(), _node);
		}
		
		List<Link> linkList = new ArrayList<>();
		linkList.add( new Link("A→B", nodeMap.get("A"), nodeMap.get("B"), 12 ));
		linkList.add( new Link("A→C", nodeMap.get("A"), nodeMap.get("C"), 28 ));
		linkList.add( new Link("B→C", nodeMap.get("B"), nodeMap.get("C"), 10 ));
		linkList.add( new Link("B→D", nodeMap.get("B"), nodeMap.get("D"), 13 ));
		linkList.add( new Link("C→D", nodeMap.get("C"), nodeMap.get("D"), 11 ));
		linkList.add( new Link("C→E", nodeMap.get("C"), nodeMap.get("E"), 7 ));
		linkList.add( new Link("D→F", nodeMap.get("D"), nodeMap.get("F"), 9 ));
		linkList.add( new Link("E→F", nodeMap.get("E"), nodeMap.get("F"), 4 ));
		
		Dijkstra djkstra = new Dijkstra(nodeList, linkList);
		
		// 起点ノード
		Node startNode = nodeMap.get("A");
		
		// 終点ノード
		List<Node> destNodeList = new ArrayList<>();
		Node endNode1 = nodeMap.get("F");
		destNodeList.add(endNode1);
		
		if (djkstra.calcuration(startNode, destNodeList))	{
			if( djkstra.getPathLinkList(endNode1) != null ) {
				System.out.println( "startNode:" + startNode.getNodeId() + " endNode:" + endNode1.getNodeId() + " cost: " + endNode1.getCost() );
				System.out.println();
				int i = 1;
				for( Link link : djkstra.getPathLinkList(endNode1) ) {
					System.out.println( (i++) + "番目link " + link.getLinkId() );
				}
				System.out.println();
				i = 1;
				for( Node node : djkstra.getPathNodeList(endNode1) ) {
					System.out.println( (i++) + "番目link " + node.getNodeId() );
				}

			}
		} else	{
			System.out.print("Calculation failure.");
		}
	}
}

