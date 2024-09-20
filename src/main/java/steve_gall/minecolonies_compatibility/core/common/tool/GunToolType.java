package steve_gall.minecolonies_compatibility.core.common.tool;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_tweaks.api.common.tool.CustomToolType;

public class GunToolType extends CustomToolType
{
	public GunToolType(@NotNull ResourceLocation name)
	{
		super(name);
	}

	@Override
	public int getDefaultLevel()
	{
		return 1;
	}

}
