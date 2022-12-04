package org.example;

public class GamingCell {
    private GamingChip chip;
    public void ChangeChip(String condition) {
        chip = new GamingChip(condition);
    }
    public GamingChip GetChip() {
        return chip;
    }

    public GamingCell(String color) {
        ChangeChip(color);
    }


}
