package jp.tfv.network;

import java.util.ArrayList;
import java.util.List;

/**
 * Path.datを作るために利用するクラス
 * @author ryutaro.hayashi
 *
 */
public class Path {
	
	private String pathId;
	/*
	 * 起点ノードID
	 */
	private String originNodeId;
	/*
	 * 終点ノードID
	 */
	private String destinationNodeId;
	/*
	 * パス（経路）の起点ノードから終点ノードまでのリンクの羅列
	 */
	private List<Link> linkList;	
	/*
	 * パス（経路）の起点ノードから終点ノードまでのノードの羅列
	 */
	private List<Node> nodeList;

	public Path(String _pathId, String _originNodeId, String _destinationNodeId)	{
		this.pathId = _pathId;
		this.originNodeId = _originNodeId;
		this.destinationNodeId = _destinationNodeId;
		this.linkList = new ArrayList<>();

		this.nodeList = new ArrayList<>();
}

	public String getPathId() {
		return pathId;
	}

	public String getOriginNodeId() {
		return originNodeId;
	}

	public String getDestinationNodeId() {
		return destinationNodeId;
	}

	public List<Link> getLinkList() {
		return linkList;
	}
	
	public void addLink(Link _link)	{
		// Nodeに格納してあるprevLinkを利用するため、終点からのリンクを格納していくため、index:0に追加している
		linkList.add(0, _link);
	}
	
	public List<Node> getNodeList() {
		return nodeList;
	}
	
	public void addNode(Node _node)	{
		// Nodeに格納してあるprevLinkを利用するため、終点からのリンクの起点ノードを格納していくため、index:0に追加している
		nodeList.add(0, _node);
	}


}
