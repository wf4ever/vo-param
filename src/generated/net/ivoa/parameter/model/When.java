//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.17 at 08:32:19 PM GMT 
//


package net.ivoa.parameter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for When complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="When">
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
@XmlType(name = "When")
public class When
    extends ConditionalClause
    implements Visitable
{


    /**
     * Default no-arg constructor
     * 
     */
    public When() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public When(final AbstractCriterion criterion) {
        super(criterion);
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

    @Override
    public When withCriterion(AbstractCriterion value) {
        setCriterion(value);
        return this;
    }

}
