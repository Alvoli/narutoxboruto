package com.turgyn.narutomod.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ModCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	public static Capability<PlayerChakra> CHAKRA = CapabilityManager.get(new CapabilityToken<>() { });

	private PlayerChakra chakra = null;

	private final LazyOptional<PlayerChakra> optional = LazyOptional.of(this::createPlayerChakra);

	private PlayerChakra createPlayerChakra() {
		if (this.chakra == null) {
			this.chakra = new PlayerChakra();
		}
		return this.chakra;
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == CHAKRA) {
			return optional.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createPlayerChakra().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createPlayerChakra().loadNBTData(nbt);
	}
}