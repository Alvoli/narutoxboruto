package com.turgyn.narutomod.event;

import com.turgyn.narutomod.Main;
import com.turgyn.narutomod.client.ModHudOverlays;
import com.turgyn.narutomod.networking.ChakraHandlerPacket;
import com.turgyn.narutomod.networking.ModPacketHandler;
import com.turgyn.narutomod.util.ModKeyBinds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
	@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT)
	public static class ClientForgeEvents {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.Key event) {
			if (ModKeyBinds.OPEN_GUI.consumeClick()) {
				ModPacketHandler.sendToServer(new ChakraHandlerPacket());
			}
		}
	}

	@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientModBusEvents {
		@SubscribeEvent
		public static void onKeyRegister(RegisterKeyMappingsEvent event) {
			event.register(ModKeyBinds.OPEN_GUI);
		}

		@SubscribeEvent
		public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
			event.registerAboveAll("chackra", ModHudOverlays.CHAKRA);
		}
	}
}