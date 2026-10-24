package com.elementallegends.core;

public record SkillNode(String id, ElementType element, String name, int cost, int requiredLevel, String parentId) {
}
