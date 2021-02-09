package org.bl.group;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author beckl
 */
public class PlantTest {
    @Test
    public void test() {
        Plant[] garden = new Plant[]{
            new Plant("A", Plant.LifeCycle.ANNUAL),
            new Plant("B", Plant.LifeCycle.BIENNIAL),
            new Plant("C", Plant.LifeCycle.PERENNIAL),
            new Plant("D", Plant.LifeCycle.BIENNIAL),
            new Plant("E", Plant.LifeCycle.PERENNIAL),
        };

        Map<Plant.LifeCycle, Set<Plant>> group1 = Plant.groupByLifeCycle1(garden);
        Map<Plant.LifeCycle, Set<Plant>> group2 = Plant.groupByLifeCycle2(garden);
        assertNotNull(group1);
        assertNotNull(group2);
        assertEquals(group1.size(), group2.size());
        assertEquals(group1.keySet().size(), group2.keySet().size());
        assertEquals(group1.values().size(), group2.values().size());
        assertEquals(group1.get(Plant.LifeCycle.ANNUAL).size(), group2.get(Plant.LifeCycle.ANNUAL).size());
        assertEquals(group1.get(Plant.LifeCycle.BIENNIAL).size(), group2.get(Plant.LifeCycle.BIENNIAL).size());
        assertEquals(group1.get(Plant.LifeCycle.PERENNIAL).size(), group2.get(Plant.LifeCycle.PERENNIAL).size());
    }
}
