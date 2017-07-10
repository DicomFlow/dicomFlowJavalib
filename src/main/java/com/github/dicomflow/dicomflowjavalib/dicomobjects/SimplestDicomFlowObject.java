package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */

public abstract class SimplestDicomFlowObject implements IDicomFlowObjects {

    public SimplestDicomFlowObject() {}

    public SimplestDicomFlowObject(Map<String, Object> params) throws DicomFlowObjectsParamMissingException {
        verifyParams(params);
    }
}
