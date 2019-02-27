package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.capita.test.EvaluateExpression;

public class EvaluateExpressionTest {

	@Test
	public void test() {

		int numberOfTC = 4;
		List<String> expressionList = new ArrayList<>();
		expressionList.add("7+(6*5^2+3-4/2)");
		expressionList.add("7+(67(56*2))");
		expressionList.add("8*+7");
		expressionList.add("(8*5/8)-(3/1)-5");

		EvaluateExpression evObj = new EvaluateExpression();
		try {
			Map<String, String> map = evObj.getExpressionValue(numberOfTC, expressionList);
			assertEquals("158", map.get("CASE #1"));
			assertEquals("INVALID EXPRESSION", map.get("CASE #2"));
			assertEquals("INVALID EXPRESSION", map.get("CASE #3"));
			assertEquals("-3", map.get("CASE #4"));
		} catch (Exception e) {
			fail("Some Exception occurred");
		}

	}
}
