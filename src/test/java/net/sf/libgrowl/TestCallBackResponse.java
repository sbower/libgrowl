package net.sf.libgrowl;

import static org.junit.Assert.*;
import net.sf.libgrowl.internal.IResponse;

import org.junit.Test;

public class TestCallBackResponse  {
	private final String responseString = "GNTP/1.0 -CALLBACK NONE\nNotification-Callback-Result: TIMEDOUT\n" +
		"Notification-Callback-Context: 12345\nNotification-Callback-Context-Type: 123456";
	
	@Test
	public void testParse() {
		CallBackResponse cr = new CallBackResponse(responseString);
		assertEquals(IResponse.CALLBACK, cr.getStatus());
        assertEquals("12345", cr.getNotificationCallbackContext());
        assertEquals("123456", cr.getNotificationCallbackContextType());
        assertEquals("TIMEDOUT", cr.getNotificationCallbackResult());
        assertTrue(cr.isCallBack());
	}
}
