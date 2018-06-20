package com.wiki.ranking.bulkimport;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * HBase bulk import example<br>
 * Data preparation MapReduce job driver
 * <ol>
 * <li>args[0]: HDFS input path
 * <li>args[1]: HDFS output path
 * <li>args[2]: HBase table name
 * </ol>
 */
public class DriverTool extends Configured implements Tool {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		int res = ToolRunner.run(conf, new DriverTool(), args);
		System.exit(res);

	}

	public final int run(final String[] args) throws Exception {

		Configuration conf = new Configuration();

		/*
		 * NBA Final 2010 game 1 tip-off time (seconds from epoch) Thu, 03 Jun
		 * 2010 18:00:00 PDT
		 */
		conf.setInt("epoch.seconds.tipoff", 1275613200);
		conf.set("hbase.table.name", args[2]);

		// Load hbase-site.xml
		HBaseConfiguration.addHbaseResources(conf);

		Job job = new Job(conf, "HBase Bulk Import Example");
		job.setJarByClass(HBaseKVMapper.class);

		job.setMapperClass(HBaseKVMapper.class);
		job.setMapOutputKeyClass(ImmutableBytesWritable.class);
		job.setMapOutputValueClass(KeyValue.class);

		job.setInputFormatClass(TextInputFormat.class);

		HTable hTable = new HTable(conf, args[2]);

		// Auto configure partitioner and reducer
		HFileOutputFormat.configureIncrementalLoad(job, hTable);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);

		// Load generated HFiles into table
		LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
		loader.doBulkLoad(new Path(args[1]), hTable);
		
		return 0;
	}
}
