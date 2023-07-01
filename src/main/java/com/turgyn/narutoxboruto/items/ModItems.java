package com.turgyn.narutoxboruto.items;

import com.turgyn.narutoxboruto.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.DeferredRegister.create;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

public class ModItems {
	public static final DeferredRegister<Item> MOD_ITEMS = create(ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> TAB, EARTH_RELEASE, WIND_RELEASE, WATER_RELEASE, FIRE_RELEASE, LIGHTNING_RELEASE, YIN_RELEASE, YANG_RELEASE,DNA_BOTTLE,JUTSU_LEARNER, FIRE_DNA, WIND_DNA,EARTH_DNA, WATER_DNA, YIN_DNA, YANG_DNA, LIGHTNING_DNA;

	static {
		TAB = registerItem("tab");
		EARTH_RELEASE = registerNatureRelease("earth");
		WIND_RELEASE = registerNatureRelease("wind");
		WATER_RELEASE = registerNatureRelease("water");
		FIRE_RELEASE = registerNatureRelease("fire");
		LIGHTNING_RELEASE = registerNatureRelease("lightning");
		YIN_RELEASE = registerNatureRelease("yin");
		YANG_RELEASE = registerNatureRelease("yang");
		DNA_BOTTLE = MOD_ITEMS.register("dna_bottle", () -> new DnaBottleItem(new Item.Properties()));
		FIRE_DNA = MOD_ITEMS.register("fire_dna", () -> new FireDnaBottle(new Item.Properties()));
		JUTSU_LEARNER = registerItem("jutsu_learner");
		WIND_DNA = MOD_ITEMS.register("wind_dna", () -> new WindDnaBottle(new Item.Properties()));
		EARTH_DNA = MOD_ITEMS.register("earth_dna", () -> new EarthDnaBottle(new Item.Properties()));
		WATER_DNA = MOD_ITEMS.register("water_dna", () -> new WaterDnaBottle(new Item.Properties()));
		YIN_DNA = MOD_ITEMS.register("yin_dna",() -> new YinDnaBottle(new Item.Properties()));
		YANG_DNA = MOD_ITEMS.register("yang_dna", () -> new YangDnaBottle(new Item.Properties()));
		LIGHTNING_DNA = MOD_ITEMS.register("lightning_dna", () -> new LightningDnaBottle(new Item.Properties()));
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