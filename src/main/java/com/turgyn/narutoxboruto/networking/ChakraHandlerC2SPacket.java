package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.capabilities.CapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraHandlerC2SPacket {
	public ChakraHandlerC2SPacket() {
	}

	public ChakraHandlerC2SPacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer serverPlayer = context.getSender();
			serverPlayer.getCapability(CapabilityProvider.CHAKRA).ifPresent(chakra -> {
				if (serverPlayer.isCrouching()) {
					if (chakra.getValue() < chakra.MAX_VALUE) {
						chakra.addValue(1, serverPlayer);
					}
				}
				ModPacketHandler.sendToPlayer(new SyncChakra(chakra.getValue()), serverPlayer);
			});
		});
	}
}