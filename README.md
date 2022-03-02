This repo is a little reproduction of an munit issue.

A reusable Fixture's `beforeAll` method runs even when it's defining suite is excluded via `--exclude-categories=`.
Consider the following test file:

```scala
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
```


Within an sbt shell,
`test` yields the following, as expected:

```
### beforeAll is running ###
### myFixture apply() ###
### myFixture apply() ###
### afterAll is running ###
example.MySuite:
  + test1 0.017s
  + test2 0.0s
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
```

The test suite MySuite is annotated with a junit Category.
Which allows us to include or exclude whole test suites.
When we exclude MySuite the tests are skipped but the Fixture's `beforeAll` method still runs:

`testOnly -- --exclude-categories=example.Slow` yields:

```
### beforeAll is running ###
### afterAll is running ###
example.MySuite:
[info] Passed: Total 0, Failed 0, Errors 0, Passed 0
```
