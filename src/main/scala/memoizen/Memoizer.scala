package memoizen


case class BasicMemoizerConf(val size:Option[Int] = None)
case class RedisMemoizerConf(val host: String,val port:Int = 6379)


object Memoizer {  


  
  def apply[A, B : Manifest](f: A => B, conf:Any):Memoizer[A, B] = {
    
    conf match {
      case b:BasicMemoizerConf => new MapMemoizer(f,b.size)
      case r:RedisMemoizerConf => new RedisBackedMemoizer(f, r.host,r.port)
    }
    
  }
  
  
  
}

trait Memoizer[A, B]{
  
  
}