package net.ivoa.pdl.interpreter.serviceInterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import exeptions.InvalidCondition;
import exeptions.InvalidConditionalStatement;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupInterpreter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class ServiceInterpreter {
	private Service service;

	public ServiceInterpreter(Service service) {
		super();
		this.service = service;
	}

	public List<GroupInterpreter> inputInterpreter() {
		Utilities.getInstance().setService(service);
		return analyseGroup(service.getInputs().getParameterGroup());
	}
	
	public List<GroupInterpreter> outputInterpreter() {
		Utilities.getInstance().setService(service);
		return analyseGroup(service.getOutputs().getParameterGroup());
	}

	private List<GroupInterpreter> analyseGroup(List<ParameterGroup> listeGroups) {
		List<GroupInterpreter> toReturn = new ArrayList<GroupInterpreter>();
		for (ParameterGroup pg : listeGroups) {
			try {
				GroupInterpreter gi = new GroupInterpreter(pg);
				gi.verifyGroup();
				toReturn.add(gi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toReturn;
	}
}