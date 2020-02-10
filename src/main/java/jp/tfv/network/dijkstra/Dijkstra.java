package jp.tfv.network.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import jp.tfv.network.Link;
import jp.tfv.network.Node;

/**
 * ダイクストラで最短経路探索を行うクラス
 * @author ryutaro.hayashi
 *
 */
public class Dijkstra {
	
	private Node originNode;
	private List<Node> nodeList;
	
	public Dijkstra(List<Node> _nodeList, List<Link> _linkList)	{

		this.nodeList = _nodeList;

		for( Link _link : _linkList )	{
			_link.getUpNode().addRoute(_link);
		}
	}
	
	/**
	 * 各ノードのコスト・計算フラグを初期化
	 */
	private void initialize()	{
		for( Node _node : nodeList )	{
			_node.setCost(Double.MAX_VALUE);
			_node.setComplete(false);
			_node.setPrevLink(null);
		}
	}
	
	/**
	 * 起点（originNode）からの最短経路探索をおこないます
	 * @param orignNodeId
	 * @return　全てのノードで最短経路を計算できた場合true, つながりが無くできなかった場合false
	 */
	public boolean calcuration(Node _originNode)	{
		
		this.originNode = _originNode;
		
		initialize();
		
		if( originNode == null ) System.out.println(originNode);
		originNode.setCost( 0 );
		originNode.setComplete(true);
		
		// 起点からリンクが１つも出ていない場合
		if( originNode.getRoutes().size() == 0 ) return false;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(
				nodeList
				);
		
		while( !pq.isEmpty() )	{
			Node from = pq.poll();
			from.setComplete(true);
			
			// fromがMAXになるのは、リンクのつながりがなくなったときなのでここで終わり
			if( from.getCost() == Double.MAX_VALUE ) return false;
			
			// fromから出ているリンクの終点ノードまでのコスト設定と終点ノードの一つ前のリンクの設定
			for( Link link : from.getRoutes() )	{
				Node to = link.getDnNode();
				double cost = from.getCost() + link.getCost();
				if( cost < to.getCost() )	{
					pq.remove(to);
					to.setCost(cost);
					pq.add(to);
					to.setPrevLink(link);
				}
			}
		}
		return true;
	}

	/**
	 * 起点（originNode）からの最短経路探索をおこないます
	 * @param orignNodeId
	 * @param destNodeList このノードのリストで最短経路が出来たら終了するためのノードリスト
	 * @return　全てのノードで最短経路を計算できた場合true, つながりが無くできなかった場合false
	 */
	public boolean calcuration(Node _originNode, List<Node> destNodeList)	{
		
		this.originNode = _originNode;

		initialize();
		
		int destNodeSize = destNodeList.size();
		
		int completeNodeCount = 0;
		
		HashMap<String, Node> destNodeMap = new HashMap<>();
		for( Node node : destNodeList ) {
			destNodeMap.put(node.getNodeId(), node);
		}
		
		if( originNode == null ) System.out.println(originNode);
		originNode.setCost( 0 );
		originNode.setComplete(true);
		
		// 起点からリンクが１つも出ていない場合
		if( originNode.getRoutes().size() == 0 ) return false;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(
				nodeList
				);
		
		while( !pq.isEmpty() )	{
			Node from = pq.poll();
			from.setComplete(true);

			// fromがMAXになるのは、リンクのつながりがなくなったときなのでここで終わり
			if( from.getCost() == Double.MAX_VALUE ) return false;

			if( destNodeMap.containsKey(from.getNodeId()) ) {
				if( ++completeNodeCount == destNodeSize ) break;
			}
			
			// fromから出ているリンクの終点ノードまでのコスト設定と終点ノードの一つ前のリンクの設定
			for( Link link : from.getRoutes() )	{
				Node to = link.getDnNode();
				double cost = from.getCost() + link.getCost();
				if( cost < to.getCost() )	{
					pq.remove(to);
					to.setCost(cost);
					pq.add(to);
					to.setPrevLink(link);
				}
			}
		}
		return true;
	}

	/**
	 * 指定（destNode）までの経路を起点（originNode）からのリンクのリストで返します
	 * @param destNode
	 * @return
	 */
	public List<Link> getPathLinkList(Node destNode)	{

		List<Link> linkList = null;

		if( destNode.getPrevLink() != null ) {
			linkList = new ArrayList<>();
			Link prevLink = destNode.getPrevLink();
			while( prevLink != null )	{
				linkList.add(prevLink);
				prevLink = prevLink.getUpNode().getPrevLink();
			}
			Collections.reverse(linkList);
		}

		return linkList;
	}

	/**
	 * 指定（destNode）までの経路を起点（originNode）からノードのリストで返します
	 * @param destNode
	 * @return
	 */
	public List<Node> getPathNodeList(Node destNode)	{

		List<Node> nodeList = null;

		if( destNode.getPrevLink() != null ) {
			nodeList = new ArrayList<>();
			nodeList.add(destNode);
			Link prevLink = destNode.getPrevLink();
			while( prevLink != null )	{
				nodeList.add(prevLink.getUpNode());
				prevLink = prevLink.getUpNode().getPrevLink();
			}
			Collections.reverse(nodeList);
		}

		return nodeList;
	}

}
