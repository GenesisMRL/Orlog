package com.nunolima.gods;
import com.nunolima.game.Player;

import static com.nunolima.utils.Utilities.*;

public abstract class Sacred {

    public int priority;
    public int favourPower;
    public boolean selected;
    public String name;
    String summedUpInfo;
    String[] favourLevel1;
    String[] favourLevel2;
    String[] favourLevel3;

    public abstract void action(Player... players);

    public void printInfo(){
        System.out.print(BLACK + YELLOW_BACKGROUND + "--------------------------------------------------------------------\n");
        System.out.println(summedUpInfo);
        System.out.println("--------------------------------------------------------------------\n" + RESET);
    };

    public void printFavour() {
        String heading1 = "LEVEL";
        String heading2 = "TOKENS";
        String heading3 = "ACTION";
        String divider = "----------------------------------------------------------------------------";
        System.out.println(YELLOW_BACKGROUND + BLACK);
        System.out.printf("%15s %n", "\u001b[4m" + this.name + RESET + BLACK + YELLOW_BACKGROUND);
        System.out.println(divider);
        System.out.printf("%-15s %15s %30s %n", heading1, heading2, heading3);
        System.out.println(divider);
        System.out.printf("%-15s %15s %30s %n", this.favourLevel1[0],this.favourLevel1[1],this.favourLevel1[2]);
        System.out.printf("%-15s %15s %30s %n", this.favourLevel2[0],this.favourLevel2[1],this.favourLevel2[2]);
        System.out.printf("%-15s %15s %30s %n", this.favourLevel3[0],this.favourLevel3[1],this.favourLevel3[2]);
        System.out.println(divider + "\n" + RESET);
    }

    public int getPriority() {
        return priority;
    }

    public int getFavourPower() {
        return favourPower;
    }

    public void setFavourPower(int favourPower) {
        this.favourPower = favourPower;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
