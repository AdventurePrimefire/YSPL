package display;

import display.ConectedImageDisplay.ConnectedImage;

public interface ConectedTexture {
    public default int getTextureSize() {
        return 24;
    }

    public default int getRows() {
        return 3;
    }

    public default int getCols() {
        return 3;
    }
    
    public void getConnectedImage(ConnectedImage image);
}