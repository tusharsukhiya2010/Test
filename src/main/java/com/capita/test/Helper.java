package com.capita.test;

public class Helper {

	static Integer getPrecedence(char c) {
		if ('+' == c || '-' == c) {
			return 1;
		} else if ('*' == c || '/' == c) {
			return 2;
		} else if ('^' == c) {
			return 3;
		} else if ('(' == c) {
			return 0;
		} else if (')' == c) {
			return 4;
		} 
		return null;
	}
	
	static Integer calculate(Integer a, Integer b, char operator) {
		switch (operator) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		case '^':
			return (int) Math.pow(a, b);
		}
		return 0;
	}
	
	static boolean isOperator(Character ch) {

		if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
			return true;
		}

		return false;
	}

	public static boolean isValidExpression(String expression) {
		
		for(int i=0;i<expression.length();++i) {
			Character ch = expression.charAt(i);
			if(!((ch >= '0' && ch <= '9') || null != getPrecedence(ch))) {
				return false;
			}
		}
		return true;
	}
}
