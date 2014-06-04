package display;

import info.gridworld.gui.ImageDisplay;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;

public class MyImageDisplay extends ImageDisplay {
    private Class cl;
    private File file;
    private BufferedImage image;

    public MyImageDisplay(Class cl) throws Exception {
        super();
        this.cl = cl;
        if (PathedImage.class.isAssignableFrom(cl)) {
            Field field = cl.getField("file");
            this.file = (File) field.get(null);
            this.image = ImageIO.read(this.file);
        }
    }

    /**
     * Draws a unit-length object facing North. This implementation draws an
     * object by tinting, scaling, and rotating the image in the image file.
     *
     * @param obj
     *            object we want to draw
     * @param comp
     *            the component we're drawing on
     * @param g2
     *            drawing surface
     */
    @Override
    public void draw(Object obj, Component comp, Graphics2D g2) {
        Image tinted = this.image;
        int width = tinted.getWidth(null);
        int height = tinted.getHeight(null);
        int size = Math.max(width, height);

        // Scale to shrink or enlarge the image to fit the size 1x1 cell.
        g2.scale(1.0 / size, 1.0 / size);
        g2.clip(new Rectangle(-width / 2, -height / 2, width, height));
        g2.drawImage(tinted, -width / 2, -height / 2, null);
    }
}
