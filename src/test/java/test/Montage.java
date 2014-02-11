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

public class Montage extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		Montage example = new Montage();
		example.marshall();
	}

	@Override
	protected Service buildService() {

		Service service = factory
				.createService()
				.withServiceId(
						//"http://pdl-calc.obspm.fr:8081/montage/OnlineCode")
						"http://dae81.iaa.es:8081/montage/OnlineCode")
				.withServiceName("Image Mosaicing Service");
		service.setDescription("Prototype of a service to build and assemble reprojected astronomical images with Montage software tasks");

		// Adding parameters to the list of service params
		Parameters parameterList = factory.createParameters();

		// Creating all the parameters

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("mail");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkosConcept("SKOS_MAIL");
		mail.setUnit("none");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(mail);
		ParameterReference mail_REF = new ParameterReference()
				.withParameterName(mail.getName());

		SingleParameter NAXIS1 = factory.createSingleParameter();
		NAXIS1.setName("NAXIS1");
		NAXIS1.setSkosConcept("Number of pixels in horizontal axis");
		NAXIS1.setParameterType(ParameterType.INTEGER);
		NAXIS1.setPrecision(mkconst(0.0));
		NAXIS1.setUnit("None");
		NAXIS1.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(NAXIS1);
		ParameterReference NAXIS1_REF = new ParameterReference()
				.withParameterName(NAXIS1.getName());

		SingleParameter NAXIS2 = factory.createSingleParameter();
		NAXIS2.setName("NAXIS2");
		NAXIS2.setSkosConcept("Number of pixels in vertical axis");
		NAXIS2.setParameterType(ParameterType.INTEGER);
		NAXIS2.setPrecision(mkconst(0.0));
		NAXIS2.setUnit("None");
		NAXIS2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(NAXIS2);
		ParameterReference NAXIS2_REF = new ParameterReference()
				.withParameterName(NAXIS2.getName());

		SingleParameter CTYPE1 = factory.createSingleParameter();
		CTYPE1.setName("CTYPE1");
		CTYPE1.setSkosConcept("WCS Type of the horizontal coordinate, including coordinate and projection, each allowed three character");
		CTYPE1.setParameterType(ParameterType.STRING);
		CTYPE1.setPrecision(mkconst(0.0));
		CTYPE1.setUnit("None");
		CTYPE1.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CTYPE1);
		ParameterReference CTYPE1_REF = new ParameterReference()
				.withParameterName(CTYPE1.getName());

		SingleParameter CTYPE2 = factory.createSingleParameter();
		CTYPE2.setName("CTYPE2");
		CTYPE2.setSkosConcept("WCS Type of the vertical coordinate, including coordinate and projection, each allowed three character");
		CTYPE2.setParameterType(ParameterType.STRING);
		CTYPE2.setPrecision(mkconst(0.0));
		CTYPE2.setUnit("None");
		CTYPE2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CTYPE2);
		ParameterReference CTYPE2_REF = new ParameterReference()
				.withParameterName(CTYPE2.getName());

		SingleParameter CRVAL1 = factory.createSingleParameter();
		CRVAL1.setName("CRVAL1");
		CRVAL1.setSkosConcept("Horizonal axis WCS value at the reference pixel");
		CRVAL1.setParameterType(ParameterType.REAL);
		CRVAL1.setPrecision(mkconst(0.0));
		CRVAL1.setUnit("degree");
		CRVAL1.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CRVAL1);
		ParameterReference CRVAL1_REF = new ParameterReference()
				.withParameterName(CRVAL1.getName());

		SingleParameter CRVAL2 = factory.createSingleParameter();
		CRVAL2.setName("CRVAL2");
		CRVAL2.setSkosConcept("Vertical axis WCS value at the reference pixel");
		CRVAL2.setParameterType(ParameterType.REAL);
		CRVAL2.setPrecision(mkconst(0.0));
		CRVAL2.setUnit("degree");
		CRVAL2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CRVAL2);
		ParameterReference CRVAL2_REF = new ParameterReference()
				.withParameterName(CRVAL2.getName());

		SingleParameter CRPIX1 = factory.createSingleParameter();
		CRPIX1.setName("CRPIX1");
		CRPIX1.setSkosConcept("Reference pixel on the horizonal axis");
		CRPIX1.setParameterType(ParameterType.INTEGER);
		CRPIX1.setPrecision(mkconst(0.0));
		CRPIX1.setUnit("None");
		CRPIX1.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CRPIX1);
		ParameterReference CRPIX1_REF = new ParameterReference()
				.withParameterName(CRPIX1.getName());

		SingleParameter CRPIX2 = factory.createSingleParameter();
		CRPIX2.setName("CRPIX2");
		CRPIX2.setSkosConcept("Reference pixel on the vertical axis");
		CRPIX2.setParameterType(ParameterType.INTEGER);
		CRPIX2.setPrecision(mkconst(0.0));
		CRPIX2.setUnit("None");
		CRPIX2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CRPIX2);
		ParameterReference CRPIX2_REF = new ParameterReference()
				.withParameterName(CRPIX2.getName());

		SingleParameter CDELT1 = factory.createSingleParameter();
		CDELT1.setName("CDELT1");
		CDELT1.setSkosConcept("Change per pixel in horizonal axis WCS value at the reference pixel");
		CDELT1.setParameterType(ParameterType.REAL);
		CDELT1.setPrecision(mkconst(0.0));
		CDELT1.setUnit("degree");
		CDELT1.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CDELT1);
		ParameterReference CDELT1_REF = new ParameterReference()
				.withParameterName(CDELT1.getName());

		SingleParameter CDELT2 = factory.createSingleParameter();
		CDELT2.setName("CDELT2");
		CDELT2.setSkosConcept("Change per pixel in vertical axis WCS value at the reference pixel");
		CDELT2.setParameterType(ParameterType.REAL);
		CDELT2.setPrecision(mkconst(0.0));
		CDELT2.setUnit("degree");
		CDELT2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CDELT2);
		ParameterReference CDELT2_REF = new ParameterReference()
				.withParameterName(CDELT2.getName());

		SingleParameter CROTA2 = factory.createSingleParameter();
		CROTA2.setName("CROTA2");
		CROTA2.setSkosConcept("Rotation angle of image around the reference pixel");
		CROTA2.setParameterType(ParameterType.REAL);
		CROTA2.setPrecision(mkconst(0.0));
		CROTA2.setUnit("degree");
		CROTA2.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(CROTA2);
		ParameterReference CROTA2_REF = new ParameterReference()
				.withParameterName(CROTA2.getName());

		SingleParameter ImageLocation = factory.createSingleParameter();
		ImageLocation.setName("ImageLocation");
		ImageLocation.setSkosConcept("Folder where the raw images are stored");
		ImageLocation.setParameterType(ParameterType.STRING);
		ImageLocation.setPrecision(mkconst(0.0));
		ImageLocation.setUnit("None");
		ImageLocation.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(ImageLocation);
		ParameterReference ImageLocationREF = new ParameterReference()
				.withParameterName(ImageLocation.getName());

		SingleParameter EQUINOX = factory.createSingleParameter();
		EQUINOX.setName("EQUINOX");
		EQUINOX.setSkosConcept("Rotation angle of image around the reference pixel");
		EQUINOX.setParameterType(ParameterType.STRING);
		EQUINOX.setPrecision(mkconst(0.0));
		EQUINOX.setUnit("year");
		EQUINOX.setDimension(mktconst("1", ParameterType.INTEGER));
		parameterList.getParameter().add(EQUINOX);
		ParameterReference EQUINOX_REF = new ParameterReference()
				.withParameterName(EQUINOX.getName());
		
		
		// Defining the parameter for the result
		
		SingleParameter result = factory.createSingleParameter();
		result.setName("fileResult");
		result.setSkosConcept("url to the tar file containing the mosaiced fits files");
		result.setParameterType(ParameterType.STRING);
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
		
	
		

		coordinatesGroup.getParameterRef().add(NAXIS1_REF);
		coordinatesGroup.getParameterRef().add(NAXIS2_REF);
		coordinatesGroup.getParameterRef().add(CTYPE1_REF);
		coordinatesGroup.getParameterRef().add(CTYPE2_REF);
		coordinatesGroup.getParameterRef().add(CRVAL1_REF);
		coordinatesGroup.getParameterRef().add(CRVAL2_REF);
		coordinatesGroup.getParameterRef().add(CRPIX1_REF);
		coordinatesGroup.getParameterRef().add(CRPIX2_REF);
		coordinatesGroup.getParameterRef().add(CDELT1_REF);
		coordinatesGroup.getParameterRef().add(CDELT2_REF);
		coordinatesGroup.getParameterRef().add(CROTA2_REF);
		coordinatesGroup.getParameterRef().add(EQUINOX_REF);
		coordinatesGroup.getParameterRef().add(ImageLocationREF);

		ParameterGroup technical = factory.createParameterGroup().withName(
				"Technical parameters");

		technical.getParameterRef().add(mail_REF);

		AtomicParameterExpression NAXIS1AtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(NAXIS1_REF);

		AtomicParameterExpression NAXIS2AtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(NAXIS2_REF);

		Criterion NAXIS1Range = new Criterion().withExpression(
				NAXIS1AtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("1", ParameterType.INTEGER))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("10000", ParameterType.INTEGER))));

		Criterion NAXIS2Range = new Criterion().withExpression(
				NAXIS2AtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("1", ParameterType.INTEGER))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("10000", ParameterType.INTEGER))));

		AtomicParameterExpression CRVAL1AtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(CRVAL1_REF);

		Criterion CRVAL1Range = new Criterion().withExpression(
				CRVAL1AtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.REAL))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("360", ParameterType.REAL))));

		AtomicParameterExpression CRVAL2AtomicExpression = factory
				.createAtomicParameterExpression().withParameterRef(CRVAL2_REF);

		Criterion CRVAL2Range = new Criterion().withExpression(
				CRVAL2AtomicExpression).withConditionType(
				new ValueInRange().withInf(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("-90", ParameterType.REAL))).withSup(
						new ValueSmallerThan().withReached(true).withValue(
								mktconst("90", ParameterType.REAL))));

		AtomicParameterExpression EQUINOXAtomicExpression = factory
				.createAtomicParameterExpression()
				.withParameterRef(EQUINOX_REF);

		Criterion EQUINOXDefaultValue = new Criterion().withExpression(
				EQUINOXAtomicExpression).withConditionType(
				new DefaultValue().withValue(mktconst("2000",
						ParameterType.STRING)));

		// defining criterion for the ImageLocation default value
		AtomicParameterExpression ImageLocationExpr = factory
				.createAtomicParameterExpression().withParameterRef(
						ImageLocationREF);

		Criterion ImageLocationDefault = new Criterion().withExpression(
				ImageLocationExpr).withConditionType(
				new DefaultValue().withValue(mktconst("SampleLocation",
						ParameterType.STRING)));

		AlwaysConditionalStatement ImageLocationAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(ImageLocationDefault))
				.withComment(
						"Default value for image location is 'SampleLocation'");

		// Defining the operations

		AtomicParameterExpression CRPIX1Expression = new AtomicParameterExpression()
				.withParameterRef(CRPIX1_REF);

		AtomicParameterExpression NAXIS1minusCRPIX1Expression = new AtomicParameterExpression()
				.withParameterRef(NAXIS1_REF).withOperation(
						new Operation().withOperationType(OperationType.MINUS)
								.withExpression(CRPIX1Expression));

		AtomicParameterExpression CRPIX2Expression = new AtomicParameterExpression()
				.withParameterRef(CRPIX2_REF);

		AtomicParameterExpression NAXIS2minusCRPIX2Expression = new AtomicParameterExpression()
				.withParameterRef(NAXIS2_REF).withOperation(
						new Operation().withOperationType(OperationType.MINUS)
								.withExpression(CRPIX2Expression));

		Criterion HorizontalPositive = new Criterion().withExpression(
				NAXIS1minusCRPIX1Expression).withConditionType(
				new ValueLargerThan().withReached(true).withValue(
						mktconst("0", ParameterType.INTEGER)));

		Criterion VerticalPositive = new Criterion().withExpression(
				NAXIS2minusCRPIX2Expression).withConditionType(
				new ValueLargerThan().withReached(true).withValue(
						mktconst("0", ParameterType.INTEGER)));

		AlwaysConditionalStatement NAXIS1Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NAXIS1Range))
				.withComment(
						"Number of pixel in horizontal axis must be between 1 and 100000");

		AlwaysConditionalStatement NAXIS2Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NAXIS2Range))
				.withComment(
						"Number of pixel in vertical axis must be between 1 and 100000");

		AlwaysConditionalStatement CRVAL1Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(CRVAL1Range))
				.withComment(
						"Horizonal axis WCS value at the reference pixel must be between 0 and 360");

		AlwaysConditionalStatement CRVAL2Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(CRVAL2Range))
				.withComment(
						"Vertical axis WCS value at the reference pixel must be between -90 and 90");

		AlwaysConditionalStatement EQUINOXAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(EQUINOXDefaultValue))
				.withComment("default value for EQUINOX is 2000");

		AlwaysConditionalStatement HorizontalAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(HorizontalPositive))
				.withComment(
						"The horizontal reference pixel should be included in the Image: CRPIX1<=NAXIS1");

		AlwaysConditionalStatement VerticalAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(VerticalPositive))
				.withComment(
						"The vertical reference pixel should be included in the Image: CRPIX2<=NAXIS2");

		// constraint on CDELT1 and CELDT2
		AtomicParameterExpression CDELT1Expr = new AtomicParameterExpression()
				.withParameterRef(CDELT1_REF);

		Criterion CDELT1_CRIT = new Criterion().withExpression(CDELT1Expr)
				.withConditionType(
						new ValueDifferentFrom().withValue((mktconst("0",
								ParameterType.INTEGER))));

		AlwaysConditionalStatement CDELT1_Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(CDELT1_CRIT))
				.withComment("CDELT1 must be different from 0");

		AtomicParameterExpression CDELT2Expr = new AtomicParameterExpression()
				.withParameterRef(CDELT2_REF);

		Criterion CDELT2_CRIT = new Criterion().withExpression(CDELT2Expr)
				.withConditionType(
						new ValueDifferentFrom().withValue((mktconst("0",
								ParameterType.INTEGER))));

		AlwaysConditionalStatement CDELT2_Always = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(CDELT2_CRIT))
				.withComment("CDELT2 must be different from 0");

		ConstraintOnGroup coordinatesConstraints = factory
				.createConstraintOnGroup();
		coordinatesConstraints.getConditionalStatement().add(NAXIS1Always);
		coordinatesConstraints.getConditionalStatement().add(NAXIS2Always);
		coordinatesConstraints.getConditionalStatement().add(CRVAL1Always);
		coordinatesConstraints.getConditionalStatement().add(CRVAL2Always);
		coordinatesConstraints.getConditionalStatement().add(EQUINOXAlways);
		coordinatesConstraints.getConditionalStatement().add(HorizontalAlways);
		coordinatesConstraints.getConditionalStatement().add(VerticalAlways);
		coordinatesConstraints.getConditionalStatement().add(CDELT1_Always);
		coordinatesConstraints.getConditionalStatement().add(CDELT2_Always);
		coordinatesConstraints.getConditionalStatement().add(ImageLocationAlways);

		coordinatesGroup.setConstraintOnGroup(coordinatesConstraints);

		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");

		inputsPG.getParameterGroup().add(coordinatesGroup);
		inputsPG.getParameterGroup().add(technical);
		
		
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");
		
		outputsPG.getParameterRef().add(result_REF);
		
		
		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		return service;
	}
}
