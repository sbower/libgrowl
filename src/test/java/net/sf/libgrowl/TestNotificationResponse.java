package net.sf.libgrowl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sf.libgrowl.internal.IResponse;

import org.junit.Test;


public class TestNotificationResponse {
	private final String responseString = "GNTP/1.0 -OK NONE\nResponse-Action: NOTIFY\nNotification-ID: 1234";

	@Test
	public void testParse() {
		NotificationResponse nr = new NotificationResponse(responseString);
		assertEquals(IResponse.OK, nr.getStatus());
		assertEquals("1234", nr.getNotificationID());
		assertTrue(nr.isNotification());

	}
}
