import java.io.FileOutputStream

import org.apache.poi.ss.usermodel.{CellStyle, Row, Workbook}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Alignment extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet()
  val row = sheet.createRow(2)
  row.setHeightInPoints(30)

  createCell(wb, row, 0, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM)
  createCell(wb, row, 1, CellStyle.ALIGN_CENTER_SELECTION, CellStyle.VERTICAL_BOTTOM)
  createCell(wb, row, 2, CellStyle.ALIGN_FILL, CellStyle.VERTICAL_CENTER)
  createCell(wb, row, 3, CellStyle.ALIGN_GENERAL, CellStyle.VERTICAL_CENTER)
  createCell(wb, row, 4, CellStyle.ALIGN_JUSTIFY, CellStyle.VERTICAL_JUSTIFY)
  createCell(wb, row, 5, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_TOP)
  createCell(wb, row, 6, CellStyle.ALIGN_RIGHT, CellStyle.VERTICAL_TOP)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()


  /**
    * Creates a cell and aligns it a certain way.
    *
    * @param wb     the workbook
    * @param row    the row to create the cell in
    * @param column the column number to create the cell in
    * @param halign the horizontal alignment for the cell.
    */
  def createCell(wb: Workbook, row: Row, column: Int, halign: Int, valign: Int): Unit = {
    val cell = row.createCell(column)
    cell.setCellValue("Align It")
    val cellStyle = wb.createCellStyle()
    cellStyle.setAlignment(halign.asInstanceOf[Short]) // Short 使うなゴルァ
    cellStyle.setVerticalAlignment(valign.asInstanceOf[Short])
    cell.setCellStyle(cellStyle)
  }

}
