package net.chunk64.tokenising;

import jdk.nashorn.internal.runtime.ParserException;
import net.chunk64.OperatorType;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokeniser
{
	private class TokenPattern
	{
		private final Pattern pattern;
		private final OperatorType type;

		public TokenPattern(Pattern pattern, OperatorType type)
		{
			this.type = type;
			this.pattern = pattern;
		}

	}

	private LinkedList<TokenPattern> tokenPatterns;
	private LinkedList<Token> tokens;

	public Tokeniser()
	{
		tokenPatterns = new LinkedList<>();
		tokens = new LinkedList<>();
	}

	public void add(String pattern, OperatorType type)
	{
		Pattern p = Pattern.compile("^(" + pattern + ")");
		tokenPatterns.add(new TokenPattern(p, type));
	}

	public void tokenize(String expr)
	{
		StringBuilder sb = new StringBuilder(expr.replace(" ", ""));
		tokens.clear();

		// empty expression
		if (expr.isEmpty())
			throw new IllegalArgumentException("Empty expression");


		int index = 0;
		final int length = sb.length();
		while (index < length)
		{
			CharSequence cs = sb.subSequence(index, length);
			boolean found = false;

			for (TokenPattern tokenPattern : tokenPatterns)
			{
				Matcher m = tokenPattern.pattern.matcher(cs);
				if (m.find())
				{
					found = true;

					String seq = m.group().trim();
					tokens.add(new Token(tokenPattern.type, seq));

					index += m.end();
					break;
				}
			}

			if (!found)
				throw new ParserException(cs.toString());
		}
	}

	public void verifyParentheses()
	{
//		for (Token token : tokens)
//			System.out.printf("%-5s : %s\n", token.getSequence(), token.getType());

		// temporary fix for start and end brackets
//		if (tokens.getFirst().getType() != OperatorType.BRACKET_OPEN)
//		{
//			tokens.add(0, new Token(OperatorType.BRACKET_OPEN, "("));
//			tokens.add(new Token(OperatorType.BRACKET_CLOSE, ")"));
//		}


	}

	public LinkedList<Token> getTokens()
	{
		return tokens;
	}

}
