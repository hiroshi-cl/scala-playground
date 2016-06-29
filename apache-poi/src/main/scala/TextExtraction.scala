import java.io.FileInputStream

import org.apache.poi.hssf.extractor.ExcelExtractor
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem

object TextExtraction extends App {
  val inp = new FileInputStream("workbook.xls")
  val wb = new HSSFWorkbook(new POIFSFileSystem(inp)) // 残念なことに XSSF 系の Extractor はないらしい？
  val extractor = new ExcelExtractor(wb)

  extractor.setFormulasNotResults(true)
  extractor.setIncludeSheetNames(false)
  println(extractor.getText)
}
