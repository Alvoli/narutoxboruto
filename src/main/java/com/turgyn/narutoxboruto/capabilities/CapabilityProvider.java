package com.turgyn.narutoxboruto.capabilities;

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
	public static Capability<Affiliation> AFFILIATION = CapabilityManager.get(new CapabilityToken<>() { });
	public static Capability<Chakra> CHAKRA = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Clan> CLAN = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Genjutsu> GENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Kenjutsu> KENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Kinjutsu> KINJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Medical> MEDICAL = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Ninjutsu> NINJUTSU = CapabilityManager.get(new CapabilityToken<>() { });
	public static Capability<Rank> RANK = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Senjutsu> SENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<ShinobiPoints> SHINOBI_POINTS = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Shurikenjutsu> SHURIKENJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Speed> SPEED = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Summoning> SUMMONING = CapabilityManager.get(new CapabilityToken<>() { });

	public static Capability<Taijutsu> TAIJUTSU = CapabilityManager.get(new CapabilityToken<>() { });

	private Affiliation affiliation = null;

	private final LazyOptional<Affiliation> lazyAffiliation = LazyOptional.of(this::createAffiliation);

	private Chakra chakra = null;

	private final LazyOptional<Chakra> lazyChakra = LazyOptional.of(this::createChakra);

	private Clan clan = null;

	private final LazyOptional<Clan> lazyClan = LazyOptional.of(this::createClan);

	private Genjutsu genjutsu = null;

	private final LazyOptional<Genjutsu> lazyGenjutsu = LazyOptional.of(this::createGenjutsu);

	private Kenjutsu kenjutsu = null;

	private final LazyOptional<Kenjutsu> lazyKenjutsu = LazyOptional.of(this::createKenjutsu);

	private Kinjutsu kinjutsu = null;

	private final LazyOptional<Kinjutsu> lazyKinjutsu = LazyOptional.of(this::createKinjutsu);

	private Medical medical = null;

	private final LazyOptional<Medical> lazyMedical = LazyOptional.of(this::createMedical);

	private Ninjutsu ninjutsu = null;

	private final LazyOptional<Ninjutsu> lazyNinjutsu = LazyOptional.of(this::createNinjutsu);

	private Rank rank = null;

	private final LazyOptional<Rank> lazyRank = LazyOptional.of(this::createRank);

	private Senjutsu senjutsu = null;

	private final LazyOptional<Senjutsu> lazySenjutsu = LazyOptional.of(this::createSenjutsu);

	private ShinobiPoints shinobiPoints = null;

	private final LazyOptional<ShinobiPoints> lazyShinobiPoints = LazyOptional.of(this::createJutsu);

	private Shurikenjutsu shurikenjutsu = null;

	private final LazyOptional<Shurikenjutsu> lazyShurikenjutsu = LazyOptional.of(this::createShurikenjutsu);

	private Summoning summoning = null;

	private final LazyOptional<Summoning> lazySummoning = LazyOptional.of(this::createSummoning);

	private Speed speed = null;

	private final LazyOptional<Speed> lazySpeed = LazyOptional.of(this::createSpeed);

	private Taijutsu taijutsu = null;

	private final LazyOptional<Taijutsu> lazyTaijutsu = LazyOptional.of(this::createTaijutsu);

	private Affiliation createAffiliation() {
		if (this.affiliation == null) {
			this.affiliation = new Affiliation();
		}
		return this.affiliation;
	}

	private Chakra createChakra() {
		if (this.chakra == null) {
			this.chakra = new Chakra();
		}
		return this.chakra;
	}

	private Clan createClan() {
		if (this.clan == null) {
			this.clan = new Clan();
		}
		return this.clan;
	}

	private Genjutsu createGenjutsu() {
		if (this.genjutsu == null) {
			this.genjutsu = new Genjutsu();
		}
		return this.genjutsu;
	}

	private ShinobiPoints createJutsu() {
		if (this.shinobiPoints == null) {
			this.shinobiPoints = new ShinobiPoints();
		}
		return this.shinobiPoints;
	}

	private Kenjutsu createKenjutsu() {
		if (this.kenjutsu == null) {
			this.kenjutsu = new Kenjutsu();
		}
		return this.kenjutsu;
	}

	private Kinjutsu createKinjutsu() {
		if (this.kinjutsu == null) {
			this.kinjutsu = new Kinjutsu();
		}
		return this.kinjutsu;
	}

	private Medical createMedical() {
		if (this.medical == null) {
			this.medical = new Medical();
		}
		return this.medical;
	}

	private Ninjutsu createNinjutsu() {
		if (this.ninjutsu == null) {
			this.ninjutsu = new Ninjutsu();
		}
		return this.ninjutsu;
	}

	private Rank createRank() {
		if (this.rank == null) {
			this.rank = new Rank();
		}
		return this.rank;
	}

	private Senjutsu createSenjutsu() {
		if (this.senjutsu == null) {
			this.senjutsu = new Senjutsu();
		}
		return this.senjutsu;
	}

	private Shurikenjutsu createShurikenjutsu() {
		if (this.shurikenjutsu == null) {
			this.shurikenjutsu = new Shurikenjutsu();
		}
		return this.shurikenjutsu;
	}

	private Speed createSpeed() {
		if (this.speed == null) {
			this.speed = new Speed();
		}
		return this.speed;
	}

	private Summoning createSummoning() {
		if (this.summoning == null) {
			this.summoning = new Summoning();
		}
		return this.summoning;
	}

	private Taijutsu createTaijutsu() {
		if (this.taijutsu == null) {
			this.taijutsu = new Taijutsu();
		}
		return this.taijutsu;
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == CHAKRA) {
			return lazyChakra.cast();
		}if (cap == AFFILIATION) {
			return lazyAffiliation.cast();
		}
		if (cap == CLAN) {
			return lazyClan.cast();
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
		if (cap == RANK) {
			return lazyRank.cast();
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
		createAffiliation().saveNBTData(nbt);
		createChakra().saveNBTData(nbt);
		createClan().saveNBTData(nbt);
		createGenjutsu().saveNBTData(nbt);
		createJutsu().saveNBTData(nbt);
		createKenjutsu().saveNBTData(nbt);
		createKinjutsu().saveNBTData(nbt);
		createMedical().saveNBTData(nbt);
		createNinjutsu().saveNBTData(nbt);
		createSenjutsu().saveNBTData(nbt);
		createShurikenjutsu().saveNBTData(nbt);
		createSpeed().saveNBTData(nbt);
		createSummoning().saveNBTData(nbt);
		createTaijutsu().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createAffiliation().loadNBTData(nbt);
		createChakra().loadNBTData(nbt);
		createClan().loadNBTData(nbt);
		createGenjutsu().loadNBTData(nbt);
		createJutsu().loadNBTData(nbt);
		createKenjutsu().loadNBTData(nbt);
		createKinjutsu().loadNBTData(nbt);
		createMedical().loadNBTData(nbt);
		createNinjutsu().loadNBTData(nbt);
		createSenjutsu().loadNBTData(nbt);
		createShurikenjutsu().loadNBTData(nbt);
		createSpeed().loadNBTData(nbt);
		createSummoning().loadNBTData(nbt);
		createTaijutsu().loadNBTData(nbt);
	}
}