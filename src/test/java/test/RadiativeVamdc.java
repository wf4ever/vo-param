package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mkconst;
import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ParameterDependency;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;

public class RadiativeVamdc extends BaseExample {

	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		RadiativeVamdc example = new RadiativeVamdc();
		example.marshall();
	}

	@Override
	protected Service buildService() {
		Service service = factory
				.createService()
				.withServiceId(
						"http://pdl-calc2.obspm.fr:8081/vamdc/OnlineCode")
				.withServiceName("A prototype for VAMDC collisions");
		service.setDescription("A prototype for VAMDC collisions");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		SingleParameter mail = factory.createSingleParameter();
		mail.setDependency(ParameterDependency.REQUIRED);
		mail.setName("mail");
		mail.setParameterType(ParameterType.STRING);
		mail.setSkosConcept("SKOS_MAIL");
		mail.setUnit("none");
		mail.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference mail_REF = new ParameterReference()
				.withParameterName(mail.getName());

		// Creating params for atoms
		SingleParameter AtomSymbol = factory.createSingleParameter();
		AtomSymbol.setDependency(ParameterDependency.OPTIONAL);
		AtomSymbol.setName("AtomSymbol");
		AtomSymbol.setParameterType(ParameterType.STRING);
		AtomSymbol.setPrecision(mktconst("0", ParameterType.INTEGER));
		AtomSymbol.setSkosConcept("Atom Symbol");
		AtomSymbol.setUnit("None");
		AtomSymbol.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference AtomSymbolRef = mkRef(AtomSymbol);

		SingleParameter MassNumberMin = factory.createSingleParameter();
		MassNumberMin.setDependency(ParameterDependency.OPTIONAL);
		MassNumberMin.setName("MassNumberMin");
		MassNumberMin.setParameterType(ParameterType.INTEGER);
		MassNumberMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		MassNumberMin.setSkosConcept("Lower bound of mass number");
		MassNumberMin.setUnit("UA");
		MassNumberMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference MassNumberMinRef = mkRef(MassNumberMin);

		SingleParameter MassNumberMax = factory.createSingleParameter();
		MassNumberMax.setDependency(ParameterDependency.OPTIONAL);
		MassNumberMax.setName("MassNumberMax");
		MassNumberMax.setParameterType(ParameterType.INTEGER);
		MassNumberMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		MassNumberMax.setSkosConcept("Higher bound of mass number");
		MassNumberMax.setUnit("UA");
		MassNumberMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference MassNumberMaxRef = mkRef(MassNumberMax);

		SingleParameter NuclearChargeMin = factory.createSingleParameter();
		NuclearChargeMin.setDependency(ParameterDependency.OPTIONAL);
		NuclearChargeMin.setName("NuclearChargeMin");
		NuclearChargeMin.setParameterType(ParameterType.INTEGER);
		NuclearChargeMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		NuclearChargeMin.setSkosConcept("Lower bound of nuclear charge");
		NuclearChargeMin.setUnit("None");
		NuclearChargeMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NuclearChargeMinRef = mkRef(NuclearChargeMin);

		SingleParameter NuclearChargeMax = factory.createSingleParameter();
		NuclearChargeMax.setDependency(ParameterDependency.OPTIONAL);
		NuclearChargeMax.setName("NuclearChargeMax");
		NuclearChargeMax.setParameterType(ParameterType.INTEGER);
		NuclearChargeMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		NuclearChargeMax.setSkosConcept("Higher bound of nuclear charge");
		NuclearChargeMax.setUnit("None");
		NuclearChargeMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NuclearChargeMaxRef = mkRef(NuclearChargeMax);

		SingleParameter IonChargeMin = factory.createSingleParameter();
		IonChargeMin.setDependency(ParameterDependency.OPTIONAL);
		IonChargeMin.setName("IonChargeMin");
		IonChargeMin.setParameterType(ParameterType.INTEGER);
		IonChargeMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		IonChargeMin.setSkosConcept("Lower bound of ion charge");
		IonChargeMin.setUnit("None");
		IonChargeMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference IonChargeMinRef = mkRef(IonChargeMin);

		SingleParameter IonChargeMax = factory.createSingleParameter();
		IonChargeMax.setDependency(ParameterDependency.OPTIONAL);
		IonChargeMax.setName("IonChargeMax");
		IonChargeMax.setParameterType(ParameterType.INTEGER);
		IonChargeMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		IonChargeMax.setSkosConcept("Higher bound of ion charge");
		IonChargeMax.setUnit("None");
		IonChargeMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference IonChargeMaxRef = mkRef(IonChargeMax);

		SingleParameter InChIKey = factory.createSingleParameter();
		InChIKey.setDependency(ParameterDependency.OPTIONAL);
		InChIKey.setName("InChIKey");
		InChIKey.setParameterType(ParameterType.STRING);
		InChIKey.setPrecision(mktconst("0", ParameterType.INTEGER));
		InChIKey.setSkosConcept("InChIKey for atom");
		InChIKey.setUnit("None");
		InChIKey.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference InChIKeyRef = mkRef(InChIKey);

		SingleParameter StateEnergyMin = factory.createSingleParameter();
		StateEnergyMin.setDependency(ParameterDependency.OPTIONAL);
		StateEnergyMin.setName("StateEnergyMin");
		StateEnergyMin.setParameterType(ParameterType.REAL);
		StateEnergyMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		StateEnergyMin.setSkosConcept("Lower bound of state energy");
		StateEnergyMin.setUnit("cm^(-1)");
		StateEnergyMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference StateEnergyMinRef = mkRef(StateEnergyMin);

		SingleParameter StateEnergyMax = factory.createSingleParameter();
		StateEnergyMax.setDependency(ParameterDependency.OPTIONAL);
		StateEnergyMax.setName("StateEnergyMax");
		StateEnergyMax.setParameterType(ParameterType.REAL);
		StateEnergyMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		StateEnergyMax.setSkosConcept("Higher bound of state energy");
		StateEnergyMax.setUnit("cm^(-1)");
		StateEnergyMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference StateEnergyMaxRef = mkRef(StateEnergyMax);

		// creating parameters for particles
		SingleParameter ParticleName = factory.createSingleParameter();
		ParticleName.setDependency(ParameterDependency.OPTIONAL);
		ParticleName.setName("ParticleName");
		ParticleName.setParameterType(ParameterType.STRING);
		ParticleName.setPrecision(mktconst("0", ParameterType.INTEGER));
		ParticleName.setSkosConcept("Particle Name");
		ParticleName.setUnit("None");
		ParticleName.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ParticleNameRef = mkRef(ParticleName);

		// creating parameters for molecules
		SingleParameter ChemicalName = factory.createSingleParameter();
		ChemicalName.setDependency(ParameterDependency.OPTIONAL);
		ChemicalName.setName("ChemicalName");
		ChemicalName.setParameterType(ParameterType.STRING);
		ChemicalName.setPrecision(mktconst("0", ParameterType.INTEGER));
		ChemicalName.setSkosConcept("Chemical Name of the molecule");
		ChemicalName.setUnit("None");
		ChemicalName.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ChemicalNameRef = mkRef(ChemicalName);

		SingleParameter StoichiometricFormula = factory.createSingleParameter();
		StoichiometricFormula.setDependency(ParameterDependency.OPTIONAL);
		StoichiometricFormula.setName("StoichiometricFormula");
		StoichiometricFormula.setParameterType(ParameterType.STRING);
		StoichiometricFormula
				.setPrecision(mktconst("0", ParameterType.INTEGER));
		StoichiometricFormula
				.setSkosConcept("Stoichiometric formula for molecule");
		StoichiometricFormula.setUnit("None");
		StoichiometricFormula
				.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference StoichiometricFormulaRef = mkRef(StoichiometricFormula);

		SingleParameter StructuralFormula = factory.createSingleParameter();
		StructuralFormula.setDependency(ParameterDependency.OPTIONAL);
		StructuralFormula.setName("StructuralFormula");
		StructuralFormula.setParameterType(ParameterType.STRING);
		StructuralFormula.setPrecision(mktconst("0", ParameterType.INTEGER));
		StructuralFormula.setSkosConcept("Structural formula for molecule");
		StructuralFormula.setUnit("None");
		StructuralFormula.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference StructuralFormulaRef = mkRef(StructuralFormula);

		SingleParameter SpinIsomer = factory.createSingleParameter();
		SpinIsomer.setDependency(ParameterDependency.OPTIONAL);
		SpinIsomer.setName("SpinIsomer");
		SpinIsomer.setParameterType(ParameterType.STRING);
		SpinIsomer.setPrecision(mktconst("0", ParameterType.INTEGER));
		SpinIsomer.setSkosConcept("Spin Isomer for molecule");
		SpinIsomer.setUnit("None");
		SpinIsomer.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference SpinIsomerRef = mkRef(SpinIsomer);

		SingleParameter StandardInChIKey = factory.createSingleParameter();
		StandardInChIKey.setDependency(ParameterDependency.OPTIONAL);
		StandardInChIKey.setName("StandardInChIKey");
		StandardInChIKey.setParameterType(ParameterType.STRING);
		StandardInChIKey.setPrecision(mktconst("0", ParameterType.INTEGER));
		StandardInChIKey.setSkosConcept("Standard InChIKey for molecule");
		StandardInChIKey.setUnit("None");
		StandardInChIKey.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference StandardInChIKeyRef = mkRef(StandardInChIKey);

		// creating parameters for radiative conditions
		SingleParameter WaveLengthMin = factory.createSingleParameter();
		WaveLengthMin.setDependency(ParameterDependency.OPTIONAL);
		WaveLengthMin.setName("WaveLengthMin");
		WaveLengthMin.setParameterType(ParameterType.REAL);
		WaveLengthMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		WaveLengthMin.setSkosConcept("Lower bound on wavelengt");
		WaveLengthMin.setUnit("A");
		WaveLengthMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference WaveLengthMinRef = mkRef(WaveLengthMin);

		SingleParameter WaveLengthMax = factory.createSingleParameter();
		WaveLengthMax.setDependency(ParameterDependency.OPTIONAL);
		WaveLengthMax.setName("WaveLengthMax");
		WaveLengthMax.setParameterType(ParameterType.REAL);
		WaveLengthMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		WaveLengthMax.setSkosConcept("Higher bound on wavelengt");
		WaveLengthMax.setUnit("A");
		WaveLengthMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference WaveLengthMaxRef = mkRef(WaveLengthMax);

		SingleParameter UpperStateEnergyMin = factory.createSingleParameter();
		UpperStateEnergyMin.setDependency(ParameterDependency.OPTIONAL);
		UpperStateEnergyMin.setName("UpperStateEnergyMin");
		UpperStateEnergyMin.setParameterType(ParameterType.REAL);
		UpperStateEnergyMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		UpperStateEnergyMin
				.setSkosConcept("Lower bound on energy for the upper level");
		UpperStateEnergyMin.setUnit("cm^(-1)");
		UpperStateEnergyMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference UpperStateEnergyMinRef = mkRef(UpperStateEnergyMin);

		SingleParameter UpperStateEnergyMax = factory.createSingleParameter();
		UpperStateEnergyMax.setDependency(ParameterDependency.OPTIONAL);
		UpperStateEnergyMax.setName("UpperStateEnergyMax");
		UpperStateEnergyMax.setParameterType(ParameterType.REAL);
		UpperStateEnergyMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		UpperStateEnergyMax
				.setSkosConcept("Higher bound on energy for the upper level");
		UpperStateEnergyMax.setUnit("cm^(-1)");
		UpperStateEnergyMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference UpperStateEnergyMaxRef = mkRef(UpperStateEnergyMax);

		SingleParameter LowerStateEnergyMin = factory.createSingleParameter();
		LowerStateEnergyMin.setDependency(ParameterDependency.OPTIONAL);
		LowerStateEnergyMin.setName("LowerStateEnergyMin");
		LowerStateEnergyMin.setParameterType(ParameterType.REAL);
		LowerStateEnergyMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		LowerStateEnergyMin
				.setSkosConcept("Lower bound on energy for the lower level");
		LowerStateEnergyMin.setUnit("cm^(-1)");
		LowerStateEnergyMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference LowerStateEnergyMinRef = mkRef(LowerStateEnergyMin);

		SingleParameter LowerStateEnergyMax = factory.createSingleParameter();
		LowerStateEnergyMax.setDependency(ParameterDependency.OPTIONAL);
		LowerStateEnergyMax.setName("LowerStateEnergyMax");
		LowerStateEnergyMax.setParameterType(ParameterType.REAL);
		LowerStateEnergyMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		LowerStateEnergyMax
				.setSkosConcept("Higher bound on energy for the lower level");
		LowerStateEnergyMax.setUnit("cm^(-1)");
		LowerStateEnergyMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference LowerStateEnergyMaxRef = mkRef(LowerStateEnergyMax);

		SingleParameter ProbabilityAMin = factory.createSingleParameter();
		ProbabilityAMin.setDependency(ParameterDependency.OPTIONAL);
		ProbabilityAMin.setName("ProbabilityAMin");
		ProbabilityAMin.setParameterType(ParameterType.REAL);
		ProbabilityAMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		ProbabilityAMin
				.setSkosConcept("Lower bound for the Einstein coefficient for spontaneous radiative de-excitation (emission).");
		ProbabilityAMin.setUnit("s^(-1)");
		ProbabilityAMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ProbabilityAMinRef = mkRef(ProbabilityAMin);

		SingleParameter ProbabilityAMax = factory.createSingleParameter();
		ProbabilityAMax.setDependency(ParameterDependency.OPTIONAL);
		ProbabilityAMax.setName("ProbabilityAMax");
		ProbabilityAMax.setParameterType(ParameterType.REAL);
		ProbabilityAMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		ProbabilityAMax
				.setSkosConcept("Higher bound for the Einstein coefficient for spontaneous radiative de-excitation (emission).");
		ProbabilityAMax.setUnit("s^(-1)");
		ProbabilityAMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference ProbabilityAMaxRef = mkRef(ProbabilityAMax);

		// creating parameters for environment configuration
		SingleParameter TemperatureMin = factory.createSingleParameter();
		TemperatureMin.setDependency(ParameterDependency.OPTIONAL);
		TemperatureMin.setName("TemperatureMin");
		TemperatureMin.setParameterType(ParameterType.REAL);
		TemperatureMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		TemperatureMin
				.setSkosConcept("Lower bound for the environment temperature.");
		TemperatureMin.setUnit("k");
		TemperatureMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TemperatureMinRef = mkRef(TemperatureMin);

		SingleParameter TemperatureMax = factory.createSingleParameter();
		TemperatureMax.setDependency(ParameterDependency.OPTIONAL);
		TemperatureMax.setName("TemperatureMax");
		TemperatureMax.setParameterType(ParameterType.REAL);
		TemperatureMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		TemperatureMax
				.setSkosConcept("Higher bound for the environment temperature.");
		TemperatureMax.setUnit("k");
		TemperatureMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference TemperatureMaxRef = mkRef(TemperatureMax);

		SingleParameter NumberDensityMin = factory.createSingleParameter();
		NumberDensityMin.setDependency(ParameterDependency.OPTIONAL);
		NumberDensityMin.setName("NumberDensityMin");
		NumberDensityMin.setParameterType(ParameterType.REAL);
		NumberDensityMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		NumberDensityMin
				.setSkosConcept("Lower bound for the the total number density of particles comprising an Environment");
		NumberDensityMin.setUnit("cm^(-3)");
		NumberDensityMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NumberDensityMinRef = mkRef(NumberDensityMin);

		SingleParameter NumberDensityMax = factory.createSingleParameter();
		NumberDensityMax.setDependency(ParameterDependency.OPTIONAL);
		NumberDensityMax.setName("NumberDensityMax");
		NumberDensityMax.setParameterType(ParameterType.REAL);
		NumberDensityMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		NumberDensityMax
				.setSkosConcept("Higher bound for the the total number density of particles comprising an Environment");
		NumberDensityMax.setUnit("cm^(-3)");
		NumberDensityMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference NumberDensityMaxRef = mkRef(NumberDensityMax);

		SingleParameter PressureMin = factory.createSingleParameter();
		PressureMin.setDependency(ParameterDependency.OPTIONAL);
		PressureMin.setName("PressureMin");
		PressureMin.setParameterType(ParameterType.REAL);
		PressureMin.setPrecision(mktconst("0", ParameterType.INTEGER));
		PressureMin.setSkosConcept("Lower bound for the environment pressure");
		PressureMin.setUnit("Pa");
		PressureMin.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference PressureMinRef = mkRef(PressureMin);

		SingleParameter PressureMax = factory.createSingleParameter();
		PressureMax.setDependency(ParameterDependency.OPTIONAL);
		PressureMax.setName("PressureMax");
		PressureMax.setParameterType(ParameterType.REAL);
		PressureMax.setPrecision(mktconst("0", ParameterType.INTEGER));
		PressureMax
				.setSkosConcept("Higher bound for the environment pressure");
		PressureMax.setUnit("Pa");
		PressureMax.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference PressureMaxRef = mkRef(PressureMax);

		SingleParameter result = factory.createSingleParameter();
		result.setDependency(ParameterDependency.REQUIRED);
		result.setName("fileResult");
		result.setSkosConcept("url to the tar file containing the xsams files");
		result.setParameterType(ParameterType.STRING);
		result.setPrecision(mkconst(0.0));
		result.setUnit("None");
		result.setDimension(mktconst("1", ParameterType.INTEGER));

		ParameterReference result_REF = new ParameterReference()
				.withParameterName(result.getName());

		ParameterGroup AtomsDetail = factory.createParameterGroup().withName(
				"AtomsDetail");
		ParameterGroup MoleculesDetail = factory.createParameterGroup()
				.withName("MoleculesDetail");
		ParameterGroup ParticleDetail = factory.createParameterGroup()
				.withName("ParticleDetail");
		ParameterGroup RadiativeConditions = factory.createParameterGroup()
				.withName("RadiativeConditions");
		ParameterGroup ThermalConditions = factory.createParameterGroup()
				.withName("ThermalConditions");

		AtomsDetail.getParameterRef().add(AtomSymbolRef);
		AtomsDetail.getParameterRef().add(MassNumberMinRef);
		AtomsDetail.getParameterRef().add(MassNumberMaxRef);
		AtomsDetail.getParameterRef().add(NuclearChargeMinRef);
		AtomsDetail.getParameterRef().add(NuclearChargeMaxRef);
		AtomsDetail.getParameterRef().add(IonChargeMinRef);
		AtomsDetail.getParameterRef().add(IonChargeMaxRef);
		AtomsDetail.getParameterRef().add(InChIKeyRef);
		AtomsDetail.getParameterRef().add(StateEnergyMinRef);
		AtomsDetail.getParameterRef().add(StateEnergyMaxRef);

		ParticleDetail.getParameterRef().add(ParticleNameRef);

		MoleculesDetail.getParameterRef().add(ChemicalNameRef);
		MoleculesDetail.getParameterRef().add(StoichiometricFormulaRef);
		MoleculesDetail.getParameterRef().add(StructuralFormulaRef);
		MoleculesDetail.getParameterRef().add(SpinIsomerRef);
		MoleculesDetail.getParameterRef().add(StandardInChIKeyRef);

		RadiativeConditions.getParameterRef().add(WaveLengthMinRef);
		RadiativeConditions.getParameterRef().add(WaveLengthMaxRef);
		RadiativeConditions.getParameterRef().add(UpperStateEnergyMinRef);
		RadiativeConditions.getParameterRef().add(UpperStateEnergyMaxRef);
		RadiativeConditions.getParameterRef().add(LowerStateEnergyMinRef);
		RadiativeConditions.getParameterRef().add(LowerStateEnergyMaxRef);
		RadiativeConditions.getParameterRef().add(ProbabilityAMinRef);
		RadiativeConditions.getParameterRef().add(ProbabilityAMaxRef);

		ThermalConditions.getParameterRef().add(TemperatureMinRef);
		ThermalConditions.getParameterRef().add(TemperatureMaxRef);
		ThermalConditions.getParameterRef().add(NumberDensityMinRef);
		ThermalConditions.getParameterRef().add(NumberDensityMaxRef);
		ThermalConditions.getParameterRef().add(PressureMinRef);
		ThermalConditions.getParameterRef().add(PressureMaxRef);

		inputsPG.getParameterRef().add(mail_REF);
		inputsPG.getParameterGroup().add(AtomsDetail);
		inputsPG.getParameterGroup().add(MoleculesDetail);
		inputsPG.getParameterGroup().add(ParticleDetail);
		inputsPG.getParameterGroup().add(RadiativeConditions);
		inputsPG.getParameterGroup().add(ThermalConditions);

		Parameters parameterList = factory.createParameters();
		List<SingleParameter> parameters = parameterList.getParameter();

		parameters.add(mail);

		parameters.add(AtomSymbol);
		parameters.add(MassNumberMin);
		parameters.add(MassNumberMax);
		parameters.add(NuclearChargeMin);
		parameters.add(NuclearChargeMax);
		parameters.add(IonChargeMin);
		parameters.add(IonChargeMax);
		parameters.add(InChIKey);
		parameters.add(StateEnergyMin);
		parameters.add(StateEnergyMax);

		parameters.add(ParticleName);

		parameters.add(ChemicalName);
		parameters.add(StoichiometricFormula);
		parameters.add(StructuralFormula);
		parameters.add(SpinIsomer);
		parameters.add(StandardInChIKey);

		parameters.add(WaveLengthMin);
		parameters.add(WaveLengthMax);
		parameters.add(UpperStateEnergyMin);
		parameters.add(UpperStateEnergyMax);
		parameters.add(LowerStateEnergyMin);
		parameters.add(LowerStateEnergyMax);
		parameters.add(ProbabilityAMin);
		parameters.add(ProbabilityAMax);

		parameters.add(TemperatureMin);
		parameters.add(TemperatureMax);
		parameters.add(NumberDensityMin);
		parameters.add(NumberDensityMax);
		parameters.add(PressureMin);
		parameters.add(PressureMax);

		parameters.add(result);

		service.setParameters(parameterList);

		outputsPG.getParameterRef().add(result_REF);

		service.setInputs(inputsPG);
		service.setOutputs(outputsPG);

		return service;
	}

}
