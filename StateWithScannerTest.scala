object StateWithScannerTest {

  import scalaz._

  object WithScanner {

    import java.util.Scanner

    import scala.util.continuations._

    @inline
    def withScanner[T](body: => T@cps[State[Scanner, Any]]): T =
      State.init[Scanner].flatMap(_ =>
        reset(State.state[Scanner, Any](body)).asInstanceOf[State[Scanner, T]]
      ).run(null)._2

    @inline
    def open(s: String): Unit@cps[State[Scanner, Any]] =
      shift {
        (k: Unit => State[Scanner, Any]) => {
          val sc = new Scanner(s)
          val result = State.put(sc).flatMap(k).map { x =>
            println("in:\t" + s)
            sc.close()
            println("close!")
            x
          }
          println("out:\t" + s)
          result
        }
      }

    // close のタイミングが難しい…

    @inline
    def next(): String@cps[State[Scanner, Any]] =
      shift {
        (k: String => State[Scanner, Any]) =>
          State.get[Scanner].map(_.next()).flatMap(k)
      }
  }

  def test(): Unit = {
    println("## hiding scanner ##")

    {
      import StateWithScannerTest.WithScanner._

      // ユーザは Scanner オブジェクトを直接触れない
      val r = withScanner {
        val s = "hoge piyo fuga"
        println("input:\t" + s)

        open(s)
        println("open!")
        println(next())
        println(next())
        println(next())

        open(s)
        println("open!")
        println(next())
        println(next())
        println(next())
      }
      println(r)
    }

    /*
    ## hiding scanner ##
    input:	hoge piyo fuga
    out:	hoge piyo fuga
    open!
    hoge
    piyo
    fuga
    out:	hoge piyo fuga
    open!
    hoge
    piyo
    fuga
    in:	hoge piyo fuga
    close!
    in:	hoge piyo fuga
    close!
    ()
     */
  }

  def main(args: Array[String]): Unit = {
    test()
  }
}
