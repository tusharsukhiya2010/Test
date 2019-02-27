package com.capita.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainApplication {

	public static void main(String[] args) {
		EvaluateExpression ev = new EvaluateExpression();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of test cases");
		int numberOfTC = Integer.parseInt(sc.nextLine());

		List<String> expressionList = new ArrayList<>();
		for (int i = 0; i < numberOfTC; ++i) {
			System.out.println("Enter expression");
			expressionList.add(sc.nextLine());
		}

		Map<String,String> outputMap = ev.getExpressionValue(numberOfTC, expressionList);
	
		for (int i = 0; i < outputMap.size(); ++i) {
			System.out.println("Value of TC #" + (i+1) + " : " + outputMap.get("CASE #" + (i+1)));
		}
	}
}
