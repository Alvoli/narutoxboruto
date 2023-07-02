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
		JUTSU_LEARNER = registerItem("jutsu_learner");
		FIRE_DNA = registerDna("fire_dna", FIRE_RELEASE);
		EARTH_DNA = registerDna("earth_dna", EARTH_RELEASE);
		WIND_DNA = registerDna("wind_dna", WIND_RELEASE);
		WATER_DNA = registerDna("water_dna", WATER_RELEASE);
		YIN_DNA = registerDna("yin_dna", YIN_RELEASE);
		YANG_DNA = registerDna("yang_dna",YANG_RELEASE);
		LIGHTNING_DNA = registerDna("lightning_dna",LIGHTNING_RELEASE);
	}

	private static RegistryObject<Item> registerItem(String name) {
		return MOD_ITEMS.register(name, () -> new Item(new Item.Properties()));
	}

	private static RegistryObject<Item> registerDna(String name, RegistryObject<Item> pRelease){
		return MOD_ITEMS.register(name,()-> new ReleaseDnaBottleItem(new Item.Properties(),pRelease.getHolder().get().get()));
	}

	private static RegistryObject<Item> registerNatureRelease(String name) {
		return MOD_ITEMS.register(name, () -> new NatureReleaseItem(new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		MOD_ITEMS.register(eventBus);
	}
}