import java.io.FileOutputStream
import java.util.{Calendar, Date}

import org.apache.poi.xssf.usermodel.XSSFWorkbook

object CreateDateCell extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // Create a row and put some cells in it. Rows are 0 based.
  val row = sheet.createRow(0)

  // Create a cell and put a date value in it.  The first cell is not styled
  // as a date.
  var cell = row.createCell(0)
  cell.setCellValue(new Date())

  // we style the second cell as a date (and time).  It is important to
  // create a new cell style from the workbook otherwise you can end up
  // modifying the built in style and effecting not only this cell but other cells.
  val cellStyle = wb.createCellStyle()
  cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"))
  cell = row.createCell(1)
  cell.setCellValue(new Date())
  cell.setCellStyle(cellStyle)

  //you can also set date as java.util.Calendar
  cell = row.createCell(2)
  cell.setCellValue(Calendar.getInstance())
  cell.setCellStyle(cellStyle)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
