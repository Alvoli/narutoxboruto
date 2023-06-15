package com.turgyn.narutoxboruto.items;

import com.turgyn.narutoxboruto.Main;
import com.turgyn.narutoxboruto.items.custom.DnaBottleItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.DeferredRegister.create;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

public class ModItems {
	public static final DeferredRegister<Item> MOD_ITEMS = create(ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> TAB, EARTH_RELEASE, WIND_RELEASE, WATER_RELEASE, FIRE_RELEASE, LIGHTNING_RELEASE, YIN_RELEASE, YANG_RELEASE,DNA_BOTTLE,JUTSU_LEARNER;

	static {
		TAB = registerItem("tab");
		EARTH_RELEASE = registerNatureRelease("earth");
		WIND_RELEASE = registerNatureRelease("wind");
		WATER_RELEASE = registerNatureRelease("water");
		FIRE_RELEASE = registerNatureRelease("fire");
		LIGHTNING_RELEASE = registerNatureRelease("lightning");
		YIN_RELEASE = registerNatureRelease("yin");
		YANG_RELEASE = registerNatureRelease("yang");
		DNA_BOTTLE = MOD_ITEMS.register("dnabottle", () -> new DnaBottleItem(new Item.Properties()));
		JUTSU_LEARNER = registerItem("jutsulearner");
	}

	private static RegistryObject<Item> registerItem(String name) {
		return MOD_ITEMS.register(name, () -> new Item(new Item.Properties()));
	}

	private static RegistryObject<Item> registerNatureRelease(String name) {
		return MOD_ITEMS.register(name, () -> new NatureReleaseItem(new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		MOD_ITEMS.register(eventBus);
	}
}