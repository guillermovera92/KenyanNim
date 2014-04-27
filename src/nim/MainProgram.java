package nim;

import acm.program.GraphicsProgram;
import nimCalculator.NimSumCalculator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillermovera on 4/25/14.
 */
public class MainProgram extends GraphicsProgram implements MouseListener {
    private List<Coin> coinList = new ArrayList<Coin>();
    private List<Button> buttonList = new ArrayList<Button>();
    private int initialCoins;
    private NewGame newGame = new NewGame();
    private int pos;
    private EndTurn et;
    private int headsInARow;
    private NimSumCalculator calc = new NimSumCalculator();
    private int latestHead;
    private int rightMostSize;
    private NimSumDisplay display;

    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen.width, screen.height);
        addMouseListeners();
    }

    @Override
    public void run() {
        if (et != null){
            remove(et);
        }
        if (display != null){
            remove(display);
        }
        et = new EndTurn(false);
        headsInARow = 0;
        deleteCoins();
        makeButtons();
        add(newGame, getWidth() - newGame.getWidth(), 0);
    }

    private void deleteCoins() {
        for (Coin coin : coinList){
            remove(coin);
        }
        coinList = new ArrayList<Coin>();
    }


    private void makeCoins() {
        for (int i = 1; i <= initialCoins; i++) {
            boolean rightMost = false;
            if (i == initialCoins) {
                rightMost = true;
            }
            Coin coin = new Coin(i, i, rightMost, "heads");
            coinList.add(coin);
            add(coin, 20 + (i - 1) * getWidth() / initialCoins, (getHeight() / 2) - (coin.getHeight() / 2));
        }
        display = new NimSumDisplay(calculateSum());
        add(display, (getWidth() / 2) - (display.getWidth() / 2), 0);
    }

    private void makeButtons() {
        for (int i = 3; i <= 7; i++) {
            Button button = new Button(i);
            add(button, (i - 2) * getWidth() / 6, getHeight() / 2);
            buttonList.add(button);
        }
    }

    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Object element = getElementAt(x, y);
        System.out.print(element);
        try {
            if (element.getClass().equals(Button.class)) {
                Button b = (Button) element;
                initialCoins = b.getNumber();
                System.out.print(initialCoins);
                deleteButtons();
            } else if (element.getClass().equals(Coin.class)){
                Coin c = (Coin) element;
                if (c.getState().equalsIgnoreCase("heads")) {
                    if (headsInARow == 0){
                        latestHead = c.getIndex();
                        headsInARow++;
                        et.setNotFinised();
                        add(et, getWidth() / 2 - et.getWidth() / 2, getHeight() - et.getHeight() * 2);
                        c.switchState();
                        pos = c.getIndex();
                    } else if (headsInARow == 1 && c.getIndex() < pos){
                        c.switchState();
                        et.setFinished();
                        remove(et);
                        headsInARow = 0;
                        pos = c.getIndex();
                        coinList.get(latestHead - 1).setSize(latestHead - (latestHead - c.getIndex()));
                        display.updateDisplay(calculateSum());
                    }
                    if (c.isRightMost()){
                        coinList.get(pos - 1).setRightMost();
                        rightMostSize = c.getHeapSize();
                        c.setSize(0);
                        display.updateDisplay(calculateSum());
                    }
                } else if (c.getState().equalsIgnoreCase("tails") && !et.isTurnFinished()){
                    if (c.getIndex() < pos){
                        c.switchState();
                        et.setFinished();
                        remove(et);
                        headsInARow = 0;
                        System.out.print("latest head: " + latestHead + "\n");
                        System.out.print("heap: " + coinList.get(latestHead-1).getHeapSize() + "\n");
                        System.out.print("subtraction: " + (latestHead-c.getIndex()) + "\n");
                        int oldSize = c.getHeapSize();
                        if (coinList.get(latestHead - 1).getHeapSize() == 0){
                            c.setSize(rightMostSize - (latestHead - c.getIndex()));
                            System.out.print("c size: " + c.getHeapSize() + "\n");
                        } else {
                            c.setSize(coinList.get(latestHead - 1).getHeapSize() - (latestHead - c.getIndex()));
                        }
                        coinList.get(latestHead - 1).setSize(oldSize);
                        display.updateDisplay(calculateSum());
                    }
                }
            } else if (element.getClass().equals(EndTurn.class)){
                et.setFinished();
                remove(et);
                headsInARow = 0;
                coinList.get(latestHead - 1).setSize(0);
                display.updateDisplay(calculateSum());
            } else if (element.getClass().equals(NewGame.class)){
                run();
            } else if (element.getClass().equals(NimSumDisplay.class)){
                display.removeCover();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private int calculateSum() {
        return calc.nimSum(coinList);
    }

    private void deleteButtons() {
        for (Button b : buttonList){
            remove(b);
        }
        makeCoins();
    }

    public static void main(String[] args){
        MainProgram mp = new MainProgram();
        mp.start();
    }
}