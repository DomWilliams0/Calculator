package net.chunk64.tree;

import net.chunk64.OperatorClass;
import net.chunk64.OperatorType;

public class UnaryOperatorNode extends Node
{
	private Node node;
	private OperatorType type;

	public UnaryOperatorNode(Node node, OperatorType type)
	{
		setLeftChild(node);
		this.type = type;

		assert type.is(OperatorClass.UNARY_OPERATOR);
	}

	@Override
	public void setLeftChild(Node leftChild)
	{
		super.setLeftChild(leftChild);
		node = leftChild;
	}

	@Override
	public double getValue()
	{
		if (type == OperatorType.NEGATIVE)
			return -node.getValue();

		throw new IllegalStateException();
	}

	@Override
	public OperatorClass getType()
	{
		return OperatorClass.UNARY_OPERATOR;
	}

	@Override
	public String toString()
	{
		return type.toString();
	}
}
