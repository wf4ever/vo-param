package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.And;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
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

public class OpacityExample extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		OpacityExample example = new OpacityExample();
		example.marshall();

	}

	@Override
	protected Service buildService() {

		Service service = factory.createService()
				.withServiceId("Lerma-Opacity-Service")
				.withServiceName("Lerma-Opacity-Service");
		service.setDescription("The Opacity Project Tables Service");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("email");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkosConcept("SKOS_MAIL");
		mail.setUnit("e-mail");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter C = factory.createSingleParameter();
		C.setName("C");
		C.setParameterType(ParameterType.REAL);
		C.setSkosConcept("C_Abundance");
		C.setUnit("number");
		C.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter N = factory.createSingleParameter();
		N.setName("N");
		N.setParameterType(ParameterType.REAL);
		N.setSkosConcept("N_Abundance");
		N.setUnit("number");
		N.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter O = factory.createSingleParameter();
		O.setName("O");
		O.setParameterType(ParameterType.REAL);
		O.setSkosConcept("O_Abundance");
		O.setUnit("number");
		O.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Ne = factory.createSingleParameter();
		Ne.setName("Ne");
		Ne.setParameterType(ParameterType.REAL);
		Ne.setSkosConcept("Ne_Abundance");
		Ne.setUnit("number");
		Ne.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Na = factory.createSingleParameter();
		Na.setName("Na");
		Na.setParameterType(ParameterType.REAL);
		Na.setSkosConcept("Na_Abundance");
		Na.setUnit("number");
		Na.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Mg = factory.createSingleParameter();
		Mg.setName("Mg");
		Mg.setParameterType(ParameterType.REAL);
		Mg.setSkosConcept("Mg_Abundance");
		Mg.setUnit("number");
		Mg.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Al = factory.createSingleParameter();
		Al.setName("Al");
		Al.setParameterType(ParameterType.REAL);
		Al.setSkosConcept("Al_Abundance");
		Al.setUnit("number");
		Al.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Si = factory.createSingleParameter();
		Si.setName("Si");
		Si.setParameterType(ParameterType.REAL);
		Si.setSkosConcept("Si_Abundance");
		Si.setUnit("number");
		Si.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter S = factory.createSingleParameter();
		S.setName("S");
		S.setParameterType(ParameterType.REAL);
		S.setSkosConcept("Si_Abundance");
		S.setUnit("number");
		S.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Ar = factory.createSingleParameter();
		Ar.setName("Ar");
		Ar.setParameterType(ParameterType.REAL);
		Ar.setSkosConcept("Ar_Abundance");
		Ar.setUnit("number");
		Ar.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Ca = factory.createSingleParameter();
		Ca.setName("Ca");
		Ca.setParameterType(ParameterType.REAL);
		Ca.setSkosConcept("Ca_Abundance");
		Ca.setUnit("number");
		Ca.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Cr = factory.createSingleParameter();
		Cr.setName("Cr");
		Cr.setParameterType(ParameterType.REAL);
		Cr.setSkosConcept("Cr_Abundance");
		Cr.setUnit("number");
		Cr.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Mn = factory.createSingleParameter();
		Mn.setName("Mn");
		Mn.setParameterType(ParameterType.REAL);
		Mn.setSkosConcept("Mn_Abundance");
		Mn.setUnit("number");
		Mn.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Fe = factory.createSingleParameter();
		Fe.setName("Fe");
		Fe.setParameterType(ParameterType.REAL);
		Fe.setSkosConcept("Fe_Abundance");
		Fe.setUnit("number");
		Fe.setDimension(mktconst("1", ParameterType.INTEGER));

		SingleParameter Ni = factory.createSingleParameter();
		Ni.setName("Ni");
		Ni.setParameterType(ParameterType.REAL);
		Ni.setSkosConcept("Ni_Abundance");
		Ni.setUnit("number");
		Ni.setDimension(mktconst("1", ParameterType.INTEGER));

		Parameters parameterList = factory.createParameters();
		parameterList.getParameter().add(mail);
		parameterList.getParameter().add(C);
		parameterList.getParameter().add(N);
		parameterList.getParameter().add(O);
		parameterList.getParameter().add(Ne);
		parameterList.getParameter().add(Na);
		parameterList.getParameter().add(Mg);
		parameterList.getParameter().add(Al);
		parameterList.getParameter().add(Si);
		parameterList.getParameter().add(S);
		parameterList.getParameter().add(Ar);
		parameterList.getParameter().add(Ca);
		parameterList.getParameter().add(Cr);
		parameterList.getParameter().add(Mn);
		parameterList.getParameter().add(Fe);
		parameterList.getParameter().add(Ni);
		service.setParameters(parameterList);

		ParameterReference mailRef = new ParameterReference()
				.withParameterName(mail.getName());

		ParameterReference CRef = new ParameterReference().withParameterName(C
				.getName());

		ParameterReference NRef = new ParameterReference().withParameterName(N
				.getName());

		ParameterReference ORef = new ParameterReference().withParameterName(O
				.getName());

		ParameterReference NeRef = new ParameterReference()
				.withParameterName(Ne.getName());

		ParameterReference NaRef = new ParameterReference()
				.withParameterName(Na.getName());

		ParameterReference MgRef = new ParameterReference()
				.withParameterName(Mg.getName());

		ParameterReference AlRef = new ParameterReference()
				.withParameterName(Al.getName());

		ParameterReference SiRef = new ParameterReference()
				.withParameterName(Si.getName());

		ParameterReference SRef = new ParameterReference().withParameterName(S
				.getName());

		ParameterReference ArRef = new ParameterReference()
				.withParameterName(Ar.getName());

		ParameterReference CaRef = new ParameterReference()
				.withParameterName(Ca.getName());

		ParameterReference CrRef = new ParameterReference()
				.withParameterName(Cr.getName());

		ParameterReference MnRef = new ParameterReference()
				.withParameterName(Mn.getName());

		ParameterReference FeRef = new ParameterReference()
				.withParameterName(Fe.getName());

		ParameterReference NiRef = new ParameterReference()
				.withParameterName(Ni.getName());

		inputsPG.getParameterRef().add(mailRef);

		inputsPG.getParameterRef().add(CRef);
		inputsPG.getParameterRef().add(NRef);
		inputsPG.getParameterRef().add(ORef);
		inputsPG.getParameterRef().add(NeRef);
		inputsPG.getParameterRef().add(NaRef);
		inputsPG.getParameterRef().add(MgRef);
		inputsPG.getParameterRef().add(AlRef);
		inputsPG.getParameterRef().add(SiRef);
		inputsPG.getParameterRef().add(SRef);
		inputsPG.getParameterRef().add(ArRef);
		inputsPG.getParameterRef().add(CaRef);
		inputsPG.getParameterRef().add(CrRef);
		inputsPG.getParameterRef().add(MnRef);
		inputsPG.getParameterRef().add(FeRef);
		inputsPG.getParameterRef().add(NiRef);

		AtomicParameterExpression Cexp = factory
				.createAtomicParameterExpression().withParameterRef(CRef);

		AtomicParameterExpression Nexp = factory
				.createAtomicParameterExpression().withParameterRef(NRef);

		AtomicParameterExpression Oexp = factory
				.createAtomicParameterExpression().withParameterRef(ORef);

		AtomicParameterExpression Neexp = factory
				.createAtomicParameterExpression().withParameterRef(NeRef);

		AtomicParameterExpression Naexp = factory
				.createAtomicParameterExpression().withParameterRef(NaRef);

		AtomicParameterExpression Mgexp = factory
				.createAtomicParameterExpression().withParameterRef(MgRef);

		AtomicParameterExpression Alexp = factory
				.createAtomicParameterExpression().withParameterRef(AlRef);

		AtomicParameterExpression Siexp = factory
				.createAtomicParameterExpression().withParameterRef(SiRef);

		AtomicParameterExpression Sexp = factory
				.createAtomicParameterExpression().withParameterRef(SRef);

		AtomicParameterExpression Arexp = factory
				.createAtomicParameterExpression().withParameterRef(ArRef);

		AtomicParameterExpression Caexp = factory
				.createAtomicParameterExpression().withParameterRef(CaRef);

		AtomicParameterExpression Crexp = factory
				.createAtomicParameterExpression().withParameterRef(CrRef);

		AtomicParameterExpression Mnexp = factory
				.createAtomicParameterExpression().withParameterRef(MnRef);

		AtomicParameterExpression Feexp = factory
				.createAtomicParameterExpression().withParameterRef(FeRef);

		AtomicParameterExpression Niexp = factory
				.createAtomicParameterExpression().withParameterRef(NiRef);

		
		
		
		
		AtomicParameterExpression CexpB = factory
				.createAtomicParameterExpression().withParameterRef(CRef);

		AtomicParameterExpression NexpB = factory
				.createAtomicParameterExpression().withParameterRef(NRef);

		AtomicParameterExpression OexpB = factory
				.createAtomicParameterExpression().withParameterRef(ORef);

		AtomicParameterExpression NeexpB = factory
				.createAtomicParameterExpression().withParameterRef(NeRef);

		AtomicParameterExpression NaexpB = factory
				.createAtomicParameterExpression().withParameterRef(NaRef);

		AtomicParameterExpression MgexpB = factory
				.createAtomicParameterExpression().withParameterRef(MgRef);

		AtomicParameterExpression AlexpB = factory
				.createAtomicParameterExpression().withParameterRef(AlRef);

		AtomicParameterExpression SiexpB = factory
				.createAtomicParameterExpression().withParameterRef(SiRef);

		AtomicParameterExpression SexpB = factory
				.createAtomicParameterExpression().withParameterRef(SRef);

		AtomicParameterExpression ArexpB = factory
				.createAtomicParameterExpression().withParameterRef(ArRef);

		AtomicParameterExpression CaexpB = factory
				.createAtomicParameterExpression().withParameterRef(CaRef);

		AtomicParameterExpression CrexpB = factory
				.createAtomicParameterExpression().withParameterRef(CrRef);

		AtomicParameterExpression MnexpB = factory
				.createAtomicParameterExpression().withParameterRef(MnRef);

		AtomicParameterExpression FeexpB = factory
				.createAtomicParameterExpression().withParameterRef(FeRef);

		AtomicParameterExpression NiexpB = factory
				.createAtomicParameterExpression().withParameterRef(NiRef);

		FeexpB.withOperation(new Operation().withExpression(NiexpB)
				.withOperationType(OperationType.PLUS));

		MnexpB.withOperation(new Operation().withExpression(FeexpB)
				.withOperationType(OperationType.PLUS));

		CrexpB.withOperation(new Operation().withExpression(MnexpB)
				.withOperationType(OperationType.PLUS));

		CaexpB.withOperation(new Operation().withExpression(CrexpB)
				.withOperationType(OperationType.PLUS));

		ArexpB.withOperation(new Operation().withExpression(CaexpB)
				.withOperationType(OperationType.PLUS));

		SexpB.withOperation(new Operation().withExpression(ArexpB)
				.withOperationType(OperationType.PLUS));

		SiexpB.withOperation(new Operation().withExpression(SexpB)
				.withOperationType(OperationType.PLUS));

		AlexpB.withOperation(new Operation().withExpression(SiexpB)
				.withOperationType(OperationType.PLUS));

		MgexpB.withOperation(new Operation().withExpression(AlexpB)
				.withOperationType(OperationType.PLUS));

		NaexpB.withOperation(new Operation().withExpression(MgexpB)
				.withOperationType(OperationType.PLUS));

		NeexpB.withOperation(new Operation().withExpression(NaexpB)
				.withOperationType(OperationType.PLUS));

		OexpB.withOperation(new Operation().withExpression(NeexpB)
				.withOperationType(OperationType.PLUS));

		NexpB.withOperation(new Operation().withExpression(OexpB)
				.withOperationType(OperationType.PLUS));

		CexpB.withOperation(new Operation().withExpression(NexpB)
				.withOperationType(OperationType.PLUS));

		Criterion sum = new Criterion().withExpression(CexpB).withConditionType(
				new BelongToSet().withValue(mktconst("1", ParameterType.REAL)));

		Criterion NiCrit = new Criterion().withExpression(Niexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.001321",
								ParameterType.REAL)));
		
		NiCrit.withLogicalConnector(new And().withCriterion(sum));
		
		Criterion FeCrit = new Criterion().withExpression(Feexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.023498",
								ParameterType.REAL)));
		FeCrit.withLogicalConnector(new And().withCriterion(NiCrit));

		Criterion MnCrit = new Criterion().withExpression(Mnexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.000252",
								ParameterType.REAL)));
		MnCrit.withLogicalConnector(new And().withCriterion(FeCrit));

		Criterion CrCrit = new Criterion().withExpression(Crexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.000429",
								ParameterType.REAL)));
		CrCrit.withLogicalConnector(new And().withCriterion(MnCrit));

		Criterion CaCrit = new Criterion().withExpression(Caexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.001764",
								ParameterType.REAL)));
		CaCrit.withLogicalConnector(new And().withCriterion(CrCrit));

		Criterion ArCrit = new Criterion().withExpression(Arexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.002008",
								ParameterType.REAL)));
		ArCrit.withLogicalConnector(new And().withCriterion(CaCrit));

		Criterion SCrit = new Criterion().withExpression(Sexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.012047",
								ParameterType.REAL)));
		SCrit.withLogicalConnector(new And().withCriterion(ArCrit));

		Criterion SiCrit = new Criterion().withExpression(Siexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.026979",
								ParameterType.REAL)));
		SiCrit.withLogicalConnector(new And().withCriterion(SCrit));

		Criterion AlCrit = new Criterion().withExpression(Alexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.002296",
								ParameterType.REAL)));
		AlCrit.withLogicalConnector(new And().withCriterion(SiCrit));

		Criterion MgCrit = new Criterion().withExpression(Mgexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.028251",
								ParameterType.REAL)));
		MgCrit.withLogicalConnector(new And().withCriterion(AlCrit));

		Criterion NaCrit = new Criterion().withExpression(Naexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.001589",
								ParameterType.REAL)));
		NaCrit.withLogicalConnector(new And().withCriterion(MgCrit));

		Criterion NeCrit = new Criterion().withExpression(Neexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.089336",
								ParameterType.REAL)));
		NeCrit.withLogicalConnector(new And().withCriterion(NaCrit));

		Criterion OCrit = new Criterion().withExpression(Oexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.502374",
								ParameterType.REAL)));
		OCrit.withLogicalConnector(new And().withCriterion(NeCrit));

		Criterion NCrit = new Criterion().withExpression(Nexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.061805",
								ParameterType.REAL)));
		NCrit.withLogicalConnector(new And().withCriterion(OCrit));

		Criterion CCrit = new Criterion().withExpression(Cexp)
				.withConditionType(
						new DefaultValue().withValue(mktconst("0.246052",
								ParameterType.REAL)));
		CCrit.withLogicalConnector(new And().withCriterion(NCrit));

		AlwaysConditionalStatement alwaysStatement = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(CCrit));

		alwaysStatement.withComment("The sum of abundances must be equal to 1");

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();
		inputsConstraint.getConditionalStatement().add(alwaysStatement);

		inputsPG.setConstraintOnGroup(inputsConstraint);

		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		return service;
	}
}