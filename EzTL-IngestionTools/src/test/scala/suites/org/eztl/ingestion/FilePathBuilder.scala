package suites.org.eztl.ingestion

import org.eztl.ingestion.FilePathBuilder.getDayPattern
import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate

class FilePathBuilder extends AnyFunSuite {

  test("getDayPattern()") {
    assert(getDayPattern(None) == "{*}")
    assertThrows[Exception](getDayPattern(Some(-1)))
    assert(getDayPattern(Some(0)) == s"{${LocalDate.now()}}")
    assert(getDayPattern(Some(2)) == s"{${LocalDate.now().minusDays(2)},${LocalDate.now().minusDays(1)},${LocalDate.now()}}")
  }

}
