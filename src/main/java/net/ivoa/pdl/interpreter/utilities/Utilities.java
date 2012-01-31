package net.ivoa.pdl.interpreter.utilities;

import java.util.List;

import CommonsObjects.GeneralParameter;

import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;


public class Utilities {
	private static final Utilities instance = new Utilities();

	public static Utilities getInstance() {
		return instance;
	}

	private Utilities() {
	}
	
	private Service service;
	
	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	
	public SingleParameter getParameterFromReference(ParameterReference ref){
		List<SingleParameter> paraters = this.service.getParameters().getParameter();
		for(SingleParameter param : paraters){
			if(param.getName().equalsIgnoreCase(ref.getParameterName())){
				return param;
			}
		}
		return null;
	}
	
	// THis function must send back the value submitted by user for every asked parameter
	public List<GeneralParameter>  getuserProvidedValuesForParameter(SingleParameter parameter){
		//TODO
		return null;
	}
}
