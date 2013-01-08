package test;


import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mkVectorConstant;
import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mkconst;
import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.AbstractCondition;
import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.LogicalConnector;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.ParenthesisContent;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;
import net.ivoa.parameter.model.When;
import net.ivoa.parameter.model.WhenConditionalStatement;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;


public class GroupActivation extends BaseExample {

    
    public static void main(String[] args) throws PropertyException, FileNotFoundException, JAXBException {
        GroupActivation example = new GroupActivation();
        example.marshall();
        
    }
	/**
	 * @param args
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws InvalidExpression 
	 * @throws InvalidParameterException 
	 */

	protected Service buildService() {
		// Creating the root element of the schema : the service
		Service service = factory.createService()
				.withServiceId("CMZ_PROTO_01")
				.withServiceName("MySuperService");
		service.setDescription("This simple service take as parameters a 3D vector speed, a mass and a time. It computes the kinetic energy and the displacement, assuming the speed is constant");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName("inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName("outputs");

		ParameterGroup speed = factory.createParameterGroup().withName(
				"vectorSpeed");
                ParameterGroup speedsp = factory.createParameterGroup().withName(
                        "SphericalSpeed");

		// Defining the parameters

		// Creating the mass parameter
		SingleParameter mass = factory.createSingleParameter();
		mass.setName("mass");
		mass.setParameterType(ParameterType.REAL);
		mass.setPrecision(mktconst("0.0001",ParameterType.REAL));
		mass.setSkossConcept("SKOSS_MASS");
		mass.setUnit("kg");
		mass.setDimension(mktconst("1",ParameterType.INTEGER));

		// Creating the time parameter
		SingleParameter time = factory.createSingleParameter();
		time.setName("time");
		time.setParameterType(ParameterType.BOOLEAN);
		time.setPrecision(mktconst("0.0001",ParameterType.REAL));
		time.setSkossConcept("SKOSS_TIME");
		time.setUnit("s");
		time.setDimension(mktconst("2",ParameterType.INTEGER));

		// Creating the kinetic energy parameter
		SingleParameter Kenergy = factory.createSingleParameter();
		Kenergy.setName("E");
		Kenergy.setParameterType(ParameterType.REAL);
		Kenergy.setPrecision(mktconst("0.0001",ParameterType.REAL));
		Kenergy.setSkossConcept("SKOSS_ENERGY");
		Kenergy.setUnit("J");
		Kenergy.setDimension(mktconst("1",ParameterType.INTEGER));

		// Creating the displacement parameter
		SingleParameter distance = factory.createSingleParameter();
		distance.setName("distance");
		distance.setParameterType(ParameterType.REAL);
		distance.setPrecision(mktconst("0.0001",ParameterType.REAL));
		distance.setSkossConcept("SKOSS_LENGHT");
		distance.setUnit("m");
		distance.setDimension(mktconst("1",ParameterType.INTEGER));
		

		// Creating the X component of speed
		SingleParameter speedX = factory.createSingleParameter();
		speedX.setName("speedX");
		speedX.setParameterType(ParameterType.REAL);
		speedX.setPrecision(mktconst("0.0001",ParameterType.REAL));
		speedX.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedX.setUnit("m/s");
		speedX.setDimension(mktconst("1",ParameterType.INTEGER));
		

		// Creating the Y component of speed
		SingleParameter speedY = factory.createSingleParameter();
		speedY.setName("speedY");
		speedY.setParameterType(ParameterType.REAL);
		speedY.setPrecision(mktconst("0.0001",ParameterType.REAL));
		speedY.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedY.setUnit("m/s");
		speedY.setDimension(mktconst("1",ParameterType.INTEGER));

		// Creating the Z component of speed
		SingleParameter speedZ = factory.createSingleParameter();
		speedZ.setName("speedZ");
		speedZ.setParameterType(ParameterType.REAL);
		speedZ.setPrecision(mktconst("0.0001",ParameterType.REAL));
		speedZ.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedZ.setUnit("m/s");
		speedZ.setDimension(mktconst("1",ParameterType.INTEGER));

		
		// Creating a parameter for basic Test
		SingleParameter positive = factory.createSingleParameter();
		positive.setName("positive");
		positive.setParameterType(ParameterType.REAL);
		positive.setPrecision(mktconst("0.0001",ParameterType.REAL));
		positive.setSkossConcept("SKOSS_POSITIVE");
		positive.setUnit("none");
		positive.setDimension(mktconst("1",ParameterType.INTEGER));
         
		positive.setRestriction((new AlwaysConditionalStatement().withAlways(new Always().withCriterion(new Criterion().withConditionType(
                new ValueLargerThan().withReached(true).withValue(mktconst("1",ParameterType.INTEGER) ))))));
         
	        //alternatives in spherical coordinates
		//radial component
                SingleParameter rdot = factory.createSingleParameter();
                rdot.setName("rdot");
                rdot.setParameterType(ParameterType.REAL);
                rdot.setPrecision(mktconst("0.0001",ParameterType.REAL));
                rdot.setSkossConcept("SKOSS_SPEED_COMPONENT");
                rdot.setUnit("m/s");
                rdot.setDimension(mktconst("1",ParameterType.INTEGER));
                

                // polar angle component of speed
                SingleParameter thetadot = factory.createSingleParameter();
                thetadot.setName("thetadot");
                thetadot.setParameterType(ParameterType.REAL);
                thetadot.setPrecision(mktconst("0.0001",ParameterType.REAL));
                thetadot.setSkossConcept("SKOSS_SPEED_COMPONENT");
                thetadot.setUnit("rad/s");
                thetadot.setDimension(mktconst("1",ParameterType.INTEGER));

                // Creating the azimuthal component of speed
                SingleParameter phidot = factory.createSingleParameter();
                phidot.setName("phidot");
                phidot.setParameterType(ParameterType.REAL);
                phidot.setPrecision(mktconst("0.0001",ParameterType.REAL));
                phidot.setSkossConcept("SKOSS_SPEED_COMPONENT");
                phidot.setUnit("rad/s");
                phidot.setDimension(mktconst("1",ParameterType.INTEGER));

                SingleParameter coordtyp = new SingleParameter().withName("coordsystem").withParameterType(ParameterType.STRING);
                
                //IMPL - would be nice to not have to put in the explicit parameter reference
                coordtyp.setRestriction(new AlwaysConditionalStatement().withAlways(new Always().withCriterion(new Criterion().withConditionType(
                        new BelongToSet().withValue(mkconst("cartesian"), mkconst("spherical"))).withExpression(mkRefExp(coordtyp)))));
                
                AbstractCondition abcon = new BelongToSet().withValue(mkconst("cartesian"));
                
                AtomicParameterExpression ctref = factory
                        .createAtomicParameterExpression().withParameterRef(mkRef(coordtyp));
                AbstractCriterion crit = new Criterion().withConditionType(abcon).withExpression(ctref);
                WhenConditionalStatement whenstmt = new WhenConditionalStatement().withWhen(new When(crit));
                speed.setActive(whenstmt);
                
                speedsp.setActive(new WhenConditionalStatement().withWhen(new When().withCriterion(new Criterion().withConditionType(
                                   new BelongToSet().withValue(mkconst("spherical"))).withExpression(ctref))));
                
		
		
		SingleParameter modeltype = new SingleParameter().withName("model").withParameterType(ParameterType.STRING);		
		AbstractCriterion crit2 = new Criterion().withConditionType(
		        new BelongToSet().withValue(mkconst("relativistic"),mkconst("non-relativistic")));
//		AlwaysConditionalStatement alwy = new AlwaysConditionalStatement().withAlways(new Always(crit));
//		modeltype.setDimension(mktconst("1",ParameterType.INTEGER));
		
                Parameters parameterList = factory.createParameters();
                    
                List<SingleParameter> parameters = parameterList.getParameter();
                parameters.add(speedX);
                parameters.add(speedY);
                parameters.add(speedZ);
                parameters.add(mass);
                parameters.add(Kenergy);
                parameters.add(time);
                parameters.add(distance);
                parameters.add(modeltype);
                parameters.add(coordtyp);
                parameters.add(rdot);
                parameters.add(phidot);
                parameters.add(thetadot);
                parameters.add(positive);
                

                
                service.setParameters(parameterList);
		ConstraintOnGroup speedConstraint = factory.createConstraintOnGroup();
		

		// Creating the expression for relativistic limit on speed vector
		ParameterReference speedxref = new ParameterReference()
				.withParameterName(speedX.getName());

		AtomicParameterExpression vx2 = factory
				.createAtomicParameterExpression().withParameterRef(speedxref)
				.withPower(mktconst("2",ParameterType.INTEGER));

		ParameterReference speedyref = new ParameterReference()
				.withParameterName(speedY.getName());

		AtomicParameterExpression vy2 = factory
				.createAtomicParameterExpression().withParameterRef(speedyref)
				.withPower(mktconst("2",ParameterType.INTEGER));

		ParameterReference speedzref = new ParameterReference()
				.withParameterName(speedZ.getName());

		AtomicParameterExpression vz2 = factory
				.createAtomicParameterExpression().withParameterRef(speedzref)
				.withPower(mktconst("2",ParameterType.INTEGER));

		Operation plusVZ2 = factory.createOperation().withOperationType(OperationType.PLUS)
				.withExpression(vz2);

		vy2.setOperation(plusVZ2);

		Operation plusVY2 = factory.createOperation().withOperationType(OperationType.PLUS)
				.withExpression(vy2);

		vx2.setOperation(plusVY2);

		ParenthesisContent speedNorm = factory.createParenthesisContent()
				.withExpression(vx2).withPower(mktconst("0.5",ParameterType.REAL));

		// Creating the criterion related to the relativistic speed constraint
		Criterion speedCriterion = factory.createCriterion().withExpression(
				speedNorm);

		//example of constructor with all values set
		ValueLargerThan vlt2 = new ValueLargerThan(mktconst("0",ParameterType.REAL), true);
		ValueSmallerThan vst2 = new ValueSmallerThan(mktconst("299792458",ParameterType.REAL), false);

		ValueInRange vir2 = factory.createValueInRange().withInf(vlt2)
				.withSup(vst2);

		speedCriterion.setConditionType(vir2);

		Always speedCondition = factory.createAlways().withCriterion(
				speedCriterion);

		// Adding the three components of speed to the group speed
		speed.getParameterRef().add(speedxref);
		speed.getParameterRef().add(speedyref);
		speed.getParameterRef().add(speedzref);
		
		AlwaysConditionalStatement alwaysStatement = factory
				.createAlwaysConditionalStatement().withAlways(speedCondition);
		alwaysStatement.setComment("The norm of speed must be smaller than c and greater than 0");
		
		speedConstraint.getConditionalStatement().add(alwaysStatement);

		// Adding the speed constraint to the group speed
		speed.setConstraintOnGroup(speedConstraint);

		
		speedsp.getParameterRef().add(mkRef(rdot));
		speedsp.getParameterRef().add(mkRef(phidot));
		speedsp.getParameterRef().add(mkRef(thetadot));
		// Defining constraint on time (must be positive)
		ParameterReference timeref = new ParameterReference()
				.withParameterName(time.getName());
		
		AtomicParameterExpression timeExpression = factory
				.createAtomicParameterExpression().withParameterRef(timeref);

	//	ValueLargerThan vltime = new ValueLargerThan(mktconst("0",ParameterType.REAL), true);
		
	//	List<Expression> listeExp = new ArrayList<Expression>();
	//	listeExp.add(mktconst("true",ParameterType.BOOLEAN));
	//	listeExp.add(mktconst("false",ParameterType.BOOLEAN));
	//	BelongToSet vltime = new BelongToSet().withValue(listeExp);
		
	//	DefaultValue vltime = new DefaultValue().withValue(mktconst("10",ParameterType.REAL));
		
		List<String> values = new ArrayList<String>();
		values.add("true");
		values.add("true");
		
		
		DefaultValue vltime = new DefaultValue().withValue(mkVectorConstant(values, ParameterType.BOOLEAN));
		
		
	//	IsNull vltime = new IsNull();
		
		
		Criterion timeCriterion = factory.createCriterion()
				.withExpression(timeExpression).withConditionType(vltime);

		// Defining constraint on mass (must be positive)
		ParameterReference massRef = mkRef(mass);
		
		AtomicParameterExpression massExpression = factory
				.createAtomicParameterExpression().withParameterRef(massRef);

		//example of the "fluid" api
		ValueLargerThan vlmass = factory.createValueLargerThan().withValue(mktconst("0",ParameterType.REAL))
				.withReached(false);

		Criterion massCriterion = factory.createCriterion()
				.withExpression(massExpression).withConditionType(vlmass);

		// Workin a little bit on inputs
		inputsPG.getParameterRef().add(massRef);
		inputsPG.getParameterRef().add(timeref);
		inputsPG.getParameterRef().add(mkRef(coordtyp));
		inputsPG.getParameterRef().add(mkRef(positive));

		inputsPG.getParameterGroup().add(speed);
		inputsPG.getParameterGroup().add(speedsp);

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();
		
		LogicalConnector massAndTime = factory.createAnd().withCriterion(
				timeCriterion);

		massCriterion.setLogicalConnector(massAndTime);
		
		Always massAndTimePositive = factory.createAlways().withCriterion(
				massCriterion);
		
		AlwaysConditionalStatement massAndTimeAlwaysStatement = factory
				.createAlwaysConditionalStatement().withAlways(
						massAndTimePositive);
		
		massAndTimeAlwaysStatement.setComment("Mass and time must always be positive (not strictly)");
		
		inputsConstraint.getConditionalStatement().add(
				massAndTimeAlwaysStatement);

//To comment the following line for tests
		inputsPG.setConstraintOnGroup(inputsConstraint);

		// Defining constraint on kinetic energy (must be positive)
		ParameterReference KenergyRef = mkRef(Kenergy);

		AtomicParameterExpression kineticExpression = factory
				.createAtomicParameterExpression().withParameterRef(KenergyRef);

		ValueLargerThan vlkinetic = new ValueLargerThan(mktconst("0",ParameterType.REAL), true);
				
		Criterion kineticCriterion = factory.createCriterion()
				.withExpression(kineticExpression).withConditionType(vlkinetic);
		
		Always alwaysKinetic = factory.createAlways().withCriterion(
				kineticCriterion);
		
		AlwaysConditionalStatement alwaysKineticEnergyPositiveStatement = factory
				.createAlwaysConditionalStatement().withAlways(alwaysKinetic);
		

		// Working on outputs
		outputsPG.getParameterRef().add(KenergyRef);
		outputsPG.getParameterRef().add(mkRef(distance));

		ConstraintOnGroup outputsConstraint = factory.createConstraintOnGroup();
		outputsConstraint.getConditionalStatement().add(
				alwaysKineticEnergyPositiveStatement);

		outputsPG.setConstraintOnGroup(outputsConstraint);

		// Last update on the UWS service
		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);
		return service;
	}

}
