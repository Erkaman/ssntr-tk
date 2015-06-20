package nitoritest;

import nitori.VM;

import static org.junit.Assert.*;


public class VMTest {
	
	private static void addTest(final byte op1, final byte op2, final byte expectedResult, final int expectedCf) {
		VM vm = new VM();	
		byte result = vm.add((byte)op1, (byte)op2);
		assertEquals(expectedResult, result);	
		assertEquals(expectedCf, vm.getCf());	
	}
	
	private static void addTest(final int op1, final int op2, final int expectedResult, final int expectedCf) {
		VM vm = new VM();	
		int result = vm.add((int)op1, (int)op2);
		assertEquals(expectedResult, result);	
		assertEquals(expectedCf, vm.getCf());	
		
		
	}
	
	@org.junit.Test
	public void testAdc() {
		// byte test.
		addTest((byte)-1,(byte)-1,(byte)-2,1);	
		addTest((byte)-39,(byte)92,(byte)53,1);	
		addTest((byte)104,(byte)45,(byte)149,0);		
		addTest((byte)10,(byte)-3,(byte)7,1);		
		addTest((byte)-19,(byte)-7,(byte)-26,1);
		addTest((byte)-75,(byte)59,(byte)-16,0);	
		addTest((byte)127,(byte)1,(byte)-128,0);		
		addTest((byte)44,(byte)45,(byte)89,0);		
		addTest((byte)-103,(byte)-69,(byte)-172,1);		
		addTest((byte)-1,(byte)1,(byte)0,1);	
		
		// int test
		addTest((int)-1,(int)-1,(int)-2,1);	
		addTest((int)-1,(int)1,(int)0,1);	
		addTest((int)1,(int)1,(int)2,0);	
		
	}

}
