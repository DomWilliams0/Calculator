package net.chunk64.tokenising;

import net.chunk64.OperatorType;

public class Token
{
	private final OperatorType type;
	private final String sequence;

	public Token(OperatorType type, String sequence)
	{
		this.type = type;
		this.sequence = sequence;
	}

	public OperatorType getType()
	{
		return type;
	}

	public String getSequence()
	{
		return sequence;
	}


	@Override
	public String toString()
	{
		return "Token{" +
				"type=" + type +
				", sequence='" + sequence + '\'' +
				'}';
	}
}
