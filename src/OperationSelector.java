interface Operation {
	double apply(double x, double y);
}
class Multiplication implements Operation {
	@Override
	public double apply(double x, double y) {
		return x * y;
	}
}
class Division implements Operation {
	@Override
	public double apply(double x, double y) {
		if (y == 0) {
			throw new ArithmeticException("Division by zero");
		}
		return x / y;
	}
}
class OperationSelector {
	static Operation createOperation(Character operator) {
		switch (operator) {
			case '*':
				return new Multiplication();
			case '/':
				return new Division();
			default:
				throw new IllegalArgumentException("Unknown operator: " + operator);
		}
	}
}