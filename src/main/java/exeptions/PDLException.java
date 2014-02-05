/*
 * $Id$
 * 
 * Created on 13 Mar 2012 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2012 Manchester University. All rights reserved.
 *
 * This software is published under the terms of the Academic 
 * Free License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package exeptions;

/**
 * Base of the PDL exceptions.
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 13 Mar 2012
 * @version $Revision$ $date$
 */
public class PDLException extends Exception {

 
    /**
     * @param message
     */
    public PDLException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public PDLException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}


/*
 * $Log$
 */
