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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Parameters" type="{http://www.ivoa.net/xml/Parameter/v0.1}Parameters"/>
 *         &lt;element name="Inputs" type="{http://www.ivoa.net/xml/Parameter/v0.1}ParameterGroup"/>
 *         &lt;element name="Outputs" type="{http://www.ivoa.net/xml/Parameter/v0.1}ParameterGroup"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceId",
    "serviceName",
    "description",
    "parameters",
    "inputs",
    "outputs"
})
@XmlRootElement(name = "Service")
public class Service implements Visitable
{

    @XmlElement(name = "ServiceId", required = true)
    protected String serviceId;
    @XmlElement(name = "ServiceName", required = true)
    protected String serviceName;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Parameters", required = true)
    protected Parameters parameters;
    @XmlElement(name = "Inputs", required = true)
    protected ParameterGroup inputs;
    @XmlElement(name = "Outputs", required = true)
    protected ParameterGroup outputs;

    /**
     * Default no-arg constructor
     * 
     */
    public Service() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public Service(final String serviceId, final String serviceName, final String description, final Parameters parameters, final ParameterGroup inputs, final ParameterGroup outputs) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.parameters = parameters;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the inputs property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterGroup }
     *     
     */
    public ParameterGroup getInputs() {
        return inputs;
    }

    /**
     * Sets the value of the inputs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterGroup }
     *     
     */
    public void setInputs(ParameterGroup value) {
        this.inputs = value;
    }

    /**
     * Gets the value of the outputs property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterGroup }
     *     
     */
    public ParameterGroup getOutputs() {
        return outputs;
    }

    /**
     * Sets the value of the outputs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterGroup }
     *     
     */
    public void setOutputs(ParameterGroup value) {
        this.outputs = value;
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    public Service withServiceId(String value) {
        setServiceId(value);
        return this;
    }

    public Service withServiceName(String value) {
        setServiceName(value);
        return this;
    }

    public Service withDescription(String value) {
        setDescription(value);
        return this;
    }

    public Service withParameters(Parameters value) {
        setParameters(value);
        return this;
    }

    public Service withInputs(ParameterGroup value) {
        setInputs(value);
        return this;
    }

    public Service withOutputs(ParameterGroup value) {
        setOutputs(value);
        return this;
    }

}
