package net.sf.libgrowl.internal;


import static org.junit.Assert.*;
import net.sf.libgrowl.internal.GenericResponse;
import net.sf.libgrowl.internal.IResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGenericResponse {
	private final String responseString = "GNTP/1.0 -OK NONE\nResponse-Action: NOTIFY\nOrigin-Machine-Name: DOC-PC\n" + 
	 "Origin-Software-Name: Growl/Win\nOrigin-Software-Version: 2.0.6.1\nOrigin-Platform-Name: Microsoft Windows NT 6.1.7600.0\n" + 
	 "Origin-Platform-Version: 6.1.7600.0\nOrigin-Platform-Version: 6.1.7600.1\nX-Message-Daemon: Growl/Win\nX-Timestamp: 6/4/2011 2:20:15 P";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testParse() {
		GenericResponse gr = new GenericResponse(responseString);
		assertEquals("NOTIFY", gr.getResponseAction());
		assertEquals("DOC-PC", gr.getOriginMachineName());
		assertEquals("Microsoft Windows NT 6.1.7600.0", gr.getOriginPlatformName());
		assertEquals("6.1.7600.1", gr.getOriginPlatformVersion());
		assertEquals("Growl/Win", gr.getOriginSoftwareName());
		assertEquals("2.0.6.1", gr.getOriginSoftwareVersion());
		assertEquals(IResponse.OK, gr.getStatus());
		assertEquals("6/4/2011 2:20:15 P", gr.getCustomHeaders().get("X-Timestamp"));
		assertEquals(responseString, gr.getOriginalResponse());
		assertEquals(responseString, gr.toString());

	}
	
	@Test
	public void testDefaultConstructor() {
		GenericResponse gr = new GenericResponse();
		assertEquals(IResponse.ERROR, gr.getStatus());
	}
	
	@Test
	public void testBadResponse() {
		GenericResponse gr = new GenericResponse("");
		assertEquals(IResponse.ERROR, gr.getStatus());
		
	}
	
	@Test
	public void tesRegisterResponse() {
		GenericResponse gr = new GenericResponse("GNTP/1.0 -OK NONE\nResponse-Action: REGISTER");
		assertTrue(gr.isRegister());
	}
	
}
