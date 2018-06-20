package moviesrating;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NonRatingMovieDriver {


	public static void main(String[] args) throws IOException,InterruptedException, ClassNotFoundException{
		
		Configuration conf = new Configuration();
	//	String[] otherArgs = new GenericOptionsParser(conf, args)
		//		.getRemainingArgs();
		
		//The input and the output path have to sent to the main/driver program
		if (args.length != 3) {
			System.err.println("Usage: movies <in> <out>");
			System.exit(2);
		}
	
		Job job=new Job(conf, "Oldest Movie");
		job.setJarByClass(MovieRatingDriver.class);
		
		
		//job.setMapperClass(MovieMapper.class);
		//job.setCombinerClass(MoviesCombiner.class);
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, MovieRecordMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, MovieRatingMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		job.setReducerClass(NonRatingMovieReducer.class);
		
		
		job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//job.setInputFormatClass(TextInputFormat.class);
		//job.setOutputFormatClass(TextOutputFormat.class);
		
		
		
		
		//job.setNumReduceTasks(2);
		
		//Submit the job and wait for it to complete
		System.exit(job.waitForCompletion(true) ? 0 : 1);
			
		
	}

}
