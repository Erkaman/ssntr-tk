package nitori;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<String> getAllFiles(final String dir){

		final List<String> files = new ArrayList<String>();
		
		getAllFilesHelper(dir, files);
		
		return files;	
	}
	
	private static void getAllFilesHelper(final String dir, final List<String> files){
	
		final File[] listedFiles = new File(dir).listFiles();
		for(final File file: listedFiles){
			
			if(file.isDirectory()){
				getAllFilesHelper(file.getAbsolutePath(), files);
			} else if(file.isFile()) {
				files.add(file.getAbsolutePath());
			}
		}
	}
	
	public static byte[] toBytes(final int i) {

		byte[] bytes = new byte[4];

		bytes[3] =(byte)((i >> 24) & 0xFF);
		bytes[2] = (byte)((i >> 16) & 0xFF);
		bytes[1] = (byte)((i >> 8) & 0xFF);
		bytes[0] = (byte)((i) & 0xFF);

		return bytes;
	}
	
	public static int readInt(final InputStream inputStream) throws IOException {
		final byte b1 = (byte)inputStream.read();
		final byte b2 = (byte)inputStream.read();
		final byte b3 = (byte)inputStream.read();
		final byte b4 = (byte)inputStream.read();

		return toInt(b1,b2,b3,b4);
	}

	public static int toInt(final byte b1, final byte b2, final byte b3, final byte b4) {

		return ((b4&0xFF) << 24) | ((b3&0xFF) << 16) | ((b2&0xFF) << 8) | (b1&0xFF);
	}
	
	public static String byteToBitString(byte b) {
		String result = "";
		
		for(int i = 0; i < 8; ++i) {
			
			String lowestStr = (b & 0x01) == 1 ? "1" : "0";
			
			result = lowestStr + result;
			
			b >>>= 1;
		}
		
		return result;
	}

}
