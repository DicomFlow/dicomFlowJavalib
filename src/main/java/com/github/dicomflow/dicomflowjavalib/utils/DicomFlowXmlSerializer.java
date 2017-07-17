package com.github.dicomflow.dicomflowjavalib.utils;


import com.github.dicomflow.dicomflowjavalib.services.Service;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateConfirm;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateRequest;
import com.github.dicomflow.dicomflowjavalib.services.certificate.CertificateResult;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestPut;
import com.github.dicomflow.dicomflowjavalib.services.request.RequestResult;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ricardobarbosa on 20/06/17.
 */

public class DicomFlowXmlSerializer {

    private static DicomFlowXmlSerializer instance;

    private DicomFlowXmlSerializer() {
    }

    public static DicomFlowXmlSerializer getInstance() {
        if (instance == null) {
            instance = new DicomFlowXmlSerializer();
        }
        return instance;
    }

    public Service deserialize(String filepath) throws Exception {
        Serializer serializer = new Persister();
        File file = new File(filepath);
        return serializer.read(getXmlClass(filepath), file);
    }

    public Class<? extends Service> getXmlClass(String filepath) throws IOException {

        FileReader reader = new FileReader(filepath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String firstLine = bufferedReader.readLine().toLowerCase();

        if (firstLine.startsWith("<?xml")) {
            firstLine = bufferedReader.readLine().toLowerCase();
        }

        if (firstLine.contains("certificate")) {

            if (firstLine.contains("request")) {
                return CertificateRequest.class;
            } else if (firstLine.contains("result")) {
                return CertificateResult.class;
            } else if (firstLine.contains("confirm")) {
                return CertificateConfirm.class;
            }

        } else if (firstLine.contains("storage")) {

        } else if (firstLine.contains("find")) {

        } else if (firstLine.contains("sharing")) {

        } else if (firstLine.contains("request")) {

            if (firstLine.contains("put")) {
                return RequestPut.class;
            } else if (firstLine.contains("result")) {
                return RequestResult.class;
            }

        } else if (firstLine.contains("discovery")) {

        }

        return null;
    }

    /**
     * Para serilizar o objeto do tipo {@link Service} basta passá-lo como parametro, junto com o
     * diretorio onde deseja que o arquivo seja criado. Se a serializacao ocorrer com sucesso, o caminho
     * absoluto do arquivo é retornado.
     * O nome do arquivo é criado no padrao nome do servico underline nome da acao: servicename_action.xml.
     * Tudo em lowercase. Exemplo: request_put.xml, certificate_confirm.xml.
     *
     * @param service o servico que deseja ser serializado.
     * @param root o diretorio onde o arquivos xml da serializacao será criado.
     * @return O campinho absoluto do arquivo sera retornado.
     * @throws Exception,
     */
    public String serialize(Service service, File root) throws Exception {

        Serializer serializer = new Persister();

        root.mkdirs();

        File xmlFile = new File(root, String.format("%s_%s.xml", service.name.toLowerCase(), service.action.toLowerCase()));
        if (xmlFile.exists()) xmlFile.delete();

        xmlFile.createNewFile();

        FileWriter writer = new FileWriter(xmlFile);
        serializer.write(service, writer);
        writer.flush();
        writer.close();

        //TODO NAO REMOVER ISSO ATE O FINAL DO PROJETO PODE SER UTIL
        FileReader reader = new FileReader(xmlFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String sCurrentLine = null;
        StringBuilder conteudo = new StringBuilder("");
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            conteudo.append(sCurrentLine);
            conteudo.append("\n");
            conteudo.append("\t");
            if (sCurrentLine.startsWith("<\\")) ;
            conteudo.deleteCharAt(conteudo.length() - 1);
        }
        reader.close();
        bufferedReader.close();

        return xmlFile.getAbsolutePath();
    }

}
