package com.capita.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

public class EvaluateExpression {

	public Map<String, String> getExpressionValue(int numberOfTC, List<String> expressionList) {
		Map<String, String> outputMap = new HashMap<>();

		for (int i = 0; i < numberOfTC; ++i) {
			String expression = expressionList.get(i);
			String output = "";
			if(Helper.isValidExpression(expression)) {
				CopyOnWriteArrayList<String> postFixList = convertToPostFix(expression);
				output = evaluatePostFix(postFixList);
			} else {
				output = "INVALID EXPRESSION";
			}

			outputMap.put("CASE #" + (i + 1), output);
		}
		return outputMap;
	}

	private String evaluatePostFix(CopyOnWriteArrayList<String> postFixList) {
		Integer total = 0;
		try {
			for (int i = 2; i < postFixList.size();) {
				if (Helper.isOperator(postFixList.get(i).charAt(0))) {
					total = Helper.calculate(Integer.parseInt(postFixList.get(i - 2)),
							Integer.parseInt(postFixList.get(i - 1)), postFixList.get(i).charAt(0));

					postFixList.remove(i - 2);
					postFixList.remove(i - 2);
					postFixList.remove(i - 2);
					postFixList.add(i - 2, total.toString());
					i = 2;
				} else {
					i++;
				}
			}

			if (postFixList.size() > 1) {
				return "INVALID EXPRESSION";
			}
			return total.toString();
		} catch (Exception e) {
			return "INVALID EXPRESSION";
		}
	}

	private CopyOnWriteArrayList<String> convertToPostFix(String expression) {
		Stack<Character> operatorStack = new Stack<>();
		CopyOnWriteArrayList<String> linkedList = new CopyOnWriteArrayList<>();

		for (int i = 0; i < expression.length(); i++) {
			Character ch = expression.charAt(i);

			// get the digits and store them in integerStack
			if (ch >= '0' && ch <= '9') {
				String str = ch.toString();

				// to get the next char value if it is present
				for (int j = i + 1; j < expression.length(); ++j) {

					Character nextChar = expression.charAt(j);
					if (nextChar >= '0' && nextChar <= '9') {
						i++;
						str += nextChar.toString();
					} else {
						break;
					}
				}

				linkedList.add(str);
			} else if ('(' == ch) {
				operatorStack.push(ch);
			} else if (')' == ch) {
				while ('(' != operatorStack.peek()) {
					linkedList.add(operatorStack.pop().toString());
				}
				operatorStack.pop();
			} else {
				while (!operatorStack.isEmpty()
						&& Helper.getPrecedence(ch) <= Helper.getPrecedence(operatorStack.peek())) {
					linkedList.add(operatorStack.pop().toString());
				}

				operatorStack.push(ch);
			}
		}

		while (!operatorStack.isEmpty()) {
			linkedList.add(operatorStack.pop().toString());
		}

		return linkedList;
	}
}
