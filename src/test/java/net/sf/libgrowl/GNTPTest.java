package net.sf.libgrowl;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GNTPTest {
	
	private java.util.List emptyList; 

	/** 
     * Sets up the test fixture. 
     * (Called before every test case method.) 
     */ 
    @Before 
    public void setUp() { 
        emptyList = new java.util.ArrayList(); 
    } 


    /** 
     * Tears down the test fixture. 
     * (Called after every test case method.) 
     */ 
    @After 
    public void tearDown() { 
        emptyList = null; 
    } 


    @Test 
    public void testSomeBehavior() { 
        assertEquals("Empty list should have 0 elements", 0, emptyList.size()); 
    } 

    @Test(expected=IndexOutOfBoundsException.class) 
    public void testForException() { 
        Object o = emptyList.get(0); 
    } 


}
