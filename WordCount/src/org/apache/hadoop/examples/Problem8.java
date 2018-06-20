package org.apache.hadoop.examples;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.examples.WordCountWithFileIndex.IntSumReducer;
import org.apache.hadoop.examples.WordCountWithFileIndex.TokenizerMapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Problem8 {
	public static class TokenizerMapper extends
	Mapper<LongWritable, Text, Text, IntWritable> {

private Text word = new Text();
private Text fileName = new Text();
private final static IntWritable one = new IntWritable(1);
//This is the user defined map function
//which is invoked for each line in the input
//the key is the file offset and the value is line
//The Context class is used to interact with the Hadoop framework
public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
	

	System.out.println("--> In the mapper. line = " + value.toString());
	String name=((FileSplit)context.getInputSplit()).getPath().getName();
	fileName=new Text(name);
	System.out.println("--> In the mapper. files = " + name);
	//The input value is converted from Text to String
	//and then split into words
	StringTokenizer itr = new StringTokenizer(value.toString());
	while (itr.hasMoreTokens()) {
		
		//For each work the actual word
		//and the number of occurrences of the word is emitted
		
		word.set(fileName+"+"+itr.nextToken());
		context.write(word, one);
	}
}
}

public static class IntSumReducer extends
	Reducer<Text, IntWritable, Text, IntWritable> {

//This is the user defined reducer function
//which is invoked for each unique key
public void reduce(Text key, Iterable<IntWritable> values,
		Context context) throws IOException, InterruptedException {
	
	
	System.out.println("--> In the reducer. key = " + key.toString());
	
	//Here we are iterating through all the values
	//and summing them up
	int sum=0;
	for(IntWritable value: values){
		sum+=value.get();
	}
	
	
	//Finally the word and the occurance is sent to Hadoop
	//to be written to the target
	context.write(key, new IntWritable(sum));
}
}

public static void main(String[] args) throws Exception {

//Load the configuration files and
//add them to the the conf object
Configuration conf = new Configuration();
String[] otherArgs = new GenericOptionsParser(conf, args)
		.getRemainingArgs();

//The input and the output path have to sent to the main/driver program
if (otherArgs.length != 2) {
	System.err.println("Usage: wordcount <in> <out>");
	System.exit(2);
}

//Create a job object and give the job a name
//It allows the user to configure the job, submit it,
//control its execution, and query the state.
Job job = new Job(conf, "Problem 8");

//Specify the jar which contains the required classes
//for the job to run.
job.setJarByClass(WordCount.class);

job.setMapperClass(TokenizerMapper.class);
job.setCombinerClass(IntSumReducer.class);
job.setReducerClass(IntSumReducer.class);

//Set the output key and the value class for the entire job
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(IntWritable.class);

//Set the Input (format and location) and similarly for the output also
FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

//job.setNumReduceTasks(4);

//Submit the job and wait for it to complete
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}
