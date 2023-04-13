package com.turgyn.narutomod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {
	public static final String KEY_CATEGORY = "key.category.narutomod";

	public static final String KEY_OPEN_GUI = "key.narutomod.opengui";

	public static final KeyMapping OPEN_GUI = new KeyMapping(KEY_OPEN_GUI, KeyConflictContext.IN_GAME,
			InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);
}
