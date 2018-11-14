package io.zipcoder.casino.game;

public class Player {
    private String name;
    private Integer bank;

    public Player() {
        this.name = "Default";
        this.bank = 10000;
    }
    public Player(String name, Integer bank) {
        this.name = name;
        this.bank = bank;
    }

    public boolean checkbet(int amount) {
        if ((this.getBank() - amount) < 0 || amount < 0 ) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkInitialBank(int amount) {
        if (amount < 0) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }
    public String getState(){
        return String.format("Highroller %s has $%,d", name, bank);
    }
}

