package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.xm.yss.FileUtils;
import test.base.BaseTest;

public class POITest extends BaseTest {
	private int row = 1;
	
	@Override
	public void test() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("黄健辉");
		
		String[] titles = {"书名","作者","出版日期","摘要"};
		HSSFRow __row = sheet.createRow(0);
		for (int col = 0; col < 4; col++) {
			__row.createCell(col).setCellValue(titles[col]);
		}
		
//		int totalFile = 33;		
//		for (int i = 0; i < totalFile; i++) {
//			parse(sheet,"book/BookSelfdef ("+i+").txt");
//		}	
		parse(sheet, "book/胡谢骅.txt");
		
		//写到磁盘上
	    try {
	        FileOutputStream fileOutputStream = new FileOutputStream(new File("test.xls"));
	        workbook.write(fileOutputStream);
	        fileOutputStream.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void parse(HSSFSheet sheet,String path){
		String name = "";
		String author = "";
		String press="";
		String date="";
		String summary="";
		String text = FileUtils.getInstance().ReadFile(path);
		String[] texts = text.split("\n");
		int len = texts.length;
		HSSFRow _row = null;
		for (int i = 0; i < len; i++) {
			String tmp=texts[i];
//			System.out.println(tmp);
			
			if (tmp.startsWith("书名")) {
				_row = sheet.createRow(row);
				name = tmp.substring(tmp.indexOf(":")+1,tmp.length());
				_row.createCell(0).setCellValue(name);
				continue;
			}
			if (tmp.startsWith("作者")) {
				author = tmp.substring(tmp.indexOf(":")+1,tmp.length());
				_row.createCell(1).setCellValue(author);
				continue;
			}
			if (tmp.startsWith("出版社")) {
				press = tmp.substring(tmp.indexOf(":")+1,tmp.length());
				continue;
			}
			if (tmp.startsWith("出版日期")) {
				date = tmp.substring(tmp.indexOf(":")+1,tmp.length());
				_row.createCell(2).setCellValue(date);
//				if((len-i-1)==0){
////					HSSFRow _row = sheet.createRow(row);
//					_row.createCell(0).setCellValue(name);
//					_row.createCell(1).setCellValue(author);
//					_row.createCell(2).setCellValue(date);
//					_row.createCell(3).setCellValue(summary);
//					summary="";
//					row++;
//					System.out.println("end==="+name+" "+author+" "+press+" "+date+" "+summary);
////					System.out.println("end output");
//				}
				continue;
			}
			if (tmp.startsWith("摘要")) {
				summary = tmp.substring(tmp.indexOf(":")+1,tmp.length());
				_row.createCell(3).setCellValue(summary);
//				if((len-i-1)==0){
//					HSSFRow _row = sheet.createRow(row);
//					_row.createCell(0).setCellValue(name);
//					_row.createCell(1).setCellValue(author);
//					_row.createCell(2).setCellValue(date);
//					_row.createCell(3).setCellValue(summary);
//					summary="";
//					row++;
//					System.out.println("end==="+name+" "+author+" "+press+" "+date+" "+summary);
////					System.out.println("end output");
//				}
				continue;
			}
			if (tmp.equals("")) {
//				HSSFRow _row = sheet.createRow(row);
//				_row.createCell(0).setCellValue(name);
//				_row.createCell(1).setCellValue(author);
//				_row.createCell(2).setCellValue(date);
//				_row.createCell(3).setCellValue(summary);
//				summary="";
				row++;
//				System.out.println(name+" "+author+" "+press+" "+date+" "+summary);
//				System.out.println("blank output");
			}
		}
	}
}
