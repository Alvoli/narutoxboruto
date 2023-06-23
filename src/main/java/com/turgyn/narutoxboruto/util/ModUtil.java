package com.turgyn.narutoxboruto.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.Item;

import java.util.Random;

import static com.turgyn.narutoxboruto.items.ModItems.*;

public class ModUtil {
	public static int getPlayerStat(ServerPlayer serverPlayer, ResourceLocation stat) {
		return serverPlayer.getStats().getValue(Stats.CUSTOM.get(stat));
	}

	public static Item getNewRelease(Item stack) {
		switch (new Random().nextInt(12)) {
			case 0, 1 -> stack = EARTH_RELEASE.get();
			case 2, 3 -> stack = FIRE_RELEASE.get();
			case 4, 5 -> stack = LIGHTNING_RELEASE.get();
			case 6, 7 -> stack = WATER_RELEASE.get();
			case 8, 9 -> stack = WIND_RELEASE.get();
			case 10 -> stack = YIN_RELEASE.get();
			case 11 -> stack = YANG_RELEASE.get();
		}
		return stack;
	}
}
