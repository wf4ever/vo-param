//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.17 at 10:02:33 AM CET 
//


package net.ivoa.parameter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for LogicalConnector complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LogicalConnector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Criterion" type="{http://www.ivoa.net/xml/Parameter/v0.1}AbstractCriterion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogicalConnector", propOrder = {
    "criterion"
})
@XmlSeeAlso({
    Or.class,
    And.class
})
public abstract class LogicalConnector implements Visitable
{

    @XmlElement(name = "Criterion", required = true)
    protected AbstractCriterion criterion;

    /**
     * Default no-arg constructor
     * 
     */
    public LogicalConnector() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public LogicalConnector(final AbstractCriterion criterion) {
        this.criterion = criterion;
    }

    /**
     * Gets the value of the criterion property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractCriterion }
     *     
     */
    public AbstractCriterion getCriterion() {
        return criterion;
    }

    /**
     * Sets the value of the criterion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractCriterion }
     *     
     */
    public void setCriterion(AbstractCriterion value) {
        this.criterion = value;
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    public LogicalConnector withCriterion(AbstractCriterion value) {
        setCriterion(value);
        return this;
    }

}
