package org.bl.group;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * @author beckl
 */
public class Plant {
    public enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    private final String name;
    private final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Map<LifeCycle, Set<Plant>> groupByLifeCycle2(Plant[] garden) {
        return Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()));
    }

    public static Map<LifeCycle, Set<Plant>> groupByLifeCycle1(Plant[] garden) {
        // Using an EnumMap to associate data with an enum
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant p : garden) {
            plantsByLifeCycle.computeIfAbsent(p.lifeCycle, (k) -> new HashSet<>()).add(p);
        }
        return plantsByLifeCycle;
    }
}