/*
 * $Id$
 * 
 * Created on 8 Mar 2012 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2012 Manchester University. All rights reserved.
 *
 * This software is published under the terms of the Academic 
 * Free License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package net.ivoa.pdl.interpreter.conditionalStatement;

import net.ivoa.parameter.model.WhenConditionalStatement;
import net.ivoa.pdl.interpreter.criterion.CriterionInterpreterFactory;
import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

/**
 * A statement interpreter for When conditions.
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 8 Mar 2012
 * @version $Revision$ $date$
 */
public class WhenConditionalStatementInterpreter extends
ConditionalStatementInterpreter {

    private WhenConditionalStatement statement;

    /**
     * @param statement 
     * 
     */
    WhenConditionalStatementInterpreter(WhenConditionalStatement statement) {
        this.statement = statement;
    }

    /* (non-Javadoc)
     * @see net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreter#isValidStatement()
     */
    @Override
    public boolean isValidStatement() throws InvalidExpression,
    InvalidParameterException, InvalidCondition, InvalidCriterion {
        // should not be called
        throw new UnsupportedOperationException(
                "WhenConditionalStatementInterpreter.isValidStatement() not implemented - should not be called?");
    }

    /* (non-Javadoc)
     * @see net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreter#isStatementSwitched()
     */
    @Override
    public boolean isStatementSwitched() throws InvalidExpression,
    InvalidParameterException, InvalidCondition, InvalidCriterion {
        try {
            return CriterionInterpreterFactory
                    .getInstance()
                    .buildCriterrionInterpreter(
                            this.statement.getWhen().getCriterion())
                            .isCriterionSatisfied();
        } catch (Exception e) {
            // If one cannot evaluate the condition, this means 
            // that the statement is not swithced
            e.printStackTrace();
            return false;
        }
    }

}


/*
 * $Log$
 */
