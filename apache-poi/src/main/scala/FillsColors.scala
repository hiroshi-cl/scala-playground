import java.io.FileOutputStream

import org.apache.poi.ss.usermodel.{CellStyle, IndexedColors}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object FillsColors extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Create a row and put some cells in it. Rows are 0 based.
  val row = sheet.createRow(1)

  // Aqua background
  var style = wb.createCellStyle()
  style.setFillBackgroundColor(IndexedColors.AQUA.getIndex)
  style.setFillPattern(CellStyle.BIG_SPOTS)
  var cell = row.createCell(1)
  cell.setCellValue("X")
  cell.setCellStyle(style)

  // Orange "foreground", foreground being the fill foreground not the font color.
  style = wb.createCellStyle()
  style.setFillForegroundColor(IndexedColors.ORANGE.getIndex)
  style.setFillPattern(CellStyle.SOLID_FOREGROUND)
  cell = row.createCell(2)
  cell.setCellValue("X")
  cell.setCellStyle(style)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
