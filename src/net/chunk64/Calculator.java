package net.chunk64;

import jdk.nashorn.internal.runtime.ParserException;
import net.chunk64.parsing.TreeParser;
import net.chunk64.tokenising.Tokeniser;
import net.chunk64.tree.Node;

import javax.swing.*;

public class Calculator
{
	private static final String TITLE = "Maths :-)";

	private Tokeniser tokeniser;

	public Calculator()
	{
		tokeniser = new Tokeniser();
		initGrammar();
		initConstants();
	}

	private void initGrammar()
	{
		tokeniser.add("[0-9]+", OperatorType.CONSTANT);
		tokeniser.add("[a-zA-Z_]+", OperatorType.VARIABLE);

		tokeniser.add("\\(", OperatorType.BRACKET_OPEN);
		tokeniser.add("\\)", OperatorType.BRACKET_CLOSE);

		tokeniser.add("\\^", OperatorType.POWER);
		tokeniser.add("\\/", OperatorType.DIVIDE);
		tokeniser.add("\\*", OperatorType.MULTIPLY);
		tokeniser.add("\\+", OperatorType.ADD);
		tokeniser.add("\\-", OperatorType.SUBTRACT);

	}

	private void initConstants()
	{
		VariableStore.setVariable("pi", Math.PI, true);
	}

	private Double calculate(String exp)
	{
		Double value = null;
		try
		{
			System.out.println("exp = " + exp);

			// parse input into tokens
			tokeniser.tokenize(exp);

			// add full parentheses
			// tokeniser.verifyParentheses();

			// parse tokens into tree
			TreeParser treeParser = new TreeParser();
			Node tree = treeParser.parse(tokeniser.getTokens());

			treeParser.print();

			// evaluate!
			value = tree.getValue();

		} catch (ParserException e)
		{
			error(String.format("Unknown symbol found in expression '%s' [%s]", exp, e.getMessage()));
		} catch (IllegalArgumentException e)
		{
			error("Invalid expression given: " + e.getMessage());
		} catch (RuntimeException e)
		{
			error("Invalid expression");
		}

		return value;
	}

	private void error(String msg)
	{
		JOptionPane.showMessageDialog(null, msg, "Uh oh", JOptionPane.ERROR_MESSAGE);
	}

	private String askForInput()
	{
		return JOptionPane.showInputDialog(null, "Enter an expression", TITLE, JOptionPane.PLAIN_MESSAGE);
	}

	private boolean displayAnswer(String exp, double answer)
	{
		int choice = JOptionPane.showConfirmDialog(null, exp + " = " + answer + "\nAgain?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		return choice == JOptionPane.YES_OPTION;
	}


	public static void main(String[] args)
	{
		Calculator calculator = new Calculator();

		while (true)
		{
			String exp = calculator.askForInput();
			if (exp == null) break;

			Double result = calculator.calculate(exp);

			if (result != null)
			{
				System.out.println("Answer found: " + result);
				if (!calculator.displayAnswer(exp, result))
					break;
			}

			System.out.println("\n\n\n");
		}


	}

}
