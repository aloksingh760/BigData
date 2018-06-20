package moviesrating;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieRecordMapper extends Mapper<Object, Text, IntWritable, Text>{

	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		String[] record=value.toString().split("\\|");
		context.write(new IntWritable(Integer.valueOf(record[0])), new Text("record\t"+record[1]+"\t"+record[2]));
		
	}
}
