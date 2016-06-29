import java.io.FileOutputStream

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object NewWorkbook extends App {
  // 実際に Excel で開こうとすると壊れていると言われる
  // 多分シートを1個も追加していないから
  {
    val wb = new HSSFWorkbook()
    val fileOut = new FileOutputStream("workbook.xls")
    wb.write(fileOut)
    fileOut.close()
  }
  {
    val wb = new XSSFWorkbook()
    val fileOut = new FileOutputStream("workbook.xlsx")
    wb.write(fileOut)
    fileOut.close()
  }
}
