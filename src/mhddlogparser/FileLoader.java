/**
 * 
 */
package mhddlogparser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author xux8
 *
 */
public class FileLoader {
	static BigInteger totalv = BigInteger.valueOf(0);
	public static Map<String, Long> parseFile(BufferedInputStream inStream) throws IOException {
		Map<String, Long> result = new TreeMap<String, Long>();
		
		byte[] readA = inStream.readNBytes(8);
		byte[] readB = inStream.readNBytes(8);
		while (readA.length == 8 && readB.length == 8) {
			String offset = Long.toString(extractLong(readA));
			long latency = extractLong(readB);
			totalv = totalv.add(BigInteger.valueOf(latency));
//			if (latency >= 5000) { // slow sector
//				// pad the string
//				offset = ("0000000000000000000" + offset).substring(offset.length());
//				result.put(offset, latency);
//			}
//FIXME enable the code
			// next loop
			readA = inStream.readNBytes(8);
			readB = inStream.readNBytes(8);
			;
		} // assume eof, otherwise UB occurs
		return result;
	}

	/**
	 * @param readA
	 */
	private static long extractLong(byte[] arr) {
		ByteBuffer wrapped = ByteBuffer.wrap(arr); // big-endian by default
		long num = wrapped.getLong(); // 1
		return num;

//		Reverse direction
//		ByteBuffer dbuf = ByteBuffer.allocate(2); //2 bytes for a short
//		dbuf.putShortnt(num); 0x01
//		byte[] bytes = dbuf.array(); // { 0, 1 }
	}

//	public static TreeMap<Long, Long> parseFileMap(BufferedInputStream inStream) throws IOException {
//		TreeMap<Long, Long> result = new TreeMap<Long, Long>();
//
//		byte[] readA = inStream.readNBytes(8);
//		byte[] readB = inStream.readNBytes(8);
//		while (readA.length == 8 && readB.length == 8) {
//			Long offset = extractLong(readA);
//			long latency = extractLong(readB);
//			result.put(offset, latency);
//			// next loop
//			readA = inStream.readNBytes(8);
//			readB = inStream.readNBytes(8);
//			;
//		} // assume eof, otherwise UB occurs
//		return result;
//	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO filename
//		String inFileName = "NEWENDIN.BIN";
		String inFileName = "ADVLOGbgENDIAN.BIN";
//		String inFileName = "endtst";
//		String outFileName = "NEWLOGslow.json";
		BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(inFileName));
		Map<String, Long> scanRecord = parseFile(inStream);
		inStream.close();
		FileSaver.saveBigInt(totalv, "totaltimeOLD.txt");
//		FileSaver.saveFile(scanRecord, outFileName);
//		parseJSON();
	}

}
//class ScanRecordFull{

//	String VER="2 ";
//	String MODE=" IDE";
//	String DEVICE=" ST3000DM001-1ER166";
//	String F/W: CC25
//	String S/N: W501FM1M
//	StringSECTORS: 5,860,533,168
//	StringSECTOR SIZE: 512 bytes
//	String SCAN BLOCK SIZE: 255 sectors
//}