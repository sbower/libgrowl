package net.sf.libgrowl.internal;

import java.util.UUID;

public class SubscribeMessage extends Message {

	public SubscribeMessage(String password) {
		super(IProtocol.MESSAGETYPE_SUBSCRIBE, password);
		
		header(IProtocol.HEADER_SUBSCRIBER_NAME, MACHINE_NAME);
    	header(IProtocol.HEADER_SUBSCRIBER_ID, String.valueOf(UUID.randomUUID()));
	}

}
