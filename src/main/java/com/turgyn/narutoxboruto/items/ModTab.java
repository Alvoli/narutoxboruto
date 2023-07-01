package com.turgyn.narutoxboruto.items;

import com.turgyn.narutoxboruto.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTab {
	public static CreativeModeTab TAB;

	@SubscribeEvent
	public static void registerModTab(CreativeModeTabEvent.Register event) {
		TAB = event.registerCreativeModeTab(new ResourceLocation(Main.MOD_ID, "tab"), builder -> {
			builder.icon(() -> new ItemStack(ModItems.TAB.get())).title(
					Component.translatable("itemGroup.narutoxboruto")).build();
		});
	}

	@SubscribeEvent
	public static void addItemsToTab(CreativeModeTabEvent.BuildContents event) {
		if (event.getTab().equals(ModTab.TAB)) {
			event.accept(ModItems.EARTH_RELEASE.get());
			event.accept(ModItems.WATER_RELEASE.get());
			event.accept(ModItems.WIND_RELEASE.get());
			event.accept(ModItems.FIRE_RELEASE.get());
			event.accept(ModItems.LIGHTNING_RELEASE.get());
			event.accept(ModItems.YANG_RELEASE.get());
			event.accept(ModItems.YIN_RELEASE.get());
			event.accept(ModItems.DNA_BOTTLE.get());
			event.accept(ModItems.JUTSU_LEARNER.get());
			event.accept(ModItems.FIRE_DNA.get());
			event.accept(ModItems.WIND_DNA.get());
			event.accept(ModItems.EARTH_DNA.get());
			event.accept(ModItems.WATER_DNA.get());
			event.accept(ModItems.YANG_DNA.get());
			event.accept(ModItems.YIN_DNA.get());
			event.accept(ModItems.LIGHTNING_DNA.get());

		}
	}

	public static void register() { }
}