package com.telran.cars.model;

public abstract class AbstractRentCompany implements IRentCompany {
    protected int finePercent; // штраф за просрочку
    protected int gasPrice;

    @Override
    public int getFinePercent() {
        return finePercent;
    }

    @Override
    public void setFinePercent(int finePercent) {
        this.finePercent = finePercent;
    }

    @Override
    public int getGasPrice() {
        return gasPrice;
    }

    @Override
    public void setGasPrice(int gasPrice) {
        this.gasPrice = gasPrice;
    }
}
