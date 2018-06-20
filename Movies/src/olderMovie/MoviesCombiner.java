package olderMovie;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class MoviesCombiner extends MapReduceBase implements Reducer<IntWritable, Text,IntWritable, Text>{
	
	int preYear=2050;
	
	public void reduce(IntWritable key, Iterator<Text> values,
			OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {
		// TODO Auto-generated method stub
		String movies = "";
	while(values.hasNext()){
		    String value= values.next().toString();
			movies+=value+",";
			
		}
		String Year_Movies=key+"-"+movies;
	    output.collect(new IntWritable(1234), new Text(Year_Movies));
	
	}
	
	}
