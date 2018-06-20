package oldestMovie;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MoviesCombiner extends Reducer<IntWritable, Text,IntWritable, Text>{
	
	int preYear=2050;
	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
	
		String movies ="";
		for(Text value: values){
			movies=value.toString();
			
		}
		String Year_Movies=key+"-"+movies;
		context.write(new IntWritable(1234), new Text(Year_Movies));
	
		
	}
	
	}
