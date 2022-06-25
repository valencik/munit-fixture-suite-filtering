package example

import org.junit.experimental.categories.Category

import cats.effect.IO
import munit.CatsEffectSuite
import example.Slow
import cats.effect.kernel.Resource


@Category(Array(classOf[Slow]))
class MyCatsEffectSuite extends CatsEffectSuite {

  val resourceFixture = ResourceSuiteLocalFixture(
    "my-resource-suite-local-fixture",
    Resource.make(IO.println("### resource fixture acquired ###"))(_ => IO.println("### resource fixture released ###"))
  )
  override def munitFixtures = List(resourceFixture)

  test("test1") {
    IO(resourceFixture()).assertEquals(())
  }

  test("test2") {
    IO(resourceFixture()).assertEquals(())
  }
}
