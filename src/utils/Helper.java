package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Helper {
	public static List<String> LoadingDictionary()
	{
		String pathname = Constant.dictionaryPath; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
		File filename = new File(pathname); // 要读取以上路径的input。txt文件
		InputStreamReader reader;
		List<String> dic = new ArrayList<String>();
		try {
			reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			try {
				line = br.readLine();
				while (line != null) {
					line = br.readLine(); // 一次读入一行数据
					dic.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立一个输入流对象reader
		return dic;
	}

	public static int UpdateGrade(int top, int grade)
	{
		 if(top == 0)
         {
         	grade -= 1;
         }
         else if(top == 1)
         {
         	grade -= 2;
         }
         else if(top == 2)
         {
         	grade -= 3;
         }
         else if(top == 3)
         {
         	grade -= 4;
         }
         else if(top == 4)
         {
         	grade -= 5;
         }
		 return grade;
	}
}
