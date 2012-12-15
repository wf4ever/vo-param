//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 11:30:13 AM CET 
//


package net.ivoa.parameter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.ivoa.parameter.visitor.Visitable;
import net.ivoa.parameter.visitor.Visitor;


/**
 * <p>Java class for Expression complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Expression">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Expression")
@XmlSeeAlso({
    ParenthesisContent.class,
    FunctionExpression.class,
    Function.class,
    MinMaxFunctionExpression.class,
    AtomicParameterExpression.class,
    AtomicConstantExpression.class
})
public abstract class Expression implements Visitable
{


    /**
     * Default no-arg constructor
     * 
     */
    public Expression() {
        super();
    }

    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

}