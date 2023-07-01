package com.turgyn.narutoxboruto.event;

import com.turgyn.narutoxboruto.Main;
import com.turgyn.narutoxboruto.capabilities.CapabilityProvider;
import com.turgyn.narutoxboruto.util.ModUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.*;
import static com.turgyn.narutoxboruto.util.ModUtil.*;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class StatEvents {
	private static boolean taiFlag, kenFlag;

	@SubscribeEvent
	public static void onPlayerHit(LivingHurtEvent event) {
		if (!event.getSource().isIndirect() && event.getSource().getEntity() instanceof ServerPlayer player) {
			if (player.getMainHandItem().isEmpty()) {
				player.getCapability(TAIJUTSU).ifPresent(taijutsu -> taijutsu.addValue(1, player));
				taiFlag = true;
			}
			else if (player.getMainHandItem().getItem() instanceof SwordItem) {
				player.getCapability(KENJUTSU).ifPresent(taijutsu -> taijutsu.addValue(1, player));
				kenFlag = true;
			}
			if (event.getEntity() instanceof ServerPlayer serverPlayer) {
				serverPlayer.getCapability(MEDICAL).ifPresent(medical -> {
					medical.addValue(1, serverPlayer);
					setBaseAttributeValue(serverPlayer, Attributes.MAX_HEALTH, (double) medical.getValue() / 20 + 20);
				});
			}
		}
	}

	@SubscribeEvent
	public static void addStatBonuses(TickEvent.PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER && event.player instanceof ServerPlayer serverPlayer) {
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
	public static void addStatExtraDamage(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (!entity.level.isClientSide() && entity.lastHurtByPlayer instanceof ServerPlayer player) {
			if (taiFlag) {
				player.getCapability(TAIJUTSU).ifPresent(
						taijutsu -> entity.hurt(entity.lastHurtByPlayer.damageSources().generic(),
								(float) taijutsu.getValue() / 33));
				taiFlag = false;
			}
			else if (kenFlag) {
				player.getCapability(KENJUTSU).ifPresent(
						kenjutsu -> entity.hurt(entity.lastHurtByPlayer.damageSources().generic(),
								(float) kenjutsu.getValue() / 33));
				kenFlag = false;
			}
		}
	}

	@SubscribeEvent
	public static void tickStats(TickEvent.PlayerTickEvent event) {
		if (event.side == LogicalSide.SERVER && event.player instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(CHAKRA).ifPresent(chakra -> {
				if (event.player.getRandom().nextFloat() < 0.001f && chakra.getValue() < chakra.MAX_VALUE) {
					chakra.addValue(1, serverPlayer);
				}
			});
			serverPlayer.getCapability(SPEED).ifPresent(speed -> {
				int i = (ModUtil.getPlayerStat(serverPlayer, Stats.SPRINT_ONE_CM) / 15000);
				int j = speed.getValue();
				if (j < i) {
					speed.addValue(i - j, serverPlayer);
				}
			});
		}
	}
}