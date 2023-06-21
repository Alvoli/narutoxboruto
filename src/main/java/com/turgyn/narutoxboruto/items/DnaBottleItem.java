package com.turgyn.narutoxboruto.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import static com.turgyn.narutoxboruto.items.ModItems.*;

public class DnaBottleItem extends Item {
	public DnaBottleItem(Properties properties) {
		super(properties);
	}

	public void giveLoot(LivingEntity pLivingEntity) {
		//todo KG
		if (pLivingEntity.getRandom().nextBoolean()) { //50%
			if (pLivingEntity instanceof ServerPlayer player) {
				int rand = player.getRandom().nextInt(12);
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
				player.addItem(stack.getDefaultInstance());
			}
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
		this.giveLoot(pLivingEntity);
		return this.drink(pStack, pLevel, pLivingEntity);
	}

	//emits the drink sound, returns the stack correctly, sends game statistics
	public ItemStack drink(ItemStack pFood, Level pLevel, LivingEntity pLivingEntity) {
		pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(),
				SoundEvents.GENERIC_DRINK, SoundSource.NEUTRAL, 1.0F,
				1.0F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.4F);
		if (!(pLivingEntity instanceof Player) || !((Player) pLivingEntity).getAbilities().instabuild) {
			pFood.shrink(1);
		}
		if (pLivingEntity instanceof ServerPlayer serverplayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pFood);
			serverplayer.awardStat(Stats.ITEM_USED.get(this));
		}
		pLivingEntity.gameEvent(GameEvent.DRINK);
		return pFood;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 32;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
		return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
	}
}
