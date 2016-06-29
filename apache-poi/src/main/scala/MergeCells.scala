import java.io.FileOutputStream

import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object MergeCells extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  val row = sheet.createRow(1)
  val cell = row.createCell(1)
  cell.setCellValue("This is a test of merging");

  sheet.addMergedRegion(new CellRangeAddress( // mergedRegion は Cell に属さず、sheet 内で global に管理
    1, //first row (0-based)
    1, //last row  (0-based)
    1, //first column (0-based)
    2  //last column  (0-based)
  ))

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
