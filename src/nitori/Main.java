package nitori;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.zip.InflaterInputStream;

import javax.imageio.stream.FileImageInputStream;

public class Main {


	private static final String DAT = 
			"../../Downloads/SSNTR/SSNTR.pak";

	public static void ar() {
		int []keys1 = new int[] {
				0x36375C89,
				0x401A4DD4,
				0x9CCE7AD9,
				0x42E748B1,  
				0x08A2151C, 
				0x9AE1CCEE,
				0x8426AA42,
				0x493B836B,
				0x01F82EE5,
				0xC70935F7,
				0xB24DF487,
				0x78BBD12C,
				0x4967628A,
				0xC3E7AE27,
				0xFBC20CB0,						
				0xC79A9E9B          
		};

		int[] keys2 = new int[keys1.length];

		final int con = 0x3C151433;


		int ebx = 0;
		int eax = 0;
		int edx = 0;
		int edi = 0;
		// eax, edx
		
		VM vm = new VM();
		
		for(int i = 0; i < keys2.length; ++i) {

			Long a = Integer.toUnsignedLong(con);
			Long b = Integer.toUnsignedLong(keys1[i]);
			
			long prod =a*b;

			// the lower bits.
			eax = (int)(prod & 0x00000000FFFFFFFF);

			// the higher bits
			edx = (int)((prod >> 32) & 0x00000000FFFFFFFF);

			ebx = vm.add(ebx, eax);
			
			edi = vm.adc(edi, edx); 

			keys2[i] = ebx;
			
//				Log.i("hex: " +  Integer.toHexString(keys2[i]));

			ebx = edi;
			
			edi = 0;
		}
		// expected output is:
		/*
		 * LOG: hex: d37f234b
LOG: hex: f26f81bd
LOG: hex: 5d93deaf
LOG: hex: fd139d5d
LOG: hex: bcf01bdd
LOG: hex: 188b1e56
LOG: hex: ecc4bfec
LOG: hex: 37ea7bd1
LOG: hex: 5e143615
LOG: hex: b0c660b6
LOG: hex: 7e6bcf00
LOG: hex: 5c370f8a
LOG: hex: 3cf35f73
LOG: hex: 9f300423
LOG: hex: c910b557
LOG: hex: 69feeaa2
		 */
		
	}
	public static void main(String [ ] args) throws IOException{

		ar();


		//decompressFile(new File("../../Downloads/SSNTR/comp.dat"), new File("../../Downloads/SSNTR/uncomp.dat"));

		/*	InputStream inputStream = new BufferedInputStream(new FileInputStream(DAT));

		inputStream.skip(0x04D37D50);

		int[] xorKeys = new int[] {0x521B1AFA, 0x456E2FD3, 0xD0C982CD, 0x44FED4D1};


		// 0x01E4 is the number of times to loop. each iteration we add +4 however. 

		for(int i = 0; i < 4; ++i) {
			int dword = Util.readInt(inputStream);

			int decoded = xorKeys[i] ^ dword;

			byte[] bytes = Util.toBytes(decoded);

			Log.i((char)bytes[0] + "");
			Log.i((char)bytes[1] + "");
			Log.i((char)bytes[2] + "");
			Log.i((char)bytes[3] + "");


		}*/
	}

	private static void shovelInToOut(InputStream in, OutputStream out)
			throws IOException
	{
		byte[] buffer = new byte[1000];
		int len;
		while((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
	}

	public static void decompressFile(File compressed, File raw)
			throws IOException {
		InputStream in =
				new InflaterInputStream(new FileInputStream(compressed));
		OutputStream out = new FileOutputStream(raw);

		shovelInToOut(in, out);



		in.close();
		out.close();
	}

	/*  public void a() {

    	int EAX, ECX;

    	int par1; // esp+4
    	int par2; // esp+8;
    	int par3;  // ESP+10
    	int par4; // :[ESP+C] 

    	EAX = par2; 
    	ECX = par3;
    	ECX |= EAX;
    	ECX = par4;                      ;  get. is this one always the same?
    	JNZ SHORT SSNTR.0046F739
    	EAX = par1;
    	MUL ECX
    	RETN 10
    	PUSH EBX
    	MUL ECX
    	MOV EBX,EAX
    	MOV EAX, par2
    	MUL DWORD PTR SS:[ESP+14]
    	ADD EBX,EAX
    	MOV EAX,par2
    	MUL ECX
    	ADD EDX,EBX
    	POP EBX
    	RETN 10



    }*/

}
