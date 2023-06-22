package com.turgyn.narutoxboruto.event;

import com.turgyn.narutoxboruto.Main;
import com.turgyn.narutoxboruto.capabilities.*;
import com.turgyn.narutoxboruto.util.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
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
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.*;
import static com.turgyn.narutoxboruto.client.PlayerData.*;
import static com.turgyn.narutoxboruto.items.ModItems.*;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class CapabilityEvents {
	private static final Random RANDOM = new Random();

	private static final String[] CLAN_LIST = {
			"fuma", "nara", "shiin", "shirogane", "uzumaki",
	};

	private static final String[] AFF_LIST = {
			"cloud", "leaf", "mist", "rain", "sand", "sound", "stone",
	};

	private static boolean taiFlag, kenFlag;

	private static void doSpawnStuff(ServerPlayer serverPlayer) {
		int clanRand = RANDOM.nextInt(CLAN_LIST.length);
		int affRand = RANDOM.nextInt(AFF_LIST.length);
		serverPlayer.getCapability(CLAN).ifPresent(clan -> clan.setClan(CLAN_LIST[clanRand], serverPlayer));
		serverPlayer.getCapability(AFFILIATION).ifPresent(
				affiliation -> affiliation.setAffiliation(AFF_LIST[affRand], serverPlayer));
		serverPlayer.getCapability(RANK).ifPresent(rank -> rank.setRank("academy", serverPlayer));
		sendClientMessage(serverPlayer, "clan", getClan());
		sendClientMessage(serverPlayer, "affiliation", getAffiliation());
		sendClientMessage(serverPlayer, "rank", getRank());
		List<Item> itemList = new ArrayList<>();
		int l = 0;
		while (l <= RANDOM.nextInt(3)) {
			int rand = RANDOM.nextInt(12);
			Item stack = null;
			switch (rand) {
				case 0, 1 -> stack = EARTH_RELEASE.get();
				case 2, 3 -> stack = FIRE_RELEASE.get();
				case 4, 5 -> stack = LIGHTNING_RELEASE.get();
				case 6, 7 -> stack = WATER_RELEASE.get();
				case 8, 9 -> stack = WIND_RELEASE.get();
				case 10 -> stack = YIN_RELEASE.get();
				case 11 -> stack = YANG_RELEASE.get();
			}
			if (!itemList.contains(stack)) {
				itemList.add(stack);
				serverPlayer.addItem(stack.getDefaultInstance());
				l++;
			}
		}

		String list = itemList.toString();
		serverPlayer.getCapability(RELEASE_LIST).ifPresent(releaseList -> releaseList.updateReleaseList(
				list, serverPlayer));
		String cleanReleaseText = StringUtils.capitaliseAllWords(list.substring(1, list.length()-1));
		String s = itemList.size() > 1 ? "s" : "";
		sendClientMessage("release" + s, Component.literal(cleanReleaseText), serverPlayer);
	}

	private static void sendClientMessage(ServerPlayer serverPlayer, String msg, String s) {
		sendClientMessage(msg, Component.translatable(msg + ".narutoxboruto." + s), serverPlayer);
	}

	private static void sendClientMessage(String msg, MutableComponent s, ServerPlayer serverPlayer) {
		serverPlayer.displayClientMessage(Component.translatable("msg.narutoxboruto." + msg, s), false);
	}

	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(Main.MOD_ID, "shinobi_stats"), new CapabilityProvider());
		}
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && event.isWasDeath()) {
			doSpawnStuff(serverPlayer);
			Player original = event.getOriginal();
			original.getCapability(AFFILIATION).ifPresent(oldPlayer -> serverPlayer.getCapability(AFFILIATION)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(CHAKRA).ifPresent(oldPlayer -> serverPlayer.getCapability(CHAKRA)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(GENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(GENJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(KENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(KENJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(KINJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(KINJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(MEDICAL).ifPresent(oldPlayer -> serverPlayer.getCapability(MEDICAL)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(RANK).ifPresent(oldPlayer -> serverPlayer.getCapability(RANK)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(SENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(SENJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(SHURIKENJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(SHURIKENJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(SHINOBI_POINTS).ifPresent(oldPlayer -> serverPlayer.getCapability(SHINOBI_POINTS)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(SPEED).ifPresent(oldPlayer -> serverPlayer.getCapability(SPEED)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(SUMMONING).ifPresent(oldPlayer -> serverPlayer.getCapability(SUMMONING)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			original.getCapability(TAIJUTSU).ifPresent(oldPlayer -> serverPlayer.getCapability(TAIJUTSU)
					.ifPresent(newPlayer -> newPlayer.copyFrom(oldPlayer, serverPlayer)));
			serverPlayer.sendSystemMessage(Component.literal("Shinobi stats reset"));
		}
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(Affiliation.class);
		event.register(Chakra.class);
		event.register(Clan.class);
		event.register(Genjutsu.class);
		event.register(ShinobiPoints.class);
		event.register(Kenjutsu.class);
		event.register(Kinjutsu.class);
		event.register(Ninjutsu.class);
		event.register(Rank.class);
		event.register(ReleaseList.class);
		event.register(Medical.class);
		event.register(Senjutsu.class);
		event.register(Shurikenjutsu.class);
		event.register(Speed.class);
		event.register(Summoning.class);
		event.register(Taijutsu.class);
	}

	@SubscribeEvent
	public static void onJoinWorldSyncCap(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(AFFILIATION).ifPresent(clan -> clan.syncValue(serverPlayer));
			serverPlayer.getCapability(CHAKRA).ifPresent(chakra -> chakra.syncValue(serverPlayer));
			serverPlayer.getCapability(CLAN).ifPresent(clan -> clan.syncValue(serverPlayer));
			serverPlayer.getCapability(GENJUTSU).ifPresent(genjutsu -> genjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(KENJUTSU).ifPresent(kenjutsu -> kenjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(KINJUTSU).ifPresent(kinjutsu -> kinjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(MEDICAL).ifPresent(medical -> medical.syncValue(serverPlayer));
			serverPlayer.getCapability(NINJUTSU).ifPresent(ninjutsu -> ninjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(RANK).ifPresent(rank -> rank.syncValue(serverPlayer));
//			serverPlayer.getCapability(RELEASE_LIST).ifPresent(releaseList -> releaseList.syncValue(serverPlayer));
			serverPlayer.getCapability(SENJUTSU).ifPresent(senjutsu -> senjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(SHINOBI_POINTS).ifPresent(shinobiPoints -> {
				shinobiPoints.syncValue(serverPlayer);
			});
			serverPlayer.getCapability(SHURIKENJUTSU).ifPresent(shurikenjutsu -> shurikenjutsu.syncValue(serverPlayer));
			serverPlayer.getCapability(SPEED).ifPresent(speed -> speed.syncValue(serverPlayer));
			serverPlayer.getCapability(SUMMONING).ifPresent(summoning -> summoning.syncValue(serverPlayer));
			serverPlayer.getCapability(TAIJUTSU).ifPresent(taijutsu -> taijutsu.syncValue(serverPlayer));
		}
	}

	@SubscribeEvent
	public static void onPlayerFirstJoin(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer
				&& Util.getPlayerStat(serverPlayer, Stats.LEAVE_GAME) == 0) {
			doSpawnStuff(serverPlayer);
		}
	}

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

	private static void setBaseAttributeValue(ServerPlayer serverPlayer, Attribute attribute, double i) {
		serverPlayer.getAttributes().getInstance(attribute).setBaseValue(i);
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
				int i = (Util.getPlayerStat(serverPlayer, Stats.SPRINT_ONE_CM) / 15000);
				int j = speed.getValue();
				if (j < i) {
					speed.addValue(i - j, serverPlayer);
				}
			});
		}
	}
}