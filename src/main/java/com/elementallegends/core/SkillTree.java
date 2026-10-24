package com.elementallegends.core;

import java.util.ArrayList;
import java.util.List;

public final class SkillTree {
    private static final List<SkillNode> NODES = List.of(
            new SkillNode("fire_bolt", ElementType.FIRE, "Fire Bolt", 1, 1, null),
            new SkillNode("fire_wave", ElementType.FIRE, "Fire Wave", 2, 2, "fire_bolt"),
            new SkillNode("fire_tornado", ElementType.FIRE, "Fire Tornado", 4, 4, "fire_wave"),
            new SkillNode("water_barrier", ElementType.WATER, "Water Barrier", 1, 1, null),
            new SkillNode("tidal_wave", ElementType.WATER, "Tidal Wave", 2, 2, "water_barrier"),
            new SkillNode("water_guardian", ElementType.WATER, "Water Guardian", 4, 4, "tidal_wave"),
            new SkillNode("air_dash", ElementType.AIR, "Air Dash", 1, 1, null),
            new SkillNode("wind_spirit", ElementType.AIR, "Wind Spirit", 2, 2, "air_dash"),
            new SkillNode("flight_mastery", ElementType.AIR, "Flight Mastery", 4, 4, "wind_spirit"),
            new SkillNode("stone_wall", ElementType.EARTH, "Stone Wall", 1, 1, null),
            new SkillNode("earthquake", ElementType.EARTH, "Earthquake", 2, 2, "stone_wall"),
            new SkillNode("earth_titan", ElementType.EARTH, "Earth Titan", 4, 4, "earthquake"),
            new SkillNode("glacial_shard", ElementType.ICE, "Glacial Shard", 1, 1, null),
            new SkillNode("ice_storm", ElementType.ICE, "Ice Storm", 2, 2, "glacial_shard"),
            new SkillNode("ice_dragon", ElementType.ICE, "Ice Dragon", 4, 4, "ice_storm"),
            new SkillNode("chain_lightning", ElementType.LIGHTNING, "Chain Lightning", 1, 1, null),
            new SkillNode("storm_lord", ElementType.LIGHTNING, "Storm Lord", 2, 2, "chain_lightning"),
            new SkillNode("thunder_dash", ElementType.LIGHTNING, "Thunder Dash", 4, 4, "storm_lord"),
            new SkillNode("nature_root", ElementType.NATURE, "Nature Root", 1, 1, null),
            new SkillNode("healing_flower", ElementType.NATURE, "Healing Flower", 2, 2, "nature_root"),
            new SkillNode("ancient_guardian", ElementType.NATURE, "Ancient Guardian", 4, 4, "healing_flower"),
            new SkillNode("dark_pulse", ElementType.SHADOW, "Dark Pulse", 1, 1, null),
            new SkillNode("shadow_step", ElementType.SHADOW, "Shadow Step", 2, 2, "dark_pulse"),
            new SkillNode("shadow_avatar", ElementType.SHADOW, "Shadow Avatar", 4, 4, "shadow_step"),
            new SkillNode("light_beam", ElementType.LIGHT, "Light Beam", 1, 1, null),
            new SkillNode("divine_guard", ElementType.LIGHT, "Divine Guard", 2, 2, "light_beam"),
            new SkillNode("light_avatar", ElementType.LIGHT, "Light Avatar", 4, 4, "divine_guard"),
            new SkillNode("magnet_pull", ElementType.METAL, "Magnet Pull", 1, 1, null),
            new SkillNode("metal_fury", ElementType.METAL, "Metal Fury", 2, 2, "magnet_pull"),
            new SkillNode("magnetic_colossus", ElementType.METAL, "Magnetic Colossus", 4, 4, "metal_fury"),
            new SkillNode("poison_cloud", ElementType.POISON, "Poison Cloud", 1, 1, null),
            new SkillNode("acid_pool", ElementType.POISON, "Acid Pool", 2, 2, "poison_cloud"),
            new SkillNode("poison_lord", ElementType.POISON, "Poison Lord", 4, 4, "acid_pool"),
            new SkillNode("time_slow", ElementType.TIME, "Time Slow", 2, 3, null),
            new SkillNode("temporal_rift", ElementType.TIME, "Temporal Rift", 5, 6, "time_slow"),
            new SkillNode("keeper_of_time", ElementType.TIME, "Keeper of Time", 7, 8, "temporal_rift"),
            new SkillNode("cosmic_rift", ElementType.COSMOS, "Cosmic Rift", 5, 7, null),
            new SkillNode("void_tear", ElementType.VOID, "Void Tear", 5, 7, null),
            new SkillNode("magma_wave", ElementType.MAGMA, "Magma Wave", 5, 7, null),
            new SkillNode("crystal_barrier", ElementType.CRYSTAL, "Crystal Barrier", 5, 7, null),
            new SkillNode("star_burst", ElementType.STAR, "Star Burst", 5, 7, null),
            new SkillNode("resonant_pulse", ElementType.SOUND, "Resonant Pulse", 5, 7, null)
    );

    private SkillTree() {
    }

    public static List<SkillNode> allNodes() {
        return new ArrayList<>(NODES);
    }

    public static List<SkillNode> forElement(ElementType element) {
        return NODES.stream()
                .filter(node -> node.element() == element)
                .toList();
    }

    public static SkillNode getById(String id) {
        return NODES.stream().filter(node -> node.id().equals(id)).findFirst().orElse(null);
    }
}
