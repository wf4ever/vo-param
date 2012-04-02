package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.And;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.IsNull;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class PDRService extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		PDRService example = new PDRService();
		example.marshall();

	}

	@Override
	protected Service buildService() {
		Service service = factory.createService()
				.withServiceId("Meudon-PDR-Service")
				.withServiceName("Meudon-PDR-Service");
		service.setDescription("The Meudon PDR code computes the atomic and molecular structure of interstellar clouds.");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("email");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkossConcept("SKOS_MAIL");
		mail.setUnit("e-mail");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter density = factory.createSingleParameter();
		density.setName("dens");
		density.setParameterType(ParameterType.REAL);
		density.setSkossConcept("SKOS_Density");
		density.setUnit("cm^-3");
		density.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter temperature = factory.createSingleParameter();
		temperature.setName("T");
		temperature.setParameterType(ParameterType.REAL);
		temperature.setSkossConcept("SKOS_Temperature");
		temperature.setUnit("K");
		temperature.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter pressure = factory.createSingleParameter();
		pressure.setName("P");
		pressure.setParameterType(ParameterType.REAL);
		pressure.setSkossConcept("SKOS_Pressure");
		pressure.setUnit("cm^-3 K");
		pressure.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter ifisob = factory.createSingleParameter();
		ifisob.setName("EOSChoice");
		ifisob.setParameterType(ParameterType.STRING);
		ifisob.setSkossConcept("SKOS_EOS_choice");
		ifisob.setUnit("");
		ifisob.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter ieqth = factory.createSingleParameter();
		ieqth.setName("thermalBalance");
		ieqth.setParameterType(ParameterType.STRING);
		ieqth.setSkossConcept("SKOS_Thermal_Balance");
		ieqth.setUnit("");
		ieqth.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter radField = factory.createSingleParameter();
		radField.setName("radiationFieldIntensity");
		radField.setParameterType(ParameterType.REAL);
		radField.setSkossConcept("SKOS_Radiation_field_intensity");
		radField.setUnit("none");
		radField.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter radSource = factory.createSingleParameter();
		radSource.setName("radiationSource");
		radSource.setParameterType(ParameterType.STRING);
		radSource.setSkossConcept("SKOS_Radiation_source");
		radSource.setUnit("none");
		radSource.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter avmax = factory.createSingleParameter();
		avmax.setName("cloudSize");
		avmax.setParameterType(ParameterType.REAL);
		avmax.setSkossConcept("SKOS_Cloud_Size");
		avmax.setUnit("");
		avmax.setDimension(mktconst("1", ParameterType.INTEGER));

		// Adding parameters to list
		Parameters parameterList = factory.createParameters();
		parameterList.getParameter().add(mail);
		parameterList.getParameter().add(density);
		parameterList.getParameter().add(temperature);
		parameterList.getParameter().add(pressure);
		parameterList.getParameter().add(ifisob);
		parameterList.getParameter().add(ieqth);
		parameterList.getParameter().add(radField);
		parameterList.getParameter().add(radSource);
		parameterList.getParameter().add(avmax);
		service.setParameters(parameterList);

		ParameterReference mailRef = new ParameterReference()
				.withParameterName(mail.getName());

		ParameterReference densityRef = new ParameterReference()
				.withParameterName(density.getName());

		ParameterReference temperatureRef = new ParameterReference()
				.withParameterName(temperature.getName());

		ParameterReference pressureRef = new ParameterReference()
				.withParameterName(pressure.getName());

		ParameterReference ifisobRef = new ParameterReference()
				.withParameterName(ifisob.getName());

		ParameterReference ieqthRef = new ParameterReference()
				.withParameterName(ieqth.getName());

		ParameterReference radFieldRef = new ParameterReference()
				.withParameterName(radField.getName());

		ParameterReference radSourceRef = new ParameterReference()
				.withParameterName(radSource.getName());

		ParameterReference avmaxRef = new ParameterReference()
				.withParameterName(avmax.getName());

		// Defining the radiative sources

		ParameterGroup radiation = factory.createParameterGroup().withName(
				"radiationDetails");

		AtomicParameterExpression radSourceExp = factory
				.createAtomicParameterExpression().withParameterRef(
						radSourceRef);

		List<Expression> radSourcesList = new ArrayList<Expression>();
		radSourcesList.add(mktconst("Galaxy", ParameterType.STRING));
		radSourcesList.add(mktconst("sig1.9", ParameterType.STRING));
		radSourcesList.add(mktconst("LMC", ParameterType.STRING));
		radSourcesList.add(mktconst("SMC", ParameterType.STRING));
		radSourcesList.add(mktconst("Orion_Bar", ParameterType.STRING));
		radSourcesList.add(mktconst("BD+31d643", ParameterType.STRING));
		radSourcesList.add(mktconst("HD14250", ParameterType.STRING));
		radSourcesList.add(mktconst("HD21483", ParameterType.STRING));
		radSourcesList.add(mktconst("HD27778", ParameterType.STRING));
		radSourcesList.add(mktconst("HD34078", ParameterType.STRING));
		radSourcesList.add(mktconst("HD36982", ParameterType.STRING));
		radSourcesList.add(mktconst("HD37022", ParameterType.STRING));
		radSourcesList.add(mktconst("HD37023", ParameterType.STRING));
		radSourcesList.add(mktconst("HD37061", ParameterType.STRING));
		radSourcesList.add(mktconst("HD37367", ParameterType.STRING));
		radSourcesList.add(mktconst("HD37903", ParameterType.STRING));
		radSourcesList.add(mktconst("HD38087", ParameterType.STRING));
		radSourcesList.add(mktconst("HD46202", ParameterType.STRING));
		radSourcesList.add(mktconst("HD48099", ParameterType.STRING));
		radSourcesList.add(mktconst("HD62542", ParameterType.STRING));
		radSourcesList.add(mktconst("HD73882", ParameterType.STRING));
		radSourcesList.add(mktconst("HD93028", ParameterType.STRING));
		radSourcesList.add(mktconst("HD93222", ParameterType.STRING));
		radSourcesList.add(mktconst("HD96675", ParameterType.STRING));
		radSourcesList.add(mktconst("HD102065", ParameterType.STRING));
		radSourcesList.add(mktconst("HD108927", ParameterType.STRING));
		radSourcesList.add(mktconst("HD147701", ParameterType.STRING));
		radSourcesList.add(mktconst("HD147888", ParameterType.STRING));
		radSourcesList.add(mktconst("HD147889", ParameterType.STRING));
		radSourcesList.add(mktconst("HD147933", ParameterType.STRING));
		radSourcesList.add(mktconst("HD149757", ParameterType.STRING));
		radSourcesList.add(mktconst("HD154368", ParameterType.STRING));
		radSourcesList.add(mktconst("HD154445", ParameterType.STRING));
		radSourcesList.add(mktconst("HD167771", ParameterType.STRING));
		radSourcesList.add(mktconst("HD168076", ParameterType.STRING));
		radSourcesList.add(mktconst("HD185418", ParameterType.STRING));
		radSourcesList.add(mktconst("HD193322", ParameterType.STRING));
		radSourcesList.add(mktconst("HD197512", ParameterType.STRING));
		radSourcesList.add(mktconst("HD199579", ParameterType.STRING));
		radSourcesList.add(mktconst("HD203938", ParameterType.STRING));
		radSourcesList.add(mktconst("HD204827", ParameterType.STRING));
		radSourcesList.add(mktconst("HD207198", ParameterType.STRING));
		radSourcesList.add(mktconst("HD210121", ParameterType.STRING));
		radSourcesList.add(mktconst("HD229196", ParameterType.STRING));
		radSourcesList.add(mktconst("HD252325", ParameterType.STRING));
		radSourcesList.add(mktconst("HD294264", ParameterType.STRING));
		radSourcesList.add(mktconst("Disk", ParameterType.STRING));

		Criterion sourceCriterion = new Criterion()
				.withExpression(radSourceExp).withConditionType(
						new BelongToSet().withValue(radSourcesList));

		AtomicParameterExpression radFieldExp = factory
				.createAtomicParameterExpression()
				.withParameterRef(radFieldRef);

		Criterion radiationCriterion = new Criterion().withExpression(
				radFieldExp).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0.1", ParameterType.REAL))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("10000000", ParameterType.REAL))));

		sourceCriterion.withLogicalConnector(new And()
				.withCriterion(radiationCriterion));

		AlwaysConditionalStatement alwaysRadiation = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(sourceCriterion))
				.withComment(
						"Radiation coefficient must be between 0.1 and 10^7");

		ConstraintOnGroup radiationConstraint = factory
				.createConstraintOnGroup();
		radiationConstraint.getConditionalStatement().add(alwaysRadiation);

		radiation.getParameterRef().add(radFieldRef);
		radiation.getParameterRef().add(radSourceRef);
		radiation.getParameterRef().add(avmaxRef);

		radiation.setConstraintOnGroup(radiationConstraint);

		// End of the work on radiation group

		// Starting working on Thermal State
		ParameterGroup densityConfig = factory.createParameterGroup().withName(
				"Equation of State");

		densityConfig.getParameterRef().add(ifisobRef);
		densityConfig.getParameterRef().add(densityRef);
		densityConfig.getParameterRef().add(pressureRef);

		List<Expression> ifisobList = new ArrayList<Expression>();
		ifisobList.add(mktconst("density", ParameterType.STRING));
		ifisobList.add(mktconst("pressure", ParameterType.STRING));

		AtomicParameterExpression ifisobExp = factory
				.createAtomicParameterExpression().withParameterRef(ifisobRef);

		Criterion ifisobCriterion = new Criterion().withExpression(ifisobExp)
				.withConditionType(new BelongToSet().withValue(ifisobList));

		List<Expression> ieqthList = new ArrayList<Expression>();
		ieqthList.add(mktconst("fixedTemperature", ParameterType.STRING));
		ieqthList.add(mktconst("isothermal", ParameterType.STRING));

		AtomicParameterExpression ieqthExp = factory
				.createAtomicParameterExpression().withParameterRef(ieqthRef);

		Criterion ieqthCriterion = new Criterion().withExpression(ieqthExp)
				.withConditionType(new BelongToSet().withValue(ieqthList));

	//	ifisobCriterion.withLogicalConnector(new And()
	//			.withCriterion(ieqthCriterion));

		AlwaysConditionalStatement choices = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(ifisobCriterion))
				.withComment(
						"First make choices for fixing EOS choice, and press valid button");

		Criterion ifisobEqualDensity = new Criterion()
				.withExpression(ifisobExp).withConditionType(
						new BelongToSet().withValue(mktconst("density",
								ParameterType.STRING)));

		Criterion ifisobEqualPressure = new Criterion().withExpression(
				ifisobExp).withConditionType(
				new BelongToSet().withValue(mktconst("pressure",
						ParameterType.STRING)));

		AtomicParameterExpression pressionExp = factory
				.createAtomicParameterExpression()
				.withParameterRef(pressureRef);

		AtomicParameterExpression densityExp = factory
				.createAtomicParameterExpression().withParameterRef(densityRef);

		Criterion pressionNull = new Criterion().withExpression(pressionExp)
				.withConditionType(new IsNull());

		Criterion densityRange = new Criterion()
				.withExpression(densityExp)
				.withConditionType(
						new ValueInRange()
								.withInf(
										new ValueLargerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"30",
																ParameterType.REAL)))
								.withSup(
										new ValueSmallerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"100000000",
																ParameterType.REAL))));

		IfThenConditionalStatement ifisobDensitySatement = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ifisobEqualDensity))
				.withThen(new Then().withCriterion(pressionNull))
				.withComment("pressure is null");

		IfThenConditionalStatement ifisobDensitySatement1 = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ifisobEqualDensity))
				.withThen(new Then().withCriterion(densityRange))
				.withComment("density must be between 30 and 10^8");

		Criterion densityNull = new Criterion().withExpression(densityExp)
				.withConditionType(new IsNull());

		Criterion pressureRange = new Criterion()
				.withExpression(pressionExp)
				.withConditionType(
						new ValueInRange()
								.withInf(
										new ValueLargerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"80",
																ParameterType.REAL)))
								.withSup(
										new ValueSmallerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"1000000000000",
																ParameterType.REAL))));

		IfThenConditionalStatement ifisobPressureStatement = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ifisobEqualPressure))
				.withThen(new Then().withCriterion(densityNull))
				.withComment("density is null");

		IfThenConditionalStatement ifisobPressureStatement1 = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ifisobEqualPressure))
				.withThen(new Then().withCriterion(pressureRange))
				.withComment("pressure must be between 80 and 10^12");

		Criterion ieqthIsoTherm = new Criterion().withExpression(ieqthExp)
				.withConditionType(
						new BelongToSet().withValue(mktconst("isothermal",
								ParameterType.STRING)));

		Criterion ieqthFixedT = new Criterion().withExpression(ieqthExp)
				.withConditionType(
						new BelongToSet().withValue(mktconst(
								"fixedTemperature", ParameterType.STRING)));

		AtomicParameterExpression temperatureExp = factory
				.createAtomicParameterExpression().withParameterRef(
						temperatureRef);

		Criterion temperatureNull = new Criterion().withExpression(
				temperatureExp).withConditionType(new IsNull());

		Criterion temperatureRange = new Criterion().withExpression(
				temperatureExp).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("2.7", ParameterType.REAL))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("10000", ParameterType.REAL))));
				
		
		IfThenConditionalStatement isothermal = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ieqthIsoTherm))
				.withThen(new Then().withCriterion(temperatureNull))
				.withComment("If thermalBalance is isothermal, T is null");

		IfThenConditionalStatement fixedT = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(ieqthFixedT))
				.withThen(new Then().withCriterion(temperatureRange))
				.withComment(
						"if thermalBalance is fixedT, then T is between 2.7 and 10^4 K");
		

		AlwaysConditionalStatement choices1 = new AlwaysConditionalStatement()
		.withAlways(new Always().withCriterion(ieqthCriterion))
		.withComment(
				"First make choices for thermal mode, than press valid button");
		
		ConstraintOnGroup densitiesConstraint = factory.createConstraintOnGroup();
		densitiesConstraint.getConditionalStatement().add(choices);
		densitiesConstraint.getConditionalStatement().add(ifisobDensitySatement);
		densitiesConstraint.getConditionalStatement()
				.add(ifisobPressureStatement);
		densitiesConstraint.getConditionalStatement().add(ifisobDensitySatement1);
		densitiesConstraint.getConditionalStatement().add(
				ifisobPressureStatement1);

		
		
		
		ParameterGroup temperatureConfig = factory.createParameterGroup().withName(
		"Temperature Configuration");
		
		
		temperatureConfig.getParameterRef().add(ieqthRef);
		temperatureConfig.getParameterRef().add(temperatureRef);
		
		ConstraintOnGroup thermalConstraint = factory.createConstraintOnGroup();
		thermalConstraint.getConditionalStatement().add(choices1);
		thermalConstraint.getConditionalStatement().add(isothermal);
		thermalConstraint.getConditionalStatement().add(fixedT);
		
		
		temperatureConfig.setConstraintOnGroup(thermalConstraint);
		
		densityConfig.setConstraintOnGroup(densitiesConstraint);

		inputsPG.getParameterRef().add(mailRef);

		inputsPG.getParameterGroup().add(radiation);
		inputsPG.getParameterGroup().add(densityConfig);
		inputsPG.getParameterGroup().add(temperatureConfig);
		
		
		service.setInputs(inputsPG);

		return service;
	}
}
