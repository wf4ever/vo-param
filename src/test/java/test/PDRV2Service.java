package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;

public class PDRV2Service extends BaseExample {

	private Map<String, SingleParameter> singleParameterMap;
	private Map<String, ParameterReference> parameterRefMap;
	private Map<String, List<String>> nameGroupNameParameterMap;

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		PDRV2Service example = new PDRV2Service();
		example.marshall();

	}

	@Override
	protected Service buildService() {
		Service service = factory.createService()
				.withServiceId("http://vm-calc-lerma02:8080/pdl/OnlineCode")
				.withServiceName("Meudon-PDR-Service");
		service.setDescription("The Meudon PDR code computes the atomic and molecular structure of interstellar clouds.");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		this.nameGroupNameParameterMap = new HashMap<String, List<String>>();

		this.initialiseSingleParameterMap();
		this.initializeReferenceMap();

		List<ParameterGroup> allInputs = this.buildGroups();

		for (ParameterGroup p : allInputs) {
			inputsPG.getParameterGroup().add(p);
		}

		service.setInputs(inputsPG);
		service.setParameters(this.getListOfParamForService());
		return service;
	}

	private Parameters getListOfParamForService() {
		Parameters toReturn = factory.createParameters();
		for (Map.Entry<String, SingleParameter> entry : this.singleParameterMap
				.entrySet()) {
			toReturn.getParameter().add(entry.getValue());
		}
		return toReturn;
	}

	private List<ParameterGroup> buildGroups() {
		List<ParameterGroup> toReturn = new ArrayList<ParameterGroup>();
		for (Map.Entry<String, List<String>> entry : this.nameGroupNameParameterMap
				.entrySet()) {
			toReturn.add(this.buildGroup(entry.getKey()));
		}
		return toReturn;
	}

	private ParameterGroup buildGroup(String groupName) {
		ParameterGroup toReturn = factory.createParameterGroup().withName(
				groupName);
		for (String paramName : this.nameGroupNameParameterMap.get(groupName)) {
			toReturn.getParameterRef().add(this.parameterRefMap.get(paramName));
		}
		return toReturn;
	}

	private void initializeReferenceMap() {
		this.parameterRefMap = new HashMap<String, ParameterReference>();
		for (Map.Entry<String, SingleParameter> entry : this.singleParameterMap
				.entrySet()) {
			this.parameterRefMap.put(entry.getKey(), mkRef(entry.getValue()));
		}
	}

	private void addParameterToGroupMap(String paramName, String groupName) {
		List<String> paramInGroup = this.nameGroupNameParameterMap
				.get(groupName);
		if (null == paramInGroup || paramInGroup.size() < 1) {
			List<String> temp = new ArrayList<String>();
			temp.add(paramName);
			this.nameGroupNameParameterMap.put(groupName, temp);
		} else {
			this.nameGroupNameParameterMap.get(groupName).add(paramName);
		}
	}

	protected void initialiseSingleParameterMap() {

		this.singleParameterMap = new HashMap<String, SingleParameter>();

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("email");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkossConcept("UserEmail");
		mail.setUnit(" ");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(mail.getName(), mail);
		this.addParameterToGroupMap(mail.getName(), "input");

		SingleParameter Avmaxinf = factory.createSingleParameter();
		Avmaxinf.setName("Avmaxinf");
		Avmaxinf.setParameterType(ParameterType.REAL);
		Avmaxinf.setSkossConcept("Avmaxinf");
		Avmaxinf.setUnit("magnitude");
		Avmaxinf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Avmaxinf.getName(), Avmaxinf);
		this.addParameterToGroupMap(Avmaxinf.getName(), "RadiationField");

		SingleParameter NAvmax = factory.createSingleParameter();
		NAvmax.setName("NAvmax");
		NAvmax.setParameterType(ParameterType.INTEGER);
		NAvmax.setSkossConcept("NAvmax");
		NAvmax.setUnit(" ");
		NAvmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NAvmax.getName(), NAvmax);
		this.addParameterToGroupMap(NAvmax.getName(), "RadiationField");

		SingleParameter deltaAvmax = factory.createSingleParameter();
		deltaAvmax.setName("deltaAvmax");
		deltaAvmax.setParameterType(ParameterType.REAL);
		deltaAvmax.setSkossConcept("deltaAvmax");
		deltaAvmax.setUnit("magnitude");
		deltaAvmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaAvmax.getName(), deltaAvmax);
		this.addParameterToGroupMap(deltaAvmax.getName(), "RadiationField");

		SingleParameter AvmaxVariationMethod = factory.createSingleParameter();
		AvmaxVariationMethod.setName("AvmaxVariationMethod");
		AvmaxVariationMethod.setParameterType(ParameterType.STRING);
		AvmaxVariationMethod.setSkossConcept("AvmaxVariationMethod");
		AvmaxVariationMethod.setUnit(" ");
		AvmaxVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(AvmaxVariationMethod.getName(),
				AvmaxVariationMethod);
		this.addParameterToGroupMap(AvmaxVariationMethod.getName(),
				"RadiationField");

		SingleParameter Chimie = factory.createSingleParameter();
		Chimie.setName("Chimie");
		Chimie.setParameterType(ParameterType.STRING);
		Chimie.setSkossConcept("Chimie");
		Chimie.setUnit(" ");
		Chimie.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Chimie.getName(), Chimie);
		this.addParameterToGroupMap(Chimie.getName(), "Chemistry");

		SingleParameter F_ISRF = factory.createSingleParameter();
		F_ISRF.setName("F_ISRF");
		F_ISRF.setParameterType(ParameterType.INTEGER);
		F_ISRF.setSkossConcept("F_ISRF");
		F_ISRF.setUnit(" ");
		F_ISRF.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(F_ISRF.getName(), F_ISRF);
		this.addParameterToGroupMap(F_ISRF.getName(), "Chemistry");

		SingleParameter F_dustem = factory.createSingleParameter();
		F_dustem.setName("F_dustem");
		F_dustem.setParameterType(ParameterType.INTEGER);
		F_dustem.setSkossConcept("F_dustem");
		F_dustem.setUnit(" ");
		F_dustem.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(F_dustem.getName(), F_dustem);
		this.addParameterToGroupMap(F_dustem.getName(), "Grains");

		SingleParameter alpgr = factory.createSingleParameter();
		alpgr.setName("alpgr");
		alpgr.setParameterType(ParameterType.REAL);
		alpgr.setSkossConcept("alpgr");
		alpgr.setUnit("?");
		alpgr.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(alpgr.getName(), alpgr);
		this.addParameterToGroupMap(alpgr.getName(), "Grains");

		SingleParameter cdunit = factory.createSingleParameter();
		cdunit.setName("cdunit");
		cdunit.setParameterType(ParameterType.REAL);
		cdunit.setSkossConcept("cdunit");
		cdunit.setUnit("?");
		cdunit.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(cdunit.getName(), cdunit);
		this.addParameterToGroupMap(cdunit.getName(), "toBeDefined");

		SingleParameter dsour = factory.createSingleParameter();
		dsour.setName("dsour");
		dsour.setParameterType(ParameterType.REAL);
		dsour.setSkossConcept("dsour");
		dsour.setUnit("paresec");
		dsour.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(dsour.getName(), dsour);
		this.addParameterToGroupMap(dsour.getName(), "RadiationField");

		SingleParameter NdensH = factory.createSingleParameter();
		NdensH.setName("NdensH");
		NdensH.setParameterType(ParameterType.INTEGER);
		NdensH.setSkossConcept("NdensH");
		NdensH.setUnit(" ");
		NdensH.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NdensH.getName(), NdensH);
		this.addParameterToGroupMap(NdensH.getName(), "Chemistry");

		SingleParameter denshInf = factory.createSingleParameter();
		denshInf.setName("denshInf");
		denshInf.setParameterType(ParameterType.REAL);
		denshInf.setSkossConcept("denshInf");
		denshInf.setUnit("cm-3");
		denshInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(denshInf.getName(), denshInf);
		this.addParameterToGroupMap(denshInf.getName(), "Chemistry");

		SingleParameter deltaDensh = factory.createSingleParameter();
		deltaDensh.setName("deltaDensh");
		deltaDensh.setParameterType(ParameterType.REAL);
		deltaDensh.setSkossConcept("deltaDensh");
		deltaDensh.setUnit("cm-3");
		deltaDensh.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaDensh.getName(), deltaDensh);
		this.addParameterToGroupMap(deltaDensh.getName(), "Chemistry");

		SingleParameter denshVariationMethod = factory.createSingleParameter();
		denshVariationMethod.setName("denshVariationMethod");
		denshVariationMethod.setParameterType(ParameterType.STRING);
		denshVariationMethod.setSkossConcept("denshVariationMethod");
		denshVariationMethod.setUnit(" ");
		denshVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(denshVariationMethod.getName(),
				denshVariationMethod);
		this.addParameterToGroupMap(denshVariationMethod.getName(), "Chemistry");

		SingleParameter Nfmrc = factory.createSingleParameter();
		Nfmrc.setName("Nfmrc");
		Nfmrc.setParameterType(ParameterType.INTEGER);
		Nfmrc.setSkossConcept("Nfmrc");
		Nfmrc.setUnit(" ");
		Nfmrc.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nfmrc.getName(), Nfmrc);
		this.addParameterToGroupMap(Nfmrc.getName(), "RadiationField");

		SingleParameter deltaFmrc = factory.createSingleParameter();
		deltaFmrc.setName("deltaFmrc");
		deltaFmrc.setParameterType(ParameterType.REAL);
		deltaFmrc.setSkossConcept("deltaFmrc");
		deltaFmrc.setUnit("?");
		deltaFmrc.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaFmrc.getName(), deltaFmrc);
		this.addParameterToGroupMap(deltaFmrc.getName(), "RadiationField");

		SingleParameter fmrcInf = factory.createSingleParameter();
		fmrcInf.setName("fmrcInf");
		fmrcInf.setParameterType(ParameterType.REAL);
		fmrcInf.setSkossConcept("fmrcInf");
		fmrcInf.setUnit("?");
		fmrcInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(fmrcInf.getName(), fmrcInf);
		this.addParameterToGroupMap(fmrcInf.getName(), "RadiationField");

		SingleParameter fmrcVatiationMethod = factory.createSingleParameter();
		fmrcVatiationMethod.setName("fmrcVatiationMethod");
		fmrcVatiationMethod.setParameterType(ParameterType.STRING);
		fmrcVatiationMethod.setSkossConcept("fmrcVatiationMethod");
		fmrcVatiationMethod.setUnit("?");
		fmrcVatiationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(fmrcVatiationMethod.getName(),
				fmrcVatiationMethod);
		this.addParameterToGroupMap(fmrcVatiationMethod.getName(),
				"RadiationField");

		SingleParameter gratio = factory.createSingleParameter();
		gratio.setName("gratio");
		gratio.setParameterType(ParameterType.REAL);
		gratio.setSkossConcept("gratio");
		gratio.setUnit("?");
		gratio.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(gratio.getName(), gratio);
		this.addParameterToGroupMap(gratio.getName(), "Grains");

		SingleParameter ichh2 = factory.createSingleParameter();
		ichh2.setName("ichh2");
		ichh2.setParameterType(ParameterType.INTEGER);
		ichh2.setSkossConcept("ichh2");
		ichh2.setUnit("?");
		ichh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ichh2.getName(), ichh2);
		this.addParameterToGroupMap(ichh2.getName(), "Chemistry");

		SingleParameter ieqth = factory.createSingleParameter();
		ieqth.setName("ieqth");
		ieqth.setParameterType(ParameterType.INTEGER);
		ieqth.setSkossConcept("ieqth");
		ieqth.setUnit(" ");
		ieqth.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ieqth.getName(), ieqth);
		this.addParameterToGroupMap(ieqth.getName(), "EquationOfState");

		SingleParameter ifafm = factory.createSingleParameter();
		ifafm.setName("ifafm");
		ifafm.setParameterType(ParameterType.INTEGER);
		ifafm.setSkossConcept("ifafm");
		ifafm.setUnit(" ");
		ifafm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ifafm.getName(), ifafm);
		this.addParameterToGroupMap(ifafm.getName(), "Algorithmics");

		SingleParameter ifisob = factory.createSingleParameter();
		ifisob.setName("ifisob");
		ifisob.setParameterType(ParameterType.INTEGER);
		ifisob.setSkossConcept("ifisob");
		ifisob.setUnit(" ");
		ifisob.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ifisob.getName(), ifisob);
		this.addParameterToGroupMap(ifisob.getName(), "EquationOfState");

		SingleParameter iforh2 = factory.createSingleParameter();
		iforh2.setName("iforh2");
		iforh2.setParameterType(ParameterType.INTEGER);
		iforh2.setSkossConcept("iforh2");
		iforh2.setUnit(" ");
		iforh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(iforh2.getName(), iforh2);
		this.addParameterToGroupMap(iforh2.getName(), "Grains");

		SingleParameter istic = factory.createSingleParameter();
		istic.setName("istic");
		istic.setParameterType(ParameterType.INTEGER);
		istic.setSkossConcept("istic");
		istic.setUnit(" ");
		istic.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(istic.getName(), istic);
		this.addParameterToGroupMap(istic.getName(), "Grains");

		SingleParameter itrfer = factory.createSingleParameter();
		itrfer.setName("itrfer");
		itrfer.setParameterType(ParameterType.INTEGER);
		itrfer.setSkossConcept("itrfer");
		itrfer.setUnit(" ");
		itrfer.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(itrfer.getName(), itrfer);
		this.addParameterToGroupMap(itrfer.getName(), "Algorithmics");

		SingleParameter jfgkh2 = factory.createSingleParameter();
		jfgkh2.setName("jfgkh2");
		jfgkh2.setParameterType(ParameterType.REAL);
		jfgkh2.setSkossConcept("jfgkh2");
		jfgkh2.setUnit("?");
		jfgkh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(jfgkh2.getName(), jfgkh2);
		this.addParameterToGroupMap(jfgkh2.getName(), "Algorithmics");

		SingleParameter los_ext = factory.createSingleParameter();
		los_ext.setName("los_ext");
		los_ext.setParameterType(ParameterType.STRING);
		los_ext.setSkossConcept("los_ext");
		los_ext.setUnit(" ");
		los_ext.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(los_ext.getName(), los_ext);
		this.addParameterToGroupMap(los_ext.getName(), "Grains");

		SingleParameter NPresse = factory.createSingleParameter();
		NPresse.setName("NPresse");
		NPresse.setParameterType(ParameterType.INTEGER);
		NPresse.setSkossConcept("NPresse");
		NPresse.setUnit("K cm-3");
		NPresse.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NPresse.getName(), NPresse);
		this.addParameterToGroupMap(NPresse.getName(), "EquationOfState");

		SingleParameter deltaPresse = factory.createSingleParameter();
		deltaPresse.setName("deltaPresse");
		deltaPresse.setParameterType(ParameterType.REAL);
		deltaPresse.setSkossConcept("deltaPresse");
		deltaPresse.setUnit("K cm-3 ");
		deltaPresse.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaPresse.getName(), deltaPresse);
		this.addParameterToGroupMap(deltaPresse.getName(), "EquationOfState");

		SingleParameter presseInf = factory.createSingleParameter();
		presseInf.setName("presseInf");
		presseInf.setParameterType(ParameterType.REAL);
		presseInf.setSkossConcept("presseInf");
		presseInf.setUnit("K cm-3 ");
		presseInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(presseInf.getName(), presseInf);
		this.addParameterToGroupMap(presseInf.getName(), "EquationOfState");

		SingleParameter presseVariationMethod = factory.createSingleParameter();
		presseVariationMethod.setName("presseVariationMethod");
		presseVariationMethod.setParameterType(ParameterType.STRING);
		presseVariationMethod.setSkossConcept("presseVariationMethod");
		presseVariationMethod.setUnit(" ");
		presseVariationMethod
				.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(presseVariationMethod.getName(),
				presseVariationMethod);
		this.addParameterToGroupMap(presseVariationMethod.getName(),
				"EquationOfState");

		SingleParameter Nradm = factory.createSingleParameter();
		Nradm.setName("Nradm");
		Nradm.setParameterType(ParameterType.INTEGER);
		Nradm.setSkossConcept("Nradm");
		Nradm.setUnit(" ");
		Nradm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nradm.getName(), Nradm);
		this.addParameterToGroupMap(Nradm.getName(), "RadiationField");

		SingleParameter deltaRadm = factory.createSingleParameter();
		deltaRadm.setName("deltaRadm");
		deltaRadm.setParameterType(ParameterType.REAL);
		deltaRadm.setSkossConcept("deltaRadm");
		deltaRadm.setUnit("?");
		deltaRadm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaRadm.getName(), deltaRadm);
		this.addParameterToGroupMap(deltaRadm.getName(), "RadiationField");

		SingleParameter radmInf = factory.createSingleParameter();
		radmInf.setName("radmInf");
		radmInf.setParameterType(ParameterType.REAL);
		radmInf.setSkossConcept("radmInf");
		radmInf.setUnit("?");
		radmInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radmInf.getName(), radmInf);
		this.addParameterToGroupMap(radmInf.getName(), "RadiationField");

		SingleParameter radmVariationMethod = factory.createSingleParameter();
		radmVariationMethod.setName("radmVariationMethod");
		radmVariationMethod.setParameterType(ParameterType.STRING);
		radmVariationMethod.setSkossConcept("radmVariationMethod");
		radmVariationMethod.setUnit("?");
		radmVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radmVariationMethod.getName(),
				radmVariationMethod);
		this.addParameterToGroupMap(radmVariationMethod.getName(),
				"RadiationField");

		SingleParameter Nradp = factory.createSingleParameter();
		Nradp.setName("Nradp");
		Nradp.setParameterType(ParameterType.INTEGER);
		Nradp.setSkossConcept("Nradp");
		Nradp.setUnit(" ");
		Nradp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nradp.getName(), Nradp);
		this.addParameterToGroupMap(Nradp.getName(), "RadiationField");

		SingleParameter deltaRadp = factory.createSingleParameter();
		deltaRadp.setName("deltaRadp");
		deltaRadp.setParameterType(ParameterType.REAL);
		deltaRadp.setSkossConcept("deltaRadp");
		deltaRadp.setUnit("?");
		deltaRadp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaRadp.getName(), deltaRadp);
		this.addParameterToGroupMap(deltaRadp.getName(), "RadiationField");

		SingleParameter radpInf = factory.createSingleParameter();
		radpInf.setName("radpInf");
		radpInf.setParameterType(ParameterType.REAL);
		radpInf.setSkossConcept("radpInf");
		radpInf.setUnit("?");
		radpInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radpInf.getName(), radpInf);
		this.addParameterToGroupMap(radpInf.getName(), "RadiationField");

		SingleParameter radpVariationMethod = factory.createSingleParameter();
		radpVariationMethod.setName("radpVariationMethod");
		radpVariationMethod.setParameterType(ParameterType.STRING);
		radpVariationMethod.setSkossConcept("radpVariationMethod");
		radpVariationMethod.setUnit(" ");
		radpVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radpVariationMethod.getName(),
				radpVariationMethod);
		this.addParameterToGroupMap(radpVariationMethod.getName(),
				"RadiationField");

		SingleParameter rgrmax = factory.createSingleParameter();
		rgrmax.setName("rgrmax");
		rgrmax.setParameterType(ParameterType.REAL);
		rgrmax.setSkossConcept("rgrmax");
		rgrmax.setUnit("cm");
		rgrmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(rgrmax.getName(), rgrmax);
		this.addParameterToGroupMap(rgrmax.getName(), "Grains");

		SingleParameter rgmin = factory.createSingleParameter();
		rgmin.setName("rgmin");
		rgmin.setParameterType(ParameterType.REAL);
		rgmin.setSkossConcept("rgmin");
		rgmin.setUnit("cm");
		rgmin.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(rgmin.getName(), rgmin);
		this.addParameterToGroupMap(rgmin.getName(), "Grains");

		SingleParameter srcpp = factory.createSingleParameter();
		srcpp.setName("srcpp");
		srcpp.setParameterType(ParameterType.STRING);
		srcpp.setSkossConcept("srcpp");
		srcpp.setUnit(" ");
		srcpp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(srcpp.getName(), srcpp);
		this.addParameterToGroupMap(srcpp.getName(), "RadiationField");

		SingleParameter TgazInf = factory.createSingleParameter();
		TgazInf.setName("TgazInf");
		TgazInf.setParameterType(ParameterType.REAL);
		TgazInf.setSkossConcept("TgazInf");
		TgazInf.setUnit("K");
		TgazInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(TgazInf.getName(), TgazInf);
		this.addParameterToGroupMap(TgazInf.getName(), "EquationOfState");

		SingleParameter deltaTgaz = factory.createSingleParameter();
		deltaTgaz.setName("deltaTgaz");
		deltaTgaz.setParameterType(ParameterType.REAL);
		deltaTgaz.setSkossConcept("deltaTgaz");
		deltaTgaz.setUnit("K");
		deltaTgaz.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaTgaz.getName(), deltaTgaz);
		this.addParameterToGroupMap(deltaTgaz.getName(), "EquationOfState");

		SingleParameter NT = factory.createSingleParameter();
		NT.setName("NT");
		NT.setParameterType(ParameterType.INTEGER);
		NT.setSkossConcept("NT");
		NT.setUnit(" ");
		NT.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NT.getName(), NT);
		this.addParameterToGroupMap(NT.getName(), "EquationOfState");

		SingleParameter tgazVariationMethod = factory.createSingleParameter();
		tgazVariationMethod.setName("tgazVariationMethod");
		tgazVariationMethod.setParameterType(ParameterType.STRING);
		tgazVariationMethod.setSkossConcept("tgazVariationMethod");
		tgazVariationMethod.setUnit(" ");
		tgazVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(tgazVariationMethod.getName(),
				tgazVariationMethod);
		this.addParameterToGroupMap(tgazVariationMethod.getName(),
				"EquationOfState");

		SingleParameter vturb = factory.createSingleParameter();
		vturb.setName("vturb");
		vturb.setParameterType(ParameterType.REAL);
		vturb.setSkossConcept("vturb");
		vturb.setUnit("km s^-1");
		vturb.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(vturb.getName(), vturb);
		this.addParameterToGroupMap(vturb.getName(), "EquationOfState");
	}

}
