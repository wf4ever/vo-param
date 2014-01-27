package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mkconst;
import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.awt.image.SampleModel;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.ValueDifferentFrom;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class test extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		test example = new test();
		example.marshall();
	}

	@Override
	protected Service buildService() {


		Service service = factory
				.createService()
				.withServiceId(
						"http://amiga.iaa.csic.es/amigasearch/search")
				.withServiceName("AMIGA cone search");
		service.setDescription("Cone search for isolated galaxies catalog");

		// Adding parameters to the list of service params
		Parameters parameterList = factory.createParameters();

		// Creating all the parameters

		//SingleParameter mail = factory.createSingleParameter();
		//mail.setName("mail");
		//mail.setParameterType(ParameterType.STRING);
		//mail.setSkossConcept("SKOS_MAIL");
		//mail.setUnit("none");
		//mail.setDimension(mktconst("1", ParameterType.INTEGER));
		//parameterList.getParameter().add(mail);
		//ParameterReference mail_REF = new ParameterReference()
		//		.withParameterName(mail.getName());

		SingleParameter RA = factory.createSingleParameter();
		RA.setName("RA");
		RA.setSkossConcept("Right ascension");
		RA.setParameterType(ParameterType.REAL);
		RA.setPrecision(mkconst(0.0));
		RA.setUnit("deg");
		RA.setUCD("POS_EQ_RA");
		RA.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(RA);
		ParameterReference RA_REF = new ParameterReference()
				.withParameterName(RA.getName());

		SingleParameter DEC = factory.createSingleParameter();
		DEC.setName("DEC");
		DEC.setSkossConcept("Declination");
		DEC.setParameterType(ParameterType.REAL);
		DEC.setPrecision(mkconst(0.0));
		DEC.setUnit("deg");
		DEC.setUCD("POS_EQ_DEC");
		DEC.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(DEC);
		ParameterReference DEC_REF = new ParameterReference()
				.withParameterName(DEC.getName());

		SingleParameter SR = factory.createSingleParameter();
		SR.setName("SR");
		SR.setSkossConcept("Radius");
		SR.setParameterType(ParameterType.REAL);
		SR.setPrecision(mkconst(0.0));
		SR.setUnit("None");
		SR.setUCD("OBS_ANG-SIZE");
		SR.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(SR);
		ParameterReference SR_REF = new ParameterReference()
				.withParameterName(SR.getName());
		
	
		// Defining the parameter for the result
		
		SingleParameter result = factory.createSingleParameter();
		result.setName("VOTableResult");
		result.setSkossConcept("url to the tar file containing the mosaiced fits files");
		result.setParameterType(ParameterType.TABLE);
		result.setPrecision(mkconst(0.0));
		result.setUnit("None");
		result.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(result);
		ParameterReference result_REF = new ParameterReference()
				.withParameterName(result.getName());

		// Adding the parameter list to the service
		service.setParameters(parameterList);

		// Defining the groups
		// Creating input and output parameter group
		ParameterGroup coordinatesGroup = factory.createParameterGroup()
				.withName("Coordinates Header");

		coordinatesGroup.getParameterRef().add(RA_REF);
		coordinatesGroup.getParameterRef().add(DEC_REF);
		coordinatesGroup.getParameterRef().add(SR_REF);
		
		//ParameterGroup technical = factory.createParameterGroup().withName(
		//		"Technical parameters");

		//technical.getParameterRef().add(mail_REF);

		AtomicParameterExpression RAAtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(RA_REF);

		AtomicParameterExpression DECAtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(DEC_REF);

		Criterion RARange = new Criterion().withExpression(
				RAAtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.INTEGER))).withSup(
						new ValueSmallerThan().withReached(false).withValue(
								mktconst("360", ParameterType.INTEGER))));

		Criterion DECRange = new Criterion().withExpression(
				DECAtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("-90", ParameterType.REAL))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("90", ParameterType.REAL))));

		AtomicParameterExpression SRAtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(SR_REF);

		Criterion SRPositive = new Criterion().withExpression(
				SRAtomicExpression).withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.INTEGER)));

		AlwaysConditionalStatement RAAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(RARange))
				.withComment(
						"Right ascension must be in the interval [0,360)");

		AlwaysConditionalStatement DECAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(DECRange))
				.withComment(
						"Declination must be in the interval (-90, 90)");

		AlwaysConditionalStatement SRAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(SRPositive))
				.withComment(
						"Radius must be greater or equal to 0");


		ConstraintOnGroup coordinatesConstraints = factory
				.createConstraintOnGroup();
		coordinatesConstraints.getConditionalStatement().add(RAAlways);
		coordinatesConstraints.getConditionalStatement().add(DECAlways);
		coordinatesConstraints.getConditionalStatement().add(SRAlways);
		
		coordinatesGroup.setConstraintOnGroup(coordinatesConstraints);

		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");

		inputsPG.getParameterGroup().add(coordinatesGroup);
		//inputsPG.getParameterGroup().add(technical);
		
		
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");
		
		outputsPG.getParameterRef().add(result_REF);
		
		
		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		return service;
	}
}
