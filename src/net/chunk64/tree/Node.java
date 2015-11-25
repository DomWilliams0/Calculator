package net.chunk64.tree;

import net.chunk64.OperatorClass;

public abstract class Node
{
	protected Node parent, leftChild, rightChild;

	public Node(Node parent, Node leftChild, Node rightChild)
	{
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public Node()
	{
		this(null, null, null);
	}

	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node parent)
	{
		this.parent = parent;

		if (!parent.hasLeftChild())
			parent.setLeftChild(this);
		else if (!parent.hasRightChild())
			parent.setRightChild(this);
		else
			throw new IllegalStateException("Node already has two children");
	}

	public void replaceParent(Node parent, Node replacing)
	{
		this.parent = parent;

		if (parent.getLeftChild() == replacing)
			parent.setLeftChild(this);
		else if (parent.getRightChild() == replacing)
			parent.setRightChild(this);

	}

	public Node getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(Node leftChild)
	{
		this.leftChild = leftChild;
	}

	public Node getRightChild()
	{
		return rightChild;
	}

	public void setRightChild(Node rightChild)
	{
		this.rightChild = rightChild;
	}

	public void transferTo(Node to)
	{
		if (hasParent())
			to.replaceParent(parent, this);

		if (hasLeftChild())
			leftChild.setParent(to);

		else if (hasRightChild())
			rightChild.setParent(to);

//		parent = null;
//		leftChild = null;
//		rightChild = null;
	}

	public boolean hasParent()
	{
		return parent != null;
	}

	public boolean hasLeftChild()
	{
		return leftChild != null;
	}

	public boolean hasRightChild()
	{
		return rightChild != null;
	}

	public abstract double getValue();

	public abstract OperatorClass getType();

	public abstract String toString();
}
