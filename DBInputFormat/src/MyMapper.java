import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MyMapper extends MapReduceBase implements
		Mapper<LongWritable, MyRecord, LongWritable, Text> {
	public void map(LongWritable key, MyRecord val,
			OutputCollector<LongWritable, Text> output, Reporter reporter)
			throws IOException {

		output.collect(new LongWritable(val.id), new Text(val.name));
	}

}
