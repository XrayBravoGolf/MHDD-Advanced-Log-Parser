/**
 * 
 */
package mhddlogparser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xux8
 *
 */
public class FileSaver {

	public static void saveFile(Map<String, Long> scanRecord, String fileName) throws IOException {

		JSONObject outputData = new JSONObject();
//		outputData.put("VER", "2 ");
//		outputData.put("MODE", " IDE");
//		outputData.put("DEVICE", " ST3000DM001-1ER166");
//		outputData.put("F/W", "CC25");
//		outputData.put("S/N", "W501FM1M");
//		outputData.put("SECTORS", "5,860,533,168");
//		outputData.put("SECTOR SIZE", "512 bytes");
//		outputData.put("SCAN BLOCK SIZE", "255 sectors");
		outputData.put("SCAN LOG", scanRecord);
		FileOutputStream os = new FileOutputStream(fileName);
		JSON.writeJSONString(os, java.nio.charset.StandardCharsets.UTF_8, outputData,
				com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat);
		os.flush();
		os.close();
	}

	public static void saveBigInt(BigInteger num, String fileName) throws IOException {
		FileOutputStream os = new FileOutputStream(fileName);
		PrintStream printstream = new PrintStream(os);
		printstream.print(num.toString());

//		Files.writeString(".", fileName, null);
		os.flush();
		os.close();
	}
}
