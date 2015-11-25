package net.chunk64.tree;

import net.chunk64.OperatorClass;

public class ConstantNode extends Node
{
	protected double value;

	public ConstantNode(double value)
	{
		this.value = value;
	}

	public ConstantNode(String sequence)
	{
		this(Double.valueOf(sequence));
	}

	@Override
	public double getValue()
	{
		return value;
	}

	@Override
	public OperatorClass getType()
	{
		return OperatorClass.VARIABLE;
	}

	@Override
	public String toString()
	{
		return String.valueOf(value);
	}
}
