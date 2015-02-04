# memoizen
Simple memoization lib for scala

[![Build Status](https://travis-ci.org/sammyrulez/memoizen.svg?branch=master)](https://travis-ci.org/sammyrulez/memoizen)

With memoizen you can easily create a momoized version of any function.

```scala
 def strSqLen(s: String) = {s.length*s.length }
 
 val strSqLenMemoized =  Memoizer(strSqLen,new BasicMemoizerConf)
``` 

Memoization came with two flavors:

in memory (Map backed) with optionaly fixed size

```scala
val strSqLenMemoized =  Memoizer(strSqLen,new BasicMemoizerConf,Some(200))
```

or external (Redis backed)

```scala
 val strSqLenMemoized =  Memoizer(strSqLen,new RedisMemoizerConf("localhost"))
 ```