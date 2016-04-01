package samza.contrib.store

import java.io.File

import org.apache.samza.container.SamzaContainerContext
import org.apache.samza.metrics.MetricsRegistry
import org.apache.samza.storage.kv.{KeyValueStore, BaseKeyValueStorageEngineFactory}
import org.apache.samza.system.SystemStreamPartition

class BloomFilterStoreFactory[K,V] extends BaseKeyValueStorageEngineFactory[K, V] {
  override def getKVStore(storeName: String,
                          storeDir: File,
                          registry: MetricsRegistry,
                          changeLogSystemStreamPartition: SystemStreamPartition,
                          containerContext: SamzaContainerContext): KeyValueStore[Array[Byte], Array[Byte]] = {
    val storageConfig = containerContext.config.subset("stores." + storeName + ".", true)
    new BloomFilterStore(storageConfig.getDouble("fp", 0.01f))
  }
}
