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

    // TODO: 10/07/17 analisar melhor a necessidade dessa classe
    public static class ServiceObjectException extends Throwable {
    }
}
