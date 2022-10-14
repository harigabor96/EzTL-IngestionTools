package org.eztl.ingestion

trait tFilePathBuilder {

  def getDayPattern(previousDaysOpt: Option[Int]): String

}
