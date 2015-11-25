package net.chunk64;

import net.chunk64.tree.ConstantNode;
import net.chunk64.tree.Node;

import java.util.HashMap;
import java.util.Map;

public class VariableStore
{
	private static Map<String, Variable> store = new HashMap<>();

	private static class Variable
	{
		Node node;
		boolean constant;

		public Variable(Node node, boolean constant)
		{
			this.node = node;
			this.constant = constant;
		}
	}

	private VariableStore()
	{
	}

	public static Node getVariable(String s)
	{
		Variable value = store.get(s);
		if (value == null)
			throw new IllegalArgumentException("Unknown variable: " + s);
		return value.node;
	}

	private static boolean canSet(String s)
	{
		Variable current = store.get(s);
		return !(current != null && current.constant);
	}

	public static void setVariable(String s, Node value, boolean constant)
	{
		if (!canSet(s))
			throw new IllegalStateException("The variable '" + s + "' is constant!");
		store.put(s, new Variable(value, constant));
	}
	public static void setVariable(String s, Node value)
	{
		setVariable(s, value, false);
	}

	public static void setVariable(String s, double value, boolean constant)
	{
		ConstantNode node = new ConstantNode(value);
		setVariable(s, node, constant);
	}
	public static void setVariable(String s, double value)
	{
		setVariable(s, value, false);
	}


}
