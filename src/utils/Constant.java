package utils;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class Constant {
	public static int initGrade = 1000;
	public static int initViewNumber = 0;
	public static String initComments = "{}";
	public static String dictionaryPath = "G:\\MyEclipseWorkSpace\\FirstPro\\WebRoot\\dict.txt";
	public static String imageSavePath = "G:\\MyEclipseWorkSpace\\FirstPro\\imageSave";
	static 
	{
		Map<String, Integer> typeMap = new HashedMap();
		typeMap.put("全职",0);
		typeMap.put("兼职",1);
	}
}
