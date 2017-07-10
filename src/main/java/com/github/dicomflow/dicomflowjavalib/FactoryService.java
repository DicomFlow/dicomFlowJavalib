package com.github.dicomflow.dicomflowjavalib;

import com.github.dicomflow.dicomflowjavalib.services.Service;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateConfirm;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateRequest;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateResult;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestPut;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestResult;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * Created by netolucena on 26/06/2017.
 */

public class FactoryService {

    private static FactoryService instance;

    private FactoryService(){}

    public static FactoryService getInstance() {
        if (instance == null) instance = new FactoryService();
        return instance;
    }

    public Service getService(Class<? extends Service> serviceType, Map<String, Object> params) throws ServiceObjectException{

        //prepare params
        prepareParams(serviceType,params);

        try {
            Constructor cons = serviceType.getConstructor(Map.class);
            return (Service) cons.newInstance(params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ServiceObjectException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServiceObjectException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ServiceObjectException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new ServiceObjectException();
        }

    }

    public static final String VERSION = "1.0";
    private void prepareParams(Class<? extends Service> serviceType, Map<String, Object> params) {
        params.put("version", VERSION);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ssZ");
        Date date = new Date();
        params.put("timestamp",dateFormat.format(date));
        params.put("messageID",UUID.randomUUID().toString());
        params.put("timeout",String.valueOf(date.getTime()));

        if (serviceType.equals(CertificateRequest.class)) {
            params.put("name", "CERTIFICATE");
            params.put("action", "REQUEST");
        }
        else
        if (serviceType.equals(CertificateResult.class)) {
            params.put("name", "CERTIFICATE");
            params.put("action", "RESULT");
        }
        else
        if (serviceType.equals(CertificateConfirm.class)) {
            params.put("name", "CERTIFICATE");
            params.put("action", "CONFIRM");
        }
        else
        if (serviceType.equals(RequestPut.class)) {
            params.put("name", "REQUEST");
            params.put("action", "PUT");
        }
        else
        if (serviceType.equals(RequestResult.class)) {
            params.put("name", "REQUEST");
            params.put("action", "RESULT");
        }
    }

    // TODO: 10/07/17 analisar melhor a necessidade dessa classe
    public static class ServiceObjectException extends Throwable {
    }
}
