package net.chunk64.parsing;

import net.chunk64.OperatorClass;
import net.chunk64.OperatorType;
import net.chunk64.VariableStore;
import net.chunk64.tokenising.Token;
import net.chunk64.tree.BinaryOperatorNode;
import net.chunk64.tree.ConstantNode;
import net.chunk64.tree.EmptyTree;
import net.chunk64.tree.Node;

import java.util.LinkedList;

public class TreeParser
{
	private Node currentNode;
	private Node rootNode;

	public TreeParser()
	{
		currentNode = new EmptyTree();
	}

	public Node parse(LinkedList<Token> tokens) throws NullPointerException
	{
		for (int i = 0, size = tokens.size(); i < size; i++)
		{
			Token token = tokens.get(i);

			// debug
			print();
			System.out.println("\n\n");
			System.out.println(token.toString());
			// debug

			OperatorType type = token.getType();

			if (type == OperatorType.BRACKET_OPEN)
			{
				// descend into new child
				Node node = new EmptyTree();
				node.setParent(currentNode);
				currentNode = node;
			}

			else if (type.is(OperatorClass.VARIABLE))
			{
				Node node = createVariableNode(token);
				currentNode.transferTo(node);

				if (node.hasParent())
					currentNode = node.getParent();
				else currentNode = node;
			}

			else if (type.is(OperatorClass.BINARY_OPERATOR))
			{
				BinaryOperatorNode node = new BinaryOperatorNode(null, null, token.getType());

				if (!(currentNode instanceof EmptyTree))
					currentNode = createNewRoot().getParent();

				currentNode.transferTo(node);

				Node sibling = new EmptyTree();
				sibling.setParent(node);
				currentNode = sibling;
			}

			else if (type == OperatorType.BRACKET_CLOSE)
			{
				if (currentNode.hasParent())
					currentNode = currentNode.getParent();
				else
				{
					// not for last token
					if (i == size - 1)
						continue;

					currentNode = createNewRoot();

				}
			}

		}

		// invalid
		if (currentNode.hasParent() || currentNode instanceof EmptyTree)
			throw new IllegalStateException();


		rootNode = traceRoot();
		return rootNode;
	}

	private Node createNewRoot()
	{
		Node root = traceRoot();
		Node newRoot = new EmptyTree();

		root.setParent(newRoot);
		return currentNode;
	}

	private Node traceRoot()
	{
		Node parent = currentNode;
		while (parent.hasParent())
			parent = parent.getParent();

		return parent;
	}

	private Node createVariableNode(Token token)
	{
		if (token.getType() == OperatorType.CONSTANT)
			return new ConstantNode(token.getSequence());
		else if (token.getType() == OperatorType.VARIABLE)
			return VariableStore.getVariable(token.getSequence());
		else
			throw new IllegalStateException();
	}

	public void print()
	{
		printNode(traceRoot(), "|", 0);
	}

	/**
	 * @param position -1 = left, 0 = root, 1 = right
	 */
	private void printNode(Node node, String prefix, int position)
	{
		String side = position == -1 ? "L" : position == 1 ? "R" : "";
		String selection = node == currentNode ? "  <--" : "";
		System.out.println(prefix + side + "__" + node.toString() + selection);

		prefix += "__";
		if (node.hasLeftChild())
			printNode(node.getLeftChild(), prefix, -1);
		if (node.hasRightChild())
			printNode(node.getRightChild(), prefix, 1);
	}

	public Node getRootNode()
	{
		return rootNode;
	}
}
