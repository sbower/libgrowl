package net.sf.libgrowl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sf.libgrowl.internal.IResponse;

import org.junit.Test;

public class TestSubscribeResponse {
	private final String responseString = "GNTP/1.0 -ERROR NONE\nResponse-Action: SUBSCRIBE\nSubscription-TTL: 1234";

	@Test
	public void testParse() {
		SubscribeResponse sr = new SubscribeResponse(responseString);
		assertEquals(IResponse.ERROR, sr.getStatus());
		assertEquals("1234", sr.getSubscriptionTTL());
		assertTrue(sr.isSubscribe());
	}
}
