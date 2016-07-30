package com.eshop.entrity.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.eshop.entrity.User;

public class OrderListExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		response.setHeader("Content-Disposition", "inline; filename="+ 
				new String("订单列表".getBytes(), "iso8859-1"));  
		List<VoOrderEntity> userList = (List<VoOrderEntity>) model.get("totalList");
		HSSFSheet sheet = workbook.createSheet("orders");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("工号");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("部门");
		header.createCell(3).setCellValue("商品名");
		header.createCell(4).setCellValue("数量");
		int rowNum = 1;
		if (userList!=null)
		{
			for (VoOrderEntity voOrderEntity : userList)
			{
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(voOrderEntity.getNick_name());
				row.createCell(1).setCellValue(voOrderEntity.getConsignee_name());
				row.createCell(2).setCellValue(voOrderEntity.getConsignee_address());
				row.createCell(3).setCellValue(voOrderEntity.getGood_name());
				row.createCell(4).setCellValue(voOrderEntity.getGood_quantity());
			}
		}
		
	}



 

	 
}
