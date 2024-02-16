package cn.mediinfo.springdemo.controller.effctivedemo.example4;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 *@title EnumAndAnnotation3_用EnumMap替代序数索引
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class EnumAndAnnotation4_用EnumMap替代序数索引 {
    enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    final String name;
    final LifeCycle lifeCycle;

    public EnumAndAnnotation4_用EnumMap替代序数索引(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    void example(){
        Map<LifeCycle, Set<EnumAndAnnotation4_用EnumMap替代序数索引>> plantsByLifeCycle =
                new EnumMap<>(LifeCycle.class);
        for (LifeCycle lc : LifeCycle.values()){
            plantsByLifeCycle.put(lc, new HashSet<>());
        }
    }
}
