package observers;
 
import java.io.IOException;
import java.util.List;
import java.util.Map;
 
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
 
public class RegionObserverExample extends BaseRegionObserver {
    public static final byte[] FIXED_ROW = Bytes.toBytes("@@@GETTIME@@@");
 
    @Override
    public void preGet(final ObserverContext<RegionCoprocessorEnvironment> e,
            final Get get, final List<KeyValue> results) throws IOException {
        if (Bytes.equals(get.getRow(), FIXED_ROW)) {
            KeyValue kv = new KeyValue(get.getRow(), FIXED_ROW, FIXED_ROW,
                    Bytes.toBytes(System.currentTimeMillis()));
            results.add(kv);
            System.out.println("in get opration");
        }
    }
    
    @Override
    public void postPut(final ObserverContext<RegionCoprocessorEnvironment> c,
        final Put put, final WALEdit edit,
        final boolean writeToWAL) throws IOException {
      Map<byte[], List<KeyValue>> familyMap  = put.getFamilyMap();
      RegionCoprocessorEnvironment e = c.getEnvironment();
     
      System.out.println("post put opration triggered without any condition");
      if (Bytes.equals(put.getRow(), FIXED_ROW)) {
       System.out.println("post put opration triggered");
      }
    }
}