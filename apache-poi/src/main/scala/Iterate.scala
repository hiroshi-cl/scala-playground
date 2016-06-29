import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import scala.collection.JavaConverters._

object Iterate extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")

  for(sheet <- wb.asScala; row <- sheet.asScala; cell <- row.asScala) // asScala が必要
    println(cell)

  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
