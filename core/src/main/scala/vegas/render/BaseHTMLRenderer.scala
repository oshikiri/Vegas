package vegas.render


trait BaseHTMLRenderer {

  val webJars = WebJars("/webjars.csv")

  val JSImports = List(
    webJars.cdnUrl("d3", "d3.min.js"),
    webJars.cdnUrl("vega", "vega.min.js"),
    webJars.cdnUrl("vega-lite", "vega-lite.min.js"),
    "https://vega.github.io/vega-editor/vendor/vega-embed.js"
  )

  def defaultName = {
    "vegas-" + java.util.UUID.randomUUID.toString
  }

  def specJson: String
}
