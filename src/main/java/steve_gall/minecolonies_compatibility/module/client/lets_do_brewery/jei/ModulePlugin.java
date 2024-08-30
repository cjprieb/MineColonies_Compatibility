package steve_gall.minecolonies_compatibility.module.client.lets_do_brewery.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.brewery.compat.jei.BreweryJEIClientPlugin;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.lets_do_brewery.SiloTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.LETS_DO_BREWERY.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(SiloTeachScreen.class, 77, 26, 22, 15, BreweryJEIClientPlugin.DRYING_TYPE);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_BREWERY.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new SiloTeachRecipeTransferHandler(transferHelper), BreweryJEIClientPlugin.DRYING_TYPE);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_BREWERY.getModId());
	}

}
