package steve_gall.minecolonies_compatibility.module.client.lets_do_vinery.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
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

		var farmerType = ModPlugin.createRecipeType(ModJobs.farmer.get());
		registration.addRecipeClickArea(ApplePressTeachScreen.class, 77, 34, 22, 15, farmerType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_VINERY.isLoaded())
		{
			return;
		}

		var farmerType = ModPlugin.createRecipeType(ModJobs.farmer.get());
		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new ApplePressTeachRecipeTransferHandler(transferHelper, farmerType), farmerType);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_VINERY.getModId());
	}

}
