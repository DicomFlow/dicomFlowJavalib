package com.github.dicomflow.dicomflowjavalib;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.SimplestDicomFlowObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FactoryDicomFlowObjects {

	private static FactoryDicomFlowObjects instance;

	private FactoryDicomFlowObjects(){}

	public static FactoryDicomFlowObjects getInstance() {
		if (instance == null) instance = new FactoryDicomFlowObjects();
		return instance;
	}

    public IDicomFlowObjects getDicomFlowObjects(Class<? extends SimplestDicomFlowObject> type, Map<String, Object> params) throws DicomFlowObjectException {
        Constructor cons = null;
        try {
            cons = type.getConstructor(Map.class);
            return (SimplestDicomFlowObject) cons.newInstance(params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new DicomFlowObjectException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new DicomFlowObjectException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new DicomFlowObjectException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DicomFlowObjectException();
        }
	}

    public class DicomFlowObjectException extends Throwable {
    }
}