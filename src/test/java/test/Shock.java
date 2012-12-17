package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;

public class Shock extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		Shock example = new Shock();
		example.marshall();
	}

	@Override
	protected Service buildService() {
		Service service = factory.createService()
				.withServiceId("http://pdl-calc.obspm.fr:8081/shock/OnlineCode")
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
				"environmen");
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

		SingleParameter vs = factory.createSingleParameter();
		vs.setName("vs");
		vs.setParameterType(ParameterType.REAL);
		vs.setSkossConcept("shock velocity");
		vs.setUnit("km/s");
		vs.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference vsRef = mkRef(vs);
		parameterList.getParameter().add(vs);
		
		
		SingleParameter deltavInit = factory.createSingleParameter();
		deltavInit.setName("deltavInit");
		deltavInit.setParameterType(ParameterType.REAL);
		deltavInit.setSkossConcept("initial drift velocity");
		deltavInit.setUnit("km/s");
		deltavInit.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference deltavInitRef = mkRef(deltavInit);
		parameterList.getParameter().add(deltavInit);

		SingleParameter Tn = factory.createSingleParameter();
		Tn.setName("Tn");
		Tn.setParameterType(ParameterType.REAL);
		Tn.setSkossConcept("initial neutral temperature");
		Tn.setUnit("K");
		Tn.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TnRef = mkRef(Tn);
		parameterList.getParameter().add(Tn);
		
		SingleParameter nH = factory.createSingleParameter();
		nH.setName("nH");
		nH.setParameterType(ParameterType.REAL);
		nH.setSkossConcept("pre-shock density");
		nH.setUnit("cm^(-3)");
		nH.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference nHRef = mkRef(nH);
		parameterList.getParameter().add(nH);
		
		SingleParameter Tgrains = factory.createSingleParameter();
		Tgrains.setName("TGrains");
		Tgrains.setParameterType(ParameterType.REAL);
		Tgrains.setSkossConcept("initial grain temperature");
		Tgrains.setUnit("K");
		Tgrains.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TgrainsRef = mkRef(Tgrains);
		parameterList.getParameter().add(Tgrains);
		
		SingleParameter coolKN = factory.createSingleParameter();
		coolKN.setName("coolKN");
		coolKN.setParameterType(ParameterType.INTEGER);
		coolKN.setSkossConcept("cooling function options");
		coolKN.setUnit("None");
		coolKN.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference coolKNRef = mkRef(coolKN);
		parameterList.getParameter().add(coolKN);
		
		
		SingleParameter zeta = factory.createSingleParameter();
		zeta.setName("zeta");
		zeta.setParameterType(ParameterType.REAL);
		zeta.setSkossConcept("cosmic ray ionization rate");
		zeta.setUnit("s^(-1)");
		zeta.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference zetaRef = mkRef(zeta);
		parameterList.getParameter().add(zeta);
		
		SingleParameter rad = factory.createSingleParameter();
		rad.setName("rad");
		rad.setParameterType(ParameterType.REAL);
		rad.setSkossConcept("flux radiation (multiplicative factor)");
		rad.setUnit("No unit");
		rad.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference radRef = mkRef(rad);
		parameterList.getParameter().add(rad);
		
		SingleParameter av = factory.createSingleParameter();
		av.setName("av");
		av.setParameterType(ParameterType.REAL);
		av.setSkossConcept("initial exinction magnitude");
		av.setUnit("No unit");
		av.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference avRef = mkRef(av);
		parameterList.getParameter().add(av);
		
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
		
		SingleParameter NH2LinesOut = factory.createSingleParameter();
		NH2LinesOut.setName("NH2LinesOut");
		NH2LinesOut.setParameterType(ParameterType.INTEGER);
		NH2LinesOut.setSkossConcept("maximum number of H2 lines in output files");
		NH2LinesOut.setUnit("No unit");
		NH2LinesOut.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NH2LinesOutRef = mkRef(NH2LinesOut);
		parameterList.getParameter().add(NH2LinesOut);
		
		SingleParameter H_H2_flag = factory.createSingleParameter();
		H_H2_flag.setName("H_H2_flag");
		H_H2_flag.setParameterType(ParameterType.STRING);
		H_H2_flag.setSkossConcept("H-H2 collision file choice");
		H_H2_flag.setUnit("No unit");
		H_H2_flag.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference H_H2_flagRef = mkRef(H_H2_flag);
		parameterList.getParameter().add(H_H2_flag);
		
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
		
		SingleParameter XLL = factory.createSingleParameter();
		XLL.setName("XLL");
		XLL.setParameterType(ParameterType.REAL);
		XLL.setSkossConcept("characteristic viscous lenght");
		XLL.setUnit("cm");
		XLL.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference XLLRef = mkRef(XLL);
		parameterList.getParameter().add(XLL);
		
		SingleParameter EpsV = factory.createSingleParameter();
		EpsV.setName("EpsV");
		EpsV.setParameterType(ParameterType.REAL);
		EpsV.setSkossConcept("precision of computation");
		EpsV.setUnit("No unit");
		EpsV.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference EpsVRef = mkRef(EpsV);
		parameterList.getParameter().add(EpsV);
		
		
		SingleParameter timeJ = factory.createSingleParameter();
		timeJ.setName("timeJ");
		timeJ.setParameterType(ParameterType.REAL);
		timeJ.setSkossConcept("shock age");
		timeJ.setUnit("yr");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference timeJRef = mkRef(timeJ);
		parameterList.getParameter().add(timeJ);
		
		SingleParameter durationMax = factory.createSingleParameter();
		durationMax.setName("durationMax");
		durationMax.setParameterType(ParameterType.REAL);
		durationMax.setSkossConcept("maximum shock duration");
		durationMax.setUnit("yr");
		durationMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference durationMaxRef = mkRef(durationMax);
		parameterList.getParameter().add(durationMax);
		
		SingleParameter force_I_C = factory.createSingleParameter();
		force_I_C.setName("force_I_C");
		force_I_C.setParameterType(ParameterType.INTEGER);
		force_I_C.setSkossConcept("force ion conversation");
		force_I_C.setUnit("No unit");
		force_I_C.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference force_I_CRef = mkRef(force_I_C);
		parameterList.getParameter().add(force_I_C);
		
		SingleParameter abundanceOption = factory.createSingleParameter();
		abundanceOption.setName("abundanceOption");
		abundanceOption.setParameterType(ParameterType.STRING);
		abundanceOption.setSkossConcept("species abundance option");
		abundanceOption.setUnit("No unit");
		abundanceOption.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference abundanceOptionRef = mkRef(abundanceOption);
		parameterList.getParameter().add(abundanceOption);
		
		SingleParameter H2LevelsOption = factory.createSingleParameter();
		H2LevelsOption.setName("H2LevelsOption");
		H2LevelsOption.setParameterType(ParameterType.STRING);
		H2LevelsOption.setSkossConcept("H2 level population option");
		H2LevelsOption.setUnit("No unit");
		H2LevelsOption.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference H2LevelsOptionRef = mkRef(H2LevelsOption);
		parameterList.getParameter().add(H2LevelsOption);
		
		SingleParameter H2LineOption = factory.createSingleParameter();
		H2LineOption.setName("H2LineOption");
		H2LineOption.setParameterType(ParameterType.STRING);
		H2LineOption.setSkossConcept("H2 line emissivity option");
		H2LineOption.setUnit("No unit");
		H2LineOption.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference H2LineOptionRef = mkRef(H2LineOption);
		parameterList.getParameter().add(H2LineOption);
		
		// Adding the parameter list to the service
		service.setParameters(parameterList);
		
		shockParameter.getParameterRef().add(shockTypeRef);
		shockParameter.getParameterRef().add(NfluidsRef);
		shockParameter.getParameterRef().add(BbetaRef);
		shockParameter.getParameterRef().add(vsRef);
		shockParameter.getParameterRef().add(deltavInitRef);
		shockParameter.getParameterRef().add(TnRef);
		shockParameter.getParameterRef().add(nHRef);
		shockParameter.getParameterRef().add(TgrainsRef);
		shockParameter.getParameterRef().add(coolKNRef);
		
		environement.getParameterRef().add(zetaRef);
		environement.getParameterRef().add(radRef);
		environement.getParameterRef().add(avRef);
		
		numericalParameters.getParameterRef().add(NstepMaxRef);
		numericalParameters.getParameterRef().add(NstepWRef);
		numericalParameters.getParameterRef().add(NH2LevRef);
		numericalParameters.getParameterRef().add(NH2LinesOutRef);
		numericalParameters.getParameterRef().add(H_H2_flagRef);
		numericalParameters.getParameterRef().add(iforH2Ref);
		numericalParameters.getParameterRef().add(ikinH2Ref);
		numericalParameters.getParameterRef().add(XLLRef);
		numericalParameters.getParameterRef().add(EpsVRef);
		numericalParameters.getParameterRef().add(timeJRef);
		numericalParameters.getParameterRef().add(durationMaxRef);
		numericalParameters.getParameterRef().add(force_I_CRef);
		
		outputSpecifications.getParameterRef().add(abundanceOptionRef);
		outputSpecifications.getParameterRef().add(H2LevelsOptionRef);
		outputSpecifications.getParameterRef().add(H2LineOptionRef);
		
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
