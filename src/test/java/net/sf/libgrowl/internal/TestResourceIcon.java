package net.sf.libgrowl.internal;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.ImageIcon;

import net.sf.libgrowl.Application;
import net.sf.libgrowl.GrowlConnector;
import net.sf.libgrowl.Notification;
import net.sf.libgrowl.NotificationType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestResourceIcon {

	private GrowlConnector growl;
	private Application downloadApp;
	private NotificationType downloadStarted;
	private NotificationType downloadFinished;
	private File icon;
	
	@Before
	public void setUp() throws Exception {
        growl = new GrowlConnector("10.0.1.10");
        assertNotNull(growl);
        growl.setPassword("test");
        
		icon = new File("src/test/resources/JENKINS.png");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testConstructer() {
		ResourceIcon ri = new ResourceIcon(icon);
		assertNotNull(ri);
	}
	
	@Test
	public void testCreatingIconInApplication() {
		
        // give your application a name and icon (optionally)
        downloadApp = new Application("Downloader", icon);
        ImageIcon ii = new ImageIcon();
        
        downloadStarted = new NotificationType("Download started");
        downloadFinished = new NotificationType("Download finished");
        NotificationType[] notificationTypes = new NotificationType[] { downloadStarted, downloadFinished };
        
     	// now register the application in growl
        growl.register(downloadApp, notificationTypes);        
        
    	Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB"); 
    	
        // finally send the notification
        growl.notify(ubuntuDownload);
        	
	}
	
	@Test
	public void testCreatingImageIconInApplication() {
		
        // give your application a name and icon (optionally)
        ImageIcon ii = new ImageIcon(icon.getAbsolutePath());
        downloadApp = new Application("Downloader", ii);
        
        downloadStarted = new NotificationType("Download started");
        downloadFinished = new NotificationType("Download finished");
        NotificationType[] notificationTypes = new NotificationType[] { downloadStarted, downloadFinished };
        
     	// now register the application in growl
        growl.register(downloadApp, notificationTypes);        
        
    	Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB"); 
    	
        // finally send the notification
        growl.notify(ubuntuDownload);
	}

}
