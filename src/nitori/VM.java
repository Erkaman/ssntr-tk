package nitori;

public class VM {
	
	private int cf; 
	
	public VM() {
		this.cf = 0;
	}
	
	public byte add(byte op1, byte op2) {
		cf = calculateOverflow(op1, op2, 8);
		return  (byte)(op1 + op2);
	}
	
	public int add(int op1, int op2) {
		cf = calculateOverflow(op1, op2, 32);
		return  (int)(op1 + op2);
	}
	
	public int adc(int op1, int op2) {
		return  (int)(op1 + op2 + cf);
		// http://x86.renejeschke.de/html/file_module_x86_id_4.html
		// TODO: we should probably set some other flags in here as well. 
	}
	
	private static int calculateOverflow(int op1, int op2, final int bitLength) {
		int cf = 0;
		for(int i = 0; i < bitLength; ++i) {
		
			int bit1 = (op1 & 0x01);
			int bit2 = (op2 & 0x01);				
		
			if( (bit1 + bit2 + cf) >= 2 ) {
				cf = 1;
			} else {
				cf = 0;
			}
		
			op1 >>>= 1;
			op2 >>>= 1;
		}
		return cf;
	}
	
	public int getCf() {
		return cf;
	}
	

}
