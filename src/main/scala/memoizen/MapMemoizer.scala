package memoizen



class MapMemoizer[A, B](f: A => B, val size:Option[Int] = None) { 

      val cache = scala.collection.mutable.Map[A, B]()
      def apply(x: A): B = {
          val targetSize = Some(cache.size)
          size match {
            case `targetSize` => cache.getOrElseUpdate(x, f(x))   
            case    _ => cache.getOrElseUpdate(x, f(x)) 
          }
      } 
  
}
