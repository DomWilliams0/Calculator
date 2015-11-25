package net.chunk64.tree;

import net.chunk64.OperatorClass;
import net.chunk64.OperatorType;

public class BinaryOperatorNode extends Node
{
	private Node nodeA, nodeB;
	private OperatorType type;

	public BinaryOperatorNode(Node nodeA, Node nodeB, OperatorType type)
	{
		setLeftChild(nodeA);
		setRightChild(nodeB);

		this.type = type;

		assert type.is(OperatorClass.BINARY_OPERATOR);
	}

	@Override
	public void setLeftChild(Node leftChild)
	{
		super.setLeftChild(leftChild);
		nodeA = leftChild;
	}

	@Override
	public void setRightChild(Node rightChild)
	{
		super.setRightChild(rightChild);
		nodeB = rightChild;
	}

	@Override
	public double getValue()
	{
		double a = nodeA.getValue();
		double b = nodeB.getValue();

		switch (type)
		{
			case POWER:
				return Math.pow(a, b);
			case DIVIDE:
				return a / b;
			case MULTIPLY:
				return a * b;
			case ADD:
				return a + b;
			case SUBTRACT:
				return a - b;
			default:
				throw new IllegalStateException();
		}
	}

	@Override
	public OperatorClass getType()
	{
		return OperatorClass.BINARY_OPERATOR;
	}

	@Override
	public String toString()
	{
		return type.toString();
	}
}
