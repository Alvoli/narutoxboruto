package com.turgyn.narutomod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.turgyn.narutomod.Main;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static com.turgyn.narutomod.client.PlayerData.*;

public class ShinobiStatsGui extends Screen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MOD_ID,
			"textures/gui/shinobi_stats.png");

	private final String[] statList = new String[] {
			"taijutsu", "ninjutsu", "genjutsu", "kenjutsu", "kinjutsu", "medical", "senjutsu", "shurikenjutsu", "speed",
			"summoning", "", "", "", "shinobi_points"
	};

	private final int[] valueList = new int[] {
			getTaijutsu(), getNinjutsu(), getGenjutsu(), getKenjutsu(), getKinjutsu(), getMedical(), getSenjutsu(),
			getShurikenjutsu(), getSpeed(), getSummoning(), 0, 0, 0, getShinobiPoints()
	};

	public ShinobiStatsGui() {
		super(Component.translatable("gui.narutomod.shinobi_stats"));
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pPoseStack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, BACKGROUND);
		blit(pPoseStack, (this.width - 192) / 2, (this.height - 192) / 2, 0, 0, 192, 192);
		this.renderInfo(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}

	protected void createMenuControls() {
		this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onClose())
				.bounds((this.width - 157) / 2, this.height / 2 + 90, 150, 20).build());
	}

	public void renderInfo(PoseStack pPoseStack) {
		int i = (this.width - 192) / 2;
		for (int l = 0; l < this.statList.length; ++l) {
			if (l < 10 || l > 12) {
				this.font.draw(pPoseStack,
						Component.translatable("stat.narutomod." + this.statList[l]).append(": " + this.valueList[l]),
						(float) (i + 36), (float) (this.height / 2 - 80 + l * 11), 0);
			}
		}
	}

	@Override
	protected void init() {
		createMenuControls();
		super.init();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
