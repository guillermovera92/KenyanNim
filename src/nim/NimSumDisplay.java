package nim;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GMath;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by guillermovera on 4/26/14.
 */
public class NimSumDisplay extends GCompound {
    private GLabel label;
    private GRect cover;
    private GLabel nimSum; 

    public NimSumDisplay(int display) {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        String coverLabel = "arial-bold-" + Math.round(screenWidth * 0.02);
        String nimLabel = "arial-bold-" + Math.round(screenWidth * 0.05);
        nimSum = new GLabel(Integer.toString(display));
        nimSum.setFont(nimLabel);
        nimSum.setColor(Color.BLACK);
        label = new GLabel("Show Nim Sum");
        cover = new GRect(300, 100);
        label.setFont(coverLabel);
        cover.setFilled(true);
        cover.setFillColor(Color.ORANGE);
        label.setColor(Color.WHITE);
        int labelX = GMath.round((cover.getWidth() - label.getWidth()) / 2);
        int labelY = GMath.round((cover.getHeight() - label.getHeight()) / 2) + (int) label.getHeight();
        int labelXdisp = GMath.round((cover.getWidth() - nimSum.getWidth()) / 2);
        int labelYdisp = GMath.round((cover.getHeight() - nimSum.getHeight()) / 2) + (int) nimSum.getHeight();
        add(nimSum, labelXdisp, labelYdisp);
        add(cover);
        add(label, labelX, labelY);
    }

    public void removeCover(){
        remove(cover);
        remove(label);
    }

    public void updateDisplay(int display){
        nimSum.setLabel(Integer.toString(display));
    }
}
