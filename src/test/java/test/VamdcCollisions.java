package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mkconst;
import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.When;
import net.ivoa.parameter.model.WhenConditionalStatement;

public class VamdcCollisions extends BaseExample {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws PropertyException
	 */
	public static void main(String[] args) throws PropertyException,
			FileNotFoundException, JAXBException {
		VamdcCollisions example = new VamdcCollisions();
		example.marshall();
	}

	@Override
	protected Service buildService() {
		Service service = factory.createService()
				.withServiceId("Vamdc_Collision_Module")
				.withServiceName("A prototype for VAMDC collisions");
		service.setDescription("A prototype for VAMDC collisions");

		// Creating input and output parameter group
		ParameterGroup inputsPG = factory.createParameterGroup().withName(
				"inputs");
		ParameterGroup outputsPG = factory.createParameterGroup().withName(
				"outputs");

		ParameterGroup targetDetails = factory.createParameterGroup().withName(
				"targetDetails");
		ParameterGroup colliderDetails = factory.createParameterGroup()
				.withName("colliderDetails");
		ParameterGroup reactantDetails = factory.createParameterGroup()
				.withName("reactantDetails");
		ParameterGroup productDetails = factory.createParameterGroup()
				.withName("productDetails");

		ParameterGroup reactantAtomDetails = factory.createParameterGroup()
				.withName("reactantAtomDetails");
		ParameterGroup reactantParticle = factory.createParameterGroup()
				.withName("reactantParticleDetails");
		ParameterGroup reactantMoleculeDetails = factory.createParameterGroup()
				.withName("reactantMoleculeDetails");

		ParameterGroup productAtomDetails = factory.createParameterGroup()
				.withName("productAtomDetails");
		ParameterGroup productParticle = factory.createParameterGroup()
				.withName("productParticleDetails");
		ParameterGroup productMoleculeDetails = factory.createParameterGroup()
				.withName("productMoleculeDetails");

		ParameterGroup collidertAtomDetails = factory.createParameterGroup()
				.withName("colliderAtomDetails");
		ParameterGroup colliderParticle = factory.createParameterGroup()
				.withName("colliderParticleDetails");
		ParameterGroup colliderMoleculeDetails = factory.createParameterGroup()
				.withName("colliderMoleculeDetails");

		ParameterGroup targetAtomDetails = factory.createParameterGroup()
				.withName("targetAtomDetails");
		ParameterGroup targetParticle = factory.createParameterGroup()
				.withName("targetParticleDetails");
		ParameterGroup targetMoleculeDetails = factory.createParameterGroup()
				.withName("targetMoleculeDetails");

		// Creating sub-groups hierarchie
		targetDetails.withParameterGroup(targetAtomDetails);
		targetDetails.withParameterGroup(targetParticle);
		targetDetails.withParameterGroup(targetMoleculeDetails);

		colliderDetails.withParameterGroup(collidertAtomDetails);
		colliderDetails.withParameterGroup(colliderParticle);
		colliderDetails.withParameterGroup(colliderMoleculeDetails);

		reactantDetails.withParameterGroup(reactantAtomDetails);
		reactantDetails.withParameterGroup(reactantParticle);
		reactantDetails.withParameterGroup(reactantMoleculeDetails);

		productDetails.withParameterGroup(productAtomDetails);
		productDetails.withParameterGroup(productParticle);
		productDetails.withParameterGroup(productMoleculeDetails);

		// Creating the mass parameter
		SingleParameter target = factory.createSingleParameter();
		target.setName("target");
		target.setParameterType(ParameterType.STRING);
		target.setPrecision(mktconst("0", ParameterType.REAL));
		target.setSkosConcept("target for the collision");
		target.setUnit("None");
		target.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference targetRef = mkRef(target);

		SingleParameter collider = factory.createSingleParameter();
		collider.setName("collider");
		collider.setParameterType(ParameterType.STRING);
		collider.setPrecision(mktconst("0", ParameterType.REAL));
		collider.setSkosConcept("collider for the collision");
		collider.setUnit("None");
		collider.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference colliderRef = mkRef(collider);

		SingleParameter reactant = factory.createSingleParameter();
		reactant.setName("reactant");
		reactant.setParameterType(ParameterType.STRING);
		reactant.setPrecision(mktconst("0", ParameterType.REAL));
		reactant.setSkosConcept("reactant for the collision");
		reactant.setUnit("None");
		reactant.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference reactantRef = mkRef(reactant);

		SingleParameter product = factory.createSingleParameter();
		product.setName("product");
		product.setParameterType(ParameterType.STRING);
		product.setPrecision(mktconst("0", ParameterType.REAL));
		product.setSkosConcept("product for the collision");
		product.setUnit("None");
		product.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference productRef = mkRef(product);
		
		
		SingleParameter atomTargetName = factory.createSingleParameter();
		atomTargetName.setName("AtomTargetName");
		atomTargetName.setParameterType(ParameterType.STRING);
		atomTargetName.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetName.setSkosConcept("Symbol of the target atom");
		atomTargetName.setUnit("None");
		atomTargetName.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetNameRef = mkRef(atomTargetName);
		
		SingleParameter atomTargetMassNumberInf = factory.createSingleParameter();
		atomTargetMassNumberInf.setName("atomTargetMassNumberInf");
		atomTargetMassNumberInf.setParameterType(ParameterType.INTEGER);
		atomTargetMassNumberInf.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetMassNumberInf.setSkosConcept("Inf of the Mass number of the target atom");
		atomTargetMassNumberInf.setUnit("AU");
		atomTargetMassNumberInf.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetMassNumberInfRef = mkRef(atomTargetMassNumberInf);
		
		SingleParameter atomTargetMassNumberSup = factory.createSingleParameter();
		atomTargetMassNumberSup.setName("atomTargetMassNumberSup");
		atomTargetMassNumberSup.setParameterType(ParameterType.INTEGER);
		atomTargetMassNumberSup.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetMassNumberSup.setSkosConcept("Sup of the Mass number of the target atom");
		atomTargetMassNumberSup.setUnit("AU");
		atomTargetMassNumberSup.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetMassNumberSupRef = mkRef(atomTargetMassNumberSup);
		
		
		SingleParameter atomTargetNuclearChargeInf = factory.createSingleParameter();
		atomTargetNuclearChargeInf.setName("atomTargetNuclearChargeInf");
		atomTargetNuclearChargeInf.setParameterType(ParameterType.INTEGER);
		atomTargetNuclearChargeInf.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetNuclearChargeInf.setSkosConcept("Inf of the nuclear charge of the target atom");
		atomTargetNuclearChargeInf.setUnit("None");
		atomTargetNuclearChargeInf.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetNuclearChargeInfRef = mkRef(atomTargetNuclearChargeInf);
		
		SingleParameter atomTargetNuclearChargeSup = factory.createSingleParameter();
		atomTargetNuclearChargeSup.setName("atomTargetNuclearChargeSup");
		atomTargetNuclearChargeSup.setParameterType(ParameterType.INTEGER);
		atomTargetNuclearChargeSup.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetNuclearChargeSup.setSkosConcept("Sup of the Mass number of the target atom");
		atomTargetNuclearChargeSup.setUnit("None");
		atomTargetNuclearChargeSup.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetNuclearChargeSupRef = mkRef(atomTargetNuclearChargeSup);
		
		SingleParameter atomTargetInChiKey = factory.createSingleParameter();
		atomTargetInChiKey.setName("targetAtomInChiKey");
		atomTargetInChiKey.setParameterType(ParameterType.STRING);
		atomTargetInChiKey.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetInChiKey.setSkosConcept("InChiKey of the target atom");
		atomTargetInChiKey.setUnit("None");
		atomTargetInChiKey.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetInChiKeyRef = mkRef(atomTargetInChiKey);
		
		SingleParameter atomTargetStateEnergyInf = factory.createSingleParameter();
		atomTargetStateEnergyInf.setName("atomTargetStateEnergyInf");
		atomTargetStateEnergyInf.setParameterType(ParameterType.REAL);
		atomTargetStateEnergyInf.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetStateEnergyInf.setSkosConcept("Inf of the state energy for the target atom");
		atomTargetStateEnergyInf.setUnit("cm^(-1)");
		atomTargetStateEnergyInf.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetStateEnergyInfRef = mkRef(atomTargetStateEnergyInf);
		
		SingleParameter atomTargetStateEnergySup = factory.createSingleParameter();
		atomTargetStateEnergySup.setName("atomTargetStateEnergySup");
		atomTargetStateEnergySup.setParameterType(ParameterType.REAL);
		atomTargetStateEnergySup.setPrecision(mktconst("0", ParameterType.REAL));
		atomTargetStateEnergySup.setSkosConcept("Sup of the state energy for the target atom");
		atomTargetStateEnergySup.setUnit("cm^(-1)");
		atomTargetStateEnergySup.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference atomTargetStateEnergySupRef = mkRef(atomTargetStateEnergySup);
		
		SingleParameter moleculeTargetChemicalName = factory.createSingleParameter();
		moleculeTargetChemicalName.setName("moleculeTargetChemicalName");
		moleculeTargetChemicalName.setParameterType(ParameterType.STRING);
		moleculeTargetChemicalName.setPrecision(mktconst("0", ParameterType.REAL));
		moleculeTargetChemicalName.setSkosConcept("Sup of the state energy for the target atom");
		moleculeTargetChemicalName.setUnit("cm^(-1)");
		moleculeTargetChemicalName.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference moleculeTargetChemicalNameRef = mkRef(moleculeTargetChemicalName);
		
		SingleParameter stoichiometricFormula = factory.createSingleParameter();
		stoichiometricFormula.setName("stoichiometric Formula");
		stoichiometricFormula.setParameterType(ParameterType.STRING);
		stoichiometricFormula.setPrecision(mktconst("0", ParameterType.REAL));
		stoichiometricFormula.setSkosConcept("Sup of the state energy for the target atom");
		stoichiometricFormula.setUnit("cm^(-1)");
		stoichiometricFormula.setDimension(mktconst("1", ParameterType.INTEGER));
		ParameterReference stoichiometricFormulaRef = mkRef(stoichiometricFormula);
		
		Parameters parameterList = factory.createParameters();
		List<SingleParameter> parameters = parameterList.getParameter();
		parameters.add(target);
		parameters.add(collider);
		parameters.add(reactant);
		parameters.add(product);
		parameters.add(atomTargetName);
		parameters.add(atomTargetMassNumberInf);
		parameters.add(atomTargetMassNumberSup);
		parameters.add(atomTargetNuclearChargeInf);
		parameters.add(atomTargetNuclearChargeSup);
		parameters.add(atomTargetInChiKey);
		parameters.add(atomTargetStateEnergyInf);
		parameters.add(atomTargetStateEnergySup);
		parameters.add(moleculeTargetChemicalName);
		parameters.add(stoichiometricFormula);
		service.setParameters(parameterList);

		targetAtomDetails.getParameterRef().add(atomTargetNameRef);
		targetAtomDetails.getParameterRef().add(atomTargetMassNumberInfRef);
		targetAtomDetails.getParameterRef().add(atomTargetMassNumberSupRef);
		targetAtomDetails.getParameterRef().add(atomTargetNuclearChargeInfRef);
		targetAtomDetails.getParameterRef().add(atomTargetNuclearChargeSupRef);
		targetAtomDetails.getParameterRef().add(atomTargetInChiKeyRef);
		targetAtomDetails.getParameterRef().add(atomTargetStateEnergyInfRef);
		targetAtomDetails.getParameterRef().add(atomTargetStateEnergySupRef);
		
		targetMoleculeDetails.getParameterRef().add(moleculeTargetChemicalNameRef);
		targetMoleculeDetails.getParameterRef().add(stoichiometricFormulaRef);
		
		inputsPG.getParameterRef().add(targetRef);
		inputsPG.getParameterRef().add(colliderRef);
		inputsPG.getParameterRef().add(reactantRef);
		inputsPG.getParameterRef().add(productRef);

		ConstraintOnGroup inputsConstraint = factory.createConstraintOnGroup();

		// constraint for colliders
		BelongToSet colliderSet = new BelongToSet()
				.withValue(mktconst("Atom", ParameterType.STRING))
				.withValue(mktconst("Particle", ParameterType.STRING))
				.withValue(mktconst("Molecule", ParameterType.STRING));

		AtomicParameterExpression colliderExp = new AtomicParameterExpression()
				.withParameterRef(mkRef(collider));

		Criterion colliderCriterion = new Criterion().withConditionType(
				colliderSet).withExpression(colliderExp);

		AlwaysConditionalStatement alwaysCollider = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(colliderCriterion))
				.withComment("collider should be: atom, molecule or particle");

		inputsConstraint.withConditionalStatement(alwaysCollider);

		// constraints for targets
		BelongToSet targetSet = new BelongToSet()
				.withValue(mktconst("Atom", ParameterType.STRING))
				.withValue(mktconst("Particle", ParameterType.STRING))
				.withValue(mktconst("Molecule", ParameterType.STRING));

		AtomicParameterExpression targetExp = new AtomicParameterExpression()
				.withParameterRef(mkRef(target));

		Criterion targetCriterion = new Criterion()
				.withConditionType(targetSet).withExpression(targetExp);

		AlwaysConditionalStatement alwaysTarget = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(targetCriterion))
				.withComment("target should be: atom, molecule or particle");

		inputsConstraint.withConditionalStatement(alwaysTarget);

		// constraints for reactant
		BelongToSet reactantSet = new BelongToSet()
				.withValue(mktconst("Atom", ParameterType.STRING))
				.withValue(mktconst("Particle", ParameterType.STRING))
				.withValue(mktconst("Molecule", ParameterType.STRING));

		AtomicParameterExpression reactantExp = new AtomicParameterExpression()
				.withParameterRef(mkRef(reactant));

		Criterion reactantCriterion = new Criterion().withConditionType(
				reactantSet).withExpression(reactantExp);

		AlwaysConditionalStatement alwaysReactant = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(reactantCriterion))
				.withComment("reactant should be: atom, molecule or particle");

		inputsConstraint.withConditionalStatement(alwaysReactant);

		BelongToSet productSet = new BelongToSet()
				.withValue(mktconst("Atom", ParameterType.STRING))
				.withValue(mktconst("Particle", ParameterType.STRING))
				.withValue(mktconst("Molecule", ParameterType.STRING));

		AtomicParameterExpression productExp = new AtomicParameterExpression()
				.withParameterRef(mkRef(product));

		Criterion productCriterion = new Criterion().withConditionType(
				productSet).withExpression(productExp);

		AlwaysConditionalStatement productReactant = new AlwaysConditionalStatement()
				.withAlways(new Always().withCriterion(productCriterion))
				.withComment("product should be: atom, molecule or particle");

		inputsConstraint.withConditionalStatement(productReactant);

		AtomicParameterExpression targetAtomRef = factory
				.createAtomicParameterExpression().withParameterRef(
						mkRef(target));

		targetAtomDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet().withValue(mkconst("Atom")))
						.withExpression(targetAtomRef))));
		
		targetParticle.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Particle")))
						.withExpression(targetAtomRef))));

		targetMoleculeDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Molecule")))
						.withExpression(targetAtomRef))));
		
		
		AtomicParameterExpression colliderAtomRef = factory
				.createAtomicParameterExpression().withParameterRef(
						mkRef(collider));
		
		collidertAtomDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet().withValue(mkconst("Atom")))
						.withExpression(colliderAtomRef))));

		colliderParticle.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Particle")))
						.withExpression(colliderAtomRef))));

		colliderMoleculeDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Molecule")))
						.withExpression(colliderAtomRef))));
		
		
		AtomicParameterExpression reactantAtomRef = factory
				.createAtomicParameterExpression().withParameterRef(
						mkRef(reactant));
		
		reactantAtomDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet().withValue(mkconst("Atom")))
						.withExpression(reactantAtomRef))));

		reactantParticle.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Particle")))
						.withExpression(reactantAtomRef))));

		reactantMoleculeDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Molecule")))
						.withExpression(reactantAtomRef))));
		
		AtomicParameterExpression productAtomRef = factory
				.createAtomicParameterExpression().withParameterRef(
						mkRef(product));
		
		productAtomDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet().withValue(mkconst("Atom")))
						.withExpression(productAtomRef))));

		productParticle.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Particle")))
						.withExpression(productAtomRef))));

		productMoleculeDetails.setActive(new WhenConditionalStatement()
				.withWhen(new When().withCriterion(new Criterion()
						.withConditionType(
								new BelongToSet()
										.withValue(mkconst("Molecule")))
						.withExpression(productAtomRef))));

		inputsPG.withParameterGroup(targetDetails);
		inputsPG.withParameterGroup(colliderDetails);
		inputsPG.withParameterGroup(reactantDetails);
		inputsPG.withParameterGroup(productDetails);

		inputsPG.withConstraintOnGroup(inputsConstraint);

		service.setInputs(inputsPG);

		return service;
	}
}
