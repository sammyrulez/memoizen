package memoizen;

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner;
import org.specs2.mutable.Specification



class Dummy(val x:String)

@RunWith(classOf[JUnitRunner])
class RedisSpec  extends Specification{
	
  def strSqLen(s: String) = {s.length*s.length }
  
  def dummyLen(d:Dummy) = d.x.length
  
  
	"The 'RedisMemoizer'" should {
      "cache results" in {      
        
        val strSqLenMemoized = new RedisBackedMemoizer(strSqLen,"localhost")
        val a = strSqLenMemoized("hello Memo")
        val b = strSqLen("hello Memo")
        a must equalTo(b)
      }
      "accept any as key" in { 
        val dummyLenMemoized =  new RedisBackedMemoizer(dummyLen,"localhost")
        val dummy = new Dummy("X")
        val d = dummyLenMemoized(dummy)   
        d must equalTo(dummy.x.length)
        
      }
  }

}
