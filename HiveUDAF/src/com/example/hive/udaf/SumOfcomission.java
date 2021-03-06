package com.example.hive.udaf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.io.FloatWritable;

public class SumOfcomission  extends UDAF{

	public static class SumUDAFEvaluator implements UDAFEvaluator {
		private FloatWritable result;
		float sum=0;
		public void init() {
		result = null;
		}
		
		public boolean iterate(FloatWritable value) {
		if (value == null) {
		return true;
		}
		if (result == null) {
		result = new FloatWritable(value.get());
		} else {
			sum+=value.get();
			result.set(sum);
		
		}
		return true;
		}
		public FloatWritable terminatePartial() {
		return result;
		}
		public boolean merge(FloatWritable other) {
		return iterate(other);
		}
		public FloatWritable terminate() {
		return result;
		}
		}
}
