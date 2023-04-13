package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.ChakraData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraDataSyncPacket {
	private final int chakra;

	public ChakraDataSyncPacket(int chakra) {
		this.chakra = chakra;
	}

	public ChakraDataSyncPacket(FriendlyByteBuf buf) {
		this.chakra = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(chakra);
	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ChakraData.set(chakra);
		});
		return true;
	}
}
