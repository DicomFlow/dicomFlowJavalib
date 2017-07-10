package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */
@Root
public class Url extends SimplestDicomFlowObject{
    @Attribute(name = "value") public final String value;
    @Element public final Credentials credentials;
    @ElementList(name = "patients", inline = true) public final List<Patient> patients;

    public Url(@Attribute(name = "value") String value,
               @Element(name="credentials") Credentials credentials,
               @ElementList(name="patients", inline = true) List<Patient> patients) {
        super();
        this.value = value;
        this.credentials = credentials;
        this.patients = patients;
    }

    public Url(Map<String, Object> params) {
        super(params);
        this.value = (String) params.get("value");
        this.credentials = new Credentials((Map<String, Object>) params.get("credentials"));

        List<Map<String, Object>> paramsClientes = (List<Map<String, Object>>) params.get("patients");
        this.patients =  new ArrayList<>();
        for (Map<String, Object> key : paramsClientes) {
            this.patients.add(new Patient(key));
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("value", value);
        map.put("credentials", credentials.toMap());

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Patient p : patients) mapList.add(p.toMap());
        map.put("patients", mapList);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException {
        if (!params.containsKey("value"))
            throw new DicomFlowObjectsParamMissingException("Param value is missing for Url.");
        // TODO: 10/07/17 colocar um warning aqui sobre o credentials

        if ( !params.containsKey("patients"))
            throw new DicomFlowObjectsParamMissingException("Param patients is missing for Url.");
        if ( params.get("patients") == null)
            throw new ValueForParamShouldNotBeNullException("Value patients is null and has to be a list not empty.");
        if ( !(params.get("patients") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Value patients must be a list type.");
        if ( ( (List)params.get("patients")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Value patients must be a not empty list.");

    }
}
