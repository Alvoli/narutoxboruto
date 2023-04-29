package com.turgyn.narutomod.items;

import com.turgyn.narutomod.capabilities.CapabilityProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NatureReleaseItem extends Item {
	public NatureReleaseItem(Properties pProperties) {
		super(pProperties.stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if (pLevel instanceof ServerLevel server && pPlayer instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(CapabilityProvider.CHAKRA).ifPresent(chakra -> {
				if (chakra.getValue() > 0) {
					chakra.subValue(1, serverPlayer);
					server.playSound(null, pPlayer.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS,
							1.0F, (float) (0.8F + (Math.random() * 0.2D)));
				}
			});
			return InteractionResultHolder.success(serverPlayer.getItemInHand(pUsedHand));
		}
		return super.use(pLevel, pPlayer, pUsedHand);
	}
}
