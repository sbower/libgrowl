package net.sf.libgrowl;

import static org.junit.Assert.*;

import net.sf.libgrowl.internal.IResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GNTPTest {
	
	/** 
     * Sets up the test fixture. 
     * (Called before every test case method.) 
     */ 
	
	private GrowlConnector growl;
	private Application downloadApp;
	private NotificationType downloadStarted;
	private NotificationType downloadFinished;
	
    @Before 
    public void setUp() { 
        // connect to Growl on the given host
        growl = new GrowlConnector("10.0.1.10");
        assertNotNull(growl);
        
        growl.setPassword("test");
        
        // give your application a name and icon (optionally)
        downloadApp = new Application("Downloader", "http://jenkins-ci.org/images/butler.png");

        // create reusable notification types, their names are used in the Growl settings
        downloadStarted = new NotificationType("Download started");
        downloadFinished = new NotificationType("Download finished");
        NotificationType[] notificationTypes = new NotificationType[] { downloadStarted, downloadFinished };

        // now register the application in growl
        growl.register(downloadApp, notificationTypes);

    } 
    /** 
     * Tears down the test fixture. 
     * (Called after every test case method.) 
     */ 
    @After 
    public void tearDown() { 
    } 
    
    @Test
    public void testNotification() {
    	Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB"); 
    	
        // finally send the notification
        growl.notify(ubuntuDownload);
        
        // finally send the notification
        assertEquals(IResponse.OK, growl.notify(ubuntuDownload));
        NotificationResponse response = (NotificationResponse) growl.getLastResponse();
        
        assertEquals("NOTIFY", response.getResponseAction());
    }
    
    @Test
    public void testCallback() {

        // create a notification with specific title and message
        Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB");
        ubuntuDownload.setCallBackSocket("blah", "blah1");
        
        // finally send the notification
        growl.notify(ubuntuDownload);
        CallBackResponse response = (CallBackResponse) growl.getLastResponse();
        
        assertEquals("blah", response.getNotificationCallbackContext());
        assertEquals("blah1", response.getNotificationCallbackContextType());
	
    }
    
    @Test
    public void testSubscribe() {
        growl.subsribe();
        SubscribeResponse sr = (SubscribeResponse) growl.getLastResponse();
        assertEquals("300", sr.getSubscriptionTTL());

    }
         	
    
}
