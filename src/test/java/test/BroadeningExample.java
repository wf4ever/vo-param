package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterDependency;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class BroadeningExample extends BaseExample {
	
	
	 public static void main(String[] args) throws PropertyException, FileNotFoundException, JAXBException {
		 BroadeningExample example = new BroadeningExample();
	        example.marshall();
	        
	    }
	
	@Override
	protected Service buildService() {

		Service service = factory.createService()
				.withServiceId("http://pdl-calc.obspm.fr:8081/broadening/OnlineCode")
				.withServiceName("Broadening-Service");
		service.setDescription("Hydrogen Stark broadening calculation for astrophysical applications");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("mail");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkosConcept("SKOS_MAIL");
		mail.setUnit("none");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		mail.setDependency(ParameterDependency.REQUIRED);

		SingleParameter initialLevel = factory.createSingleParameter();
		initialLevel.setName("InitialLevel");
		initialLevel.setParameterType(ParameterType.INTEGER);
		initialLevel.setSkosConcept("SKOS_INITIAL_LEVEL");
		initialLevel.setUnit("number");
		initialLevel.setDimension(mktconst("1", ParameterType.INTEGER));
		initialLevel.setDependency(ParameterDependency.REQUIRED);

		SingleParameter finaleLevel = factory.createSingleParameter();
		finaleLevel.setName("FinalLevel");
		finaleLevel.setParameterType(ParameterType.INTEGER);
		finaleLevel.setSkosConcept("SKOS_INITIAL_LEVEL");
		finaleLevel.setUnit("number");
		finaleLevel.setDimension(mktconst("1", ParameterType.INTEGER));
		finaleLevel.setDependency(ParameterDependency.REQUIRED);
		
		
		SingleParameter temperature = factory.createSingleParameter();
		temperature.setName("Temperature");
		temperature.setParameterType(ParameterType.REAL);
		temperature.setSkosConcept("SKOS_TEMPERATURE");
		temperature.setUnit("K");
		temperature.setDimension(mktconst("1", ParameterType.INTEGER));
		temperature.setDependency(ParameterDependency.REQUIRED);

		SingleParameter density = factory.createSingleParameter();
		density.setName("Density");
		density.setParameterType(ParameterType.REAL);
		density.setSkosConcept("SKOS_DENSITY");
		density.setUnit("cm^-3");
		density.setDimension(mktconst("1", ParameterType.INTEGER));
		density.setDependency(ParameterDependency.REQUIRED);

		Parameters parameterList = factory.createParameters();
		parameterList.getParameter().add(mail);
		parameterList.getParameter().add(initialLevel);
		parameterList.getParameter().add(finaleLevel);
		parameterList.getParameter().add(temperature);
		parameterList.getParameter().add(density);
		service.setParameters(parameterList);

		ParameterReference mailRef = new ParameterReference()
				.withParameterName(mail.getName());

		ParameterReference initialRef = new ParameterReference()
				.withParameterName(initialLevel.getName());

		ParameterReference finalRef = new ParameterReference()
				.withParameterName(finaleLevel.getName());

		ParameterReference temperatureRef = new ParameterReference()
				.withParameterName(temperature.getName());

		ParameterReference densityRef = new ParameterReference()
				.withParameterName(density.getName());

		AtomicParameterExpression nf = factory
				.createAtomicParameterExpression().withParameterRef(finalRef);

		AtomicParameterExpression ni = factory
				.createAtomicParameterExpression().withParameterRef(initialRef);

		nf.withOperation(new Operation().withExpression(ni).withOperationType(
				OperationType.MINUS));

		Criterion levelCR = new Criterion().withExpression(nf)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("1", ParameterType.REAL)));

		AlwaysConditionalStatement onLevels = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(levelCR)).withComment(
						"Final value must be greater the the initial one");

		List<String> constantValues = new ArrayList<String>();
		constantValues.add("0.09");
		AtomicConstantExpression zerozero9 = new AtomicConstantExpression()
				.withConstant(constantValues).withConstantType(
						ParameterType.REAL);

		AtomicParameterExpression sqrtT = new AtomicParameterExpression()
				.withParameterRef(temperatureRef).withPower(
						mktconst("0.5", ParameterType.REAL));

		AtomicParameterExpression DensPow = new AtomicParameterExpression()
				.withParameterRef(densityRef).withPower(
						mktconst("0.16666666", ParameterType.REAL));

		DensPow.withOperation(new Operation().withExpression(sqrtT)
				.withOperationType(OperationType.DIVIDE));

		zerozero9.withOperation(new Operation().withExpression(DensPow)
				.withOperationType(OperationType.MULTIPLY));

		Criterion debeyCR = new Criterion().withExpression(zerozero9)
				.withConditionType(
						new ValueSmallerThan().withReached(false).withValue(
								mktconst("1", ParameterType.REAL)));

		AlwaysConditionalStatement debey = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(debeyCR))
				.withComment(
						"Density and temperature must verify the Debye approximation");

		inputsPG.getParameterRef().add(mailRef);
		inputsPG.getParameterRef().add(initialRef);
		inputsPG.getParameterRef().add(finalRef);
		inputsPG.getParameterRef().add(temperatureRef);
		inputsPG.getParameterRef().add(densityRef);

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();
		inputsConstraint.getConditionalStatement().add(onLevels);
		inputsConstraint.getConditionalStatement().add(debey);
		
		inputsPG.setConstraintOnGroup(inputsConstraint);
		
		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);
		
		return service;
	}
}
