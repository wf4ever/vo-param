/*
 * $Id$
 * 
 * Created on 6 Mar 2012 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2012 Manchester University. All rights reserved.
 *
 * This software is published under the terms of the Academic 
 * Free License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package net.ivoa.pdl.interpreter.utilities;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.ParameterType;

/**
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 6 Mar 2012
 * @version $Revision$ $date$
 */
public class ConstantUtils {

    private ConstantUtils(){};
    
    private static <T> AtomicConstantExpression pmkconst(T... values){
        List<String> sl = new ArrayList<String>();
        if(values != null)
            for(T value: values)
                sl.add(value.toString());
        AtomicConstantExpression ace = new AtomicConstantExpression().withConstant(sl);
        return ace;
    }
    public static AtomicConstantExpression mkconst(Double... values){
        AtomicConstantExpression ace = pmkconst(values);
        ace.setConstantType(ParameterType.REAL);
        return ace;
    }
   
     public static AtomicConstantExpression mkconst(Integer... values){
        AtomicConstantExpression ace = pmkconst(values);
        ace.setConstantType(ParameterType.INTEGER);
        return ace;
    }
     
     public static AtomicConstantExpression mkconst(String... values){
         AtomicConstantExpression ace = pmkconst(values);
         ace.setConstantType(ParameterType.STRING);
         return ace;
     }
     
     public static AtomicConstantExpression mkconst(Boolean... values){
         AtomicConstantExpression ace = pmkconst(values);
         ace.setConstantType(ParameterType.BOOLEAN);
         return ace;
     }

     public static  AtomicConstantExpression mktconst(String exp, ParameterType type) {
         List<String> sl = new ArrayList<String>();
         sl.add(exp);
         AtomicConstantExpression ace = new AtomicConstantExpression().withConstant(sl);
         ace.setConstantType(type);
        return ace ;
     }

     public static  AtomicConstantExpression mkVectorConstant(List<String> exp, ParameterType type) {
                 List<String> sl = new ArrayList<String>();
                 for(String temp:exp){
                         sl.add(temp);
                 }
                 AtomicConstantExpression ace = new AtomicConstantExpression().withConstant(sl);
         ace.setConstantType(type);
        return ace ;
         }



}


/*
 * $Log$
 */
