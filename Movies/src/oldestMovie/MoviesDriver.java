package oldestMovie;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MoviesDriver {

	
	public static void main(String[] args) throws IOException,InterruptedException, ClassNotFoundException{
		
		Configuration conf = new Configuration();
	//	String[] otherArgs = new GenericOptionsParser(conf, args)
		//		.getRemainingArgs();
		
		//The input and the output path have to sent to the main/driver program
		if (args.length != 2) {
			System.err.println("Usage: movies <in> <out>");
			System.exit(2);
		}
	
		Job job=new Job(conf, "Oldest Movie");
		job.setJarByClass(MoviesDriver.class);
		
		
		job.setMapperClass(MovieMapper.class);
		job.setCombinerClass(MoviesCombiner.class);
		job.setReducerClass(MoviesReducer.class);
		
		
		job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//job.setInputFormatClass(TextInputFormat.class);
		//job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//job.setNumReduceTasks(2);
		
		//Submit the job and wait for it to complete
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
		
		
		
		
	}
}
