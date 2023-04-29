package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KenjutsuSyncS2CPacket {
	private final int kenjutsu;

	public KenjutsuSyncS2CPacket(int kenjutsu) {
		this.kenjutsu = kenjutsu;
	}

	public KenjutsuSyncS2CPacket(FriendlyByteBuf buf) {
		this.kenjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(kenjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setKenjutsu(kenjutsu));
	}
}
