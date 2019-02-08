package com.cegeka.switchfully.security.feature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public enum Feature {

    GET_ARMY_INFO(ArmyRole.PRIVATE, ArmyRole.GENERAL),
    JOIN_ARMY(ArmyRole.CIVILIAN),
    PROMOTE_PRIVATE(ArmyRole.HUMAN_RELATIONS),
    DISCHARGE_SOLDIER(ArmyRole.HUMAN_RELATIONS),
    LAUNCH_NUKES(ArmyRole.GENERAL);

    private ArmyRole[] roles;

    Feature(ArmyRole... roles) {
        this.roles = roles;
    }

    public List<ArmyRole> getRoles() {
        return newArrayList(roles);
    }

    public static List<Feature> getFeaturesForRoles(List<String> rolesOfUserAsString) {
        List<ArmyRole> rolesOfUser = rolesOfUserAsString.stream()
                .map(ArmyRole::valueOf)
                .collect(Collectors.toList());
        return Arrays.stream(Feature.values())
                .filter(feature -> !Collections.disjoint(feature.getRoles(), rolesOfUser))
                .collect(Collectors.toList());
    }
}
