package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */
@Root
public class Domain extends ValueObject {

    public Domain(@Attribute(name = "value") String value) {
        super(value);
    }

    public Domain(Map<String, Object> params) {
        super(params);
    }

    public Map<String, Object> toMap() {
        return super.toMap();
    }
}
