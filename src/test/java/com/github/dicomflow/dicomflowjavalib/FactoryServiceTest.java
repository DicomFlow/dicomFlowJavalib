package com.github.dicomflow.dicomflowjavalib;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Completed;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Credentials;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Data;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;
import com.github.dicomflow.dicomflowjavalib.services.Service;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateConfirm;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateRequest;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateResult;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestPut;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestResult;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by ricardobarbosa on 10/07/17.
 */
public class FactoryServiceTest {
    FactoryService factory = FactoryService.getInstance();
    Map<String, Object> params = new HashMap<>();

    @Before
    public void setUp() throws RuntimeException, FactoryDicomFlowObjects.DicomFlowObjectException {
        FactoryDicomFlowObjects factory = FactoryDicomFlowObjects.getInstance();

        //Necessario pra o request put
        params.put("from", "rbrico@gmail.com");
        params.put("requestType", RequestPut.RequestType.Report.name());

        Date date = new Date();
        params.put("timeout", String.valueOf(date.getTime()));
        params.put("value", "value");
        params.put("credentials", factory.getDicomFlowObjects(Credentials.class, params).toMap());

        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> series = new ArrayList<>();
        params.put("bodypart", "type...");
        params.put("instances", new Long(1));
        series.add(params);
        series.add(params);
        series.add(params);
        params2.put("series", series);
//        params.put("series", series);


        List<Map<String, Object>> studies = new ArrayList<>();
        params2.put("id", "id");
        params2.put("type", "type...");
        params2.put("description", "description...");
        params.put("description", "description...");
        params2.put("size", new Long(1));
        params.put("size", new Long(1));
        params2.put("datetime", new Date().getTime());
        params.put("datetime", new Date().getTime());
        studies.add(params2);
        studies.add(params2);
        studies.add(params2);
        params.put("studies", studies);

        Map<String, Object> patients = new HashMap<>();
        params.put("id", "id");
        params.put("name", "name");
        params.put("gender", "gender");
        params.put("birthdate", "birthdate");
        patients.put("1", params);
        patients.put("2", params);
        patients.put("3", params);
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
        params.put("domain", "domain...");
        params.put("mail", "mail...");
        params.put("port", "port ...");

        //certificate result
        //certificate confirm
        params.put("credential", "credential ..");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getInstance() throws Exception {
        FactoryService o1 = FactoryService.getInstance();
        FactoryService o2 = FactoryService.getInstance();
        assertThat(o1, CoreMatchers.is(o2));
    }

    private void verify(Service service, Class<? extends Service> type) throws FactoryService.ServiceObjectException {
        assertTrue(service.getClass().equals(type));
        System.out.print(service.toMap());
    }
    @Test
    public void getServiceCertificateRequest() throws FactoryService.ServiceObjectException {
        params.put("type",  ServiceIF.CERTIFICATE_REQUEST);
        Service service = factory.getService(CertificateRequest.class, params);
        verify(service, CertificateRequest.class);
    }

    @Test
    public void getServiceCertificateResult() throws FactoryService.ServiceObjectException {
        params.put("type",  ServiceIF.CERTIFICATE_RESULT);
        Service service = factory.getService(CertificateResult.class, params);
        verify(service, CertificateResult.class);
    }

    @Test
    public void getServiceCertificateConfirm() throws FactoryService.ServiceObjectException {
        params.put("type",  ServiceIF.CERTIFICATE_CONFIRM);
        Service service = factory.getService(CertificateConfirm.class, params);
        verify(service, CertificateConfirm.class);
    }

    @Test
    public void getServiceRequestPut() throws FactoryService.ServiceObjectException {
        params.put("type",  ServiceIF.REQUEST_PUT);
        Service service = factory.getService(RequestPut.class, params);
        verify(service, RequestPut.class);
    }

    @Test
    public void getServiceRequestResult() throws FactoryService.ServiceObjectException {
        params.put("type",  ServiceIF.REQUEST_RESULT);
        Service service = factory.getService(RequestResult.class, params);
        verify(service, RequestResult.class);
    }

    // TODO: 10/07/17 verificar as exceptions quando faltam parametros especificos por servico

}