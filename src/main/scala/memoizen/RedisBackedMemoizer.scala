package memoizen
import com.redis._
import serialization._
import Parse.Implicits.parseString

import java.lang.reflect.{Type, ParameterizedType}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.`type`.TypeReference;

class RedisBackedMemoizer[A, B:Manifest](f: A => B, val host: String) {

  private val redis: RedisClient = new RedisClient(host, 6379)

  def apply(x: A): B = {
    
    val cached = redis.get(x)
    cached match {
      case None =>
      val out = f(x); redis.set(x, JacksonWrapper.serialize(out)); out
      case _ => JacksonWrapper.deserialize[B](cached.get)
    }

  }

}


object JacksonWrapper {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def serialize(value: Any): String = {
    import java.io.StringWriter
    val writer = new StringWriter()
    mapper.writeValue(writer, value)
    writer.toString
  }

  def deserialize[T: Manifest](value: String) : T =
    mapper.readValue(value, typeReference[T])

  private [this] def typeReference[T: Manifest] = new TypeReference[T] {
    override def getType = typeFromManifest(manifest[T])
  }

  private [this] def typeFromManifest(m: Manifest[_]): Type = {
    if (m.typeArguments.isEmpty) { m.erasure }
    else new ParameterizedType {
      def getRawType = m.erasure
      def getActualTypeArguments = m.typeArguments.map(typeFromManifest).toArray
      def getOwnerType = null
    }
  }
}