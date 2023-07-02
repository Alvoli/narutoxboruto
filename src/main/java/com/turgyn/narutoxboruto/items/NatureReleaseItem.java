package com.turgyn.narutoxboruto.items;

import com.turgyn.narutoxboruto.capabilities.CapabilityProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class NatureReleaseItem extends ProjectileWeaponItem implements Vanishable {
	public NatureReleaseItem(Properties pProperties) {
		super(pProperties.stacksTo(1));
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return null;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 256;
	}

	@Override
	public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
		if (pEntityLiving instanceof Player player) {
			int i = this.getUseDuration(pStack) - pTimeLeft;
			if (i < 20) { return; }
			if (pLevel instanceof ServerLevel server && pEntityLiving instanceof ServerPlayer serverPlayer) {
				LargeFireball blob = new LargeFireball(pLevel, pEntityLiving, 0,0,0,2);
				blob.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
				pLevel.addFreshEntity(blob);
				pStack.hurtAndBreak(1, player, (player1) -> {
					player1.broadcastBreakEvent(player.getUsedItemHand());
				});
				serverPlayer.getCapability(CapabilityProvider.CHAKRA).ifPresent(chakra -> {
					if (chakra.getValue() > 0) {
						chakra.subValue(1, serverPlayer);
						server.playSound(null, serverPlayer.blockPosition(), SoundEvents.EVOKER_CAST_SPELL,
								SoundSource.PLAYERS, 1.0F, (float) (0.8F + (Math.random() * 0.2D)));
						player.awardStat(Stats.ITEM_USED.get(this));
					}
				});
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
		pPlayer.startUsingItem(pHand);
		return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
	}
}