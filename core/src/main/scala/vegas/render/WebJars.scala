package vegas.render

import scala.io.Source


case class WebJars(path: String) {
  val version = {
    val stream = getClass.getResourceAsStream(path)
    Source
      .fromInputStream(stream)
      .getLines
      .map { line =>
        val l = line.split(",")
        l(0) -> l(1)
      }
      .toMap
  }

  def cdnUrl(artifact: String, file: String) =
    s"https://cdn.jsdelivr.net/webjars/org.webjars.bower/$artifact/${version(artifact)}/$file"
}
