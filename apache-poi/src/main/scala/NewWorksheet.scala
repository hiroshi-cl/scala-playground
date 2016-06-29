import java.io.FileOutputStream

import org.apache.poi.ss.util.WorkbookUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object NewWorksheet extends App {
  val wb = new XSSFWorkbook()
  val sheet1 = wb.createSheet("new sheet")
  val sheet2 = wb.createSheet("second sheet")

  // Note that sheet name is Excel must not exceed 31 characters
  // and must not contain any of the any of the following characters:
  // 0x0000
  // 0x0003
  // colon (:)
  // backslash (\)
  // asterisk (*)
  // question mark (?)
  // forward slash (/)
  // opening square bracket ([)
  // closing square bracket (])

  // You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
  // for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
  val safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]") // returns " O'Brien's sales   "
  val sheet3 = wb.createSheet(safeName)

  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()
}
