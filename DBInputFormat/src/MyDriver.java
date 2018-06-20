// cc MaxTemperature Application to find the maximum temperature in the weather dataset
// vv MaxTemperature
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;

public class MyDriver {

	public static void main(String[] args) throws IOException {

		JobConf conf = new JobConf(MyDriver.class);
		conf.setInputFormat(DBInputFormat.class);
		DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost/bigdata?user=root&password=vm4learning");
		String[] fields = { "employee_id", "name" };
		DBInputFormat.setInput(conf, MyRecord.class, "employees",
				null /* conditions */, "employee_id", fields);

		FileOutputFormat.setOutputPath(conf, new Path(args[0]));
		conf.setMapperClass(MyMapper.class);

		JobClient.runJob(conf);

	}
}
// ^^ MaxTemperature
