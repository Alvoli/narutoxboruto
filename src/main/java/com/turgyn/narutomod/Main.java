package com.turgyn.narutomod;

import com.mojang.logging.LogUtils;
import com.turgyn.narutomod.event.ClientEvents;
import com.turgyn.narutomod.items.ModItems;
import com.turgyn.narutomod.items.ModTab;
import com.turgyn.narutomod.networking.ModPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main {
	public static final String MOD_ID = "narutomod";

	private static final Logger LOGGER = LogUtils.getLogger();

	public Main() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.register(modEventBus);
		ModTab.register();
		modEventBus.addListener(this::commonSetup);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> { });
		ModPacketHandler.register();
	}
}
