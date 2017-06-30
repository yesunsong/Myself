package test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.xm.yss.FileUtils;
import test.base.BaseTest;

public class POITest extends BaseTest {
	@Override
	public void test() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("黄健辉");
		int totalFile = 32;
		String text = FileUtils.getInstance().ReadFile("book/BookSelfdef (1).txt");
//		System.out.println(text);
		String[] texts = text.split("\n");
//		System.out.println(texts[3]);
//		System.out.println(texts[4]);
//		System.out.println(texts[5]);
//		System.out.println(texts[6]);
		
		HSSFRow row = sheet.createRow(0);
		for (int col = 0; col < 4; col++) {
			row.createCell(col).setCellValue("1");
		}
		
		String name = "";
		String author = "";
		String press="";
		String date="";
		String summary="";
		int len = texts.length;
		for (int i = 0; i < len; i++) {
			String tmp=texts[i];
//			System.out.println(tmp);
			
			if (tmp.startsWith("书名")) {
				name = tmp;
				continue;
			}
			if (tmp.startsWith("作者")) {
				author = tmp;
				continue;
			}
			if (tmp.startsWith("出版社")) {
				press = tmp;
				continue;
			}
			if (tmp.startsWith("出版日期")) {
				date = tmp;
				continue;
			}
			if (tmp.startsWith("摘要")) {
				summary = tmp;
				if((len-i-1)==0){
					System.out.println("end==="+name+" "+author+" "+press+" "+date+" "+summary);
//					System.out.println("end output");
				}
				continue;
			}
			if (tmp.equals("")) {
				System.out.println(name+" "+author+" "+press+" "+date+" "+summary);
//				System.out.println("blank output");
			}
			

		}
	}
}
