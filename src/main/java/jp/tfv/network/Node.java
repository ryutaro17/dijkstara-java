package jp.tfv.network;

import java.util.ArrayList;
import java.util.List;

/**
 * ノードのクラス、ダイクストラの時に利用
 * @author ryutaro.hayashi
 *
 */
public class Node implements Comparable<Node> {
	
	private String nodeId;
	
	private float x;
	private float y;

	// このノードから出ているリンク
	List<Link> routes = new ArrayList<Link>();
	
	// 始点からのコスト
	private double cost = Double.MAX_VALUE;
	
	// 一つ前のリンク（このノードを終点とするリンク）
	private Link prevLink;
	
	// 最短経路対照のノードになっているかどうか
	private boolean complete = false;

	public Node(String _nodeId, float _x, float _y)	{
		this.nodeId = _nodeId;
		this.x = _x;
		this.y = _y;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Link> getRoutes() {
		return routes;
	}

	@Override
	public int compareTo(Node o) {
		
		return (int)(cost - o.cost);

	}
	
	public void addRoute(String _linkId, Node _dnNode, int _cost)	{
		routes.add( new Link(_linkId, this, _dnNode, _cost) );
	}

	public void addRoute(Link _link)	{
		routes.add( _link );
	}

	public Link getPrevLink() {
		return prevLink;
	}

	public void setPrevLink(Link prevLink) {
		this.prevLink = prevLink;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}


}
