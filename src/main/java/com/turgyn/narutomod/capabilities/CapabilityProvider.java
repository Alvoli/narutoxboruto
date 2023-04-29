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

public class CapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	public static Capability<Chakra> CHAKRA = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Genjutsu> GENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Kenjutsu> KENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Kinjutsu> KINJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Medical> MEDICAL = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Ninjutsu> NINJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Senjutsu> SENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<ShinobiPoints> SHINOBI_POINTS = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Shurikenjutsu> SHURIKENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Speed> SPEED = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Summoning> SUMMONING = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Taijutsu> TAIJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	private Genjutsu genjutsu = null;

	private final LazyOptional<Genjutsu> lazyGenjutsu = LazyOptional.of(this::createPlayerGenjutsu);

	private Chakra chakra = null;

	private final LazyOptional<Chakra> lazyChakra = LazyOptional.of(this::createPlayerChakra);

	private Kenjutsu kenjutsu = null;

	private final LazyOptional<Kenjutsu> lazyKenjutsu = LazyOptional.of(this::createPlayerKenjutsu);

	private Kinjutsu kinjutsu = null;

	private final LazyOptional<Kinjutsu> lazyKinjutsu = LazyOptional.of(this::createPlayerKinjutsu);

	private Medical medical = null;

	private final LazyOptional<Medical> lazyMedical = LazyOptional.of(this::createPlayerMedical);

	private Ninjutsu ninjutsu = null;

	private final LazyOptional<Ninjutsu> lazyNinjutsu = LazyOptional.of(this::createPlayerNinjutsu);

	private Senjutsu senjutsu = null;

	private final LazyOptional<Senjutsu> lazySenjutsu = LazyOptional.of(this::createPlayerSenjutsu);

	private ShinobiPoints shinobiPoints = null;

	private final LazyOptional<ShinobiPoints> lazyShinobiPoints = LazyOptional.of(this::createPlayerJutsu);

	private Shurikenjutsu shurikenjutsu = null;

	private final LazyOptional<Shurikenjutsu> lazyShurikenjutsu = LazyOptional.of(this::createPlayerShurikenjutsu);

	private Summoning summoning = null;

	private final LazyOptional<Summoning> lazySummoning = LazyOptional.of(this::createPlayerSummoning);

	private Speed speed = null;

	private final LazyOptional<Speed> lazySpeed = LazyOptional.of(this::createPlayerSpeed);

	private Taijutsu taijutsu = null;

	private final LazyOptional<Taijutsu> lazyTaijutsu = LazyOptional.of(this::createPlayerTaijutsu);

	private Chakra createPlayerChakra() {
		if (this.chakra == null) {
			this.chakra = new Chakra();
		}
		return this.chakra;
	}

	private Genjutsu createPlayerGenjutsu() {
		if (this.genjutsu == null) {
			this.genjutsu = new Genjutsu();
		}
		return this.genjutsu;
	}

	private ShinobiPoints createPlayerJutsu() {
		if (this.shinobiPoints == null) {
			this.shinobiPoints = new ShinobiPoints();
		}
		return this.shinobiPoints;
	}

	private Kenjutsu createPlayerKenjutsu() {
		if (this.kenjutsu == null) {
			this.kenjutsu = new Kenjutsu();
		}
		return this.kenjutsu;
	}

	private Kinjutsu createPlayerKinjutsu() {
		if (this.kinjutsu == null) {
			this.kinjutsu = new Kinjutsu();
		}
		return this.kinjutsu;
	}

	private Medical createPlayerMedical() {
		if (this.medical == null) {
			this.medical = new Medical();
		}
		return this.medical;
	}

	private Ninjutsu createPlayerNinjutsu() {
		if (this.ninjutsu == null) {
			this.ninjutsu = new Ninjutsu();
		}
		return this.ninjutsu;
	}

	private Senjutsu createPlayerSenjutsu() {
		if (this.senjutsu == null) {
			this.senjutsu = new Senjutsu();
		}
		return this.senjutsu;
	}

	private Shurikenjutsu createPlayerShurikenjutsu() {
		if (this.shurikenjutsu == null) {
			this.shurikenjutsu = new Shurikenjutsu();
		}
		return this.shurikenjutsu;
	}

	private Speed createPlayerSpeed() {
		if (this.speed == null) {
			this.speed = new Speed();
		}
		return this.speed;
	}

	private Summoning createPlayerSummoning() {
		if (this.summoning == null) {
			this.summoning = new Summoning();
		}
		return this.summoning;
	}

	private Taijutsu createPlayerTaijutsu() {
		if (this.taijutsu == null) {
			this.taijutsu = new Taijutsu();
		}
		return this.taijutsu;
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == CHAKRA) {
			return lazyChakra.cast();
		}
		if (cap == GENJUTSU) {
			return lazyGenjutsu.cast();
		}
		if (cap == SHINOBI_POINTS) {
			return lazyShinobiPoints.cast();
		}
		if (cap == KENJUTSU) {
			return lazyKenjutsu.cast();
		}
		if (cap == KINJUTSU) {
			return lazyKinjutsu.cast();
		}
		if (cap == MEDICAL) {
			return lazyMedical.cast();
		}
		if (cap == NINJUTSU) {
			return lazyNinjutsu.cast();
		}
		if (cap == SENJUTSU) {
			return lazySenjutsu.cast();
		}
		if (cap == SHURIKENJUTSU) {
			return lazyShurikenjutsu.cast();
		}
		if (cap == SPEED) {
			return lazySpeed.cast();
		}
		if (cap == SUMMONING) {
			return lazySummoning.cast();
		}
		if (cap == TAIJUTSU) {
			return lazyTaijutsu.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createPlayerChakra().saveNBTData(nbt);
		createPlayerGenjutsu().saveNBTData(nbt);
		createPlayerJutsu().saveNBTData(nbt);
		createPlayerKenjutsu().saveNBTData(nbt);
		createPlayerKinjutsu().saveNBTData(nbt);
		createPlayerMedical().saveNBTData(nbt);
		createPlayerNinjutsu().saveNBTData(nbt);
		createPlayerSenjutsu().saveNBTData(nbt);
		createPlayerShurikenjutsu().saveNBTData(nbt);
		createPlayerSpeed().saveNBTData(nbt);
		createPlayerSummoning().saveNBTData(nbt);
		createPlayerTaijutsu().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createPlayerChakra().loadNBTData(nbt);
		createPlayerGenjutsu().loadNBTData(nbt);
		createPlayerJutsu().loadNBTData(nbt);
		createPlayerKenjutsu().loadNBTData(nbt);
		createPlayerKinjutsu().loadNBTData(nbt);
		createPlayerMedical().loadNBTData(nbt);
		createPlayerNinjutsu().loadNBTData(nbt);
		createPlayerSenjutsu().loadNBTData(nbt);
		createPlayerShurikenjutsu().loadNBTData(nbt);
		createPlayerSpeed().loadNBTData(nbt);
		createPlayerSummoning().loadNBTData(nbt);
		createPlayerTaijutsu().loadNBTData(nbt);
	}
}