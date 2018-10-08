package vegas.spec

import java.io.File

import io.circe._
import io.circe.syntax._
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source
import vegas.JsonMatchers

class SpecSpec extends FlatSpec with Matchers with JsonMatchers {

  import vegas.spec.Spec._
  import vegas.spec.Spec.Implicits._

  val examples = new File("core/src/test/resources/example-specs")
    .listFiles
    .toList
    .filter(_.getName.endsWith(".json"))

  "Spec" should "provide case-classes for vega-lite objects" in {
    """
    val spec = ExtendedUnitSpec(
      mark=MarkEnums.Line,
      name=Some("Test"),
      encoding=Some(Encoding(
        x=Some(PositionChannelDef(field=Some("foo")))
      ))
    )
    """ should (compile)
  }


  examples.sortBy(_.getName).foreach { example =>
    val jsonName = example.getName
    it should s"round trip the vega-lite example set with $jsonName" in {
      val json = Source.fromFile(example).getLines.mkString
      val spec = parser.decode[VegaUnion](json)

      spec should be ('isRight)
      spec.right.get.asJson should beSameJsonAs(json)
    }
  }

}
