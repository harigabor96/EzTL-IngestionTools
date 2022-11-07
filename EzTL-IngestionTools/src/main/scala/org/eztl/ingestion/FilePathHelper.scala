package org.eztl.ingestion

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.collection.mutable.ListBuffer

object FilePathHelper extends tFilePathHelper {

  override def _get_path_part(partFromBack: Int)(path: String): String = {
    if (path == "") throw new Exception("Path cannot be empty!")
    val parts = path.split("/")
    parts(parts.length - 1 - partFromBack)
  }
  override def get_path_part(partFromBack: Int): UserDefinedFunction = udf(_get_path_part(partFromBack)(_))

  override def createDayPattern(previousDays: Option[Int]): String = {
    val pd = previousDays.getOrElse(return "{*}")

    if (pd < 0) throw new Exception("Previous day count must be 0 or greater!")

    val today = LocalDate.now()

    s"{${getDateRange(today.minusDays(pd), today).mkString(",")}}"
  }

  private def getDateRange(startDate: LocalDate, endDate: LocalDate): List[String] = {

    val numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate).toInt
    val daysBuffer = new ListBuffer[String]

    for (i <- 0 to numOfDaysBetween) {
      daysBuffer += startDate.plusDays(i).toString
    }

    daysBuffer.toList
  }

}
