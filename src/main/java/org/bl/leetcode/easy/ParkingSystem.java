package org.bl.leetcode.easy;

/**
 * @author beckl
 */
public class ParkingSystem {
    private final int[] parkingLots;

    public ParkingSystem(int big, int medium, int small) {
        parkingLots = new int[]{0, big, medium, small};
    }

    public boolean addCar(int carType) {
        if (carType < 0 || carType >= parkingLots.length) {
            return false;
        }

        return parkingLots[carType]-- > 0;
    }
}
