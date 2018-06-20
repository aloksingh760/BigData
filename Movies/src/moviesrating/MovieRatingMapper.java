package moviesrating;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MovieRatingMapper extends Mapper<Object, Text, IntWritable, Text>{

	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		String[] record=value.toString().split("\\|");
		context.write(new IntWritable(Integer.valueOf(record[1])), new Text("rating\t"+record[2]));
		
	}
}

