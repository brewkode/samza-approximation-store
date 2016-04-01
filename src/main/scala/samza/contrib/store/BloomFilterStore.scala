package samza.contrib.store

import java.util

import com.google.common.hash.{Funnels, BloomFilter}
import org.apache.samza.storage.kv.{KeyValueIterator, Entry, KeyValueStore}
import scala.collection.JavaConversions._

/*
* BloomFilter based lookup store
* Since this is a simple lookup based store, it only supports a subset of operation.
*
* */
class BloomFilterStore(fp: Double) extends KeyValueStore[Array[Byte], Array[Byte]] {
  val bloom = BloomFilter.create(Funnels.byteArrayFunnel(), 100000, fp)
  val TRUE: Array[Byte] = Array(1.toByte)
  val FALSE: Array[Byte] = Array(0.toByte)

  override def get(k: Array[Byte]): Array[Byte] = if(bloom.mightContain(k)) TRUE else FALSE

  override def range(k: Array[Byte], k1: Array[Byte]): KeyValueIterator[Array[Byte], Array[Byte]] = ???

  override def deleteAll(list: util.List[Array[Byte]]): Unit = ???

  override def put(k: Array[Byte], v: Array[Byte]): Unit = bloom.put(k)

  override def all(): KeyValueIterator[Array[Byte], Array[Byte]] = ???

  override def flush(): Unit = {}

  override def delete(k: Array[Byte]): Unit = ???

  override def close(): Unit = {}

  override def putAll(items: util.List[Entry[Array[Byte], Array[Byte]]]): Unit = items.foreach{t => bloom.put(t.getKey)}

  override def getAll(list: util.List[Array[Byte]]): util.Map[Array[Byte], Array[Byte]] = ???
}


