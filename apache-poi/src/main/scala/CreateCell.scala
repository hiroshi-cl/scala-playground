import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook

object CreateCell extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Create a row and put some cells in it. Rows are 0 based.
  // 0-based 重要情報!
  // サンプルでは short にキャストしてたけど引数普通に int じゃん
  val row = sheet.createRow(0)
  // Create a cell and put a value in it.
  val cell = row.createCell(0)
  cell.setCellValue(1)

  // Write the output to a file
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
