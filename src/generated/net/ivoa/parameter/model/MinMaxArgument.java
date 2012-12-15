//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 11:30:13 AM CET 
//


package net.ivoa.parameter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for MinMaxArgument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinMaxArgument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameterRef" type="{http://www.ivoa.net/xml/Parameter/v0.1}ParameterReference" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="min"/>
 *             &lt;enumeration value="MAX"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinMaxArgument", propOrder = {
    "parameterRef"
})
public class MinMaxArgument implements Visitable
{

    @XmlElement(required = true)
    protected List<ParameterReference> parameterRef;
    @XmlAttribute(name = "type")
    protected String type;

    /**
     * Default no-arg constructor
     * 
     */
    public MinMaxArgument() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public MinMaxArgument(final List<ParameterReference> parameterRef, final String type) {
        this.parameterRef = parameterRef;
        this.type = type;
    }

    /**
     * Gets the value of the parameterRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterReference }
     * 
     * 
     */
    public List<ParameterReference> getParameterRef() {
        if (parameterRef == null) {
            parameterRef = new ArrayList<ParameterReference>();
        }
        return this.parameterRef;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    public MinMaxArgument withParameterRef(ParameterReference... values) {
        if (values!= null) {
            for (ParameterReference value: values) {
                getParameterRef().add(value);
            }
        }
        return this;
    }

    public MinMaxArgument withParameterRef(Collection<ParameterReference> values) {
        if (values!= null) {
            getParameterRef().addAll(values);
        }
        return this;
    }

    public MinMaxArgument withType(String value) {
        setType(value);
        return this;
    }

}