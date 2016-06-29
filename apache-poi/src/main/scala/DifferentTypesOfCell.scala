import java.io.FileOutputStream
import java.util.{Calendar, Date}

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object DifferentTypesOfCell extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")
  val row = sheet.createRow(2)
  row.createCell(0).setCellValue(1.1)
  row.createCell(1).setCellValue(new Date())
  row.createCell(2).setCellValue(Calendar.getInstance())
  row.createCell(3).setCellValue("a string")
  row.createCell(4).setCellValue(true)
  row.createCell(5).setCellType(Cell.CELL_TYPE_ERROR) // 反映されてないっpoi
  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
