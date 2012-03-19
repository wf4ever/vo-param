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
import net.ivoa.parameter.model.Function;
import net.ivoa.parameter.model.FunctionExpression;
import net.ivoa.parameter.model.FunctionType;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.IsInteger;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.ParenthesisContent;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class Example02 extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		Example02 example = new Example02();
		example.marshall();

	}

	@Override
	protected Service buildService() {

		// Creating the root element of the schema : the service
		Service service = factory.createService().withServiceId("PDLExample02")
				.withServiceName("MySuperService");
		service.setDescription("Description corresponding to the second example in the PDL documentation");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		// Defining the parameters

		// Creating the mass parameter
		SingleParameter p1 = factory.createSingleParameter();
		p1.setName("p1");
		p1.setParameterType(ParameterType.REAL);
		p1.setPrecision(mktconst("0.0001", ParameterType.REAL));
		p1.setSkossConcept("SKOS_REAL");
		p1.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter p2 = factory.createSingleParameter();
		p2.setName("p2");
		p2.setParameterType(ParameterType.INTEGER);
		p2.setPrecision(mktconst("0", ParameterType.REAL));
		p2.setSkossConcept("SKOS_INTEGER");
		p1.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter p3 = factory.createSingleParameter();
		p3.setName("p3");
		p3.setParameterType(ParameterType.REAL);
		p3.setPrecision(mktconst("0.0001", ParameterType.REAL));
		p3.setSkossConcept("SKOS_REAL");
		p3.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter p4 = factory.createSingleParameter();
		p4.setName("p4");
		p4.setParameterType(ParameterType.REAL);
		p4.setPrecision(mktconst("0.0001", ParameterType.REAL));
		p4.setSkossConcept("SKOS_REAL_VECTOR");
		p4.setDimension(mktconst("3", ParameterType.INTEGER));

		SingleParameter p5 = factory.createSingleParameter();
		p5.setName("p5");
		p5.setParameterType(ParameterType.REAL);
		p5.setPrecision(mktconst("0.0001", ParameterType.REAL));
		p5.setSkossConcept("SKOS_REAL_VECTOR");
		p5.setDimension(mktconst("3", ParameterType.INTEGER));

		ParameterReference p1Ref = new ParameterReference()
				.withParameterName(p1.getName());

		ParameterReference p2Ref = new ParameterReference()
				.withParameterName(p2.getName());

		ParameterReference p3Ref = new ParameterReference()
				.withParameterName(p3.getName());

		ParameterReference p4Ref = new ParameterReference()
				.withParameterName(p4.getName());

		ParameterReference p5Ref = new ParameterReference()
				.withParameterName(p5.getName());

		AtomicParameterExpression p1Exp = factory
				.createAtomicParameterExpression().withParameterRef(p1Ref);

		AtomicParameterExpression p2Exp = factory
				.createAtomicParameterExpression().withParameterRef(p2Ref);

		AtomicParameterExpression p3Exp = factory
				.createAtomicParameterExpression().withParameterRef(p3Ref);

		AtomicParameterExpression p4Exp = factory
				.createAtomicParameterExpression().withParameterRef(p4Ref);

		AtomicParameterExpression p5Exp = factory
				.createAtomicParameterExpression().withParameterRef(p5Ref);
		
		
		
		
		 Parameters parameterList = factory.createParameters();
         
         parameterList.getParameter().add(p1);
         parameterList.getParameter().add(p2);
         parameterList.getParameter().add(p3);
         parameterList.getParameter().add(p4);
         parameterList.getParameter().add(p5);
         
         service.setParameters(parameterList);
		
		ValueInRange p1In = new ValueInRange().withInf(
				new ValueLargerThan().withValue(
						mktconst("0", ParameterType.REAL)).withReached(false))
				.withSup(
						new ValueSmallerThan().withValue(
								mktconst("1.5707963", ParameterType.REAL))
								.withReached(true));

		List<Expression> listeExp = new ArrayList<Expression>();
		listeExp.add(mktconst("2", ParameterType.INTEGER));
		listeExp.add(mktconst("4", ParameterType.INTEGER));
		listeExp.add(mktconst("6", ParameterType.INTEGER));
		BelongToSet p2Set = new BelongToSet().withValue(listeExp);

		ValueInRange p3In = new ValueInRange().withInf(
				new ValueLargerThan().withReached(true).withValue(
						mktconst("-1", ParameterType.REAL))).withSup(
				new ValueSmallerThan().withReached(true).withValue(
						mktconst("1", ParameterType.REAL)));

		FunctionExpression sinp1PowerP2MinusP3 = new FunctionExpression()
				.withFunction(
						new Function().withExpression(p1Exp).withFunctionName(
								FunctionType.SIN))
				.withPower(p2Exp)
				.withOperation(
						new Operation().withOperationType(OperationType.MINUS)
								.withExpression(p3Exp));

		Expression sqrtAbsvalueMinus32 = new FunctionExpression()
				.withFunction(
						new Function().withFunctionName(FunctionType.ABS)
								.withExpression(sinp1PowerP2MinusP3))
				.withPower(mktconst("0.5", ParameterType.REAL))
				.withOperation(
						new Operation().withExpression(
								mktconst("1.5", ParameterType.REAL))
								.withOperationType(OperationType.MINUS));

		Criterion crp1_1 = new Criterion().withExpression(p1Exp)
				.withConditionType(p1In);

		Criterion crp2_1 = new Criterion().withExpression(p2Exp)
				.withConditionType(p2Set);

		Criterion crp3_1 = new Criterion().withExpression(p3Exp)
				.withConditionType(p3In);

		Criterion function = new Criterion()
				.withExpression(sqrtAbsvalueMinus32).withConditionType(
						new ValueSmallerThan().withReached(false).withValue(
								mktconst("0", ParameterType.REAL)));

		crp3_1.withLogicalConnector(new And().withCriterion(function));

		crp2_1.withLogicalConnector(new And().withCriterion(crp3_1));

		IfThenConditionalStatement firstIf = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(crp1_1)).withThen(
						new Then().withCriterion(crp2_1));

		ValueInRange p1InB = new ValueInRange().withInf(
				new ValueLargerThan().withValue(
						mktconst("1.5707963", ParameterType.REAL)).withReached(
						false)).withSup(
				new ValueSmallerThan().withValue(
						mktconst("3.1415927", ParameterType.REAL)).withReached(
						true));

		ValueInRange p2InB = new ValueInRange().withInf(
				new ValueLargerThan().withValue(
						mktconst("0", ParameterType.INTEGER))
						.withReached(false)).withSup(
				new ValueSmallerThan().withValue(
						mktconst("10", ParameterType.INTEGER)).withReached(
						false));

		FunctionExpression logExp = new FunctionExpression().withFunction(
				new Function(p2Exp, FunctionType.LOG)).withOperation(
				new Operation().withOperationType(OperationType.MINUS)
						.withExpression(p3Exp));

		ParenthesisContent product = new ParenthesisContent().withExpression(
				p1Exp).withOperation(
				new Operation().withOperationType(OperationType.MULTIPLY)
						.withExpression(p2Exp));

		Criterion crp1_2 = new Criterion().withExpression(p1Exp)
				.withConditionType(p1InB);

		Criterion crp2_2 = new Criterion().withExpression(p2Exp)
				.withConditionType(p2InB);

		Criterion crp3_2 = new Criterion().withExpression(logExp)
				.withConditionType(
						new ValueSmallerThan().withReached(false).withValue(
								mktconst("0", ParameterType.REAL)));

		Criterion crp_pr = new Criterion().withExpression(product)
				.withConditionType(new IsInteger());

		crp3_2.withLogicalConnector(new And().withCriterion(crp_pr));
		crp2_2.withLogicalConnector(new And().withCriterion(crp3_2));

		IfThenConditionalStatement secondIf = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(crp1_2)).withThen(
						new Then().withCriterion(crp2_2));

		ConstraintOnGroup inputsContraint = new ConstraintOnGroup();
		inputsContraint.getConditionalStatement().add(firstIf);
		inputsContraint.getConditionalStatement().add(secondIf);

		inputsPG.setConstraintOnGroup(inputsContraint);

		// Workin a little bit on inputs
		inputsPG.getParameterRef().add(p1Ref);
		inputsPG.getParameterRef().add(p2Ref);
		inputsPG.getParameterRef().add(p3Ref);
		
		
		
		// Defining constraints on outputs

		

		ParenthesisContent numerator = new ParenthesisContent().withExpression(
				p5Exp).withOperation(
				new Operation().withExpression(p5Exp).withOperationType(
						OperationType.SCALAR));

		ParenthesisContent denominator = new ParenthesisContent()
				.withExpression(p4Exp).withOperation(
						new Operation().withExpression(p4Exp)
								.withOperationType(OperationType.SCALAR));

		ParenthesisContent fraction = new ParenthesisContent().withExpression(
				numerator).withOperation(
				new Operation().withExpression(denominator).withOperationType(
						OperationType.DIVIDE));
		
		Criterion outCr = new Criterion().withExpression(fraction).withConditionType(new ValueSmallerThan().withReached(false).withValue(mktconst("0.01", ParameterType.REAL)));

		AlwaysConditionalStatement alwaysOut = new AlwaysConditionalStatement().withAlways(new Always().withCriterion(outCr));
		
		ConstraintOnGroup outputConstraint = new ConstraintOnGroup().withConditionalStatement(alwaysOut);
		outputsPG.setConstraintOnGroup(outputConstraint);
		
		outputsPG.getParameterRef().add(p4Ref);
		outputsPG.getParameterRef().add(p5Ref);

		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		return service;
	}

}
