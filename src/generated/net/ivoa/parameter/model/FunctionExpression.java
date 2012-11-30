//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 11:30:13 AM CET 
//


package net.ivoa.parameter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for FunctionExpression complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FunctionExpression">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ivoa.net/xml/Parameter/v0.1}Expression">
 *       &lt;sequence>
 *         &lt;element name="Function" type="{http://www.ivoa.net/xml/Parameter/v0.1}Function"/>
 *         &lt;element name="Power" type="{http://www.ivoa.net/xml/Parameter/v0.1}Expression" minOccurs="0"/>
 *         &lt;element name="Operation" type="{http://www.ivoa.net/xml/Parameter/v0.1}Operation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FunctionExpression", propOrder = {
    "function",
    "power",
    "operation"
})
public class FunctionExpression
    extends Expression
    implements Visitable
{

    @XmlElement(name = "Function", required = true)
    protected Function function;
    @XmlElement(name = "Power")
    protected Expression power;
    @XmlElement(name = "Operation")
    protected Operation operation;

    /**
     * Default no-arg constructor
     * 
     */
    public FunctionExpression() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public FunctionExpression(final Function function, final Expression power, final Operation operation) {
        super();
        this.function = function;
        this.power = power;
        this.operation = operation;
    }

    /**
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link Function }
     *     
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link Function }
     *     
     */
    public void setFunction(Function value) {
        this.function = value;
    }

    /**
     * Gets the value of the power property.
     * 
     * @return
     *     possible object is
     *     {@link Expression }
     *     
     */
    public Expression getPower() {
        return power;
    }

    /**
     * Sets the value of the power property.
     * 
     * @param value
     *     allowed object is
     *     {@link Expression }
     *     
     */
    public void setPower(Expression value) {
        this.power = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link Operation }
     *     
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Operation }
     *     
     */
    public void setOperation(Operation value) {
        this.operation = value;
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    public FunctionExpression withFunction(Function value) {
        setFunction(value);
        return this;
    }

    public FunctionExpression withPower(Expression value) {
        setPower(value);
        return this;
    }

    public FunctionExpression withOperation(Operation value) {
        setOperation(value);
        return this;
    }

}
