import java.io.FileOutputStream

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object IterateWithBlankCells extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Decide which rows to process
  val rowStart = Math.min(15, sheet.getFirstRowNum)
  val rowEnd = Math.max(1400, sheet.getLastRowNum)

  for (rowNum <- rowStart until rowEnd) {
    val r = sheet.getRow(rowNum)
    if (r == null) {
      // This whole row is empty
      // Handle it as needed

    } else {

      val MY_MINIMUM_COLUMN_COUNT = 1
      val lastColumn = Math.max(r.getLastCellNum, MY_MINIMUM_COLUMN_COUNT)

      for (cn <- 0 until lastColumn) {
        val c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL) // MissingCellPolicy
        if (c == null) {
          // The spreadsheet is empty in this cell
        } else {
          // Do something useful with the cell's contents
        }
      }
    }
  }

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
