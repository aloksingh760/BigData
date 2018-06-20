package moviesrating;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class NonRatingMovieReducer extends Reducer<IntWritable, Text, Text, IntWritable> {
	
	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException,InterruptedException{
		
		String movieName="";
		int year=0;
		int rating=0;
		
		for(Text value: values){
			String[] part=value.toString().split("\t");
			//System.out.println(values.toString());
			if(part[0].equals("record")){
			 	movieName=part[1];
			 	year=Integer.valueOf(part[2]);
			}else if(part[0].equals("rating")){
				rating+=Integer.valueOf(part[1]);
			}
			
		}
		if(rating==0){
		context.write(new Text(movieName), new IntWritable(year));
		}
	}

}

