package average;

import java.io.IOException;
import java.util.Iterator;

import javax.sound.midi.Receiver;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import util.StudentRecord;

public class StudentAverageReducer extends MapReduceBase implements Reducer<Text, StudentRecord, Text, FloatWritable>{

	public void reduce(Text key, Iterator<StudentRecord> value,
			OutputCollector<Text, FloatWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
        StudentRecord record=new StudentRecord();
		float maxAverageMarks =0;
		int rollNumber=0;
		String schoolName="";
		String name="";
		int age=0;
		String gender="";
		while(value.hasNext()){
			record = value.next();
			float averageMarks=record.getAverageMarks().get();
			if(maxAverageMarks<averageMarks){
				maxAverageMarks=averageMarks;
				rollNumber=(int) record.getRoll().get();
				schoolName=record.getSchoolName().toString();
				name=record.getStudentName().toString();
				age=(int)record.getAge().get();
				gender=record.getGender().toString();
				
				
			}
		}
		
		Text outputKey=new Text(rollNumber+ "\t"+schoolName+ "\t"+name+ "\t"+age+ "\t"+gender);
	    output.collect(outputKey, new FloatWritable(maxAverageMarks));
	}
	
	

}
