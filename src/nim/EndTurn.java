package nim;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GMath;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by guillermovera on 4/25/14.
 */
public class EndTurn extends GCompound {

    private boolean turnFinished;
    private GRect turn;

    public EndTurn(boolean turnFinished) {
        this.turnFinished = turnFinished;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        String labelFont = "arial-bold-" + Math.round(screenWidth * 0.023);
        GRect rect = new GRect(400, 60);
        GLabel labelInRect = new GLabel("End Turn?");
        labelInRect.setFont(labelFont);
        rect.setFilled(true);
        rect.setFillColor(Color.RED);
        labelInRect.setColor(Color.WHITE);
        int labelX = GMath.round((rect.getWidth() - labelInRect.getWidth()) / 2);
        int labelY = GMath.round((rect.getHeight() - labelInRect.getHeight()) / 2) + (int) labelInRect.getHeight();
        add(rect);
        add(labelInRect, labelX, labelY);
    }

    public boolean isTurnFinished() {
        return turnFinished;
    }

    public void setFinished() {
        this.turnFinished = true;
    }

    public void setNotFinised() {
        this.turnFinished = false;
    }
}
