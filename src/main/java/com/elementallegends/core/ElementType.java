package com.elementallegends.core;

import java.util.Locale;

public enum ElementType {
    FIRE("fire", 0xFF5C3D, "Fire"),
    WATER("water", 0x4DB7FF, "Water"),
    AIR("air", 0xB9F3FF, "Air"),
    EARTH("earth", 0x8B5E3C, "Earth"),
    ICE("ice", 0x7DE3FF, "Ice"),
    LIGHTNING("lightning", 0xFDEB68, "Lightning"),
    NATURE("nature", 0x72D96A, "Nature"),
    SHADOW("shadow", 0x5B4D8C, "Shadow"),
    LIGHT("light", 0xFFF3B5, "Light"),
    METAL("metal", 0xD9D9D9, "Metal"),
    POISON("poison", 0x7EFF7E, "Poison"),
    TIME("time", 0xC090FF, "Time"),
    COSMOS("cosmos", 0x9A8CFF, "Cosmos"),
    VOID("void", 0x3E2A66, "Void"),
    MAGMA("magma", 0xFF7A59, "Magma"),
    CRYSTAL("crystal", 0x7AF2FF, "Crystal"),
    STAR("star", 0xF9D976, "Star Energy"),
    SOUND("sound", 0xFFB4F2, "Sound");

    private final String id;
    private final int color;
    private final String displayName;

    ElementType(String id, int color, String displayName) {
        this.id = id;
        this.color = color;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTranslationKey() {
        return "elementallegends.element." + name().toLowerCase(Locale.ROOT);
    }
}
