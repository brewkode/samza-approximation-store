package samza.contrib.store

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class BloomFilterStoreSpec extends FlatSpec {
  "BloomFilterStore" should "add entries into the bloomfilter datastructure" in {
    val store = new BloomFilterStore(0.1)
    (0 to 100).foreach(i => store.put(i.toString.getBytes, Array(1.toByte)))
    store.get("1".getBytes()) should be(Array(1.toByte))
    store.get("5".getBytes()) should be(Array(1.toByte))
    store.get("101".getBytes()) should be(Array(0.toByte))
  }
}
