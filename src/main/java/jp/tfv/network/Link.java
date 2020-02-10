package jp.tfv.network;

/**
 * リンクのクラス
 * @author ryutaro.hayashi
 *
 */
public class Link {

	private String linkId;
	/*
	 * upNode : 上流側のノード
	 * dnNode : 下流側のノード
	 */
	private Node upNode, dnNode;
	/*
	 * コスト（リンク長）
	 */
	private double cost;
	
	public Link(String _linkId, Node _upNode, Node _dnNode, double _cost)	{
		this.linkId = _linkId;
		this.upNode = _upNode;
		this.dnNode = _dnNode;
		this.cost = _cost;
	}
	
	public String getLinkId() {
		return linkId;
	}
	public Node getUpNode() {
		return upNode;
	}
	public Node getDnNode() {
		return dnNode;
	}
	public double getCost() {
		return cost;
	}
}
