package steve_gall.minecolonies_compatibility.module.client.farmersdelight.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.CuttingTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.FARMERSDELIGHT.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(CuttingTeachScreen.class, 40, 36, 22, 15, FDRecipeTypes.CUTTING);
		registration.addRecipeClickArea(CookingTeachScreen.class, 91, 26, 22, 15, FDRecipeTypes.COOKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.FARMERSDELIGHT.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new CuttingTeachRecipeTransferHandler(transferHelper), FDRecipeTypes.CUTTING);
		registration.addRecipeTransferHandler(new CookingTeachRecipeTransferHandler(transferHelper), FDRecipeTypes.COOKING);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.FARMERSDELIGHT.getModId());
	}

}
