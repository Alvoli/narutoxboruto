package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SenjutsuSyncS2CPacket {
	private final int senjutsu;

	public SenjutsuSyncS2CPacket(int senjutsu) {
		this.senjutsu = senjutsu;
	}

	public SenjutsuSyncS2CPacket(FriendlyByteBuf buf) {
		this.senjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(senjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setSenjutsu(senjutsu));
	}
}
