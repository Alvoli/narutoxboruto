package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.YANG_RELEASE;

public class YangDnaBottle extends DnaBottleItem{
    public YangDnaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public Item getRelease() {
        return YANG_RELEASE.get();
    }
}

