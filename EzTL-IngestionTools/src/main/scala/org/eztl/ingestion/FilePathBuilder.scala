package org.eztl.ingestion

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.collection.mutable.ListBuffer

object FilePathBuilder extends tFilePathBuilder {

  override def getDayPattern(previousDays: Option[Int]): String = {
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
