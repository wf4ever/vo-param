package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.And;
import net.ivoa.parameter.model.AtomicConstantExpression;
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
import net.ivoa.parameter.model.ObjectFactory;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.ParenthesisContent;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class ExampleInteropPune02 {

    /**
     * @param args
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws JAXBException,
    FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext
                .newInstance("net.ivoa.parameter.model");

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(
                true));
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                "http://www.ivoa.net/xml/Parameter/v0.1 UWS2-V1.1.xsd");
        // Creating the object factory factory
        ObjectFactory factory = new ObjectFactory();

        // Creating the root element of the schema : the service
        Service service = factory.createService()
                .withServiceId("CMZ_ex01")
                .withServiceName("MyFirstExempleService");
        service.setDescription("This is just an example for showing capabilities of our approach");

        // Creating all the parameters
        SingleParameter p1 = factory.createSingleParameter();
        p1.setName("p1");
        p1.setParameterType(ParameterType.REAL);
        p1.setPrecision(mkconst("0.0001"));
        p1.setUnit("None");
        ParameterReference p1ref = new ParameterReference()
        .withParameterName(p1.getName());

        SingleParameter p2 = factory.createSingleParameter();
        p2.setName("p2");
        p2.setParameterType(ParameterType.INTEGER);
        p2.setPrecision(mkconst("0"));
        p2.setUnit("None");
        ParameterReference p2ref = new ParameterReference()
        .withParameterName(p2.getName());

        SingleParameter p3 = factory.createSingleParameter();
        p3.setName("p3");
        p3.setParameterType(ParameterType.REAL);
        p3.setPrecision(mkconst("0.0001"));
        p3.setUnit("None");
        ParameterReference p3ref = new ParameterReference()
        .withParameterName(p3.getName());

        SingleParameter p4x = factory.createSingleParameter();
        p4x.setName("p4x");
        p4x.setParameterType(ParameterType.REAL);
        p4x.setPrecision(mkconst("0.0001"));
        p4x.setUnit("None");
        ParameterReference p4xref = new ParameterReference()
        .withParameterName(p4x.getName());

        SingleParameter p4y = factory.createSingleParameter();
        p4y.setName("p4y");
        p4y.setParameterType(ParameterType.REAL);
        p4y.setPrecision(mkconst("0.0001"));
        p4y.setUnit("None");
        ParameterReference p4yref = new ParameterReference()
        .withParameterName(p4y.getName());

        SingleParameter p4z = factory.createSingleParameter();
        p4z.setName("p4z");
        p4z.setParameterType(ParameterType.REAL);
        p4z.setPrecision(mkconst("0.0001"));
        p4z.setUnit("None");
        ParameterReference p4zref = new ParameterReference()
        .withParameterName(p4z.getName());

        SingleParameter p5x = factory.createSingleParameter();
        p5x.setName("p5x");
        p5x.setParameterType(ParameterType.REAL);
        p5x.setPrecision(mkconst("0.0001"));
        p5x.setUnit("None");
        ParameterReference p5xref = new ParameterReference()
        .withParameterName(p5x.getName());
        SingleParameter p5y = factory.createSingleParameter();

        p5y.setName("p5y");
        p5y.setParameterType(ParameterType.REAL);
        p5y.setPrecision(mkconst("0.0001"));
        p5y.setUnit("None");
        ParameterReference p5yref = new ParameterReference()
        .withParameterName(p5y.getName());

        SingleParameter p5z = factory.createSingleParameter();
        p5z.setName("p5z");
        p5z.setParameterType(ParameterType.REAL);
        p5z.setPrecision(mkconst("0.0001"));
        p5z.setUnit("None");
        ParameterReference p5zref = new ParameterReference()
        .withParameterName(p5z.getName());

        // Adding parameters to the list of service params
        Parameters parameterList = factory.createParameters();

        parameterList.getParameter().add(p1);
        parameterList.getParameter().add(p2);
        parameterList.getParameter().add(p3);
        parameterList.getParameter().add(p4x);
        parameterList.getParameter().add(p4y);
        parameterList.getParameter().add(p4z);
        parameterList.getParameter().add(p5x);
        parameterList.getParameter().add(p5y);
        parameterList.getParameter().add(p5z);

        service.setParameters(parameterList);

        // Creating input and output parameter group
        ParameterGroup inputsPG = factory.createParameterGroup();
        ParameterGroup outputsPG = factory.createParameterGroup();

        // defining inputs
        inputsPG.getParameterRef().add(p1ref);
        inputsPG.getParameterRef().add(p2ref);
        inputsPG.getParameterRef().add(p3ref);

        Criterion p1FirstCriterion = new Criterion().withExpression(
                new AtomicParameterExpression().withParameterRef(p1ref))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(false)
                                .withValue(mkconst("0"))).withSup(
                                        new ValueSmallerThan().withReached(true)
                                        .withValue(mkconst("1.5707963"))));

        Criterion p2FirstCriterion = new Criterion().withExpression(
                new AtomicParameterExpression().withParameterRef(p2ref))
                .withConditionType(
                        new BelongToSet().withValue(mkconst("2")).withValue(mkconst("4"))
                        .withValue(mkconst("6")));

        Criterion p3FirstCriterion = new Criterion().withExpression(
                new AtomicParameterExpression().withParameterRef(p3ref))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(true)
                                .withValue(mkconst("-1"))).withSup(
                                        new ValueSmallerThan().withReached(true)
                                        .withValue(mkconst("1"))));
        // Defining the complicated expression
        FunctionExpression sinP1 = new FunctionExpression()
        .withFunction(
                new Function().withFunctionName(FunctionType.SIN).withExpression(
                        new AtomicParameterExpression()
                        .withParameterRef(p1ref)))
                        .withPower(new AtomicParameterExpression().withParameterRef(p2ref)
                                .withOperation(
                                        new Operation(new AtomicParameterExpression()
                                        .withParameterRef(p3ref), "MINUS")));

        FunctionExpression finalExression = new FunctionExpression()
        .withFunction(
                new Function().withFunctionName(FunctionType.ABS).withExpression(
                        sinP1)).withPower(mkconst("0.5"));

        Criterion functionCriterion = new Criterion().withExpression(
                finalExression).withConditionType(
                        new ValueSmallerThan().withReached(false).withValue(mkconst("1.5")));

        p3FirstCriterion.withLogicalConnector(new And()
        .withCriterion(functionCriterion));
        p2FirstCriterion.withLogicalConnector(new And()
        .withCriterion(p3FirstCriterion));

        IfThenConditionalStatement firstConditionalStat = new IfThenConditionalStatement()
        .withIf(new If(p1FirstCriterion)).withThen(
                new Then().withCriterion(p2FirstCriterion));

        ConstraintOnGroup inpConst = new ConstraintOnGroup();
        inpConst.getConditionalStatement().add(firstConditionalStat);


        Criterion p1SecondCriterion = new Criterion().withExpression(
                new AtomicParameterExpression().withParameterRef(p1ref))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(true)
                                .withValue(mkconst("1.5707963"))).withSup(
                                        new ValueSmallerThan().withReached(true)
                                        .withValue(mkconst("3,14159265"))));

        AtomicParameterExpression p3exp = new AtomicParameterExpression()
        .withParameterRef(p3ref)
        .withOperation(
                new Operation()
                .withOperationType("MINUS")
                .withExpression(
                        new FunctionExpression()
                        .withFunction(new Function()
                        .withFunctionName(FunctionType.LOG)
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(p3ref)))));

        Criterion p3SecondCriterion = new Criterion()
        .withExpression(p3exp)
        .withConditionType(
                new ValueLargerThan().withReached(false).withValue(mkconst("0")));

        Criterion p2SecondCriterion = new Criterion().withExpression(
                new AtomicParameterExpression().withParameterRef(p2ref))
                .withConditionType(
                        new ValueInRange().withInf(
                                new ValueLargerThan().withReached(false)
                                .withValue(mkconst("0"))).withSup(
                                        new ValueSmallerThan().withReached(false)
                                        .withValue(mkconst("10"))));

        Criterion p1p2Criterion = new Criterion()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(p1ref)
                .withOperation(
                        new Operation()
                        .withOperationType("MULTIPLY")
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(p2ref))))
                                .withConditionType(new IsInteger());

        p3SecondCriterion.withLogicalConnector(new And()
        .withCriterion(p1p2Criterion));
        p2SecondCriterion.withLogicalConnector(new And()
        .withCriterion(p3SecondCriterion));

        IfThenConditionalStatement secondCOnditionalStat = new IfThenConditionalStatement()
        .withIf(new If().withCriterion(p1SecondCriterion)).withThen(
                new Then().withCriterion(p2SecondCriterion));

        inpConst.getConditionalStatement().add(secondCOnditionalStat);

        inputsPG.setConstraintOnGroup(inpConst);

        // Working on output

        ParameterGroup p4vector = new ParameterGroup().withName("p4Vector")
                .withParameterRef(p4xref).withParameterRef(p4yref)
                .withParameterRef(p4zref);

        ParameterGroup p5vector = new ParameterGroup().withName("p5Vector")
                .withParameterRef(p5xref).withParameterRef(p5yref)
                .withParameterRef(p5zref);

        outputsPG.withParameterGroup(p4vector).withParameterGroup(p5vector);

        Expression denominator = new ParenthesisContent()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(p4xref)
                .withPower(mkconst("2"))
                .withOperation(
                        new Operation()
                        .withOperationType("PLUS")
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(
                                        p4yref)
                                        .withPower(mkconst("2"))
                                        .withOperation(
                                                new Operation()
                                                .withOperationType(
                                                        "PLUS")
                                                        .withExpression(
                                                                new AtomicParameterExpression()
                                                                .withParameterRef(
                                                                        p4zref)
                                                                        .withPower(mkconst(
                                                                                "2")))))))
                                                                                .withPower(mkconst("0.5"));

        Expression numerator = new ParenthesisContent()
        .withExpression(
                new AtomicParameterExpression()
                .withParameterRef(p5xref)
                .withPower(mkconst("2"))
                .withOperation(
                        new Operation()
                        .withOperationType("PLUS")
                        .withExpression(
                                new AtomicParameterExpression()
                                .withParameterRef(
                                        p5yref)
                                        .withPower(mkconst("2"))
                                        .withOperation(
                                                new Operation()
                                                .withOperationType(
                                                        "PLUS")
                                                        .withExpression(
                                                                new AtomicParameterExpression()
                                                                .withParameterRef(
                                                                        p5zref)
                                                                        .withPower(mkconst(
                                                                                "2")))))))
                                                                                .withPower(mkconst("0.5"));

        Expression fraction = new ParenthesisContent()
        .withExpression(numerator).withOperation(
                new Operation().withOperationType("DIVIDE")
                .withExpression(denominator));

        Expression express = new ParenthesisContent().withExpression(fraction)
                .withOperation(
                        new Operation().withOperationType("MINUS")
                        .withExpression(
                                new AtomicConstantExpression()
                                .withConstant("0.01")));

        Criterion outCriterion = new Criterion().withExpression(express)
                .withConditionType(
                        new ValueSmallerThan().withReached(false)
                        .withValue(mkconst("0")));

        AlwaysConditionalStatement outStat = new AlwaysConditionalStatement()
        .withAlways(new Always().withCriterion(outCriterion));

        ConstraintOnGroup outConstraint = new ConstraintOnGroup();
        outConstraint.getConditionalStatement().add(outStat);

        outputsPG.setConstraintOnGroup(outConstraint);

        // Last update on the UWS service
        service.setInputs(inputsPG);
        service.setOutputs(outputsPG);

        marshaller.marshal(service, new FileOutputStream(
                "ZZZ.xml"));
    }

    private static AtomicConstantExpression mkconst(String exp){
        List<String> sl = new ArrayList<String>();
        sl.add(exp);
        AtomicConstantExpression ace = new AtomicConstantExpression().withConstant(sl);
        ace.setConstatType(ParameterType.REAL);
        return ace ;
    }

}
