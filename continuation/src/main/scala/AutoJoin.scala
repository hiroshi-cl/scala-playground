object AutoJoin {

  import scala.concurrent._
  import duration.Duration
  import ExecutionContext.Implicits.global

  object Auto {
    import scala.util.continuations._

    @inline
    def autoJoin[T](body: => T@cps[Any]): T =
      reset(body).asInstanceOf[T]

    @inline
    def fork(thunk: => Any): Unit@cps[Any] =
      shift {
        (k: Unit => Any) => {
          val f = Future(thunk)
          val r = k()

          Await.result(f, Duration.Inf) // automatically join
          println("joined")
          r
        }
      }
  }

  def test(): Unit = {
    import Auto._
    println("## auto join ##")
    autoJoin {
      fork {
        println("hoge 1")
        for(i <- 1 to 100) yield
          i * i
        println("hoge 2")
      }
      println("forked")
      fork {
        println("piyo 1")
        for(i <- 1 to 100) yield
          i * i
        println("piyo 2")
      }
      println("forked")
    }
    println("end")
  }

  def main(args: Array[String]): Unit = {
    test()
  }
}
