package commissionUdf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class SalesCommoission extends EvalFunc<Float>{



public Float exec(Tuple input) throws IOException {
	// TODO Auto-generated method stub
	
	Float sales=(Float)input.get(0);
	Float commissionPercentage=(Float)input.get(1);
	float cAMMOUNT=sales*(commissionPercentage/100);
	return cAMMOUNT;
}

}
