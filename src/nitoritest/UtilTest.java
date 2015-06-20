package nitoritest;

import static org.junit.Assert.*;
import nitori.Util;

public class UtilTest {

	@org.junit.Test
	public void testByteToBitString() {
		
		assertEquals("00000001", Util.byteToBitString((byte)1));
		assertEquals("00100011", Util.byteToBitString((byte)35));
		
		assertEquals("10000001", Util.byteToBitString((byte)-127));
	
		assertEquals("10000000", Util.byteToBitString((byte)-128));
		
		
	}

}
