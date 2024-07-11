package steve_gall.minecolonies_compatibility.module.client.farmersdelight.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.FarmersCookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.FarmersCuttingTeachScreen;
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

		registration.addRecipeClickArea(FarmersCuttingTeachScreen.class, 40, 36, 22, 15, FDRecipeTypes.CUTTING);
		registration.addRecipeClickArea(FarmersCookingTeachScreen.class, 91, 26, 22, 15, FDRecipeTypes.COOKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.FARMERSDELIGHT.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new FarmersCuttingTeachRecipeTransferHandler(transferHelper), FDRecipeTypes.CUTTING);
		registration.addRecipeTransferHandler(new FarmersCookingTeachRecipeTransferHandler(transferHelper), FDRecipeTypes.COOKING);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.FARMERSDELIGHT.getModId());
	}

}
