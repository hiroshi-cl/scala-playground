import java.io.FileOutputStream

import org.apache.poi.ss.usermodel.{Cell, DateUtil}
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.annotation.switch
import scala.collection.JavaConverters._

object CellContents extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  val sheet1 = wb.getSheetAt(0)
  for (row <- sheet1.asScala; cell <- row.asScala) {
    val cellRef = new CellReference(row.getRowNum, cell.getColumnIndex)
    print(cellRef.formatAsString())
    print(" - ")

    (cell.getCellType: @switch) match {
      case Cell.CELL_TYPE_STRING =>
        println(cell.getRichStringCellValue.getString)

      case Cell.CELL_TYPE_NUMERIC =>
        if (DateUtil.isCellDateFormatted(cell)) {
          println(cell.getDateCellValue)
        } else {
          println(cell.getNumericCellValue)
        }

      case Cell.CELL_TYPE_BOOLEAN =>
        println(cell.getBooleanCellValue)

      case Cell.CELL_TYPE_FORMULA =>
        println(cell.getCellFormula)

      case _ =>
        println();
    }
  }

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
