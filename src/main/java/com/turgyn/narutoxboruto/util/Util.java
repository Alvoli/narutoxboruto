package com.turgyn.narutoxboruto.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

public class Util {
	public static int getPlayerStat(ServerPlayer serverPlayer, ResourceLocation stat) {
		return serverPlayer.getStats().getValue(Stats.CUSTOM.get(stat));
	}
}
