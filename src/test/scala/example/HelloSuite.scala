package example

import org.junit.experimental.categories.Category

class Slow extends munit.Tag("Slow")

@Category(Array(classOf[Slow]))
class MySuite extends munit.FunSuite {

  val myFixture = new Fixture[Unit]("myFixture") {
    def apply(): Unit = println("### myFixture apply() ###")

    override def beforeAll(): Unit = {
      println("### beforeAll is running ###")
    }

    override def afterAll(): Unit = {
      println("### afterAll is running ###")
    }
  }
  override def munitFixtures = List(myFixture)

  test("test1") {
    myFixture()
    assertEquals(1, 1)
  }

  test("test2") {
    myFixture()
    assertEquals(1, 1)
  }
}
