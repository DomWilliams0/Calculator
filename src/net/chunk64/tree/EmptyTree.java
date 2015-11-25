package net.chunk64.tree;

import net.chunk64.OperatorClass;

public class EmptyTree extends Node
{
	public EmptyTree(Node parent, Node leftChild, Node rightChild)
	{
		super(parent, leftChild, rightChild);
	}

	public EmptyTree()
	{
	}


	@Override
	public double getValue()
	{
		return 0;
	}

	@Override
	public OperatorClass getType()
	{
		return null;
	}

	@Override
	public String toString()
	{
		return "EmptyTree";
	}


}
