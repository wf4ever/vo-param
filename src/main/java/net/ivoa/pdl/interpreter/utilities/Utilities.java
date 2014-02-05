package net.ivoa.pdl.interpreter.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import visitors.GeneralParameterVisitor;

import net.ivoa.parameter.model.ParameterDependency;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.servicecaller.IserviceCaller;
import net.ivoa.pdl.servicecaller.ServiceCallerFactory;
import CommonsObjects.GeneralParameter;

public class Utilities {

	private static final ThreadLocal<Utilities> instance = new ThreadLocal<Utilities>(){
		protected Utilities initialValue() {
			return new Utilities();
		};
	};

	/*
	 * private static final ThreadLocal<Utilities> instance = new
	 * ThreadLocal<Utilities>(){ protected Utilities initialValue() { return new
	 * Utilities(); }; };
	 * 
	 * public static Utilities getInstance() { return instance.get(); }
	 */

	//private static final Utilities instance = new Utilities();


	public static Utilities getInstance() {
		return instance.get();
	}

	private Utilities() {
	}

	private Service service;

	private UserMapper mapper;

	public UserMapper getMapper() {
		return mapper;
	}

	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public SingleParameter getParameterFromReference(ParameterReference ref) {
		List<SingleParameter> paraters = this.service.getParameters()
				.getParameter();
		for (SingleParameter param : paraters) {
			if (param.getName().equalsIgnoreCase(ref.getParameterName())) {
				return param;
			}
		}
		return null;
	}

	// This function must send back the value submitted by user for every asked
	// parameter
	public List<GeneralParameter> getuserProvidedValuesForParameter(
			SingleParameter parameter) {
		return this.mapper.getuserProvidedValuesForParameter(parameter);
	}

	// This function must send back the value submitted by user for every asked
	// parameter name
	public List<GeneralParameter> getuserProvidedValuesForParame(
			String paramName) {
		return this.mapper.getUserProvidedValuesForParam(paramName);
	}

	// This function provide the list of the parameters belonging to a given
	// group
	public List<SingleParameter> getParameterForTheGroup(ParameterGroup group) {
		List<SingleParameter> toReturn = new ArrayList<SingleParameter>();

		for (ParameterReference paramRef : group.getParameterRef()) {
			toReturn.add(Utilities.getInstance().getParameterFromReference(
					paramRef));
		}

		return toReturn;
	}

	public String callService() {
		IserviceCaller serviceCaller = ServiceCallerFactory.getInstance()
				.buildCaller(this.service);
		return serviceCaller.callService();
	}

	public List<SingleParameter> getParametersWhichAreOutputs() {
		List<SingleParameter> toReturn = new ArrayList<SingleParameter>();
		ParameterGroup outputGroup = this.service.getOutputs();

		toReturn.addAll(Utilities.getInstance()
				.getParametersInGroupAndSubGroup(outputGroup));

		return toReturn;
	}

	private List<SingleParameter> getParametersInGroupAndSubGroup(
			ParameterGroup group) {
		List<SingleParameter> toReturn = new ArrayList<SingleParameter>();

		toReturn.addAll(Utilities.getInstance().getParameterForTheGroup(group));
		for (ParameterGroup sonGroup : group.getParameterGroup()) {
			toReturn.addAll(getParametersInGroupAndSubGroup(sonGroup));
		}
		return toReturn;
	}

	public List<String> getRequiredFieldsNotProvided() {
		List<String> toReturn = new ArrayList<String>();
		List<SingleParameter> parameterList = this.service.getParameters()
				.getParameter();
		for (SingleParameter currentParam : parameterList) {
			boolean isParameterRequired = currentParam.getDependency()
					.toString()
					.equalsIgnoreCase(ParameterDependency.REQUIRED.toString());
			boolean isParameterProvided = null != Utilities.getInstance()
					.getMapper()
					.getuserProvidedValuesForParameter(currentParam);

			boolean isParameterInOutput = Utilities.getInstance()
					.getParametersWhichAreOutputs().contains(currentParam);
			
			// If the parameter is required and not provided and if it is not an output parameter
			if ((!isParameterProvided && isParameterRequired) && !isParameterInOutput) {
				// we add it to the list of the "not provided and required" params
				toReturn.add(currentParam.getName());
			}
		}
		return toReturn;
	}
	
	public void writeMapIntoFile() throws IOException{
		String fileName="PDLConfig.in";
		File file = new File(fileName);
		file.delete();
		Map<String, List<GeneralParameter>> valuesMap = Utilities.getInstance().getMapper().getMap();
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		
		for (Map.Entry<String,List<GeneralParameter>> entry : valuesMap.entrySet()) {
		    String paramName = entry.getKey();
		    String paramType = entry.getValue().get(0).getType().toString();
		    String paramValue = "";
		    Integer paramSize=entry.getValue().size();
		    for(int i=0; i<paramSize;i++){
		    	paramValue+=entry.getValue().get(i).getValue();
		    	if(paramSize>1 && i < paramSize-1){
		    		paramValue+=";";
		    	}
		    }
		    writer.println(paramType+"!!!"+paramName+"!!!"+paramValue);
		}
		writer.close();
	
	}
	
	public void getValuesFromConfigFile() throws IOException{
		String fileName="PDLConfig.in";
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		while ((line = reader.readLine()) != null) {
			String[] lineContent = line.split("!!!");
			String parameterType = lineContent[0];
			String parameterName = lineContent[1];
			String parameterValue = lineContent[2];
			String [] parametersVectorValue = parameterValue.split(";");
			List<GeneralParameter> parameter = new ArrayList<GeneralParameter>();
				for(int i =0;i<parametersVectorValue.length;i++){
					GeneralParameter temp = new GeneralParameter(parametersVectorValue[i], getTypeFromeItName(parameterType), "", new GeneralParameterVisitor());
					parameter.add(temp);
				}
			Utilities.getInstance().getMapper().getMap().put(parameterName,parameter);
		}
		reader.close();
	}
	
	private ParameterType getTypeFromeItName(String paramName){
		return ParameterType.valueOf(paramName);
	}
	
}
