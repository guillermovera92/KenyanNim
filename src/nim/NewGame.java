package nim;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GMath;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by guillermovera on 4/26/14.
 */
public class NewGame extends GCompound {

    public NewGame() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        String labelFont = "arial-bold-" + Math.round(screenWidth * 0.023);
        GRect rect = new GRect(300, 60);
        GLabel labelInRect = new GLabel("New Game");
        labelInRect.setFont(labelFont);
        rect.setFilled(true);
        rect.setFillColor(Color.RED);
        labelInRect.setColor(Color.WHITE);
        int labelX = GMath.round((rect.getWidth() - labelInRect.getWidth()) / 2);
        int labelY = GMath.round((rect.getHeight() - labelInRect.getHeight()) / 2) + (int) labelInRect.getHeight();
        add(rect);
        add(labelInRect, labelX, labelY);
    }
}
