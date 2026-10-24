package com.elementallegends.item;

import com.elementallegends.core.ElementType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.elementallegends.ElementalLegends;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElementalLegends.MOD_ID);

    public static final RegistryObject<StaffItem> FIRE_STAFF = ITEMS.register("fire_staff", () -> new StaffItem(ElementType.FIRE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> WATER_STAFF = ITEMS.register("water_staff", () -> new StaffItem(ElementType.WATER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> AIR_STAFF = ITEMS.register("air_staff", () -> new StaffItem(ElementType.AIR, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> EARTH_STAFF = ITEMS.register("earth_staff", () -> new StaffItem(ElementType.EARTH, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> ICE_STAFF = ITEMS.register("ice_staff", () -> new StaffItem(ElementType.ICE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> LIGHTNING_STAFF = ITEMS.register("lightning_staff", () -> new StaffItem(ElementType.LIGHTNING, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> NATURE_STAFF = ITEMS.register("nature_staff", () -> new StaffItem(ElementType.NATURE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> SHADOW_STAFF = ITEMS.register("shadow_staff", () -> new StaffItem(ElementType.SHADOW, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> LIGHT_STAFF = ITEMS.register("light_staff", () -> new StaffItem(ElementType.LIGHT, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> METAL_STAFF = ITEMS.register("metal_staff", () -> new StaffItem(ElementType.METAL, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> POISON_STAFF = ITEMS.register("poison_staff", () -> new StaffItem(ElementType.POISON, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<StaffItem> TIME_STAFF = ITEMS.register("time_staff", () -> new StaffItem(ElementType.TIME, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ELEMENTAL_MANUAL = ITEMS.register("elemental_manual", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHARGE_AMULET = ITEMS.register("charge_amulet", () -> new ArtifactItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<ElementalArmorItem> POISON_HELMET = ITEMS.register("poison_helmet", () -> new ElementalArmorItem(ElementalArmorItem.ArmorSlot.HELMET, ElementType.POISON, new Item.Properties().stacksTo(1)));
}
