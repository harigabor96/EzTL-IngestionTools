package org.eztl.ingestion

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

trait tFilePathHelper {

  //Create day level date pattern for ingestion input path
  def createDayPattern(previousDays: Option[Int]): String

  //Gets a substring (counting from the back) between / and /
  def _get_path_part(partFromBack: Int)(path: String): String
  def get_path_part(partFromBack: Int): UserDefinedFunction = udf(_get_path_part(partFromBack)(_))

}
