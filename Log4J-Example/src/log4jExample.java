import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class log4jExample {

	static Logger log = Logger.getRootLogger();
			
	public static void main(String[] args) throws IOException, SQLException {

		System.out.println("Hi");
		log.debug("Hello this is an debug message");

	}
}