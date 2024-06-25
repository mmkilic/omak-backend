package mmk.omak.component;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Line;
import mmk.omak.entity.SalesOrder;

@Component
@RequiredArgsConstructor
public class ExcelGenerator {
	
	private XSSFWorkbook workbook;
	
	public void excelOffer(HttpServletResponse response, SalesOrder order) {
		try (XSSFWorkbook workbook = new XSSFWorkbook(ExcelGenerator.class.getResourceAsStream("/excel/gmc-offer-letter-temp.xlsx"))){
			this.workbook = workbook;
			writeData(order);
			ServletOutputStream outputStream = response.getOutputStream();
			this.workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException("Error: Excel file has not been created.");
		}
	}
	
	private void writeData(SalesOrder order) {
		XSSFSheet sheet = workbook.getSheet("Teklif");
		
		Row row = sheet.getRow(14);
		CellStyle style = row.getCell(3).getCellStyle();
		
		createCell(sheet, style, row, 3, order.getCustomer().getName());
		createCell(sheet, style, row, 13, order.getDateCreated().toString());
		
		row = sheet.getRow(16);
		createCell(sheet, style, row, 3, order.getCustomer().getContacts().stream().filter(c -> c.isKeyContact()).findFirst().get().getName());
		createCell(sheet, style, row, 13, order.getId());
		
		row = sheet.getRow(56);
		style = row.getCell(9).getCellStyle();
		createCell(sheet, style, row, 9, order.getSalesman().getFullName());
		row = sheet.getRow(58);
		createCell(sheet, style, row, 9, order.getSalesman().getEmail());
		row = sheet.getRow(60);
		createCell(sheet, style, row, 9, order.getSalesman().getPhoneNumber());
		
		int rowCount = 105;
		style = sheet.getRow(105).getCell(9).getCellStyle();
		for (Line line : order.getOrderlines()) {
			row = sheet.getRow(rowCount++);
			createCell(sheet, style, row, 1, "-");
			createCell(sheet, style, row, 2, "-");
			createCell(sheet, style, row, 3, line.getProductCode());
			createCell(sheet, style, row, 4, line.getProductDescription());
			createCell(sheet, style, row, 5, String.valueOf(line.getQuantity()));
			createCell(sheet, style, row, 6, "-");
			createCell(sheet, style, row, 7, String.valueOf(line.getUnitPrice()));
			createCell(sheet, style, row, 8, String.valueOf(line.getTotalPrice()));
			createCell(sheet, style, row, 9, line.getDuration());
		}
	}
	
	
	private CellStyle style(double fontHeight, boolean bold) {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(bold);
		font.setFontHeight(fontHeight);
		style.setFont(font);
		
		return style;
	}
	
	private void createCell(XSSFSheet sheet, CellStyle style, Row row,  int columnCount, Object value) {
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} 
        else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
}