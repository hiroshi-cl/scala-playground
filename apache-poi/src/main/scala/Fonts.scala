import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Fonts extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Create a row and put some cells in it. Rows are 0 based.
  val row = sheet.createRow(1)

  // Create a new font and alter it.
  val font = wb.createFont()
  // 実はフォントは 32767 個までしか使えない、セルごとに別々にFontオブジェクトを作るのではなく使い回す必要がある
  font.setFontHeightInPoints(24)
  font.setFontName("Courier New")
  font.setItalic(true)
  font.setStrikeout(true)

  // Fonts are set into a style so create a new one to use.
  val style = wb.createCellStyle()
  style.setFont(font)

  // Create a cell and put a value in it.
  val cell = row.createCell(1)
  cell.setCellValue("This is a test of fonts")
  cell.setCellStyle(style)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
