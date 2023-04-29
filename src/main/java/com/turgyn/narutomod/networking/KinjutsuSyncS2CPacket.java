package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KinjutsuSyncS2CPacket {
	private final int kinjutsu;

	public KinjutsuSyncS2CPacket(int kinjutsu) {
		this.kinjutsu = kinjutsu;
	}

	public KinjutsuSyncS2CPacket(FriendlyByteBuf buf) {
		this.kinjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(kinjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setKinjutsu(kinjutsu));
	}
}
