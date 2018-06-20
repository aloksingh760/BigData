/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.examples;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TotalSorting {

	public static class SortingMapper extends
			Mapper<Object, Text, IntWritable, NullWritable> {

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			IntWritable intWrt = new IntWritable(
					new Integer(value.toString()).intValue());

			context.write(intWrt, NullWritable.get());
		}
	}

	public static class SortingReducer extends
			Reducer<IntWritable, NullWritable, IntWritable, NullWritable> {

		// This is the user defined reducer function
		// which is invoked for each unique key
		public void reduce(IntWritable key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {

			context.write(key, NullWritable.get());
		}
	}

	public static void main(String[] args) throws Exception {

		// Load the configuration files and
		// add them to the the conf object
		Configuration conf = new Configuration();

		if (args.length != 2) {
			System.err.println("Usage: TotalSorting <in> <out>");
			System.exit(2);
		}

		// Create a job object and give the job a name
		// It allows the user to configure the job, submit it,
		// control its execution, and query the state.
		Job job = new Job(conf, "total sorting");

		// Specify the jar which contains the required classes
		// for the job to run.
		job.setJarByClass(TotalSorting.class);

		job.setMapperClass(SortingMapper.class);
		job.setReducerClass(SortingReducer.class);
		job.setPartitionerClass(RangePartitioner.class);

		job.setNumReduceTasks(2);

		// Set the output key and the value class for the entire job
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(NullWritable.class);

		// Set the Input (format and location) and similarly for the output also
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// Submit the job and wait for it to complete
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}