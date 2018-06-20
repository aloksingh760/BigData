package fare_better_problem_9d;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import util.StudentRecord;

public class StudentAverageMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, StudentRecord> {

	public void map(LongWritable key, Text value,
			OutputCollector<Text, StudentRecord> output, Reporter reporter)
			throws IOException {

		String[] pieces = value.toString().split("\\|");
		
		StudentRecord record = new StudentRecord();
		
		record.setRoll(new LongWritable(new Long(pieces[0]).longValue()));
		record.setSchoolName(new Text(pieces[1]));
		record.setStudentName(new Text(pieces[2]));
		record.setAge(new IntWritable(new Integer(pieces[3]).intValue()));
		record.setGender(new Text(pieces[4]));
		record.setClss(new Text(pieces[5]));
		record.setSubject(new Text(pieces[6]));
		record.setMarks(new IntWritable(new Integer(pieces[7]).intValue()));

		output.collect(new Text(pieces[5]+","+pieces[0]+","+pieces[2]), record);

	}

}