package org.eztl.ingestion

trait tFilePathBuilder {

  def getDayPattern(previousDays: Option[Int]): String

}
