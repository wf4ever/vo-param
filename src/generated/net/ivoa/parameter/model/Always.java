//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 11:30:13 AM CET 
//


package net.ivoa.parameter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for Always complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Always">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ivoa.net/xml/Parameter/v0.1}ConditionalClause">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Always")
public class Always
    extends ConditionalClause
    implements Visitable
{


    /**
     * Default no-arg constructor
     * 
     */
    public Always() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public Always(final AbstractCriterion criterion) {
        super(criterion);
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    @Override
    public Always withCriterion(AbstractCriterion value) {
        setCriterion(value);
        return this;
    }

}