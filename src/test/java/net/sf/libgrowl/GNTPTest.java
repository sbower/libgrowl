package net.sf.libgrowl;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GNTPTest {
	
	/** 
     * Sets up the test fixture. 
     * (Called before every test case method.) 
     */ 
    @Before 
    public void setUp() { 

     // connect to Growl on the given host
     GrowlConnector growl = new GrowlConnector("10.0.1.10");
     growl.setPassword("test");
     
     // give your application a name and icon (optionally)
     Application downloadApp = new Application("Downloader", "http://example.com/icon.png");

     // create reusable notification types, their names are used in the Growl settings
     NotificationType downloadStarted = new NotificationType("Download started", "http://example.com/icon.png");
     NotificationType downloadFinished = new NotificationType("Download finished", "http://example.com/icon.png");
     NotificationType[] notificationTypes = new NotificationType[] { downloadStarted, downloadFinished };

     // now register the application in growl
     growl.register(downloadApp, notificationTypes);

     // create a notification with specific title and message
     Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB");
     ubuntuDownload.setCallBackURL("http://google.com");
     ubuntuDownload.setSticky(true);
     
     // finally send the notification
     growl.notify(ubuntuDownload);


    } 
    /** 
     * Tears down the test fixture. 
     * (Called after every test case method.) 
     */ 
    @After 
    public void tearDown() { 
    } 
    
    @Test
    public void testSomething() {
    	GrowlConnector growl = new GrowlConnector("10.0.1.10");
    	assertNotNull(growl);
    }
}
