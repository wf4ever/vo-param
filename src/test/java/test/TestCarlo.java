package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueLargerThan;

public class TestCarlo extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		TestCarlo example = new TestCarlo();
		example.marshall();

	}

	@Override
	protected Service buildService() {

		Service service = factory.createService()
				.withServiceId("Carlo-Dynamic-Test")
				.withServiceName("Carlo-Dynamic-Test");
		service.setDescription("Test for cheching the dynamic behaviuor");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		SingleParameter first = factory.createSingleParameter();
		first.setName("p1");
		first.setParameterType(ParameterType.REAL);
		first.setSkosConcept("Number");
		first.setUnit("");
		first.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter second = factory.createSingleParameter();
		second.setName("p2");
		second.setParameterType(ParameterType.REAL);
		second.setSkosConcept("Number");
		second.setUnit("");
		second.setDimension(mktconst("1", ParameterType.INTEGER));

		Parameters parameterList = factory.createParameters();
		parameterList.getParameter().add(first);
		parameterList.getParameter().add(second);
		service.setParameters(parameterList);

		// Creating references for parameters
		ParameterReference firstRef = new ParameterReference()
				.withParameterName(first.getName());

		ParameterReference secondRef = new ParameterReference()
				.withParameterName(second.getName());
		
		
		inputsPG.getParameterRef().add(firstRef);
		inputsPG.getParameterRef().add(secondRef);
		
		
		// Creating the atomic expression relates to the two parameters
		AtomicParameterExpression firstExp = factory
				.createAtomicParameterExpression().withParameterRef(firstRef);

		AtomicParameterExpression secondExp = factory
				.createAtomicParameterExpression().withParameterRef(secondRef);

		Criterion firstPositive = new Criterion().withExpression(firstExp)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("1", ParameterType.REAL)));

		List<Expression> listeExp = new ArrayList<Expression>();
		listeExp.add(mktconst("1000", ParameterType.REAL));
		listeExp.add(mktconst("3000", ParameterType.REAL));
		BelongToSet bts = new BelongToSet().withValue(listeExp);

		Criterion secondConsequence = new Criterion().withExpression(secondExp)
				.withConditionType(bts);

		IfThenConditionalStatement ifthen = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(firstPositive)).withThen(
						new Then().withCriterion(secondConsequence)).withComment("if p1 is positive than...");

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();
		inputsConstraint.getConditionalStatement().add(ifthen);

		inputsPG.setConstraintOnGroup(inputsConstraint);
		service.setInputs(inputsPG);

		return service;
	}

}
