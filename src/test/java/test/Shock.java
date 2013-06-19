package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
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

public class Shock extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		Shock example = new Shock();
		example.marshall();
	}

	@Override
	protected Service buildService() {
		Service service = factory
				.createService()
				.withServiceId(
						"http://pdl-calc.obspm.fr:8081/ParisDurham/OnlineCode")
				.withServiceName("Paris-Durham shock code");
		service.setDescription("Simulates the physical, dynamical, and chemical structure of a shocked layer of the ISM.");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		// Adding parameters to the list of service params
		Parameters parameterList = factory.createParameters();

		ParameterGroup shockParameter = factory.createParameterGroup()
				.withName("shockParameters");
		ParameterGroup environement = factory.createParameterGroup().withName(
				"environment");
		ParameterGroup numericalParameters = factory.createParameterGroup()
				.withName("numericalParameters");
		ParameterGroup outputSpecifications = factory.createParameterGroup()
				.withName("outputSpecifications");

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("mail");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkossConcept("mail of the user");
		mail.setUnit("no unit");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference mailRef = mkRef(mail);
		parameterList.getParameter().add(mail);

		SingleParameter shockType = factory.createSingleParameter();
		shockType.setName("shockType");
		shockType.setParameterType(ParameterType.STRING);
		shockType.setSkossConcept("shock type");
		shockType.setUnit("no unit");
		shockType.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference shockTypeRef = mkRef(shockType);
		parameterList.getParameter().add(shockType);

		SingleParameter Nfluids = factory.createSingleParameter();
		Nfluids.setName("Nfluids");
		Nfluids.setParameterType(ParameterType.INTEGER);
		Nfluids.setSkossConcept("number of fluids to consider");
		Nfluids.setUnit("no unit");
		Nfluids.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NfluidsRef = mkRef(Nfluids);
		parameterList.getParameter().add(Nfluids);

		SingleParameter Bbeta = factory.createSingleParameter();
		Bbeta.setName("Bbeta");
		Bbeta.setParameterType(ParameterType.REAL);
		Bbeta.setSkossConcept("magnetic field strenght parameter");
		Bbeta.setUnit("micro Gauss cm^(3/2)");
		Bbeta.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference BbetaRef = mkRef(Bbeta);
		parameterList.getParameter().add(Bbeta);

		SingleParameter Vs = factory.createSingleParameter();
		Vs.setName("Vs");
		Vs.setParameterType(ParameterType.REAL);
		Vs.setSkossConcept("shock velocity");
		Vs.setUnit("km/s");
		Vs.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference vsRef = mkRef(Vs);
		parameterList.getParameter().add(Vs);

		SingleParameter Vdi = factory.createSingleParameter();
		Vdi.setName("Vdi");
		Vdi.setParameterType(ParameterType.REAL);
		Vdi.setSkossConcept("initial drift velocity");
		Vdi.setUnit("km/s");
		Vdi.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference VdiRef = mkRef(Vdi);
		parameterList.getParameter().add(Vdi);

		SingleParameter OpH2 = factory.createSingleParameter();
		OpH2.setName("OpH2");
		OpH2.setParameterType(ParameterType.REAL);
		OpH2.setSkossConcept("initial H2 ortho/para ratio");
		OpH2.setUnit("None");
		OpH2.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference OpH2Ref = mkRef(OpH2);
		parameterList.getParameter().add(OpH2);

		SingleParameter Ti = factory.createSingleParameter();
		Ti.setName("Ti");
		Ti.setParameterType(ParameterType.REAL);
		Ti.setSkossConcept("initial gas temperature");
		Ti.setUnit("K");
		Ti.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TiRef = mkRef(Ti);
		parameterList.getParameter().add(Ti);

		SingleParameter nHi = factory.createSingleParameter();
		nHi.setName("nHi");
		nHi.setParameterType(ParameterType.REAL);
		nHi.setSkossConcept("pre-shock density");
		nHi.setUnit("cm^(-3)");
		nHi.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference nHiRef = mkRef(nHi);
		parameterList.getParameter().add(nHi);

		SingleParameter Tg = factory.createSingleParameter();
		Tg.setName("Tg");
		Tg.setParameterType(ParameterType.REAL);
		Tg.setSkossConcept("initial grain temperature");
		Tg.setUnit("K");
		Tg.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TgRef = mkRef(Tg);
		parameterList.getParameter().add(Tg);

		SingleParameter Zeta = factory.createSingleParameter();
		Zeta.setName("Zeta");
		Zeta.setParameterType(ParameterType.REAL);
		Zeta.setSkossConcept("cosmic ray ionization rate");
		Zeta.setUnit("s^(-1)");
		Zeta.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ZetaRef = mkRef(Zeta);
		parameterList.getParameter().add(Zeta);

		SingleParameter NstepMax = factory.createSingleParameter();
		NstepMax.setName("NstepMax");
		NstepMax.setParameterType(ParameterType.INTEGER);
		NstepMax.setSkossConcept("maximum number of integration steps");
		NstepMax.setUnit("No unit");
		NstepMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NstepMaxRef = mkRef(NstepMax);
		parameterList.getParameter().add(NstepMax);

		SingleParameter NstepW = factory.createSingleParameter();
		NstepW.setName("NstepW");
		NstepW.setParameterType(ParameterType.INTEGER);
		NstepW.setSkossConcept("number of steps between two outputs");
		NstepW.setUnit("No unit");
		NstepW.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NstepWRef = mkRef(NstepW);
		parameterList.getParameter().add(NstepW);

		SingleParameter NH2Lev = factory.createSingleParameter();
		NH2Lev.setName("NH2Lev");
		NH2Lev.setParameterType(ParameterType.INTEGER);
		NH2Lev.setSkossConcept("number of H2 levels included");
		NH2Lev.setUnit("No unit");
		NH2Lev.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NH2LevRef = mkRef(NH2Lev);
		parameterList.getParameter().add(NH2Lev);

		SingleParameter NH2Lines = factory.createSingleParameter();
		NH2Lines.setName("NH2Lines");
		NH2Lines.setParameterType(ParameterType.INTEGER);
		NH2Lines.setSkossConcept("maximum number of H2 lines in output files");
		NH2Lines.setUnit("No unit");
		NH2Lines.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NH2LinesRef = mkRef(NH2Lines);
		parameterList.getParameter().add(NH2Lines);

		SingleParameter iforH2 = factory.createSingleParameter();
		iforH2.setName("iforH2");
		iforH2.setParameterType(ParameterType.INTEGER);
		iforH2.setSkossConcept("formation of grain model");
		iforH2.setUnit("No unit");
		iforH2.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference iforH2Ref = mkRef(iforH2);
		parameterList.getParameter().add(iforH2);

		SingleParameter ikinH2 = factory.createSingleParameter();
		ikinH2.setName("ikinH2");
		ikinH2.setParameterType(ParameterType.INTEGER);
		ikinH2.setSkossConcept("kinetic energy of newly formed H2");
		ikinH2.setUnit("No unit");
		ikinH2.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ikinH2Ref = mkRef(ikinH2);
		parameterList.getParameter().add(ikinH2);

		SingleParameter xll = factory.createSingleParameter();
		xll.setName("xll");
		xll.setParameterType(ParameterType.REAL);
		xll.setSkossConcept("characteristic viscous lenght");
		xll.setUnit("cm");
		xll.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference xllRef = mkRef(xll);
		parameterList.getParameter().add(xll);

		SingleParameter epsV = factory.createSingleParameter();
		epsV.setName("epsV");
		epsV.setParameterType(ParameterType.REAL);
		epsV.setSkossConcept("precision of computation");
		epsV.setUnit("No unit");
		epsV.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference epsVRef = mkRef(epsV);
		parameterList.getParameter().add(epsV);

		SingleParameter TimeJ = factory.createSingleParameter();
		TimeJ.setName("TimeJ");
		TimeJ.setParameterType(ParameterType.REAL);
		TimeJ.setSkossConcept("shock age");
		TimeJ.setUnit("yr");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TimeJRef = mkRef(TimeJ);
		parameterList.getParameter().add(TimeJ);

		SingleParameter MaxTimeN = factory.createSingleParameter();
		MaxTimeN.setName("MaxTimeN");
		MaxTimeN.setParameterType(ParameterType.REAL);
		MaxTimeN.setSkossConcept("maximum shock duration");
		MaxTimeN.setUnit("yr");
		MaxTimeN.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference MaxTimeNRef = mkRef(MaxTimeN);
		parameterList.getParameter().add(MaxTimeN);

		SingleParameter SOS = factory.createSingleParameter();
		SOS.setName("SOS");
		SOS.setParameterType(ParameterType.STRING);
		SOS.setSkossConcept("species abundance option");
		SOS.setUnit("No unit");
		SOS.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference SOSRef = mkRef(SOS);
		parameterList.getParameter().add(SOS);

		SingleParameter LEOS = factory.createSingleParameter();
		LEOS.setName("LEOS");
		LEOS.setParameterType(ParameterType.STRING);
		LEOS.setSkossConcept("H2 level population option");
		LEOS.setUnit("No unit");
		LEOS.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference LEOSRef = mkRef(LEOS);
		parameterList.getParameter().add(LEOS);

		SingleParameter LIOS = factory.createSingleParameter();
		LIOS.setName("LIOS");
		LIOS.setParameterType(ParameterType.STRING);
		LIOS.setSkossConcept("H2 line emissivity option");
		LIOS.setUnit("No unit");
		LIOS.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference LIOSRef = mkRef(LIOS);
		parameterList.getParameter().add(LIOS);

		// Adding the parameter list to the service
		service.setParameters(parameterList);

		// defining constraints for parameters

		// constraint on shock type
		AtomicParameterExpression shockTypeExpr = factory
				.createAtomicParameterExpression().withParameterRef(
						shockTypeRef);

		Criterion shockTypeSet = new Criterion()
				.withExpression(shockTypeExpr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("C", ParameterType.STRING))
								.withValue(mktconst("J", ParameterType.STRING))
								.withValue(mktconst("S", ParameterType.STRING)));

		AlwaysConditionalStatement shockTpeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(shockTypeSet))
				.withComment("Shock type must be C, J or S");

		// constraint on the NH2Lev
		AtomicParameterExpression NH2LevExpr = factory
				.createAtomicParameterExpression().withParameterRef(NH2LevRef);

		Criterion NH2LevRange = new Criterion()
				.withExpression(NH2LevExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"160",
																ParameterType.INTEGER)))
								.withInf(
										new ValueLargerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"0",
																ParameterType.INTEGER))));

		AlwaysConditionalStatement NH2LevRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NH2LevRange))
				.withComment(
						"NH2Lev value must be between 0 and 160 (both limits could be reached)");

		// constraint on Vs
		AtomicParameterExpression vsExpr = factory
				.createAtomicParameterExpression().withParameterRef(vsRef);

		Criterion vsPos = new Criterion().withExpression(vsExpr)
				.withConditionType(
						new ValueLargerThan().withReached(false).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement vsPosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(vsPos)).withComment(
						"vs must be strictly positive");

		// constraint on vdi
		AtomicParameterExpression vdiExpr = factory
				.createAtomicParameterExpression().withParameterRef(VdiRef);

		Criterion vdiPos = new Criterion().withExpression(vdiExpr)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement vdiPosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(vdiPos)).withComment(
						"vdi must be positive (not stricltly)");

		// constraint on OpH2
		AtomicParameterExpression OpH2Expr = factory
				.createAtomicParameterExpression().withParameterRef(OpH2Ref);

		Criterion OpH2Pos = new Criterion().withExpression(OpH2Expr)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement OpH2PosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(OpH2Pos)).withComment(
						"OpH2 must be positive (not stricltly)");

		// constraint on Ti
		AtomicParameterExpression TiExpr = factory
				.createAtomicParameterExpression().withParameterRef(TiRef);

		Criterion TiPos = new Criterion().withExpression(TiExpr)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement TiPosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(TiPos)).withComment(
						"Ti must be positive (not stricltly)");

		// constraint on nHi
		AtomicParameterExpression nHiExpr = factory
				.createAtomicParameterExpression().withParameterRef(nHiRef);

		Criterion nHiPos = new Criterion().withExpression(nHiExpr)
				.withConditionType(
						new ValueLargerThan().withReached(false).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement nHiPosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(nHiPos)).withComment(
						"nHi must be strictly positive");

		// constraint on Tg
		AtomicParameterExpression TgExpr = factory
				.createAtomicParameterExpression().withParameterRef(TgRef);

		Criterion TgPos = new Criterion().withExpression(TgExpr)
				.withConditionType(
						new ValueLargerThan().withReached(true).withValue(
								mktconst("0", ParameterType.REAL)));

		AlwaysConditionalStatement TgPosAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(TgPos)).withComment(
						"Tg must be positive (not strictly)");

		// constraint on the fluids number
		AtomicParameterExpression NfluidExpr = factory
				.createAtomicParameterExpression().withParameterRef(NfluidsRef);

		Criterion NfluidSet = new Criterion()
				.withExpression(NfluidExpr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("1", ParameterType.INTEGER))
								.withValue(mktconst("2", ParameterType.INTEGER))
								.withValue(mktconst("3", ParameterType.INTEGER)));

		AlwaysConditionalStatement NfluidAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NfluidSet)).withComment(
						"Number of fluid must be equal to 1, 2 or 3");

		// constraint on Zeta
		AtomicParameterExpression ZetaExpr = factory
				.createAtomicParameterExpression().withParameterRef(ZetaRef);

		Criterion ZetaRange = new Criterion()
				.withExpression(ZetaExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"1e-14",
																ParameterType.REAL)))
								.withInf(
										new ValueLargerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"0",
																ParameterType.REAL))));

		AlwaysConditionalStatement ZetaRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(ZetaRange))
				.withComment(
						"Zeta value must be between 0 and 1e-14 (both limits could be reached)");

		// constraint on NstepMax
		AtomicParameterExpression NStepMaxExpr = factory
				.createAtomicParameterExpression()
				.withParameterRef(NstepMaxRef);

		Criterion NStepMaxRange = new Criterion()
				.withExpression(NStepMaxExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan().withReached(
												false).withValue(
												mktconst("1e6",
														ParameterType.REAL)))
								.withInf(
										new ValueLargerThan()
												.withReached(false)
												.withValue(
														mktconst(
																"0",
																ParameterType.REAL))));

		AlwaysConditionalStatement NStepMaxRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NStepMaxRange))
				.withComment(
						"NStepMax value must be between 0 and 1e6 (both limits could not be reached)");

		// constraint on NStepW
		AtomicParameterExpression NStepWExpr = factory
				.createAtomicParameterExpression().withParameterRef(NstepWRef);

		Criterion NStepWRange = new Criterion()
				.withExpression(NStepWExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan().withReached(
												false).withValue(
												mktconst("1e4",
														ParameterType.REAL)))
								.withInf(
										new ValueLargerThan()
												.withReached(false)
												.withValue(
														mktconst(
																"0",
																ParameterType.REAL))));

		AlwaysConditionalStatement NStepWRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NStepWRange))
				.withComment(
						"NStepW value must be between 0 and 1e4 (both limits could not be reached)");

		// constraint on NH2Lines
		AtomicParameterExpression NH2LinesExpr = factory
				.createAtomicParameterExpression()
				.withParameterRef(NH2LinesRef);

		Criterion NH2LinesRange = new Criterion()
				.withExpression(NH2LinesExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan()
												.withReached(true)
												.withValue(
														mktconst(
																"500",
																ParameterType.INTEGER)))
								.withInf(
										new ValueLargerThan()
												.withReached(false)
												.withValue(
														mktconst(
																"0",
																ParameterType.INTEGER))));

		AlwaysConditionalStatement NH2LinesRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(NH2LinesRange))
				.withComment("NH2Lines value must be >0 and <= 500");

		// constraint on iforH2
		AtomicParameterExpression iforH2Expr = factory
				.createAtomicParameterExpression().withParameterRef(iforH2Ref);

		Criterion iforH2Set = new Criterion()
				.withExpression(iforH2Expr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("1", ParameterType.INTEGER))
								.withValue(mktconst("2", ParameterType.INTEGER))
								.withValue(mktconst("3", ParameterType.INTEGER))
								.withValue(mktconst("4", ParameterType.INTEGER)));

		AlwaysConditionalStatement iforH2SetAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(iforH2Set)).withComment(
						"iforH2 must be equal to 1,2,3 or 4");

		// constraint on ikinH2
		AtomicParameterExpression ikinH2Expr = factory
				.createAtomicParameterExpression().withParameterRef(ikinH2Ref);

		Criterion ikinH2Set = new Criterion()
				.withExpression(ikinH2Expr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("1", ParameterType.INTEGER))
								.withValue(mktconst("2", ParameterType.INTEGER)));

		AlwaysConditionalStatement ikinH2SetAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(ikinH2Set)).withComment(
						"iforH2 must be equal to 1 or 2");

		// constraint on TimeJ
		AtomicParameterExpression TimeJExpr = factory
				.createAtomicParameterExpression().withParameterRef(TimeJRef);

		Criterion TimeJRange = new Criterion()
				.withExpression(TimeJExpr)
				.withConditionType(
						new ValueInRange()
								.withSup(
										new ValueSmallerThan().withReached(
												false).withValue(
												mktconst("1e8",
														ParameterType.REAL)))
								.withInf(
										new ValueLargerThan()
												.withReached(false)
												.withValue(
														mktconst(
																"0",
																ParameterType.REAL))));

		AlwaysConditionalStatement TimeJRangeAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(TimeJRange))
				.withComment("TimeJRange value must be >0 and < 1e8");

		// defining constraint on SOS
		AtomicParameterExpression SOSExpr = factory
				.createAtomicParameterExpression().withParameterRef(SOSRef);

		Criterion SOSSet = new Criterion()
				.withExpression(SOSExpr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("AD", ParameterType.STRING))
								.withValue(mktconst("CD", ParameterType.STRING))
								.withValue(mktconst("FD", ParameterType.STRING)));

		AlwaysConditionalStatement SOSSetAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(SOSSet))
				.withComment(
						"SOS values are : 'AD'(cm-3), 'CD'(cm-2) or 'FD'(n(x)/nH)");

		// defining constraint on LEOS
		AtomicParameterExpression LEOSExpr = factory
				.createAtomicParameterExpression().withParameterRef(LEOSRef);

		Criterion LEOSSet = new Criterion()
				.withExpression(LEOSExpr)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("AD", ParameterType.STRING))
								.withValue(mktconst("CD", ParameterType.STRING))
								.withValue(
										mktconst("ln(N/g)",
												ParameterType.STRING)));

		AlwaysConditionalStatement LEOSSetAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(LEOSSet)).withComment(
						"LEOS values are : 'AD'(cm-3), 'CD'(cm-2) or ln(N/g)");

		// defining constraint on LIOS
		AtomicParameterExpression LIOSExpr = factory
				.createAtomicParameterExpression().withParameterRef(LIOSRef);

		Criterion LIOSSet = new Criterion()
				.withExpression(LIOSExpr)
				.withConditionType(
						new BelongToSet()
								.withValue(
										mktconst("local", ParameterType.STRING))
								.withValue(
										mktconst("integrated",
												ParameterType.STRING)));

		AlwaysConditionalStatement LIOSSetAlways = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(LIOSSet))
				.withComment(
						"LIOS values are : 'local'(erg/s/cm3) or 'integrated'(erg/s/cm2/sr)");

		// defining some conditional criteria
		AtomicParameterExpression shockTypeExpr2 = factory
				.createAtomicParameterExpression().withParameterRef(
						shockTypeRef);

		AtomicParameterExpression NfluidExpr2 = factory
				.createAtomicParameterExpression().withParameterRef(NfluidsRef);

		AtomicParameterExpression MaxTimeNExpr = factory
				.createAtomicParameterExpression()
				.withParameterRef(MaxTimeNRef);

		Criterion shockTypeSetC = new Criterion()
				.withExpression(shockTypeExpr2).withConditionType(
						new BelongToSet().withValue(mktconst("C",
								ParameterType.STRING)));

		Criterion shockTypeSetJ = new Criterion()
				.withExpression(shockTypeExpr2).withConditionType(
						new BelongToSet().withValue(mktconst("J",
								ParameterType.STRING)));

		Criterion shockTypeSetS = new Criterion()
				.withExpression(shockTypeExpr2).withConditionType(
						new BelongToSet().withValue(mktconst("S",
								ParameterType.STRING)));

		Criterion Nfluid2ou3 = new Criterion()
				.withExpression(NfluidExpr2)
				.withConditionType(
						new BelongToSet()
								.withValue(mktconst("2", ParameterType.INTEGER))
								.withValue(mktconst("3", ParameterType.INTEGER)));

		Criterion Nfluid1 = new Criterion().withExpression(NfluidExpr2)
				.withConditionType(
						new BelongToSet().withValue(mktconst("1",
								ParameterType.INTEGER)));

		Criterion MaxTimeNStatique = new Criterion().withExpression(
				MaxTimeNExpr).withConditionType(
				new ValueLargerThan().withReached(true).withValue(
						mktconst("1e8", ParameterType.REAL)));

		AtomicParameterExpression TimeJMINUSMaxTimeN = factory
				.createAtomicParameterExpression().withParameterRef(TimeJRef);
		TimeJMINUSMaxTimeN.withOperation(new Operation().withOperationType(
				OperationType.MINUS).withExpression(MaxTimeNExpr));

		Criterion TimeJMINUSMaxTimeNRange = new Criterion().withExpression(
				TimeJMINUSMaxTimeN).withConditionType(
				new ValueLargerThan().withReached(false).withValue(
						mktconst("0", ParameterType.REAL)));

		IfThenConditionalStatement ifShockCNfluid2or3 = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(shockTypeSetC))
				.withThen(new Then().withCriterion(Nfluid2ou3))
				.withComment(
						"if shock type equal to C, than Nfluids must be 2 or 3");

		IfThenConditionalStatement ifShockCTimeJMINUSMaxTimeNRange = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(shockTypeSetC))
				.withThen(new Then().withCriterion(TimeJMINUSMaxTimeNRange))
				.withComment("if shock type equal to C, than MaxTimeN-TimeJ>0");

		IfThenConditionalStatement ifShockJNfluid1 = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(shockTypeSetJ))
				.withThen(new Then().withCriterion(Nfluid1))
				.withComment("if shock type equal to J, than Nfluids must be 1");

		IfThenConditionalStatement ifShockSMaxTimeLimit = new IfThenConditionalStatement()
				.withIf(new If().withCriterion(shockTypeSetS))
				.withThen(new Then().withCriterion(MaxTimeNStatique))
				.withComment("If Shock type equal to S, then MaxTimeN>1e8");

		// Storing parameters into groups
		shockParameter.getParameterRef().add(shockTypeRef);
		shockParameter.getParameterRef().add(NfluidsRef);
		shockParameter.getParameterRef().add(BbetaRef);
		shockParameter.getParameterRef().add(vsRef);
		shockParameter.getParameterRef().add(VdiRef);
		shockParameter.getParameterRef().add(OpH2Ref);
		shockParameter.getParameterRef().add(TiRef);
		shockParameter.getParameterRef().add(nHiRef);
		shockParameter.getParameterRef().add(TgRef);

		shockParameter.withConstraintOnGroup(new ConstraintOnGroup()
				.withConditionalStatement(shockTpeAlways)
				.withConditionalStatement(NfluidAlways)
				.withConditionalStatement(vsPosAlways)
				.withConditionalStatement(vdiPosAlways)
				.withConditionalStatement(OpH2PosAlways)
				.withConditionalStatement(TiPosAlways)
				.withConditionalStatement(nHiPosAlways)
				.withConditionalStatement(TgPosAlways)
				.withConditionalStatement(ifShockCNfluid2or3)
				.withConditionalStatement(ifShockCTimeJMINUSMaxTimeNRange)
				.withConditionalStatement(ifShockJNfluid1)
				.withConditionalStatement(ifShockSMaxTimeLimit));

		environement.getParameterRef().add(ZetaRef);

		environement.withConstraintOnGroup(new ConstraintOnGroup()
				.withConditionalStatement(ZetaRangeAlways));

		numericalParameters.getParameterRef().add(NstepMaxRef);
		numericalParameters.getParameterRef().add(NstepWRef);
		numericalParameters.getParameterRef().add(NH2LevRef);
		numericalParameters.getParameterRef().add(NH2LinesRef);
		numericalParameters.getParameterRef().add(iforH2Ref);
		numericalParameters.getParameterRef().add(ikinH2Ref);
		numericalParameters.getParameterRef().add(xllRef);
		numericalParameters.getParameterRef().add(epsVRef);
		numericalParameters.getParameterRef().add(TimeJRef);
		numericalParameters.getParameterRef().add(MaxTimeNRef);

		numericalParameters.withConstraintOnGroup(new ConstraintOnGroup()
				.withConditionalStatement(NH2LevRangeAlways)
				.withConditionalStatement(NStepMaxRangeAlways)
				.withConditionalStatement(NStepWRangeAlways)
				.withConditionalStatement(NH2LinesRangeAlways)
				.withConditionalStatement(iforH2SetAlways)
				.withConditionalStatement(ikinH2SetAlways)
				.withConditionalStatement(TimeJRangeAlways));

		outputSpecifications.getParameterRef().add(SOSRef);
		outputSpecifications.getParameterRef().add(LEOSRef);
		outputSpecifications.getParameterRef().add(LIOSRef);

		outputSpecifications.withConstraintOnGroup(new ConstraintOnGroup()
				.withConditionalStatement(SOSSetAlways)
				.withConditionalStatement(LEOSSetAlways)
				.withConditionalStatement(LIOSSetAlways));

		inputsPG.getParameterRef().add(mailRef);

		inputsPG.getParameterGroup().add(shockParameter);
		inputsPG.getParameterGroup().add(environement);
		inputsPG.getParameterGroup().add(numericalParameters);
		inputsPG.getParameterGroup().add(outputSpecifications);

		service.withInputs(inputsPG);
		service.withOutputs(outputsPG);

		return service;
	}
}
