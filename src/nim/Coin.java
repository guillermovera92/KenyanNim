package nim;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GMath;
import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by guillermovera on 4/25/14.
 */
public class Coin extends GCompound {
    private final int index;
    private int heapSize;
    private boolean rightMost;
    private String state;
    private GOval coin;
    private GLabel labelInRect;


    public Coin(int position, int size, boolean rightMost, String state){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        double coinSize = screenWidth * 0.11;
        this.index = position;
        this.rightMost = rightMost;
        this.state = state;
        this.heapSize = size;
        coin = new GOval(coinSize, coinSize);
        colorCoin(state);
        add(coin);
        String labelFont = "arial-bold-" + Math.round(screenWidth * 0.1);
        labelInRect = new GLabel("H");
        labelInRect.setFont(labelFont);
        labelInRect.setColor(Color.WHITE);
        int labelX = GMath.round((coin.getWidth() - labelInRect.getWidth()) / 2);
        int labelY = GMath.round((coin.getHeight() - labelInRect.getHeight()) / 2) + (int) labelInRect.getHeight();
        add(labelInRect, labelX, labelY);
    }

    private void colorCoin(String state) {
        coin.setFilled(true);
        if (state.equalsIgnoreCase("heads")) {
            coin.setFillColor(Color.BLACK);
        } else if (state.equalsIgnoreCase("tails")){
            coin.setFillColor(Color.BLUE);
        }
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setSize(int size) {
        this.heapSize = size;
    }

    public boolean isRightMost() {
        return rightMost;
    }

    public void setRightMost() {
        this.rightMost = true;
    }

    public String getState() {
        return state;
    }

    public void switchState() {
        if (state.equalsIgnoreCase("tails")) {
            state = "heads";
            coin.setFillColor(Color.BLACK);
            labelInRect.setLabel("H");
        } else if (state.equalsIgnoreCase("heads")) {
            state = "tails";
            coin.setFillColor(Color.BLUE);
            labelInRect.setLabel("T");
        }
    }

    public int getIndex() {
        return index;
    }
}
