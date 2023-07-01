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
public class CapabilityEvents {
	private static final Random RANDOM = new Random();

	private static final List<String> CLAN_LIST = Arrays.asList("fuma", "nara", "shiin", "shirogane", "uzumaki");

	private static final List<String> AFF_LIST = Arrays.asList("cloud", "leaf", "mist", "rain", "sand", "sound",
			"stone");

	private static boolean taiFlag, kenFlag;


	private static void doSpawnStuff(ServerPlayer serverPlayer) {

		serverPlayer.getCapability(CLAN).ifPresent(clan -> clan.setClan(getRandomString(CLAN_LIST), serverPlayer));
		serverPlayer.getCapability(AFFILIATION).ifPresent(
				affiliation -> affiliation.setAffiliation(getRandomString(AFF_LIST), serverPlayer));
		serverPlayer.getCapability(RANK).ifPresent(rank -> rank.setRank("academy", serverPlayer));
		List<Item> newReleaseList = new ArrayList<>();
		int l = 0;
		while (l <= RANDOM.nextInt(3)) {
			Item stack = null;
			stack = getNewRelease();
			if (!newReleaseList.contains(stack)) {
				serverPlayer.addItem(stack.getDefaultInstance());
				newReleaseList.add(stack);
				l++;
			}
		}

	}
	@SubscribeEvent
	public static void TEST_ONLY_DELETE_LATER(PlayerSleepInBedEvent event) {
		if (event.getEntity() instanceof ServerPlayer) {
			getNewRelease();

		}
	}

	@SubscribeEvent
	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(Main.MOD_ID, "shinobi_stats"), new CapabilityProvider());
		}
	}

	private void givePlayerStatBonuses(ServerPlayer serverPlayer){
		serverPlayer.getCapability(CLAN).ifPresent((clan)-> {
			switch(clan.getValue()){
				case "uzumaki" ->{

				}
			}
		});
	}

	@SubscribeEvent
	public static void onPlayerFirstJoin(EntityJoinLevelEvent event) {
		if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer
				&& ModUtil.getPlayerStat(serverPlayer, Stats.LEAVE_GAME) == 0) {
			serverPlayer.getCapability(CLAN).ifPresent(clan -> clan.setClan(getRandomString(CLAN_LIST), serverPlayer));
			serverPlayer.getCapability(AFFILIATION).ifPresent(
					affiliation -> affiliation.setAffiliation(getRandomString(AFF_LIST), serverPlayer));
			serverPlayer.getCapability(RANK).ifPresent(rank -> rank.setRank("academy", serverPlayer));
			List<Item> newReleaseList = new ArrayList<>();
			int l = 0;
			while (l <= RANDOM.nextInt(3)) {
				Item stack = getNewRelease();
				if (!newReleaseList.contains(stack)) {
					serverPlayer.addItem(stack.getDefaultInstance());
					newReleaseList.add(stack);
					l++;
				}
			}
			String list = newReleaseList.toString();
			String formattedList = list.substring(1, list.length() - 1);
			serverPlayer.getCapability(RELEASE_LIST).ifPresent(
					releaseList -> releaseList.updateReleaseList(formattedList));
			msgPlayerInfo(serverPlayer);
		}
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
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.getEntity() instanceof ServerPlayer newPlayer
				&& event.getOriginal() instanceof ServerPlayer original) {
			event.getOriginal().reviveCaps();
			sendClientMessage(newPlayer, "control", " has", true);
			original.getCapability(AFFILIATION).ifPresent(oldCap -> newPlayer.getCapability(AFFILIATION)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(CHAKRA).ifPresent(
					oldCap -> newPlayer.getCapability(CHAKRA).ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(CLAN).ifPresent(
					oldCap -> newPlayer.getCapability(CLAN).ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(GENJUTSU).ifPresent(oldCap -> newPlayer.getCapability(GENJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(KENJUTSU).ifPresent(oldCap -> newPlayer.getCapability(KENJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(KINJUTSU).ifPresent(oldCap -> newPlayer.getCapability(KINJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(MEDICAL).ifPresent(
					oldCap -> newPlayer.getCapability(MEDICAL).ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(RANK).ifPresent(
					oldCap -> newPlayer.getCapability(RANK).ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(SENJUTSU).ifPresent(oldCap -> newPlayer.getCapability(SENJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(SHURIKENJUTSU).ifPresent(oldCap -> newPlayer.getCapability(SHURIKENJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(SHINOBI_POINTS).ifPresent(oldCap -> newPlayer.getCapability(SHINOBI_POINTS)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(SPEED).ifPresent(
					oldCap -> newPlayer.getCapability(SPEED).ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(SUMMONING).ifPresent(oldCap -> newPlayer.getCapability(SUMMONING)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			original.getCapability(TAIJUTSU).ifPresent(oldCap -> newPlayer.getCapability(TAIJUTSU)
					.ifPresent(newCap -> newCap.copyFrom(oldCap, newPlayer)));
			msgPlayerInfo(newPlayer);
		}
	}
}