package net.ivoa.pdl.interpreter.utilities;

import java.util.List;

import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.SingleParameter;


public class Utilities {
	private static final Utilities instance = new Utilities();

	public static Utilities getInstance() {
		return instance;
	}

	private Utilities() {
	}
	
	
	public SingleParameter getParameterFromReference(ParameterReference ref){
		//TODO
		return null;
	}
	
	public List<String>  getuserProvidedValuesForParameter(SingleParameter parameter){
		//TODO
		return null;
	}
}
