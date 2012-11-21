package net.ivoa.pdl.interpreter.utilities;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.servicecaller.IserviceCaller;
import net.ivoa.pdl.servicecaller.ServiceCallerFactory;
import CommonsObjects.GeneralParameter;

public class Utilities {
	private static final Utilities instance = new Utilities();

	public static Utilities getInstance() {
		return instance;
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
		return  serviceCaller.callService();
	}

}
