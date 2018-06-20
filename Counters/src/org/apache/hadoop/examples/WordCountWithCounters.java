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
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCountWithCounters {

	public static enum RECORDS {
		NUMBER_OF_RECORDS
	};

	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// This is the user defined map function
		// which is invoked for each line in the input
		// the key is the file offset and the value is line
		// The Context class is used to interact with the Hadoop framework
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			// increment the counters enumeration
			context.getCounter(RECORDS.NUMBER_OF_RECORDS).increment(1);

			// The input value is converted from Text to String
			// and then split into words
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {

				// For each work the actual word
				// and the number of occurrences of the word is emitted
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		// This is the user defined reducer function
		// which is invoked for each unique key
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;

			// Here we are iterating through all the values
			// and summing them up
			for (IntWritable val : values) {
				sum += val.get();
			}

			// The sum is converted into a Hadoop type
			result.set(sum);

			// Finally the word and the occurance is sent to Hadoop
			// to be written to the target
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {

		// Load the configuration files and
		// add them to the the conf object
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();

		// The input and the output path have to sent to the main/driver program
		if (otherArgs.length != 2) {
			System.err.println("Usage: wordcount <in> <out>");
			System.exit(2);
		}

		// Create a job object and give the job a name
		// It allows the user to configure the job, submit it,
		// control its execution, and query the state.
		Job job = new Job(conf, "word count");

		// Specify the jar which contains the required classes
		// for the job to run.
		job.setJarByClass(WordCountWithCounters.class);

		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);

		// Set the output key and the value class for the entire job
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// Set the Input (format and location) and similarly for the output also
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.setNumReduceTasks(2);

		// Submit the job and wait for it to complete
		job.waitForCompletion(true);

		//Get the instance of counters which has all the counters
		Counters counters = job.getCounters();

		//Get the specific counter and print it out
		Counter c1 = counters.findCounter(RECORDS.NUMBER_OF_RECORDS);
		System.out.println("Number of input records = " + c1.getValue());

	}
}