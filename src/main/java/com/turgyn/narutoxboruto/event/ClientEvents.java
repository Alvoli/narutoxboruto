package com.turgyn.narutoxboruto.event;

import com.turgyn.narutoxboruto.Main;
import com.turgyn.narutoxboruto.client.ModHudOverlays;
import com.turgyn.narutoxboruto.gui.ShinobiStatsGui;
import com.turgyn.narutoxboruto.networking.ChakraHandlerC2SPacket;
import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.util.ModKeyBinds;
import net.minecraft.client.Minecraft;
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
				Minecraft minecraft = Minecraft.getInstance();
				if (!minecraft.player.isCrouching()) {
					minecraft.setScreen(new ShinobiStatsGui());
				}
				else {
					ModPacketHandler.sendToServer(new ChakraHandlerC2SPacket());
				}
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
			event.registerAboveAll("chakra", ModHudOverlays.CHAKRA);
		}
	}
}
