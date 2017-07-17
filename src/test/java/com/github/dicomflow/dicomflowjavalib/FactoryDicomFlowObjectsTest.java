package com.github.dicomflow.dicomflowjavalib;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by ricardobarbosa on 10/07/17.
 */
public class FactoryDicomFlowObjectsTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getInstance() throws Exception {
        FactoryDicomFlowObjects o1 = FactoryDicomFlowObjects.getInstance();
        FactoryDicomFlowObjects o2 = FactoryDicomFlowObjects.getInstance();
        assertThat(o1, CoreMatchers.is(o2));
    }

    @Test
    public void getDicomFlowObjects() throws Exception, FactoryDicomFlowObjects.DicomFlowObjectException {
//        FactoryDicomFlowObjects factory = FactoryDicomFlowObjects.getInstance();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("value", "xxxx");
//        IDicomFlowObjects o = factory.getDicomFlowObjects(Domain.class, params);
//
//        assertTrue(o.toMap().equals(params));
//        assertTrue(o instanceof Domain);
//        assertTrue(((Domain) o).value.equals("xxxx"));
    }

}