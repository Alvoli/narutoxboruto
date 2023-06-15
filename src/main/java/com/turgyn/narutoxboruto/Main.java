package com.turgyn.narutoxboruto;

import com.mojang.logging.LogUtils;
import com.turgyn.narutoxboruto.event.ClientEvents;
import com.turgyn.narutoxboruto.items.ModItems;
import com.turgyn.narutoxboruto.items.ModTab;
import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main {
	public static final String MOD_ID = "narutoxboruto";

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
