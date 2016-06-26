object Trans {

  import scalaz._
  import Scalaz._
  import language.higherKinds

  object MaybeMaybe {

    import scala.util.continuations._

    @inline
    def maybe[T](body: => T@cps[MaybeT[Maybe, Any]]): MaybeT[Maybe, T] =
      reset(Maybe.just[Any](body).liftM[MaybeT]).asInstanceOf[MaybeT[Maybe, T]]

    @inline
    def reflect[T](m: Maybe[T]): T@cps[MaybeT[Maybe, Any]] =
      shift {
        (k: T => MaybeT[Maybe, Any]) =>
          m.map(k).liftM[MaybeT].join
      }
    // memo: _.glue[trans] = _.map.liftM[trans].join

    @inline
    def failInner: Nothing@cps[MaybeT[Maybe, Any]] =
      shift {
        (k: Nothing => MaybeT[Maybe, Any]) =>
          Maybe.empty.liftM[MaybeT]
      }

    @inline
    def failOuter: Nothing@cps[MaybeT[Maybe, Any]] =
      shift {
        (k: Nothing => MaybeT[Maybe, Any]) =>
          MaybeT.empty
      }
  }

  def test(): Unit = {
    {
      import MaybeMaybe._

      val m1 = maybe {
        val x1 = reflect(Maybe.just(10))
        println(x1)
        val x2 = reflect(failInner)
        println("not reachable")
      }
      println(m1)

      val m2 = maybe {
        val x1 = reflect(Maybe.just(10))
        println(x1)
        val x2 = reflect(failOuter)
        println("not reachable")
      }
      println(m2)
    }
  }

  def main(args: Array[String]): Unit = {
    test()
  }
}
