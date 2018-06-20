package olderMovie;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class MovieMapper extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>{

//	int oldestMovieYear=0
	
	public void map(LongWritable key, Text values,
			OutputCollector<IntWritable, Text> output, Reporter reporter)
			throws IOException {

		// TODO Auto-generated method stub
		String[] pieces=values.toString().split("\\|");
		int movieId=Integer.valueOf(pieces[0]);
		String movieName=pieces[1];
		int movieYear=Integer.valueOf(pieces[2]);
		
		if(isMalformed(movieYear)){
			
		}else{
		output.collect(new IntWritable(movieYear),new Text(movieName));
		}
		
	}

private boolean isMalformed(int movieYear) {
	// TODO Auto-generated method stub
	if(movieYear<1800){
	return true;
	}else {
	return false;
	}
}
	
}
