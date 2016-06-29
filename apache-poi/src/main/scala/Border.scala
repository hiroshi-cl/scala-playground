import java.io.FileOutputStream

import org.apache.poi.ss.usermodel.{CellStyle, IndexedColors}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Border extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Create a row and put some cells in it. Rows are 0 based.
  val row = sheet.createRow(1)

  // Create a cell and put a value in it.
  val cell = row.createCell(1)
  cell.setCellValue(4)

  // Style the cell with borders all around.
  val style = wb.createCellStyle()
  style.setBorderBottom(CellStyle.BORDER_THIN)
  style.setBottomBorderColor(IndexedColors.BLACK.getIndex)
  style.setBorderLeft(CellStyle.BORDER_THIN)
  style.setLeftBorderColor(IndexedColors.GREEN.getIndex)
  style.setBorderRight(CellStyle.BORDER_THIN)
  style.setRightBorderColor(IndexedColors.BLUE.getIndex)
  style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED)
  style.setTopBorderColor(IndexedColors.BLACK.getIndex)
  cell.setCellStyle(style)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
