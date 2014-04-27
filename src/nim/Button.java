package nim;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GMath;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by guillermovera on 4/25/14.
 */
public class Button extends GCompound {

    private int number;

    public Button(int number){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        double buttonSize = screenWidth * 0.08;
        this.number = number;
        GRect rect = new GRect(buttonSize, buttonSize);
        GLabel labelInRect = new GLabel(Integer.toString(number));
        String labelFont = "arial-bold-" + Math.round(screenWidth * 0.05);
        labelInRect.setFont(labelFont);
        rect.setFilled(true);
        rect.setFillColor(Color.GREEN);
        labelInRect.setColor(Color.WHITE);
        int labelX = GMath.round((rect.getWidth() - labelInRect.getWidth()) / 2);
        int labelY = GMath.round((rect.getHeight() - labelInRect.getHeight()) / 2) + (int) labelInRect.getHeight();
        add(rect);
        add(labelInRect, labelX, labelY);
    }

    public int getNumber() {
        return number;
    }
}
