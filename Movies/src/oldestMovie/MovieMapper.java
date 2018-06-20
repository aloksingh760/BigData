package oldestMovie;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper extends Mapper<LongWritable, Text, IntWritable, Text>{

//	int oldestMovieYear=0
	public void map(LongWritable key, Text values, Context context) throws IOException, InterruptedException{
		
		
		String[] pieces=values.toString().split("\\|");
		int movieId=Integer.valueOf(pieces[0]);
		String movieName=pieces[1];
		int movieYear=Integer.valueOf(pieces[2]);
		
		
		context.write(new IntWritable(movieYear),new Text(movieName));
	}
	
}
