package vegas.render

import org.scalatest.{FlatSpec, Matchers}


class WebJarsSpec extends FlatSpec with Matchers {
  val webJars = WebJars("/sample_webjars.csv")

  "version" should "be equals to expected one" in {
    val expected = Map("vega-lite" -> "0.1.0")
    webJars.version should be(expected)
  }

  "cdnUrl" should "return the right url" in {
    val actual = webJars.cdnUrl("vega-lite", "vega.min.js")
    val expected = "https://cdn.jsdelivr.net/webjars/org.webjars.bower/vega-lite/0.1.0/vega.min.js"
    actual should be(expected)
  }
}
