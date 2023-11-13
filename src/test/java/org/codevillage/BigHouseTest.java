package org.codevillage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigHouseTest {

    @Test
    void testConstructorNoCoordinates() {
        BigHouse house = new BigHouse();
        assertNotNull(house);
        assertEquals(0, house.getX());
        assertEquals(0, house.getY());
    }

    @Test
    void testConstructorWithCoordinates() {
        BigHouse house = new BigHouse(10, 10);
        assertNotNull(house);
        assertEquals(10, house.getX());
        assertEquals(10, house.getY());
    }
}