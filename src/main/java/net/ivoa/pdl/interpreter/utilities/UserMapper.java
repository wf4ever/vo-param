package net.ivoa.pdl.interpreter.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import visitors.GeneralParameterVisitor;

import com.sun.xml.xsom.impl.scd.Iterators.Map;

import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.SingleParameter;
import CommonsObjects.GeneralParameter;

public class UserMapper {
	
	private HashMap<String,GeneralParameter> map;
	
	public UserMapper(){
		this.map = new HashMap<String, GeneralParameter>();
		GeneralParameterVisitor visitor = new GeneralParameterVisitor();
		this.map.put("mass", new GeneralParameter("3.0", ParameterType.REAL.toString(), "speed", visitor));
		this.map.put("time", new GeneralParameter("2.3", ParameterType.REAL.toString(), "time", visitor));
		this.map.put("E", new GeneralParameter("33", ParameterType.REAL.toString(), "E", visitor));
		this.map.put("distance", new GeneralParameter("1.1", ParameterType.REAL.toString(), "distance", visitor));
		this.map.put("speedX",  new GeneralParameter("10", ParameterType.REAL.toString(), "speedX", visitor));
		this.map.put("speedY",  new GeneralParameter("20", ParameterType.REAL.toString(), "speedY", visitor));
		this.map.put("speedZ",  new GeneralParameter("30", ParameterType.REAL.toString(), "speedZ", visitor));
	}
	
	public List<GeneralParameter> getuserProvidedValuesForParameter(
			SingleParameter parameter) {
		return makeListFromSingle(this.map.get(parameter.getName()));
	}
	
	
	List<GeneralParameter> makeListFromSingle(GeneralParameter a){
		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();
		toReturn.add(a);
		return toReturn;
	}

}