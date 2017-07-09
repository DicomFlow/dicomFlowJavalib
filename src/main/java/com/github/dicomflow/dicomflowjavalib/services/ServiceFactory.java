package com.github.dicomflow.dicomflowjavalib.services;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Completed;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Credentials;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Data;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Patient;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Serie;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Study;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestPut;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by netolucena on 26/06/2017.
 */

public class ServiceFactory {
    public static Service getService(ServiceTypes serviceType, Map<String, Object> params) {
        Service service = null;
        try {
            if (serviceType == ServiceTypes.REQUESTPUT) {


                service = new RequestPut(params);

//                String from = (String)params.get("from");
//                String requestType = (String)params.get("requestType");
//                Url url = (Url) params.get("url");
//                service = new RequestPut(from, requestType, url);
            } else if (serviceType == ServiceTypes.REQUESTRESULT) {

                service = new RequestPut(params);


                ArrayList<Patient> patients = new ArrayList<Patient>();
                ArrayList<Study> studies = new ArrayList<>();
                List<Serie> series = new ArrayList<>();
                series.add(new Serie("1", "", "", 1));
                studies.add(new Study("1", "", "", 1, 1l, series));
                patients.add(new Patient("", "", "", "", studies));
                Credentials credentials = new Credentials("Credential");
                Url url = new Url("www.com...", credentials, patients);
                List<Url> urls = new ArrayList<Url>();
                urls.add(url);

                String from = (String) params.get("from");
                String filename = (String) params.get("filename");
                String bytes = (String) params.get("bytes");
                Data data = new Data(filename, bytes);

                Completed completed = new Completed("1", "OK"); //TODO
                Integer id = 1; //TODO

                //TODO Param do message original
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ssZ");
                Date date = new Date();
                Result result = new Result(completed, data, (String)params.get("originalMessageID"), dateFormat.format(date), urls);

                List resultList = new ArrayList<>();
                resultList.add(result);
                service = new RequestResult(from, resultList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return service;
    }

}
