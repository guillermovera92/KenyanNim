package nimCalculator;

import nim.Coin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillermovera on 4/26/14.
 */
public class NimSumCalculator {

    public NimSumCalculator() {
    }

    public int nimSum(List<Coin> coinList) {
        List<String> binaryList = new ArrayList<String>();
        for (Coin c : coinList){
            String heap = Integer.toBinaryString(c.getHeapSize());
            binaryList.add(heap);
        }
        return makeSameSize(binaryList);
    }

    private int makeSameSize(List<String> binaryList) {
        List<String> newBinaryList = new ArrayList<String>();
        int biggest = 0;
        for (String s : binaryList) {
            if (s.length() > biggest) {
                biggest = s.length();
            }
        }
        for (String s : binaryList) {
            int zerosToAdd = biggest - s.length();
            for (int i = 0; i < zerosToAdd; i++){
                s = "0" + s;
            }
            newBinaryList.add(s);
        }
        return sum(newBinaryList, biggest);
    }

    private int sum(List<String> binaryList, int biggest) {
        String sum = "";
        for (int i = biggest - 1; i >= 0; i--) {
            String currentRow = "0";
            for (String s : binaryList) {
                String currentChar = Character.toString(s.charAt(i));
                if (currentChar.equals(currentRow)) {
                    if (currentChar.equals("1")){
                        currentRow = "0";
                    }
                } else if (!currentChar.equals(currentRow)) {
                    if (currentRow.equals("0")){
                        currentRow = "1";
                    }
                }
            }
            sum = currentRow + sum;
        }
        return convertToDec(sum);
    }

    private int convertToDec(String sum) {
        return Integer.parseInt(sum, 2);
    }

    public static void main(String[] args){
        NimSumCalculator nsc = new NimSumCalculator();
        List<Coin> coins = new ArrayList<Coin>();
        for (int i = 1; i <= 6; i++){
            coins.add(new Coin(i, i, false, "heads"));
        }
        System.out.print(nsc.nimSum(coins));
    }
}
