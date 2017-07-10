package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Root;

import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */
@Root
public class Port extends ValueObject{
    public Port(String value) {
        super(value);
    }

    public Port(Map<String, Object> params) {
        super(params);
    }

    public Map<String, Object> toMap() {
        return super.toMap();
    }
}
