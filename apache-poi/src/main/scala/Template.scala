import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Template extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
