package test;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.LogicalConnector;
import net.ivoa.parameter.model.ObjectFactory;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterList;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.ParenthesisContent;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.UWSService;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class Principale {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws JAXBException,
			FileNotFoundException {
		// Some technical stuffs for reading the java version of the DM
		JAXBContext jaxbContext = JAXBContext.newInstance("net.ivoa.parameter.model");

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(
				true));
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.ivoa.net/xml/Parameter/v0.1 UWS2-V1.1.xsd");
		// Creating the object factory factory
		ObjectFactory factory = new ObjectFactory();

		// Creating the root element of the schema : the service
		UWSService service = factory.createUWSService();
		service.setServiceId("CMZ_PROTO_01");
		service.setServiceName("name");
		service.setDescription("This simple service take as parameters a 3D vector speed, a mass and a time. It computes the kinetic energy and the displacement, assuming the speed is constant");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup();
		ParameterGroup outputsPG = factory.createParameterGroup();

		ParameterGroup speed = factory.createParameterGroup();
		speed.setName("vectorSpeed");

		// Defining the parameters

		// Creating the mass parameter
		SingleParameter mass = factory.createSingleParameter();
		mass.setName("mass");
		mass.setParameterType(ParameterType.REAL);
		mass.setPrecision("0.0001");
		mass.setSkossConcept("SKOSS_MASS");
		mass.setUnit("kg");

		// Creating the time parameter
		SingleParameter time = factory.createSingleParameter();
		time.setName("time");
		time.setParameterType(ParameterType.REAL);
		time.setPrecision("0.0001");
		time.setSkossConcept("SKOSS_TIME");
		time.setUnit("s");

		// Creating the kinetic energy parameter
		SingleParameter Kenergy = factory.createSingleParameter();
		Kenergy.setName("E");
		Kenergy.setParameterType(ParameterType.REAL);
		Kenergy.setPrecision("0.0001");
		Kenergy.setSkossConcept("SKOSS_ENERGY");
		Kenergy.setUnit("J");

		// Creating the displacement parameter
		SingleParameter distance = factory.createSingleParameter();
		distance.setName("distance");
		distance.setParameterType(ParameterType.REAL);
		distance.setPrecision("0.0001");
		distance.setSkossConcept("SKOSS_LENGHT");
		distance.setUnit("m");

		// Creating the X component of speed
		SingleParameter speedX = factory.createSingleParameter();
		speedX.setName("speedX");
		speedX.setParameterType(ParameterType.REAL);
		speedX.setPrecision("0.0001");
		speedX.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedX.setUnit("m/s");

		// Creating the Y component of speed
		SingleParameter speedY = factory.createSingleParameter();
		speedY.setName("speedY");
		speedY.setParameterType(ParameterType.REAL);
		speedY.setPrecision("0.0001");
		speedY.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedY.setUnit("m/s");

		// Creating the Z component of speed
		SingleParameter speedZ = factory.createSingleParameter();
		speedZ.setName("speedZ");
		speedZ.setParameterType(ParameterType.REAL);
		speedZ.setPrecision("0.0001");
		speedZ.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedZ.setUnit("m/s");
		
		
                ParameterList parameterList = factory.createParameterList();
                    
                parameterList.getParameter().add(speedX);
                parameterList.getParameter().add(speedY);
                parameterList.getParameter().add(speedZ);
                parameterList.getParameter().add(mass);
                parameterList.getParameter().add(Kenergy);
                parameterList.getParameter().add(time);
                parameterList.getParameter().add(distance);

                
                service.setParameterList(parameterList);
		ConstraintOnGroup speedConstraint = factory.createConstraintOnGroup();

		// Creating the expression for relativistic limit on speed vector
		AtomicParameterExpression vx2 = factory
				.createAtomicParameterExpression();
		ParameterReference speedxref = new ParameterReference();
		speedxref.setParameterName(speedX.getName());
		vx2.setParameterRef(speedxref);
		vx2.setPower("2");

		AtomicParameterExpression vy2 = factory
				.createAtomicParameterExpression();
                ParameterReference speedyref = new ParameterReference();
                speedyref.setParameterName(speedY.getName());
		vy2.setParameterRef(speedyref);
		vy2.setPower("2");

		AtomicParameterExpression vz2 = factory
				.createAtomicParameterExpression();
                ParameterReference speedzref = new ParameterReference();
                speedzref.setParameterName(speedZ.getName());
		vz2.setParameterRef(speedzref);
		vz2.setPower("2");

		Operation plusVZ2 = factory.createOperation();
		plusVZ2.setOperationType("PLUS");
		plusVZ2.setExpression(vz2);

		vy2.setOperation(plusVZ2);

		Operation plusVY2 = factory.createOperation();
		plusVY2.setOperationType("PLUS");
		plusVY2.setExpression(vy2);

		vx2.setOperation(plusVY2);

		ParenthesisContent speedNorm = factory.createParenthesisContent();
		speedNorm.setExpression(vx2);
		speedNorm.setPower("0.5");

		// Creating the criterion related to the relativistic speed constraint
		Criterion speedCriterion = factory.createCriterion();
		speedCriterion.setExpression(speedNorm);

		//example of constructor with all values set
		ValueLargerThan vlt2 = new ValueLargerThan("0", true);
		ValueSmallerThan vst2 = factory.createValueSmallerThan();
		vst2.setValue("299792458");
		vst2.setReached(false);
		ValueInRange vir2 = factory.createValueInRange();
		vir2.setInf(vlt2);
		vir2.setSup(vst2);
		speedCriterion.setConditionType(vir2);

		Always speedCondition = factory.createAlways();
		speedCondition.setCriterion(speedCriterion);

		// Adding the three components of speed to the group speed
		speed.getParameterRef().add(speedxref);
		speed.getParameterRef().add(speedyref);
		speed.getParameterRef().add(speedzref);
		
		
		AlwaysConditionalStatement alwaysStatement = factory.createAlwaysConditionalStatement();
		
		alwaysStatement.setAlways(speedCondition);
		
		
		speedConstraint.getConditionalStatement().add(alwaysStatement);

		// Adding the speed constraint to the group speed
		speed.setConstraintOnGroup(speedConstraint);

		
		
		// Defining constraint on time (must be positive)
		AtomicParameterExpression timeExpression = factory
				.createAtomicParameterExpression();
		ParameterReference timeref = new ParameterReference(); 
		timeref.setParameterName(time.getName());
                timeExpression.setParameterRef(timeref);

		Criterion timeCriterion = factory.createCriterion();
		timeCriterion.setExpression(timeExpression);
		ValueLargerThan vltime = factory.createValueLargerThan();
		vltime.setValue("0");
		vltime.setReached(true);
		timeCriterion.setConditionType(vltime);

		
		// Defining constraint on mass (must be positive)
		AtomicParameterExpression massExpression = factory
				.createAtomicParameterExpression();
		ParameterReference massRef = mkRef(mass);
                massExpression.setParameterRef(massRef);

		Criterion massCriterion = factory.createCriterion();
		massCriterion.setExpression(massExpression);
		//example of the "fluid" api
		ValueLargerThan vlmass = factory.createValueLargerThan().withValue("0").withReached(false);
		vlmass.setValue("0");
		vlmass.setReached(true);
		massCriterion.setConditionType(vlmass);

		// Workin a little bit on inputs
		inputsPG.getParameterRef().add(massRef);
		inputsPG.getParameterRef().add(timeref);

		inputsPG.getParameterGroup().add(speed);

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();
		
		LogicalConnector massAndTime = factory.createAnd();
		massAndTime.setCriterion(timeCriterion);
		massCriterion.setLogicalConnector(massAndTime);
		
		Always massAndTimePositive = factory.createAlways();
		massAndTimePositive.setCriterion(massCriterion);
		
		
		AlwaysConditionalStatement massAndTimeAlwaysStatement = factory.createAlwaysConditionalStatement();
		massAndTimeAlwaysStatement.setAlways(massAndTimePositive);
		
		inputsConstraint.getConditionalStatement().add(massAndTimeAlwaysStatement);
		

		inputsPG.setConstraintOnGroup(inputsConstraint);

		// Defining constraint on kinetic energy (must be positive)
		AtomicParameterExpression kineticExpression = factory
				.createAtomicParameterExpression();
		ParameterReference KenergyRef = mkRef(Kenergy);
        kineticExpression.setParameterRef(KenergyRef);

		Criterion kineticCriterion = factory.createCriterion();
		kineticCriterion.setExpression(kineticExpression);
		ValueLargerThan vlkinetic = factory.createValueLargerThan();
		vlkinetic.setValue("0");
		vlkinetic.setReached(true);
		kineticCriterion.setConditionType(vlkinetic);

				
		Always alwaysKinetic = factory.createAlways();
		alwaysKinetic.setCriterion(kineticCriterion);
		
		AlwaysConditionalStatement alwaysKineticEnergyPositiveStatement = factory.createAlwaysConditionalStatement();
		alwaysKineticEnergyPositiveStatement.setAlways(alwaysKinetic);
		
		

		// Working on outputs
		outputsPG.getParameterRef().add(KenergyRef);
		outputsPG.getParameterRef().add(mkRef(distance));

		ConstraintOnGroup outputsConstraint = factory.createConstraintOnGroup();
		outputsConstraint.getConditionalStatement().add(alwaysKineticEnergyPositiveStatement);

		outputsPG.setConstraintOnGroup(outputsConstraint);

		// Last update on the UWS service
		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		marshaller.marshal(service, new FileOutputStream("UWS_TEST.xml"));

	}
	
	private static ParameterReference mkRef(SingleParameter par){
	    ParameterReference ref = new ParameterReference();
	    ref.setParameterName(par.getName());
            return ref ;
	}

}
