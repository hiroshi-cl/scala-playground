// purely functional program (except for println)
object StateTest {

  import scalaz._

  object SingleVersion {

    import scala.util.continuations._

    @inline
    def state[T](body: => T@cps[State[Int, Any]]): State[Int, T] =
      reset(State.state[Int, Any](body)).asInstanceOf[State[Int, T]]


    @inline
    def state2[T](body: => T@cps[State[Int, Any]]): State[Int, T] =
      State.init.flatMap(_ => reset(State.state[Int, Any](body)).asInstanceOf[State[Int, T]])

    @inline
    def get(): Int@cps[State[Int, Any]] =
      shift {
        (k: Int => State[Int, Any]) =>
          State.get[Int].flatMap(k)
      }

    @inline
    def put(a: Int): Unit@cps[State[Int, Any]] =
      shift {
        (k: Unit => State[Int, Any]) =>
          State.put(a).flatMap(k)
      }

    @inline
    def variable: Int@cps[State[Int, Any]] = get()

    @inline
    def variable_=(a: Int): Unit@cps[State[Int, Any]] = put(a)
  }

  def test(): Unit = {
    println("## state ##")

    {
      import SingleVersion._

      // local variable
      val s = state {
        println("* 1: outside of a state monad *")
        println(variable)
        println("* 3: inside of a state monad *")
        variable += 1 // syntax sugar of variable_=(variable() + 1)
        println(variable)
        println("* 4 *")
      }
      println("* 2 *")
      println(s)
      println("* initialized by 10 *")
      s.run(10)
      println("* initialized by 20 *")
      s.run(20)
    }
    /*
    ## state ##
    * 1: outside of a state monad *
    * 2 *
    scalaz.IndexedStateT$$anon$10@2ac273d3
    * initialized by 10 *
    10
    * 3: inside of a state monad *
    11
    * 4 *
    * initialized by 20 *
    20
    * 3: inside of a state monad *
    21
    * 4 *
    */

    println("## state2 ##")

    {
      import SingleVersion._

      // local variable
      val s = state2 {
        println("* 2: inside of a state monad *")
        println(variable)
        println("* 3: inside of a state monad *")
        variable += 1 // syntax sugar of variable_=(variable() + 1)
        println(variable)
        println("* 4 *")
      }
      println("* 1 *")
      println(s)
      println("* initialized by 10 *")
      s.run(10)
      println("* initialized by 20 *")
      s.run(20)
    }

    /*
    ## state2 ##
    * 1 *
    scalaz.IndexedStateT$$anon$10@6737fd8f
    * initialized by 10 *
    * 2: inside of a state monad *
    10
    * 3: inside of a state monad *
    11
    * 4 *
    * initialized by 20 *
    * 2: inside of a state monad *
    20
    * 3: inside of a state monad *
    21
    * 4 *
     */

  }

  def main(args: Array[String]): Unit = {
    test()
  }
}
