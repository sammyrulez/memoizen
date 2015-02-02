# memoizen
Simple memoization lib for scala

With memoizen you can easily create a momoized version of any function.

```
 def strSqLen(s: String) = {s.length*s.length }
 
 val strSqLenMemoized =  Memoizer(strSqLen,new BasicMemoizerConf)
``` 

Memoization came with two flavors:

in memory (Map backed) with optionaly fixed size

```
val strSqLenMemoized =  Memoizer(strSqLen,new BasicMemoizerConf,Some(200))
```

or external (Redis backed)

```
 val strSqLenMemoized =  Memoizer(strSqLen,new RedisMemoizerConf("localhost"))
 ```