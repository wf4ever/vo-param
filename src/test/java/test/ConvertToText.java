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

package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.visitor.DepthFirstTraverserImpl;

/**
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 22 Nov 2011
 * @version $Revision$ $date$
 */
public class ConvertToText {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Service service = new Service();
        try {
            // Some technical stuffs for reading the java version of the DM
            JAXBContext jaxbContext = JAXBContext.newInstance("net.ivoa.parameter.model");
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource source = new StreamSource(ConvertToText.class.getResourceAsStream("UWS_TEST.xml"));

            service =  um.unmarshal(source, Service.class).getValue();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        DepthFirstTraverserImpl traverser = new DepthFirstTraverserImpl();


    }

}


/*
 * $Log$
 */
