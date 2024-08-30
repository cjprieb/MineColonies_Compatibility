package steve_gall.minecolonies_compatibility.module.client.lets_do_vinery.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.vinery.compat.jei.category.ApplePressCategory;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.lets_do_vinery.ApplePressTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.LETS_DO_VINERY.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(ApplePressTeachScreen.class, 77, 34, 22, 15, ApplePressCategory.APPLE_PRESS);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_VINERY.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new ApplePressTeachRecipeTransferHandler(transferHelper), ApplePressCategory.APPLE_PRESS);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_VINERY.getModId());
	}

}
