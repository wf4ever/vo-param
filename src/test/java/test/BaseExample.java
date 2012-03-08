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

package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.ObjectFactory;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;

/**
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 8 Mar 2012
 * @version $Revision$ $date$
 */
public abstract class BaseExample {

     protected final ObjectFactory factory;

    /**
     * 
     */
    public BaseExample() {
       factory = new ObjectFactory();
    }

    protected abstract Service buildService();

    
    public void marshall() throws JAXBException, PropertyException,
            FileNotFoundException {
                JAXBContext jaxbContext = JAXBContext
                        .newInstance("net.ivoa.parameter.model");
            
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(
                        true));
                marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                        "http://www.ivoa.net/xml/Parameter/v0.1 PDL-V1.0.xsd");
            
                Service service = buildService();
            
                marshaller.marshal(service, new FileOutputStream(
                        "PDL-Description.xml"));
            }
    
    
    protected  ParameterReference mkRef(SingleParameter par) {
        ParameterReference ref = new ParameterReference();
        ref.setParameterName(par.getName());
        return ref ;
    }

}


/*
 * $Log$
 */
