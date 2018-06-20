package olderMovie;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MoviesReducer extends MapReduceBase implements Reducer<IntWritable, Text,Text, IntWritable>{
	
	int preYear=2050;
	
	public void reduce(IntWritable key, Iterator<Text> values,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		String oldestMovies = null;
		int year=0;
		int oldestYear=2050;
		while(values.hasNext()){
			String value=values.next().toString();
			String[] pieces=value.split("-");
			year=Integer.valueOf(pieces[0]);
			
			if(year<oldestYear){
				oldestYear=year;
				oldestMovies=pieces[1];
			}
		}
		
		
		
	  output.collect(new Text(oldestMovies), new IntWritable(oldestYear));
		
	}
	
	}



