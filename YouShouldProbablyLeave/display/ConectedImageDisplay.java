package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

public class ConectedImageDisplay {
    private WeakReference<MyImageDisplay> display;
    private BufferedImage[] cutImages;

    public ConectedImageDisplay(MyImageDisplay display) throws Exception {
        this.display = new WeakReference<MyImageDisplay>(display);
    }

    private void cutImage(ConectedTexture tile) {
        this.cutImages = new BufferedImage[tile.getRows() * tile.getCols()];
        for (int row = 0; row < tile.getRows(); row++) {
            for (int col = 0; col < tile.getCols(); col++) {
                this.cutImages[row * tile.getCols() + col] = this.display.get().image.getSubimage(row * tile.getTextureSize(), col * tile.getTextureSize(), tile.getTextureSize(), tile.getTextureSize());
            }
        }
    }

    public BufferedImage connectedImage(ConectedTexture tile) {
        if (this.cutImages == null) {
            this.cutImage(tile);
        }
        ConnectedImage conim = new ConnectedImage(this.display.get().image, tile);
        tile.getConnectedImage(conim);
        return conim.connectedImage();
    }

    public class ConnectedImage {
        private BufferedImage image;
        private ConectedTexture tile;
        private BufferedImage q0;
        private BufferedImage q1;
        private BufferedImage q2;
        private BufferedImage q3;

        public ConnectedImage(BufferedImage image, ConectedTexture tile) {
            this.image = image;
            this.tile = tile;
        }

        public BufferedImage connectedImage() {
            BufferedImage out = new BufferedImage(2 * tile.getTextureSize(), 2 * tile.getTextureSize(), BufferedImage.TYPE_INT_RGB);
            Graphics g1 = out.getGraphics();
            g1.drawImage(q0, 0, 0, null);
            g1.drawImage(q1, tile.getTextureSize(), 0, null);
            g1.drawImage(q2, tile.getTextureSize(), tile.getTextureSize(), null);
            g1.drawImage(q3, 0, tile.getTextureSize(), null);
            return out;
        }

        public void setImageLocation(int possision, int row, int col) {
            BufferedImage image = cutImages[row * tile.getCols() + col];
            switch (possision) {
                case 0:
                    this.q0 = image;
                    break;
                case 1:
                    this.q1 = image;
                    break;
                case 2:
                    this.q2 = image;
                    break;
                case 3:
                    this.q3 = image;
                    break;
            }
        }
    }
}
