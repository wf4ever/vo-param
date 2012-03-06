/*
 * $Id$
 * 
 * Created on 22 Nov 2011 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2011 Manchester University. All rights reserved.
 *
 * This software is published under the terms of the Academic 
 * Free License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package net.ivoa.descriptor;

import java.io.PrintStream;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.visitor.BaseVisitor;

/**
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 22 Nov 2011
 * @version $Revision$ $date$
 */
public class ScriptVisitor extends BaseVisitor {

    
    PrintStream out = System.out;
    /* (non-Javadoc)
     * @see net.ivoa.parameter.visitor.BaseVisitor#visit(net.ivoa.parameter.model.SingleParameter)
     */
    @Override
    public void visit(SingleParameter aBean) {
        out.println("parameter name=\""+aBean.getName()+"\"");
    }

}


/*
 * $Log$
 */
