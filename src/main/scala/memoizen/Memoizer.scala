package memoizen

object Memoizer {  

  def apply[A, B](f: A => B):Memoizer[A, B] = {
     new MapMemoizer(f)
  }
  
}

trait Memoizer[A, B]{
  
  
}