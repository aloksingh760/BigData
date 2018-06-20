package oldestMovie;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MoviesReducer extends Reducer<Text, Text,Text, IntWritable>{
	
	int preYear=2050;
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
	
		String oldestMovies = null;
		int year=0;
		int oldestYear=2050;
		for(Text value: values){
			String[] pieces=value.toString().split("-");
			year=Integer.valueOf(pieces[0]);
			
			if(year<oldestYear){
				oldestYear=year;
				oldestMovies=pieces[1];
			}
		}
		
		
		
		context.write(new Text(oldestMovies), new IntWritable(oldestYear));
	
		
	}
	
	}



