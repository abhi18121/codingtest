package com.company.test.cdk;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SlabTest {
    @Test
    public void shouldBeApplicableSlab() {
        //given
        Slab validSlab = new Slab(500, 1000, 10, false);
        //when
        boolean applicable = validSlab.isApplicable(2000);
        //then
        assertTrue(applicable);
    }

    @Test
    public void shouldNotBeApplicableSlab() {
        //given
        Slab validSlab = new Slab(500, 1000, 10, false);
        //when
        boolean applicable = validSlab.isApplicable(499);
        //then
        assertFalse(applicable);
    }

    @Test
    public void shouldCalculateDiscountForSlabBetween500To1000() {
        //given
        Slab slabFor10PercentagesDiscount = new Slab(500, 1000, 10, false);
        //when
        double calculatedDiscount = slabFor10PercentagesDiscount.apply(2000);
        //then
        assertEquals(Double.doubleToLongBits(50.0), Double.doubleToLongBits(calculatedDiscount));
    }

    @Test
    public void shouldCalculateDiscountForSlabBetween1000To2000() {
        //given
        Slab slabFor20PercentagesDiscount = new Slab(1000, 2000, 20, false);
        //when
        double calculatedDiscount = slabFor20PercentagesDiscount.apply(1500);
        //then
        assertEquals(Double.doubleToLongBits(100.000), Double.doubleToLongBits(calculatedDiscount));
    }

    @Test
    public void shouldCalculateDiscountForLastSlabBetween1000To2000() {
        //given
        Slab slabFor20PercentagesDiscount = new Slab(1000, 2000, 30, true);
        //when
        double calculatedDiscount = slabFor20PercentagesDiscount.apply(2500);
        //then
        assertEquals(Double.doubleToLongBits(450.00), Double.doubleToLongBits(calculatedDiscount));
    }

    @Test
    public void shouldApplyDiscountForAllSlabs() {
        Slab slab10 = new Slab(500, 1000, 10, false);
        Slab slab20 = new Slab(1000, 2000, 20, false);
        Slab slab30 = new Slab(2000, 5000, 30, true);
        //when
        List<Slab> slabs = Arrays.asList(slab10, slab20, slab30);
        double amount = 15000;
        slabs
                .stream()
                .filter(slab -> slab.isApplicable(amount))
                .forEach(slab -> {
                    double calculatedSlab = slab.apply(amount);
                    System.out.println(calculatedSlab);
                });
        //then
    }
}
