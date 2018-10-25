import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;


import org.junit.Before;
import org.junit.Test;

public class TestIssueReportedxmlParsingImp {
	
	xmlParsingService parser;
	
	@Before
	public void setup() {
		parser = new IssueReportedxmlParsingServiceImp();
	}
	
	@Test //1. Test calculate on Collision issue reported
	public void testCalculateWithCollisionIssue() {
		int ret = parser.calculateIssue("Collision");
		assertEquals(35, ret);
	}
	
	@Test //2. Test Categorize when num of incidents is between 30 and 50
	public void testCategorizeWhenBetween30and50() {
		String ret = parser.categorizeIssue(35);
		assertEquals(" Risk Level: Moderate.", ret);		
	}
	
	@Test //3. Test Categorize when num of incidents is exactly 30
	public void testCategorizeWhenExactly30() {
		String ret = parser.categorizeIssue(30);
		assertEquals(" Risk Level: Medium.", ret);		
	}
	
	@Test //4. Test Categorize when num of incidents is exactly 50
	public void testCategorizeWhenExactly50() {
		String ret = parser.categorizeIssue(50);
		assertEquals(" Risk Level: Moderate.", ret);		
	}
	
	@Test //5. Test Categorize when num of incidents is over 200
	public void testCategorizeWhenOver200() {
		String ret = parser.categorizeIssue(201);
		assertEquals(" Risk Level: Extreme.", ret);		
	}

}
