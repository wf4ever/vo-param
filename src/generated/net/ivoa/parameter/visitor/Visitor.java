//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 11:30:13 AM CET 
//


package net.ivoa.parameter.visitor;

import net.ivoa.parameter.model.AbstractCondition;
import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.And;
import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConditionalClause;
import net.ivoa.parameter.model.ConditionalStatement;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.Description;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.Function;
import net.ivoa.parameter.model.FunctionExpression;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.IsInteger;
import net.ivoa.parameter.model.IsNull;
import net.ivoa.parameter.model.IsRational;
import net.ivoa.parameter.model.IsReal;
import net.ivoa.parameter.model.LogicalConnector;
import net.ivoa.parameter.model.MinMaxArgument;
import net.ivoa.parameter.model.MinMaxFunctionExpression;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.Or;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.ParenthesisContent;
import net.ivoa.parameter.model.ParenthesisCriterion;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueDifferentFrom;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;
import net.ivoa.xml.pdlserver.v0.ClientServerCommunication;
import net.ivoa.xml.pdlserver.v0.Error;
import net.ivoa.xml.pdlserver.v0.Job;
import net.ivoa.xml.pdlserver.v0.PDLService;
import net.ivoa.xml.pdlserver.v0.Parameter;
import net.ivoa.xml.pdlserver.v0.Result;
import net.ivoa.xml.pdlserver.v0.User;

public interface Visitor {


    public void visit(AbstractCondition aBean);

    public void visit(AbstractCriterion aBean);

    public void visit(Always aBean);

    public void visit(AlwaysConditionalStatement aBean);

    public void visit(And aBean);

    public void visit(AtomicConstantExpression aBean);

    public void visit(AtomicParameterExpression aBean);

    public void visit(BelongToSet aBean);

    public void visit(ConditionalClause aBean);

    public void visit(ConditionalStatement aBean);

    public void visit(ConstraintOnGroup aBean);

    public void visit(Criterion aBean);

    public void visit(DefaultValue aBean);

    public void visit(Description aBean);

    public void visit(Expression aBean);

    public void visit(Function aBean);

    public void visit(FunctionExpression aBean);

    public void visit(If aBean);

    public void visit(IfThenConditionalStatement aBean);

    public void visit(IsInteger aBean);

    public void visit(IsNull aBean);

    public void visit(IsRational aBean);

    public void visit(IsReal aBean);

    public void visit(LogicalConnector aBean);

    public void visit(MinMaxArgument aBean);

    public void visit(MinMaxFunctionExpression aBean);

    public void visit(Operation aBean);

    public void visit(Or aBean);

    public void visit(ParameterGroup aBean);

    public void visit(ParameterReference aBean);

    public void visit(Parameters aBean);

    public void visit(ParenthesisContent aBean);

    public void visit(ParenthesisCriterion aBean);

    public void visit(Service aBean);

    public void visit(SingleParameter aBean);

    public void visit(Then aBean);

    public void visit(ValueDifferentFrom aBean);

    public void visit(ValueInRange aBean);

    public void visit(ValueLargerThan aBean);

    public void visit(ValueSmallerThan aBean);

    public void visit(ClientServerCommunication aBean);

    public void visit(Error aBean);

    public void visit(Job aBean);

    public void visit(PDLService aBean);

    public void visit(Parameter aBean);

    public void visit(Result aBean);

    public void visit(User aBean);

}
