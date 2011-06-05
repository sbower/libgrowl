/*
 * Copyright Shawn bower
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.libgrowl.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenericResponse implements IResponse {
	private final String ORIGIN_MACHINE_NAME = "Origin-Machine-Name";
	private final String ORIGIN_SOFTWARE_NAME = "Origin-Software-Name";
	private final String ORIGIN_SOFTWARE_VERSION = "Origin-Software-Version";
	private final String ORIGIN_PLATFORM_NAME = "Origin-Platform-Name";
	private final String ORIGIN_PLATFORM_VERSION = "Origin-Platform-Version";
	
	private String originalResponse;
	
	private String responseAction;
	private int status;
	private String originMachineName;
	private String originSoftwareName;
	private String originSoftwareVersion;
	private String originPlatformName;
	private String originPlatformVersion;
	private Map<String, String> customHeaders;
	
	public GenericResponse() {
		setStatus(ERROR);
	}
	
	public GenericResponse(String responseString) {
		originalResponse = responseString;
		customHeaders = new HashMap<String, String>();
		String [] responseArray = responseString.split("\n");
		
		setStatus(ERROR);
		
		if (responseArray.length > 0){
			if (responseArray[0].contains(IProtocol.MESSAGETYPE_OK)) {
				setStatus(OK);
			} else if (responseArray[0].contains(IProtocol.MESSAGETYPE_CALLBACK)){
				setStatus(CALLBACK);
			}
			responseArray = Arrays.copyOfRange(responseArray, 1, responseArray.length);
		} 
		
		for (String line : responseArray) {
			String [] keyValuePair = line.replaceAll("\r", "").split(": ", 2);
			
			if (2 == keyValuePair.length) {
				if(IProtocol.HEADER_RESPONSE_ACTION.equals(keyValuePair[0])) {
					setResponseAction(keyValuePair[1]);
				} else if(ORIGIN_MACHINE_NAME.equals(keyValuePair[0])) {
					setOriginMachineName(keyValuePair[1]);
				} else if(ORIGIN_SOFTWARE_NAME.equals(keyValuePair[0])) {
					setOriginSoftwareName(keyValuePair[1]);
				} else if(ORIGIN_SOFTWARE_VERSION.equals(keyValuePair[0])) {
					setOriginSoftwareVersion(keyValuePair[1]);
				} else if(ORIGIN_PLATFORM_NAME.equals(keyValuePair[0])) {
					setOriginPlatformName(keyValuePair[1]);
				} else if(ORIGIN_PLATFORM_VERSION.equals(keyValuePair[0])) {
					setOriginPlatformVersion(keyValuePair[1]);
				} else {
					customHeaders.put(keyValuePair[0], keyValuePair[1]);
				}
			} 
		}		
	}

	public String getOriginalResponse() {
		return originalResponse;
	}
	
	public String toString() {
		return originalResponse;
	}
	
	public String getResponseAction() {
		return responseAction;
	}

	private void setResponseAction(String responseAction) {
		this.responseAction = responseAction;
	}

	public int getStatus() {
		return status;
	}

	private void setStatus(int status) {
		this.status = status;
	}

	public String getOriginMachineName() {
		return originMachineName;
	}

	private void setOriginMachineName(String originMachineName) {
		this.originMachineName = originMachineName;
	}

	public String getOriginSoftwareName() {
		return originSoftwareName;
	}

	private void setOriginSoftwareName(String originSoftwareName) {
		this.originSoftwareName = originSoftwareName;
	}

	public String getOriginSoftwareVersion() {
		return originSoftwareVersion;
	}

	private void setOriginSoftwareVersion(String originSoftwareVersion) {
		this.originSoftwareVersion = originSoftwareVersion;
	}

	public String getOriginPlatformName() {
		return originPlatformName;
	}

	private void setOriginPlatformName(String originPlatformName) {
		this.originPlatformName = originPlatformName;
	}

	private void setOriginPlatformVersion(String originPlatformVersion) {
		this.originPlatformVersion = originPlatformVersion;
	}

	public String getOriginPlatformVersion() {
		return originPlatformVersion;
	}
	
	public Map<String, String> getCustomHeaders() {
		return customHeaders;
	}

	public boolean isNotification() {
		return IProtocol.MESSAGETYPE_NOTIFY.equals(responseAction);
	}
	
	public boolean isRegister() {
		return IProtocol.MESSAGETYPE_REGISTER.equals(responseAction);
	}
	
	public boolean isSubscribe() {
		return IProtocol.MESSAGETYPE_SUBSCRIBE.equals(responseAction);
	}

	public boolean isCallBack() {
		return (CALLBACK == status);
	}
}
