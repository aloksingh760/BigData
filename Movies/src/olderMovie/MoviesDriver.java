package olderMovie;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.GenericOptionsParser;


public class MoviesDriver {

	
	public static void main(String[] args) throws IOException {
	    if (args.length != 2) {
	      System.err.println("Usage: MaxTemperature <input path> <output path>");
	      System.exit(-1);
	    }
	    
	    JobConf conf = new JobConf(MoviesDriver.class);
	    conf.setJobName("Oldest Movie");

	    FileInputFormat.addInputPath(conf, new Path(args[0]));
	    FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	    
	    conf.setMapperClass(MovieMapper.class);
	    conf.setCombinerClass(MoviesCombiner.class);
	    conf.setReducerClass(MoviesReducer.class);
	    
	    conf.setMapOutputKeyClass(IntWritable.class);
	    conf.setMapOutputValueClass(Text.class);
	   
	    conf.setOutputKeyClass(Text.class);
	    conf.setOutputValueClass(IntWritable.class);

	    JobClient.runJob(conf);
	  }
}
