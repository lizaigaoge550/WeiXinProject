package test;

import java.util.ArrayList;
import java.util.List;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

public class jiebaTest {
	public static String Join(String[] list, String conjunction)
	{
	   StringBuilder sb = new StringBuilder();
	   boolean first = true;
	   for (String item : list)
	   {
		  System.out.println("item " + item);
	      if (first)
	         first = false;
	      else
	         sb.append(conjunction);
	      sb.append(item);
	   }
	   return sb.toString();
	}
	
	
	public static void main(String[] args) {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		String text = "这是一个伸手不见五指的黑夜";
		List<SegToken> segtokens = segmenter.process(text,SegMode.SEARCH);
		List<String> tokens = new ArrayList<String>();
		for(SegToken segtoken:segtokens)
		{
			tokens.add(segtoken.word);
		}
		String[] list_tst = {"woshi","gao","yuanbo"};
		System.out.println(Join(list_tst, ","));
	}
}
