package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

public class ReleaseDnaBottleItem extends DnaBottleItem {
	private final Item pRelease;

	public ReleaseDnaBottleItem(Properties properties, Item pRelease) {
		super(properties);
		this.pRelease = pRelease;
	}

	@Override
	public Item getRelease() {
		return pRelease;
	}
}
