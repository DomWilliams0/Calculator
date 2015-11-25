package net.chunk64.tree;

public class VariableNode extends ConstantNode
{
	private String name;

	public VariableNode(String name, double value)
	{
		super(value);
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "Variable";
	}
}
