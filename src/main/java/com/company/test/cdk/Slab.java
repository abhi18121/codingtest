package com.company.test.cdk;

public class Slab {
    public static final double ZERO = 0.0;
    private double start;
    private double end;
    private double discount;
    private boolean isLastLimit;

    public Slab(double start, double end, double discount, boolean isLastLimit) {
        if (start < ZERO || end < ZERO || discount <= ZERO) {
            throw new IllegalStateException("slab parameters can not be zero or less than zero");
        }
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.isLastLimit = isLastLimit;
    }

    public double apply(double amount) {
        if (isApplicable(amount)) {
            return getSlabAmount(amount) * discount / 100;
        }
        return ZERO;
    }

    private double getSlabAmount(double amount) {
        if (isLastLimit) {
            return amount - start;
        }
        if (amount >= end) {
            return end - start;
        }
        return amount - start;
    }

    public boolean isApplicable(double amount) {
        return isLessEqualToStartOfSlab(amount);
    }

    private boolean isLessEqualToStartOfSlab(double amount) {
        return start <= amount;
    }

}
