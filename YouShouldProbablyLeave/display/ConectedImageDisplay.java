package display;

import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

public class ConectedImageDisplay {
    private WeakReference<MyImageDisplay> display;

    public ConectedImageDisplay(MyImageDisplay display) throws Exception {
        this.display = new WeakReference<MyImageDisplay>(display);
    }

    public BufferedImage connectedImage(ConectedTexture tile) {
        ConnectedImage conim = new ConnectedImage(this.display.get().image);
        tile.getConnectedImage(conim);
        return conim.connectedImage();
    }

    public class ConnectedImage {
        private BufferedImage image;
        private BufferedImage connectedImage;

        public ConnectedImage(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage connectedImage() {
            return this.connectedImage;
        }
    }
}
