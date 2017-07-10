package com.github.dicomflow.dicomflowjavalib;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Completed;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Credentials;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Data;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateConfirm;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateRequest;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateResult;
import com.github.dicomflow.dicomflowjavalib.services.request.Request;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestPut;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestResult;
import com.github.dicomflow.dicomflowjavalib.utils.DicomFlowXmlSerializer;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardobarbosa on 09/07/17.
 */
public class DicomFlowXmlSerializerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    DicomFlowXmlSerializer dicomFlowXmlSerializer = DicomFlowXmlSerializer.getInstance();
    File root;

    FactoryService factory = FactoryService.getInstance();
    Map<String, Object> params = new HashMap<>();

    @Before
    public void setUp() throws RuntimeException, FactoryDicomFlowObjects.DicomFlowObjectException {

        root = new File("temp/");

        FactoryDicomFlowObjects factory = FactoryDicomFlowObjects.getInstance();

        //Necessario pra o request put
        params.put("from", "rbrico@gmail.com");
        params.put("requestType", RequestPut.RequestType.Report.name());

        Date date = new Date();
        params.put("timeout", String.valueOf(date.getTime()));
        params.put("value", "value");
        params.put("credentials", factory.getDicomFlowObjects(Credentials.class, params).toMap());

        List<Map<String, Object>> series = new ArrayList<>();
        params.put("bodypart", "type...");
        params.put("instances", new Long(1));
        series.add(params);
        series.add(params);
        series.add(params);
        params.put("series", series);

        List<Map<String, Object>> studies = new ArrayList<>();
        params.put("type", "type...");
        params.put("description", "description...");
        params.put("size", new Long(1));
        params.put("datetime", new Date().getTime());
        studies.add(params);
        studies.add(params);
        studies.add(params);
        params.put("studies", studies);

        List<Map<String, Object>> patients = new ArrayList<>();
        params.put("id", "id");
        params.put("name", "name");
        params.put("gender", "gender");
        params.put("birthdate", "birthdate");
        patients.add(params);
        patients.add(params);
        patients.add(params);
        params.put("patients", patients);
        params.put("url", factory.getDicomFlowObjects(Url.class, params).toMap());

        //necessarios para o result
        List<Map<String, Object>> results = new ArrayList<>();
        params.put("originalMessageID", "id");
        params.put("timestamp", "name");
        params.put("status", Completed.Status.SUCCESS.name());
        params.put("completedMessage", "completedMessage...");
        params.put("completed", factory.getDicomFlowObjects(Completed.class, params).toMap());
        params.put("encoded", "bytes...");
        params.put("filename", "filename...");
        params.put("data", factory.getDicomFlowObjects(Data.class, params).toMap());
        results.add(params);
        results.add(params);
        results.add(params);
        params.put("results", results);

        List<Map<String, Object>> urls = new ArrayList<>();
        urls.add(params);
        urls.add(params);
        urls.add(params);
        params.put("urls", urls);

        //necessarios para o certificate request
        params.put("domain", factory.getDicomFlowObjects(Domain.class, params).toMap());
        params.put("mail", factory.getDicomFlowObjects(Mail.class, params).toMap());
        params.put("port", factory.getDicomFlowObjects(Port.class, params).toMap());

        //certificate result
        //certificate confirm
        params.put("credential", "credential ..");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
        root.delete();
    }

    @Test
    public void serializeAndDeserializeCertificateRequest() throws Exception, FactoryService.ServiceObjectException {
        CertificateRequest certificateRequestToSerialization = (CertificateRequest) factory.getService(CertificateRequest.class, params);
        String absoluthPath = dicomFlowXmlSerializer.serialize(certificateRequestToSerialization, root);
        assertThat(absoluthPath, CoreMatchers.endsWith("certificate_request.xml"));
        System.out.println(absoluthPath);

        CertificateRequest certificateRequestFromDeserialization = (CertificateRequest) dicomFlowXmlSerializer.deserialize(absoluthPath);
        assertTrue(certificateRequestFromDeserialization.messageID.equals(certificateRequestToSerialization.messageID));
    }

    @Test
    public void serializeAndDeserializeCertificateResult() throws Exception, FactoryService.ServiceObjectException {
        CertificateResult certificateResultToSerialization = (CertificateResult) factory.getService(CertificateResult.class, params);
        String absoluthPath = dicomFlowXmlSerializer.serialize(certificateResultToSerialization, root);
        assertThat(absoluthPath, CoreMatchers.endsWith("certificate_result.xml"));
        System.out.println(absoluthPath);

        CertificateResult certificateResultFromDeserialization = (CertificateResult) dicomFlowXmlSerializer.deserialize(absoluthPath);
        assertTrue(certificateResultFromDeserialization.messageID.equals(certificateResultToSerialization.messageID));
    }

    @Test
    public void serializeAndDeserializeCertificateConfirm() throws Exception, FactoryService.ServiceObjectException {
        CertificateConfirm certificateConfirmToSerialization = (CertificateConfirm) factory.getService(CertificateConfirm.class, params);
        String absoluthPath = dicomFlowXmlSerializer.serialize(certificateConfirmToSerialization, root);
        assertThat(absoluthPath, CoreMatchers.endsWith("certificate_confirm.xml"));
        System.out.println(absoluthPath);

        CertificateConfirm certificateConfirmFromDeserialization = (CertificateConfirm) dicomFlowXmlSerializer.deserialize(absoluthPath);
        assertTrue(certificateConfirmFromDeserialization.messageID.equals(certificateConfirmToSerialization.messageID));
    }

    @Test
    public void serializeAndDeserializeRequestPut() throws Exception, FactoryService.ServiceObjectException {
        RequestPut requestPutToSerialization = (RequestPut) factory.getService(RequestPut.class, params);
        String absoluthPath = dicomFlowXmlSerializer.serialize(requestPutToSerialization, root);
        assertThat(absoluthPath, CoreMatchers.endsWith("request_put.xml"));
        System.out.println(absoluthPath);

        RequestPut requestPutFromDeserialization = (RequestPut) dicomFlowXmlSerializer.deserialize(absoluthPath);
        assertTrue(requestPutFromDeserialization.messageID.equals(requestPutToSerialization.messageID));
    }

    @Test
    public void serializeAndDeserializeRequestResult() throws Exception, FactoryService.ServiceObjectException {
        RequestResult requestResultToSerialization = (RequestResult) factory.getService(RequestResult.class, params);
        String absoluthPath = dicomFlowXmlSerializer.serialize(requestResultToSerialization, root);
        assertThat(absoluthPath, CoreMatchers.endsWith("request_result.xml"));
        System.out.println(absoluthPath);

        RequestResult requestResultFromDeserialization = (RequestResult) dicomFlowXmlSerializer.deserialize(absoluthPath);
        assertTrue(requestResultFromDeserialization.messageID.equals(requestResultToSerialization.messageID));
    }

//    /**
//     *
//     * @throws Exception
//     */
//    @Test(expected = Exception.class)
//    public void verificaSeDaErroComValoresNull() throws Exception{
//        exception.expect(Exception.class);
//        dicomFlowXmlSerializer.serialize(null, root);
//        exception.expect(Exception.class);
//        dicomFlowXmlSerializer.serialize(null, null);
//    }


    @Test
    public void getXmlClass() throws Exception {
//        dicomFlowXmlSerializer.
    }




}