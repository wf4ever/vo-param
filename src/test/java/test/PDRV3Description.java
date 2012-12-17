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

public class PDRV3Description extends BaseExample {

	private Map<String, SingleParameter> singleParameterMap;
	private Map<String, ParameterReference> parameterRefMap;


	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		PDRV3Description example = new PDRV3Description();
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

		this.initialiseSingleParameterMap();
		this.initializeReferenceMap();

		ParameterGroup radiation = factory.createParameterGroup().withName("RadiationField");
		ParameterGroup chem = factory.createParameterGroup().withName("Chemistry");
		ParameterGroup grains = factory.createParameterGroup().withName("Grains");
		ParameterGroup eos = factory.createParameterGroup().withName("EquationOfState");
		ParameterGroup algo = factory.createParameterGroup().withName("Algorithms");
		ParameterGroup technical = factory.createParameterGroup().withName("TechnicalParameters");
		
		ParameterGroup avmax = factory.createParameterGroup().withName("Avmax");
		ParameterGroup fmrc = factory.createParameterGroup().withName("Fmrc");
		ParameterGroup radm = factory.createParameterGroup().withName("Radm");
		ParameterGroup radp = factory.createParameterGroup().withName("Radp");
		
		ParameterGroup presse = factory.createParameterGroup().withName("Presse");
		ParameterGroup tgaz = factory.createParameterGroup().withName("Tgaz");
		
		
		technical.withParameterRef(parameterRefMap.get("mail"));
		
		avmax.withParameterRef(parameterRefMap.get("AvmaxInf"));
		avmax.withParameterRef(parameterRefMap.get("NAvmax"));
		avmax.withParameterRef(parameterRefMap.get("deltaAvmax"));
		avmax.withParameterRef(parameterRefMap.get("AvmaxVariationMethod"));
		fmrc.withParameterRef(parameterRefMap.get("FmrcInf"));
		fmrc.withParameterRef(parameterRefMap.get("NFmrc"));
		fmrc.withParameterRef(parameterRefMap.get("deltaFmrc"));
		fmrc.withParameterRef(parameterRefMap.get("FmrcVariationMethod"));
		radm.withParameterRef(parameterRefMap.get("RadmInf"));
		radm.withParameterRef(parameterRefMap.get("NRadm"));
		radm.withParameterRef(parameterRefMap.get("deltaRadm"));
		radm.withParameterRef(parameterRefMap.get("RadmVariationMethod"));
		radp.withParameterRef(parameterRefMap.get("RadpInf"));		
		radp.withParameterRef(parameterRefMap.get("NRadp"));
		radp.withParameterRef(parameterRefMap.get("deltaRadp"));
		radp.withParameterRef(parameterRefMap.get("RadpVariationMethod"));
		radiation.withParameterRef(parameterRefMap.get("srcpp"));
		radiation.withParameterRef(parameterRefMap.get("dsour"));
		
		radiation.withParameterGroup(avmax);
		radiation.withParameterGroup(fmrc);
		radiation.withParameterGroup(radm);
		radiation.withParameterGroup(radp);
		
		chem.withParameterRef(parameterRefMap.get("Chimie"));
		chem.withParameterRef(parameterRefMap.get("Fisrf"));
		chem.withParameterRef(parameterRefMap.get("densh"));
		chem.withParameterRef(parameterRefMap.get("ichh2"));
		
		grains.withParameterRef(parameterRefMap.get("F_dustem"));
		grains.withParameterRef(parameterRefMap.get("F_dustem"));
		grains.withParameterRef(parameterRefMap.get("alpgr"));
		grains.withParameterRef(parameterRefMap.get("gratio"));
		grains.withParameterRef(parameterRefMap.get("iforh2"));
		grains.withParameterRef(parameterRefMap.get("istic"));
		grains.withParameterRef(parameterRefMap.get("los_ext"));
		grains.withParameterRef(parameterRefMap.get("rgrmax"));
		grains.withParameterRef(parameterRefMap.get("rgmin"));
		
		eos.withParameterRef(parameterRefMap.get("ieqth"));
		eos.withParameterRef(parameterRefMap.get("ifisob"));
		eos.withParameterRef(parameterRefMap.get("ifisob"));
		presse.withParameterRef(parameterRefMap.get("PresseInf"));
		presse.withParameterRef(parameterRefMap.get("NPresse"));
		presse.withParameterRef(parameterRefMap.get("deltaPresse"));
		presse.withParameterRef(parameterRefMap.get("PresseVariationMethod"));
		tgaz.withParameterRef(parameterRefMap.get("TgazInf"));
		tgaz.withParameterRef(parameterRefMap.get("NTgaz"));
		tgaz.withParameterRef(parameterRefMap.get("deltaTgaz"));
		tgaz.withParameterRef(parameterRefMap.get("TgazVariationMethod"));
		eos.withParameterRef(parameterRefMap.get("vturb"));
		
		eos.withParameterGroup(presse);
		eos.withParameterGroup(tgaz);
		
		algo.withParameterRef(parameterRefMap.get("cdunit"));
		algo.withParameterRef(parameterRefMap.get("ifafm"));
		algo.withParameterRef(parameterRefMap.get("itrfer"));
		algo.withParameterRef(parameterRefMap.get("jfg1kh2"));
		
		
		inputsPG.withParameterGroup(technical);
		inputsPG.withParameterGroup(radiation);
		inputsPG.withParameterGroup(chem);
		inputsPG.withParameterGroup(grains);
		inputsPG.withParameterGroup(eos);
		inputsPG.withParameterGroup(algo);
		
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

	

	

	private void initializeReferenceMap() {
		this.parameterRefMap = new HashMap<String, ParameterReference>();
		for (Map.Entry<String, SingleParameter> entry : this.singleParameterMap
				.entrySet()) {
			this.parameterRefMap.put(entry.getKey(), mkRef(entry.getValue()));
		}
	}


	protected void initialiseSingleParameterMap() {

		this.singleParameterMap = new HashMap<String, SingleParameter>();

		SingleParameter mail = factory.createSingleParameter();
		mail.setName("mail");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/UserEmail");
		mail.setUnit("no unit");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(mail.getName(), mail);
		

		SingleParameter Avmaxinf = factory.createSingleParameter();
		Avmaxinf.setName("AvmaxInf");
		Avmaxinf.setParameterType(ParameterType.REAL);
		Avmaxinf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/AvmaxInf");
		Avmaxinf.setUnit("magnitude");
		Avmaxinf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Avmaxinf.getName(), Avmaxinf);
		

		SingleParameter NAvmax = factory.createSingleParameter();
		NAvmax.setName("NAvmax");
		NAvmax.setParameterType(ParameterType.INTEGER);
		NAvmax.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/NAvmax");
		NAvmax.setUnit("no unit");
		NAvmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NAvmax.getName(), NAvmax);
		

		SingleParameter deltaAvmax = factory.createSingleParameter();
		deltaAvmax.setName("deltaAvmax");
		deltaAvmax.setParameterType(ParameterType.REAL);
		deltaAvmax.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaAvmax");
		deltaAvmax.setUnit("magnitude");
		deltaAvmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaAvmax.getName(), deltaAvmax);
	

		SingleParameter AvmaxVariationMethod = factory.createSingleParameter();
		AvmaxVariationMethod.setName("AvmaxVariationMethod");
		AvmaxVariationMethod.setParameterType(ParameterType.STRING);
		AvmaxVariationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/AvmaxVariationMethod");
		AvmaxVariationMethod.setUnit("no unit");
		AvmaxVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(AvmaxVariationMethod.getName(),
				AvmaxVariationMethod);
		

		SingleParameter Chimie = factory.createSingleParameter();
		Chimie.setName("Chimie");
		Chimie.setParameterType(ParameterType.STRING);
		Chimie.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/Chimie");
		Chimie.setUnit("no unit");
		Chimie.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Chimie.getName(), Chimie);
		

		SingleParameter F_ISRF = factory.createSingleParameter();
		F_ISRF.setName("Fisrf");
		F_ISRF.setParameterType(ParameterType.INTEGER);
		F_ISRF.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/F_ISRF");
		F_ISRF.setUnit("no unit");
		F_ISRF.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(F_ISRF.getName(), F_ISRF);
		
		SingleParameter F_dustem = factory.createSingleParameter();
		F_dustem.setName("F_dustem");
		F_dustem.setParameterType(ParameterType.INTEGER);
		F_dustem.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/F_dustem");
		F_dustem.setUnit("no unit");
		F_dustem.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(F_dustem.getName(), F_dustem);
		

		SingleParameter alpgr = factory.createSingleParameter();
		alpgr.setName("alpgr");
		alpgr.setParameterType(ParameterType.REAL);
		alpgr.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/alpgr");
		alpgr.setUnit("?");
		alpgr.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(alpgr.getName(), alpgr);
		

		SingleParameter cdunit = factory.createSingleParameter();
		cdunit.setName("cdunit");
		cdunit.setParameterType(ParameterType.REAL);
		cdunit.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/cdunit");
		cdunit.setUnit("?");
		cdunit.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(cdunit.getName(), cdunit);
		

		SingleParameter dsour = factory.createSingleParameter();
		dsour.setName("dsour");
		dsour.setParameterType(ParameterType.REAL);
		dsour.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/d_sour");
		dsour.setUnit("paresec");
		dsour.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(dsour.getName(), dsour);
		


		SingleParameter densH = factory.createSingleParameter();
		densH.setName("densh");
		densH.setParameterType(ParameterType.REAL);
		densH.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/densh");
		densH.setUnit("cm-3");
		densH.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(densH.getName(), densH);

		
		SingleParameter Nfmrc = factory.createSingleParameter();
		Nfmrc.setName("NFmrc");
		Nfmrc.setParameterType(ParameterType.INTEGER);
		Nfmrc.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/Nfmrc");
		Nfmrc.setUnit("no unit");
		Nfmrc.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nfmrc.getName(), Nfmrc);

		SingleParameter deltaFmrc = factory.createSingleParameter();
		deltaFmrc.setName("deltaFmrc");
		deltaFmrc.setParameterType(ParameterType.REAL);
		deltaFmrc.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaFmrc");
		deltaFmrc.setUnit("?");
		deltaFmrc.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaFmrc.getName(), deltaFmrc);

		SingleParameter fmrcInf = factory.createSingleParameter();
		fmrcInf.setName("FmrcInf");
		fmrcInf.setParameterType(ParameterType.REAL);
		fmrcInf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/fmrcInf");
		fmrcInf.setUnit("?");
		fmrcInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(fmrcInf.getName(), fmrcInf);

		SingleParameter fmrcVatiationMethod = factory.createSingleParameter();
		fmrcVatiationMethod.setName("FmrcVariationMethod");
		fmrcVatiationMethod.setParameterType(ParameterType.STRING);
		fmrcVatiationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/fmrcVatiationMethod");
		fmrcVatiationMethod.setUnit("?");
		fmrcVatiationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(fmrcVatiationMethod.getName(),
				fmrcVatiationMethod);

		SingleParameter gratio = factory.createSingleParameter();
		gratio.setName("gratio");
		gratio.setParameterType(ParameterType.REAL);
		gratio.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/gratio");
		gratio.setUnit("?");
		gratio.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(gratio.getName(), gratio);


		SingleParameter ichh2 = factory.createSingleParameter();
		ichh2.setName("ichh2");
		ichh2.setParameterType(ParameterType.INTEGER);
		ichh2.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/ichh2");
		ichh2.setUnit("?");
		ichh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ichh2.getName(), ichh2);


		SingleParameter ieqth = factory.createSingleParameter();
		ieqth.setName("ieqth");
		ieqth.setParameterType(ParameterType.INTEGER);
		ieqth.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/ieqth");
		ieqth.setUnit("no unit");
		ieqth.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ieqth.getName(), ieqth);

		SingleParameter ifafm = factory.createSingleParameter();
		ifafm.setName("ifafm");
		ifafm.setParameterType(ParameterType.INTEGER);
		ifafm.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/ifafm");
		ifafm.setUnit(" ");
		ifafm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ifafm.getName(), ifafm);

		SingleParameter ifisob = factory.createSingleParameter();
		ifisob.setName("ifisob");
		ifisob.setParameterType(ParameterType.INTEGER);
		ifisob.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/ifisob");
		ifisob.setUnit("no unit");
		ifisob.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(ifisob.getName(), ifisob);

		SingleParameter iforh2 = factory.createSingleParameter();
		iforh2.setName("iforh2");
		iforh2.setParameterType(ParameterType.INTEGER);
		iforh2.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/iforh2");
		iforh2.setUnit("no unit");
		iforh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(iforh2.getName(), iforh2);

		SingleParameter istic = factory.createSingleParameter();
		istic.setName("istic");
		istic.setParameterType(ParameterType.INTEGER);
		istic.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/istic");
		istic.setUnit("no unit");
		istic.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(istic.getName(), istic);

		SingleParameter itrfer = factory.createSingleParameter();
		itrfer.setName("itrfer");
		itrfer.setParameterType(ParameterType.INTEGER);
		itrfer.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/itrfer");
		itrfer.setUnit("no unit");
		itrfer.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(itrfer.getName(), itrfer);

		SingleParameter jfgkh2 = factory.createSingleParameter();
		jfgkh2.setName("jfg1kh2");
		jfgkh2.setParameterType(ParameterType.REAL);
		jfgkh2.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/jfgkh2");
		jfgkh2.setUnit("?");
		jfgkh2.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(jfgkh2.getName(), jfgkh2);

		SingleParameter los_ext = factory.createSingleParameter();
		los_ext.setName("los_ext");
		los_ext.setParameterType(ParameterType.STRING);
		los_ext.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/los_ext");
		los_ext.setUnit("no unit");
		los_ext.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(los_ext.getName(), los_ext);

		SingleParameter NPresse = factory.createSingleParameter();
		NPresse.setName("NPresse");
		NPresse.setParameterType(ParameterType.INTEGER);
		NPresse.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/NPresse");
		NPresse.setUnit("K cm-3");
		NPresse.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NPresse.getName(), NPresse);

		SingleParameter deltaPresse = factory.createSingleParameter();
		deltaPresse.setName("deltaPresse");
		deltaPresse.setParameterType(ParameterType.REAL);
		deltaPresse.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaPresse");
		deltaPresse.setUnit("K cm-3 ");
		deltaPresse.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaPresse.getName(), deltaPresse);

		SingleParameter presseInf = factory.createSingleParameter();
		presseInf.setName("PresseInf");
		presseInf.setParameterType(ParameterType.REAL);
		presseInf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/presseInf");
		presseInf.setUnit("K cm-3 ");
		presseInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(presseInf.getName(), presseInf);

		SingleParameter presseVariationMethod = factory.createSingleParameter();
		presseVariationMethod.setName("PresseVariationMethod");
		presseVariationMethod.setParameterType(ParameterType.STRING);
		presseVariationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/presseVariationMethod");
		presseVariationMethod.setUnit("no unit");
		presseVariationMethod
				.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(presseVariationMethod.getName(),
				presseVariationMethod);

		SingleParameter Nradm = factory.createSingleParameter();
		Nradm.setName("NRadm");
		Nradm.setParameterType(ParameterType.INTEGER);
		Nradm.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/Nradm");
		Nradm.setUnit("no unit");
		Nradm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nradm.getName(), Nradm);

		SingleParameter deltaRadm = factory.createSingleParameter();
		deltaRadm.setName("deltaRadm");
		deltaRadm.setParameterType(ParameterType.REAL);
		deltaRadm.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaRadm");
		deltaRadm.setUnit("?");
		deltaRadm.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaRadm.getName(), deltaRadm);

		SingleParameter radmInf = factory.createSingleParameter();
		radmInf.setName("RadmInf");
		radmInf.setParameterType(ParameterType.REAL);
		radmInf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/radmInf");
		radmInf.setUnit("?");
		radmInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radmInf.getName(), radmInf);

		SingleParameter radmVariationMethod = factory.createSingleParameter();
		radmVariationMethod.setName("RadmVariationMethod");
		radmVariationMethod.setParameterType(ParameterType.STRING);
		radmVariationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/radmVariationMethod");
		radmVariationMethod.setUnit("no unit");
		radmVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radmVariationMethod.getName(),
				radmVariationMethod);

		SingleParameter Nradp = factory.createSingleParameter();
		Nradp.setName("NRadp");
		Nradp.setParameterType(ParameterType.INTEGER);
		Nradp.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/Nradp");
		Nradp.setUnit("no unit");
		Nradp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(Nradp.getName(), Nradp);

		SingleParameter deltaRadp = factory.createSingleParameter();
		deltaRadp.setName("deltaRadp");
		deltaRadp.setParameterType(ParameterType.REAL);
		deltaRadp.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaRadp");
		deltaRadp.setUnit("?");
		deltaRadp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaRadp.getName(), deltaRadp);

		SingleParameter radpInf = factory.createSingleParameter();
		radpInf.setName("RadpInf");
		radpInf.setParameterType(ParameterType.REAL);
		radpInf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/radpInf");
		radpInf.setUnit("?");
		radpInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radpInf.getName(), radpInf);

		SingleParameter radpVariationMethod = factory.createSingleParameter();
		radpVariationMethod.setName("RadpVariationMethod");
		radpVariationMethod.setParameterType(ParameterType.STRING);
		radpVariationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/radpVariationMethod");
		radpVariationMethod.setUnit("no unit");
		radpVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(radpVariationMethod.getName(),
				radpVariationMethod);

		SingleParameter rgrmax = factory.createSingleParameter();
		rgrmax.setName("rgrmax");
		rgrmax.setParameterType(ParameterType.REAL);
		rgrmax.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/rgrmax");
		rgrmax.setUnit("cm");
		rgrmax.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(rgrmax.getName(), rgrmax);

		SingleParameter rgmin = factory.createSingleParameter();
		rgmin.setName("rgmin");
		rgmin.setParameterType(ParameterType.REAL);
		rgmin.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/rgrmin");
		rgmin.setUnit("cm");
		rgmin.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(rgmin.getName(), rgmin);

		SingleParameter srcpp = factory.createSingleParameter();
		srcpp.setName("srcpp");
		srcpp.setParameterType(ParameterType.STRING);
		srcpp.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/srcpp");
		srcpp.setUnit("no unit");
		srcpp.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(srcpp.getName(), srcpp);

		SingleParameter TgazInf = factory.createSingleParameter();
		TgazInf.setName("TgazInf");
		TgazInf.setParameterType(ParameterType.REAL);
		TgazInf.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/TgazInf");
		TgazInf.setUnit("K");
		TgazInf.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(TgazInf.getName(), TgazInf);

		SingleParameter deltaTgaz = factory.createSingleParameter();
		deltaTgaz.setName("deltaTgaz");
		deltaTgaz.setParameterType(ParameterType.REAL);
		deltaTgaz.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/deltaTgaz");
		deltaTgaz.setUnit("K");
		deltaTgaz.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(deltaTgaz.getName(), deltaTgaz);

		SingleParameter NTgaz = factory.createSingleParameter();
		NTgaz.setName("NTgaz");
		NTgaz.setParameterType(ParameterType.INTEGER);
		NTgaz.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/NT");
		NTgaz.setUnit("no unit");
		NTgaz.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(NTgaz.getName(), NTgaz);

		SingleParameter tgazVariationMethod = factory.createSingleParameter();
		tgazVariationMethod.setName("TgazVariationMethod");
		tgazVariationMethod.setParameterType(ParameterType.STRING);
		tgazVariationMethod.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/tgazVariationMethod");
		tgazVariationMethod.setUnit("no unit");
		tgazVariationMethod.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(tgazVariationMethod.getName(),
				tgazVariationMethod);

		SingleParameter vturb = factory.createSingleParameter();
		vturb.setName("vturb");
		vturb.setParameterType(ParameterType.REAL);
		vturb.setSkossConcept("http://purl.org/astronomy/vocab/PDRInputParameters/vturb");
		vturb.setUnit("km s^-1");
		vturb.setDimension(mktconst("1", ParameterType.INTEGER));
		this.singleParameterMap.put(vturb.getName(), vturb);
	}

}
