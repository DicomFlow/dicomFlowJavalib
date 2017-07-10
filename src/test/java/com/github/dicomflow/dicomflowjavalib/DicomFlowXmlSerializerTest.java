package com.github.dicomflow.dicomflowjavalib;

import com.github.dicomflow.dicomflowjavalib.utils.DicomFlowXmlSerializer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

/**
 * Created by ricardobarbosa on 09/07/17.
 */
public class DicomFlowXmlSerializerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    DicomFlowXmlSerializer dicomFlowXmlSerializer;
    File root;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        dicomFlowXmlSerializer = DicomFlowXmlSerializer.getInstance();
        root = new File("/");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }


    /**
     *
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void verificaSeDaErroComValoresNull() throws Exception{
        exception.expect(Exception.class);
        dicomFlowXmlSerializer.serialize(null, root);
        exception.expect(Exception.class);
        dicomFlowXmlSerializer.serialize(null, null);
    }


    @Test
    public void deserialize() throws Exception {
//        dicomFlowXmlSerializer.getXmlClass();
    }

    @Test
    public void getXmlClass() throws Exception {
//        dicomFlowXmlSerializer.
    }

    @Test
    public void serializeRequest() throws Exception {

//        RequestResult requestResult = new RequestResult();
//        dicomFlowXmlSerializer.deserialize(requestResult, root);
    }

    @Test
    public void serialize1() throws Exception {

    }

}