package com.turgyn.narutomod.items;

import com.turgyn.narutomod.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.DeferredRegister.create;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

public class ModItems {
	public static final DeferredRegister<Item> MOD_ITEMS = create(ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> TAB, EARTH_RELEASE, WIND_RELEASE, WATER_RELEASE, FIRE_RELEASE, LIGHTNING_RELEASE, YIN_RELEASE, YANG_RELEASE;

	static {
		TAB = registerItem("tab");
		EARTH_RELEASE = registerNatureRelease("earth");
		WIND_RELEASE = registerNatureRelease("wind");
		WATER_RELEASE = registerNatureRelease("water");
		FIRE_RELEASE = registerNatureRelease("fire");
		LIGHTNING_RELEASE = registerNatureRelease("lightning");
		YIN_RELEASE = registerNatureRelease("yin");
		YANG_RELEASE = registerNatureRelease("yang");
	}

	private static RegistryObject<Item> registerItem(String name) {
		return MOD_ITEMS.register(name, () -> new NatureReleaseItem(new Item.Properties()));
	}

	private static RegistryObject<Item> registerNatureRelease(String name) {
		return MOD_ITEMS.register(name, () -> new NatureReleaseItem(new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		MOD_ITEMS.register(eventBus);
	}
}