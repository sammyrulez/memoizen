package memoizen

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import java.util.Date


@RunWith(classOf[JUnitRunner])
class MapSpec extends Specification{
  
 
 def strSqLen(s: String) = {s.length*s.length }
 
  def fibonacci(n: Int): Int = n match {
    case n if n <= 0 => 0
    case 1 => 1
    case _ => fibonacci(n - 1) + fibonacci(n - 2)
  }    
  
  def meter[A,B](f: A => B,x:A):(Long,B) = {
    val d = new Date().getTime
    val out = f(x)
    return ( new Date().getTime-d,out)
    
  }
  
  
  "The 'MapMemoizer'" should {
      "cache results" in {      
        val strSqLenMemoized = new MapMemoizer(strSqLen,Some(2))
        val a = strSqLenMemoized("hello Memo")
        val b = strSqLen("hello Memo")
        a must equalTo(b)
      }
      "accept undefined size" in {
         val strSqLenMemoized = new MapMemoizer(strSqLen)
        val a = strSqLenMemoized("hello Memo")
        val b = strSqLen("hello Memo")
        a must equalTo(b)
      }
       "not cache results over max lenght" in {      
        val strSqLenMemoized = new MapMemoizer(strSqLen,Some(2))
        val a = strSqLenMemoized("hello Memo")
        val a1 = strSqLenMemoized("hello Memo2")
        val a2 = strSqLenMemoized("hello Memo3")
        val b = strSqLen("hello Memo")
        a must equalTo(b)
      }
       "be performant" in {
         
         val fibMemoized = new MapMemoizer(fibonacci,Some(30))
         fibMemoized(30)
         val outM = meter(fibMemoized.apply,30)        
         val outN = meter(fibonacci,30)              
         outM._1 must lessThan(outN._1) 
         
       }
     
    }


}