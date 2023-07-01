package com.turgyn.narutoxboruto.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import org.codehaus.plexus.util.StringUtils;

import java.util.List;
import java.util.Random;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.RELEASE_LIST;
import static com.turgyn.narutoxboruto.client.PlayerData.*;
import static com.turgyn.narutoxboruto.items.ModItems.*;

public class ModUtil {
	private static final Random random = new Random();

	public static int getPlayerStat(ServerPlayer serverPlayer, ResourceLocation stat) {
		return serverPlayer.getStats().getValue(Stats.CUSTOM.get(stat));
	}

	public static Item getNewRelease(Item release) {
		switch (random.nextInt(12)) {
			case 0, 1 -> release = EARTH_RELEASE.get();
			case 2, 3 -> release = FIRE_RELEASE.get();
			case 4, 5 -> release = LIGHTNING_RELEASE.get();
			case 6, 7 -> release = WATER_RELEASE.get();
			case 8, 9 -> release = WIND_RELEASE.get();
			case 10 -> release = YIN_RELEASE.get();
			case 11 -> release = YANG_RELEASE.get();
		}
		return release;
	}

	public static void sendClientMessage(ServerPlayer serverPlayer, String msg, String s, boolean b) {
		Component message = Component.translatable("msg.narutoxboruto." + msg,
				b ? s : Component.translatable(msg + ".narutoxboruto." + s));
		serverPlayer.displayClientMessage(message, false);
	}

	public static String getRandomString(List<String> array) {
		return array.get(random.nextInt(array.size()));
	}

	public static void setBaseAttributeValue(ServerPlayer serverPlayer, Attribute attribute, double i) {
		serverPlayer.getAttributes().getInstance(attribute).setBaseValue(i);
	}

	public static void msgPlayerInfo(ServerPlayer serverPlayer) {
		sendClientMessage(serverPlayer, "affiliation", getAffiliation(), false);
		sendClientMessage(serverPlayer, "clan", getClan(), false);
		sendClientMessage(serverPlayer, "rank", getRank(), false);
		serverPlayer.getCapability(RELEASE_LIST).ifPresent(releaseList -> {
			String list = releaseList.getList();
			String s = list.contains(",") ? "s" : "";
			sendClientMessage(serverPlayer, "release" + s, StringUtils.capitaliseAllWords(list), true);
		});
	}
}
