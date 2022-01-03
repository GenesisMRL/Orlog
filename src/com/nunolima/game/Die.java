package com.nunolima.game;

public class Die {

    private String[] faces = new String[6];

    public Die(String f1, String f2, String f3, String f4, String f5, String f6) {
        this.faces[0] = f1;
        this.faces[1] = f2;
        this.faces[2] = f3;
        this.faces[3] = f4;
        this.faces[4] = f5;
        this.faces[5] = f6;
    }

    public String[] getFaces() {
        return faces;
    }
}
