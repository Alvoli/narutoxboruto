package com.turgyn.narutomod.event;

import com.turgyn.narutomod.Main;
import com.turgyn.narutomod.capabilities.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import static com.turgyn.narutomod.capabilities.CapabilityProvider.*;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class CapabilityEvents {
	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(Main.MOD_ID, "shinobi_stats"), new CapabilityProvider());
		}
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && event.isWasDeath()) {
//			Player original = event.getOriginal();
//			original.getCapability(CHAKRA).ifPresent(oldPlayer -> serverPlayer.getCapability(CHAKRA)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(GENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(GENJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(KENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(KENJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(KINJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(KINJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(MEDICAL).ifPresent(oldPlayer -> serverPlayer.getCapability(MEDICAL)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(SENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(SENJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(SHURIKENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(SHURIKENJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(SHINOBI_POINTS).ifPresent(oldPlayer -> serverPlayer.getCapability(SHINOBI_POINTS)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(SPEED).ifPresent(oldPlayer -> serverPlayer.getCapability(SPEED)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(SUMMONING).ifPresent(oldPlayer -> serverPlayer.getCapability(SUMMONING)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
//			original.getCapability(TAIJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(TAIJUTSU)
//					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			serverPlayer.sendSystemMessage(Component.literal("Shinobis stats reset upon death for testing purposes"));
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(Chakra.class);
		event.register(Genjutsu.class);
		event.register(ShinobiPoints.class);
		event.register(Kenjutsu.class);
		event.register(Kinjutsu.class);
		event.register(Ninjutsu.class);
		event.register(Medical.class);
		event.register(Senjutsu.class);
		event.register(Shurikenjutsu.class);
		event.register(Speed.class);
		event.register(Summoning.class);
		event.register(Taijutsu.class);
	}

	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide()) {
			if (event.getEntity() instanceof ServerPlayer player) {
				player.getCapability(CHAKRA).ifPresent(chakra -> chakra.syncValue(player));
				player.getCapability(GENJUTSU).ifPresent(genjutsu -> genjutsu.syncValue(player));
				player.getCapability(KENJUTSU).ifPresent(kenjutsu -> kenjutsu.syncValue(player));
				player.getCapability(KINJUTSU).ifPresent(kinjutsu -> kinjutsu.syncValue(player));
				player.getCapability(MEDICAL).ifPresent(medical -> medical.syncValue(player));
				player.getCapability(NINJUTSU).ifPresent(ninjutsu -> ninjutsu.syncValue(player));
				player.getCapability(SENJUTSU).ifPresent(senjutsu -> senjutsu.syncValue(player));
				player.getCapability(SHINOBI_POINTS).ifPresent(shinobiPoints -> shinobiPoints.syncValue(player));
				player.getCapability(SHURIKENJUTSU).ifPresent(shurikenjutsu -> shurikenjutsu.syncValue(player));
				player.getCapability(SPEED).ifPresent(speed -> speed.syncValue(player));
				player.getCapability(SUMMONING).ifPresent(summoning -> summoning.syncValue(player));
				player.getCapability(TAIJUTSU).ifPresent(taijutsu -> taijutsu.syncValue(player));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHit(LivingHurtEvent event) {
		if (event.getSource().getEntity() instanceof ServerPlayer player) {
			player.getCapability(TAIJUTSU).ifPresent(taijutsu -> taijutsu.addValue(1, player));
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER && event.player instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(CHAKRA).ifPresent(chakra -> {
				if (chakra.getValue() < chakra.MAX_VALUE && event.player.getRandom().nextFloat() < 0.001f) {
					chakra.addValue(1, serverPlayer);
				}
			});
			serverPlayer.getCapability(SPEED).ifPresent(speed -> {
				if (speed.getValue() / 10 > 0) {
					serverPlayer.addEffect(
							new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION,
									speed.getValue() / 10 - 1, false, false));
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
		if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(SPEED).ifPresent(speed -> {
				if (speed.getValue() < speed.MAX_VALUE) {
					speed.addValue(1, serverPlayer);
				}
			});
		}
	}
}