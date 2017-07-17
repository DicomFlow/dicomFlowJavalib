package com.github.dicomflow.dicomflowjavalib.services;

public interface ServiceIF {


    /**
	 * StogareSave Service Identifier 
	 */
	public static final int STORAGE_SAVE = 1;
	/**
	 * StogareUpdate Service Identifier 
	 */
	public static final int STORAGE_UPDATE = 2;
	/**
	 * StogareDelete Service Identifier 
	 */
	public static final int STORAGE_DELETE = 3;
	/**
	 * StogareResult Service Identifier 
	 */
	public static final int STORAGE_RESULT = 4;	
	/**
	 * DispositionNotification Service Identifier 
	 */
	public static final int DISPOSITION_NOTIFICATION = 5;
	
	/**
	 * CertificateRequest Service Identifier
	 */
	public static final int CERTIFICATE_REQUEST = 6;
	/**
	 * CertificateResult Service Identifier
	 */
	public static final int CERTIFICATE_RESULT = 7;
	
	/**
	 * SharingPut Service Identifier
	 */
	public static final int SHARING_PUT = 8;
	/**
	 * SharingResult Service Identifier
	 */
	public static final int SHARING_RESULT = 9;
	
	/**
	 * RequestPut Service Identifier
	 */
	public static final int REQUEST_PUT = 10;
	/**
	 * RequestResult Service Identifier
	 */
	public static final int REQUEST_RESULT = 11;
	
	/**
	 * DiscoveryVerifyAllServices Service Identifier
	 */
	public static final int DISCOVERY_VERIFY_ALL_SERVICES = 12;
	
	/**
	 * DiscoveryVerifyServices Service Identifier
	 */
	public static final int DISCOVERY_VERIFY_SERVICES = 13;
	
	/**
	 * DiscoveryVerifyResult Service Identifier
	 */
	public static final int DISCOVERY_VERIFY_RESULT = 14;
	
	/**
	 * FindPut Service Identifier
	 */
	public static final int FIND_PUT = 15;
	
	/**
	 * FindResult Service Identifier
	 */
	public static final int FIND_RESULT = 16;
	
	/**
	 * CertificateConfirm Service Identifier
	 */
	public static final int CERTIFICATE_CONFIRM = 17;

	public int getType();
	
	public void setType(int type);
	
	public String getVersion();
	
	
	public void setVersion(String version);
	
	public String getName();
	
	public void setName(String name);
	
	public String getAction();
	
	public void setAction(String action);

	public String getMessageID();

	public void setMessageID(String messageID);

	public String getTimestamp();

	public void setTimestamp(String timestamp);

	public String getTimeout();

	public void setTimeout(String timeout);

}