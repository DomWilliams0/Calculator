package net.chunk64;

public enum OperatorType
{
	CONSTANT(OperatorClass.VARIABLE),
	VARIABLE(OperatorClass.VARIABLE),

	BRACKET_OPEN(OperatorClass.BRACKET),
	BRACKET_CLOSE(OperatorClass.BRACKET),

	POWER(OperatorClass.BINARY_OPERATOR),
	DIVIDE(OperatorClass.BINARY_OPERATOR),
	MULTIPLY(OperatorClass.BINARY_OPERATOR),
	ADD(OperatorClass.BINARY_OPERATOR),
	SUBTRACT(OperatorClass.BINARY_OPERATOR),

	NEGATIVE(OperatorClass.UNARY_OPERATOR),

	EPSILON(null);

	private OperatorClass type;

	OperatorType(OperatorClass type)
	{
		this.type = type;
	}

	public boolean is(OperatorClass tokenClass)
	{
		return type == tokenClass;
	}
}

