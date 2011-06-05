/*
 * Copyright Michael Keppler
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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Icon represented by a resource
 *
 * @author Bananeweizen
 *
 */
public class ResourceIcon extends Icon {

  private static final String[] BEST_FORMAT = { "PNG", "GIF", "JPEG" };
  private String mResourceId;
  private byte[] mImageData;

  public ResourceIcon(final ImageIcon icon) {
    final BufferedImage iconImage = new BufferedImage(icon.getIconWidth(), icon
        .getIconWidth(), BufferedImage.TYPE_INT_ARGB);
    final Graphics2D g2 = iconImage.createGraphics();
    icon.paintIcon(null, g2, 0, 0);
    g2.dispose();

    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(iconImage, getBestFormat(ImageIO.getWriterFormatNames()),
          outStream);
      mImageData = outStream.toByteArray();
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ResourceIcon(final File iconFile) {
    try {
      if (iconFile.canRead()) {
        FileInputStream stream = new FileInputStream(iconFile);
        mImageData = new byte[(int) iconFile.length()];
        stream.read(mImageData);
        stream.close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getBestFormat(final String[] formatNames) {
    for (String bestFormat : BEST_FORMAT) {
      for (String supportedFormat : formatNames) {
        if (supportedFormat.equals(bestFormat)) {
          return bestFormat;
        }
      }
    }
    return formatNames[0];
  }

  @Override
  public void header(final String headerName, final Message message) {
    if (mResourceId == null) {
      if (mImageData != null) {
        mResourceId = Encryption.md5(mImageData);
      }
      else {
    	mResourceId = String.valueOf(Math.random());
      }
    }
    message.header(headerName,
        IProtocol.X_GROWL_RESOURCE + mResourceId);
    message.addResourceInternal(mResourceId, mImageData);
  }

}
