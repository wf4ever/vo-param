package test;


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
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.IsNull;
import net.ivoa.parameter.model.Or;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueDifferentFrom;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.*;

public class PDRExample extends BaseExample {

    
    public static void main(String[] args) throws PropertyException, FileNotFoundException, JAXBException {
        PDRExample example = new PDRExample();
        example.marshall();
    }

    /* (non-Javadoc)
     * @see test.BaseExample#buildService()
     */
    @Override
    protected Service buildService() {
        // Creating the root element of the schema : the service
        Service service = factory.createService()
                .withServiceId("PDR_ONLINE").withServiceName("PDR-1D");
        service.setDescription("Description layer of the PDR code");

        // Adding parameters to the list of service params
        Parameters parameterList = factory.createParameters();

        // Creating all the parameters
        SingleParameter F_ISRF = factory.createSingleParameter();
        F_ISRF.setName("F_ISRF");
        F_ISRF.setParameterType(ParameterType.INTEGER);
        F_ISRF.setPrecision(mkconst(0.0));
        F_ISRF.setUnit("None");
        parameterList.getParameter().add(F_ISRF);
        ParameterReference F_ISRFREF = new ParameterReference()
        .withParameterName(F_ISRF.getName());

        SingleParameter radm = factory.createSingleParameter();
        radm.setName("radm");
        radm.setParameterType(ParameterType.REAL);
        parameterList.getParameter().add(radm);
        ParameterReference radmRef = new ParameterReference()
        .withParameterName(radm.getName());

        SingleParameter radp = factory.createSingleParameter();
        radp.setName("radp");
        radp.setParameterType(ParameterType.REAL);
        parameterList.getParameter().add(radp);
        ParameterReference radpRef = new ParameterReference()
        .withParameterName(radp.getName());

        SingleParameter d_sour = factory.createSingleParameter();
        d_sour.setName("d_sour");
        d_sour.setParameterType(ParameterType.REAL);
        parameterList.getParameter().add(d_sour);
        ParameterReference d_sourRef = new ParameterReference()
        .withParameterName(d_sour.getName());

        SingleParameter srcppLabel = factory.createSingleParameter();
        srcppLabel.setName("srcpp");
        srcppLabel.setParameterType(ParameterType.STRING);
        parameterList.getParameter().add(srcppLabel);
        ParameterReference srcppLabelRef = new ParameterReference()
        .withParameterName(srcppLabel.getName());

        SingleParameter srcppFile = factory.createSingleParameter();
        srcppFile.setName("srcpp_spectrum");
        srcppFile.setParameterType(ParameterType.SPECTRUM);
        parameterList.getParameter().add(srcppFile);
        ParameterReference srcppFileRef = new ParameterReference()
        .withParameterName(srcppFile.getName());

        SingleParameter ieqth = factory.createSingleParameter();
        ieqth.setName("ieqth");
        ieqth.setParameterType(ParameterType.INTEGER);
        parameterList.getParameter().add(ieqth);
        ParameterReference ieqthRef = new ParameterReference()
        .withParameterName(ieqth.getName());

        SingleParameter ifisob = factory.createSingleParameter();
        ifisob.setName("ifisob");
        ifisob.setParameterType(ParameterType.INTEGER);
        parameterList.getParameter().add(ifisob);
        ParameterReference ifisobRef = new ParameterReference()
        .withParameterName(ifisob.getName());

        SingleParameter densh = factory.createSingleParameter();
        densh.setName("densh");
        densh.setParameterType(ParameterType.REAL);
        parameterList.getParameter().add(densh);
        ParameterReference denshRef = new ParameterReference()
        .withParameterName(densh.getName());

        SingleParameter tgaz = factory.createSingleParameter();
        tgaz.setName("tgaz");
        tgaz.setParameterType(ParameterType.REAL);
        parameterList.getParameter().add(tgaz);
        ParameterReference tgazRef = new ParameterReference()
        .withParameterName(tgaz.getName());

        SingleParameter fprofil = factory.createSingleParameter()
                .withName("fprofil").withParameterType(ParameterType.BINARY);
        parameterList.getParameter().add(fprofil);
        ParameterReference fprofilRef = new ParameterReference()
        .withParameterName(fprofil.getName());

        SingleParameter presse = factory.createSingleParameter()
                .withName("presse").withParameterType(ParameterType.REAL);
        parameterList.getParameter().add(F_ISRF);
        ParameterReference pressRef = factory.createParameterReference()
                .withParameterName(presse.getName());

        // Adding the list of params
        service.setParameters(parameterList);

        // Creating parameter groups

        // A first group

        ParameterGroup rayAndGeom = new ParameterGroup()
        .withName("RadiationFieldAndGeometry");
        rayAndGeom.getParameterRef().add(F_ISRFREF);
        rayAndGeom.getParameterRef().add(radmRef);
        rayAndGeom.getParameterRef().add(radpRef);
        rayAndGeom.getParameterRef().add(d_sourRef);
        rayAndGeom.getParameterRef().add(srcppLabelRef);
        rayAndGeom.getParameterRef().add(srcppFileRef);

        AlwaysConditionalStatement always1 = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(F_ISRFREF))
                .withConditionType(
                        new BelongToSet().withValue(mkconst(1),mkconst(2)))));

        AlwaysConditionalStatement always2 = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(radmRef))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(true)
                                .withValue(mkconst(0))).withSup(
                                        new ValueSmallerThan()
                                        .withReached(true).withValue(
                                                mkconst(1e8))))));

        AlwaysConditionalStatement always3 = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(radpRef))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(true)
                                .withValue(mkconst(0))).withSup(
                                        new ValueSmallerThan()
                                        .withReached(true).withValue(mkconst(
                                                1E8))))));

        IfThenConditionalStatement dsourCondition1 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(d_sourRef))
                .withConditionType(new BelongToSet().withValue(mkconst(0)))))
                .withThen(
                        new Then().withCriterion(new Criterion()
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(srcppLabelRef))
                                .withConditionType(new IsNull())
                                .withLogicalConnector(
                                        new And()
                                        .withCriterion(new Criterion()
                                        .withExpression(
                                                new AtomicParameterExpression()
                                                .withParameterRef(srcppFileRef))
                                                .withConditionType(
                                                        new IsNull())))));

        AlwaysConditionalStatement always4 = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(srcppLabelRef))
                .withConditionType(
                        new BelongToSet().withValue(mkconst("spectro1"))
                        .withValue(mkconst("spectro2"))
                        .withValue(mkconst("spectroN")))
                        .withLogicalConnector(
                                new Or().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(srcppLabelRef))
                                        .withConditionType(new IsNull())))));

        IfThenConditionalStatement dsourCondition2 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(d_sourRef))
                .withConditionType(
                        new ValueDifferentFrom().withValue(mkconst(0)))
                        .withLogicalConnector(
                                new And()
                                .withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(srcppLabelRef))
                                        .withConditionType(
                                                new BelongToSet()
                                                .withValue(mkconst(
                                                        "spectro1"))
                                                        .withValue(mkconst(
                                                                "spectro2"))
                                                                .withValue(mkconst(
                                                                        "spectroN")))))))
                                                                        .withThen(
                                                                                new Then().withCriterion(new Criterion()
                                                                                .withExpression(
                                                                                        new AtomicParameterExpression()
                                                                                        .withParameterRef(srcppFileRef))
                                                                                        .withConditionType(new IsNull())));

        ConstraintOnGroup geomConst = new ConstraintOnGroup();
        geomConst.getConditionalStatement().add(always1);
        geomConst.getConditionalStatement().add(always2);
        geomConst.getConditionalStatement().add(always3);
        geomConst.getConditionalStatement().add(always4);
        geomConst.getConditionalStatement().add(dsourCondition1);
        geomConst.getConditionalStatement().add(dsourCondition2);

        rayAndGeom.setConstraintOnGroup(geomConst);

        // A second group
        ParameterGroup EOS = new ParameterGroup().withName("EquationOfState");
        EOS.getParameterRef().add(ieqthRef);
        EOS.getParameterRef().add(ifisobRef);
        EOS.getParameterRef().add(denshRef);
        EOS.getParameterRef().add(tgazRef);
        EOS.getParameterRef().add(fprofilRef);
        EOS.getParameterRef().add(pressRef);

        AlwaysConditionalStatement always1eos = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ieqthRef))
                .withConditionType(
                        new BelongToSet().withValue(mkconst(0)).withValue(mkconst(1)))));

        AlwaysConditionalStatement always2eos = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(
                        new BelongToSet().withValue(mkconst(0)).withValue(mkconst(1))
                        .withValue(mkconst(2)))));

        IfThenConditionalStatement ifthenEOS1 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ieqthRef)).withConditionType(
                        new BelongToSet().withValue(mkconst(0)))))
                        .withThen(
                                new Then().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(tgazRef))
                                        .withConditionType(
                                                new ValueInRange().withInf(
                                                        new ValueLargerThan()
                                                        .withReached(true)
                                                        .withValue(mkconst(2.7)))
                                                        .withSup(
                                                                new ValueSmallerThan()
                                                                .withReached(
                                                                        true)
                                                                        .withValue(mkconst(
                                                                                1E4))))
                                                                                .withLogicalConnector(
                                                                                        new And()
                                                                                        .withCriterion(new Criterion()
                                                                                        .withExpression(
                                                                                                new AtomicParameterExpression()
                                                                                                .withParameterRef(ifisobRef))
                                                                                                .withConditionType(
                                                                                                        new BelongToSet()
                                                                                                        .withValue(mkconst(1)))))));

        IfThenConditionalStatement ifthenEOS2 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ieqthRef)).withConditionType(
                        new ValueDifferentFrom().withValue(mkconst(0))))).withThen(
                                new Then().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(tgazRef))
                                        .withConditionType(new IsNull())));

        IfThenConditionalStatement ifthenEOS3 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(new BelongToSet().withValue(mkconst(0)))))
                .withThen(
                        new Then().withCriterion(new Criterion()
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(denshRef))
                                .withConditionType(
                                        new ValueInRange()
                                        .withInf(
                                                new ValueLargerThan()
                                                .withReached(
                                                        true)
                                                        .withValue(mkconst(1)))
                                                        .withSup(
                                                                new ValueSmallerThan()
                                                                .withReached(
                                                                        true)
                                                                        .withValue(mkconst(1e15)
                                                                                )))));

        IfThenConditionalStatement ifthenEOS04 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(
                        new ValueDifferentFrom().withValue(mkconst(0)))))
                        .withThen(
                                new Then().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(denshRef))
                                        .withConditionType(new IsNull())));

        IfThenConditionalStatement ifthenEOS05 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(
                        new ValueDifferentFrom().withValue(mkconst(1)))))
                        .withThen(
                                new Then().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(fprofilRef))
                                        .withConditionType(new IsNull())));

        IfThenConditionalStatement ifthenEOS06 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(new BelongToSet().withValue(mkconst(2)))))
                .withThen(
                        new Then().withCriterion(new Criterion()
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(pressRef))
                                .withConditionType(
                                        new ValueInRange()
                                        .withInf(
                                                new ValueLargerThan()
                                                .withReached(
                                                        true)
                                                        .withValue(mkconst(1e1)
                                                                ))
                                                                .withSup(
                                                                        new ValueSmallerThan()
                                                                        .withReached(
                                                                                true)
                                                                                .withValue(mkconst(1e10)
                                                                                        )))));

        IfThenConditionalStatement ifthenEOS07 = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(new Criterion().withExpression(
                new AtomicParameterExpression()
                .withParameterRef(ifisobRef))
                .withConditionType(
                        new ValueDifferentFrom().withValue(mkconst(2)))))
                        .withThen(
                                new Then().withCriterion(new Criterion()
                                .withExpression(
                                        new AtomicParameterExpression()
                                        .withParameterRef(pressRef))
                                        .withConditionType(new IsNull())));

        ConstraintOnGroup eosConst = new ConstraintOnGroup();
        eosConst.getConditionalStatement().add(always1eos);
        eosConst.getConditionalStatement().add(always2eos);
        eosConst.getConditionalStatement().add(ifthenEOS1);
        eosConst.getConditionalStatement().add(ifthenEOS2);
        eosConst.getConditionalStatement().add(ifthenEOS3);
        eosConst.getConditionalStatement().add(ifthenEOS04);
        eosConst.getConditionalStatement().add(ifthenEOS05);
        eosConst.getConditionalStatement().add(ifthenEOS06);
        eosConst.getConditionalStatement().add(ifthenEOS07);

        EOS.setConstraintOnGroup(eosConst);

        // Creating input and output parameter group
        ParameterGroup inputsPG = factory.createParameterGroup();
        ParameterGroup outputsPG = factory.createParameterGroup();

        inputsPG.getParameterGroup().add(rayAndGeom);
        inputsPG.getParameterGroup().add(EOS);

        // Last update on the UWS service
        service.setInputs(inputsPG);
        service.setOutputs(outputsPG);
        return service;
    }
}
