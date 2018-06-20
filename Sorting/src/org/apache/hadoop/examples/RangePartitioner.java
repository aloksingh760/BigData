package org.apache.hadoop.examples;

import org.apache.hadoop.mapreduce.Partitioner;

public class RangePartitioner<K, V> extends Partitioner<K, V> {

	/** Use {@link Object#hashCode()} to partition. */
	public int getPartition(K key, V value, int numReduceTasks) {

		if (Integer.parseInt(key.toString()) < 2222) {
			return 0;
		} else {
			return 1;
		}
	}

}