package com.turgyn.narutomod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.turgyn.narutomod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ModHudOverlays {
	private static final ResourceLocation FULL_CHAKRA = new ResourceLocation(Main.MOD_ID,
			"textures/chakra/full_chakra_1.png");

	private static final ResourceLocation EMPTY_CHAKRA = new ResourceLocation(Main.MOD_ID,
			"textures/chakra/empty_chakra_1.png");

	@OnlyIn(Dist.CLIENT) public static final IGuiOverlay CHAKRA = ((gui, poseStack, partialTick, width, height) -> {
		int x = width / 2;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, EMPTY_CHAKRA);
		for (int i = 0; i < 10; i++) {
			drawHudOverlay(poseStack, height, x, i);
		}
		RenderSystem.setShaderTexture(0, FULL_CHAKRA);
		for (int i = 0; i < 10; i++) {
			if (PlayerData.getChakra() / 5 > i) {
				drawHudOverlay(poseStack, height, x, i);
			}
			else {
				break;
			}
		}
	});

	private static void drawHudOverlay(PoseStack poseStack, int height, int x, int i) {
		int size = 8;
		int pY = Minecraft.getInstance().player.isCreative() ? height - 32 : height - 48;
		GuiComponent.blit(poseStack, x + 82 - (i * size), pY, 0, 0, size, size, size, size);
	}
}
