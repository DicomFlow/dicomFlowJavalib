package com.github.dicomflow.dicomflowjavalib;

import java.util.Map;

/**
 * Created by ricardobarbosa on 09/07/17.
 */

public interface IDicomFlowObjects {
    Map<String, Object> toMap();
    void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException;

    class DicomFlowObjectsParamMissingException extends RuntimeException {
        public DicomFlowObjectsParamMissingException(String m) {
            super(m);
        }
    }

    class ValueForParamShouldNotBeNullException extends RuntimeException {
        public ValueForParamShouldNotBeNullException(String m) {
            super(m);
        }
    }

    class ValueForParamMustBeAListNotEmptyException extends RuntimeException {
        public ValueForParamMustBeAListNotEmptyException(String m) {
            super(m);
        }
    }
}
