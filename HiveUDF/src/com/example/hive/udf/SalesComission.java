package com.example.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class SalesComission extends UDF {

	public float evaluate(float percentage, float comission){
		
		
		float cAMMOUNT=comission*(percentage/100);
		return cAMMOUNT;
		
	}
	
}
