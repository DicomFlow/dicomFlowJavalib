package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Root;

/**
 * Created by ricardobarbosa on 21/06/17.
 */
@Root
public class Mail extends ValueObject{
    public Mail(String value) {
        super(value);
    }
}
