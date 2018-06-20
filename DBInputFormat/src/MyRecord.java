import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

class MyRecord implements Writable, DBWritable {
	long id;
	String name;

	public void readFields(DataInput in) throws IOException {
		this.id = in.readLong();
		this.name = Text.readString(in);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getLong(1);
		this.name = resultSet.getString(2);
	}

	public void write(DataOutput out) throws IOException {
		out.writeLong(this.id);
		Text.writeString(out, this.name);
	}

	public void write(PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, this.id);
		stmt.setString(2, this.name);
	}
}
