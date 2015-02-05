package memoizen

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class FactorySpecTest extends Specification{
    
   def strSqLen(s: String) = {s.length*s.length }

  "The 'Memoizer'" should {
      "create MapMemoizer upon request" in {      
        val strSqLenMemoized =  Memoizer(strSqLen,new BasicMemoizerConf)
        
        strSqLenMemoized.getClass.getName must equalTo("memoizen.MapMemoizer")
      }
       "create RedisMemoizer upon request" in {      
        val strSqLenMemoized =  Memoizer(strSqLen,new RedisMemoizerConf("localhost"))
        
        strSqLenMemoized.getClass.getName must equalTo("memoizen.RedisBackedMemoizer")
      }
  }
  
  
}