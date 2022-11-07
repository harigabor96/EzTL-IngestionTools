package suites.org.eztl.ingestion

import org.eztl.ingestion.FilePathHelper._
import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate

class FilePathHelper extends AnyFunSuite {

  test("get_path_part()") {
    assert(_get_path_part(0)("/part1/part2/part3/part4") == "part4")
    assert(_get_path_part(3)("/part1/part2/part3/part4") == "part1")
    assert(_get_path_part(4)("/part1/part2/part3/part4") == "")
    assertThrows[Exception](_get_path_part(5)("/part1/part2/part3/part4"))
    assertThrows[Exception](_get_path_part(-1)("/part1/part2/part3/part4"))
    assertThrows[Exception](_get_path_part(0)(""))
    assertThrows[Exception](_get_path_part(0)(null))
  }

  test("createDayPattern()") {
    assert(createDayPattern(None) == "{*}")
    assertThrows[Exception](createDayPattern(Some(-1)))
    assert(createDayPattern(Some(0)) == s"{${LocalDate.now()}}")
    assert(createDayPattern(Some(2)) == s"{${LocalDate.now().minusDays(2)},${LocalDate.now().minusDays(1)},${LocalDate.now()}}")
  }

}
