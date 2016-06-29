import java.io.{File, FileInputStream, FileOutputStream}

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Files extends App {
  val wb = new XSSFWorkbook()
  val createHelper = wb.getCreationHelper
  val sheet = wb.createSheet("new sheet")
  // output
  val fileOut = new FileOutputStream("workbook.xlsx")
  wb.write(fileOut)
  fileOut.close()

  val fileWb = WorkbookFactory.create(new File("workbook.xlsx"))
  val isWb = WorkbookFactory.create(new FileInputStream("workbook.xlsx"))

  // XSSFWorkbook, File
  val filePkg = OPCPackage.open(new File("workbook.xlsx"))
  val filePkgWb = new XSSFWorkbook(filePkg)
  filePkg.close()

  // XSSFWorkbook, InputStream, needs more memory
  val isPkg = OPCPackage.open(new FileInputStream("workbook.xlsx"))
  val isPkgWb = new XSSFWorkbook(isPkg)
  isPkg.close()

}
