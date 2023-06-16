package com.turgyn.narutoxboruto.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class DnaBottleItem extends Item {
	public DnaBottleItem(Properties properties) {
		super(properties);
	}

	public void giveKG(RandomSource random) {
		//calculate KG chance here
		random.nextInt();
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
		this.giveKG(pLivingEntity.getRandom());
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
