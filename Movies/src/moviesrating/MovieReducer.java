package moviesrating;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
	
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
		
		context.write(key, new Text(movieName+"\t"+year+"\t"+rating));
		
	}

}
