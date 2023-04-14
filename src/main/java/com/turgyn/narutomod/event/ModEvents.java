package com.turgyn.narutomod.event;

import com.turgyn.narutomod.Main;
import com.turgyn.narutomod.capabilities.ModCapabilityProvider;
import com.turgyn.narutomod.capabilities.PlayerChakra;
import com.turgyn.narutomod.networking.ChakraDataSyncPacket;
import com.turgyn.narutomod.networking.ModPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class ModEvents {
	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			if (!event.getObject().getCapability(ModCapabilityProvider.CHAKRA).isPresent()) {
				event.addCapability(new ResourceLocation(Main.MOD_ID, "properties"), new ModCapabilityProvider());
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			event.getOriginal().getCapability(ModCapabilityProvider.CHAKRA).ifPresent(
					oldStore -> event.getOriginal().getCapability(ModCapabilityProvider.CHAKRA)
							.ifPresent(newStore -> newStore.copyFrom(oldStore)));
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(PlayerChakra.class);
	}

	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide()) {
			if (event.getEntity() instanceof ServerPlayer player) {
				player.getCapability(ModCapabilityProvider.CHAKRA).ifPresent(
						chakra -> ModPacketHandler.sendToPlayer(new ChakraDataSyncPacket(chakra.getValue()), player));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER && event.player instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(ModCapabilityProvider.CHAKRA).ifPresent(chakra -> {
				if (chakra.getValue() < chakra.MAX_VALUE && event.player.getRandom().nextFloat() < 0.0005f) {
					chakra.addValue(1);
					ModPacketHandler.sendToPlayer(new ChakraDataSyncPacket(chakra.getValue()), serverPlayer);
				}
			});
		}
	}
}