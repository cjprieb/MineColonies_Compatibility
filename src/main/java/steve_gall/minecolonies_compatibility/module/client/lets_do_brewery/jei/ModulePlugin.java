package steve_gall.minecolonies_compatibility.module.client.lets_do_brewery.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.satisfy.brewery.compat.jei.BreweryJEIClientPlugin;
import steve_gall.minecolonies_compatibility.module.client.jei.AbstractModulePlugin;
import steve_gall.minecolonies_compatibility.module.client.lets_do_brewery.SiloTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;
import steve_gall.minecolonies_compatibility.module.common.OptionalModule;

@JeiPlugin
public class ModulePlugin extends AbstractModulePlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(SiloTeachScreen.class, 77, 26, 22, 15, BreweryJEIClientPlugin.DRYING_TYPE);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new SiloTeachRecipeTransferHandler(transferHelper), BreweryJEIClientPlugin.DRYING_TYPE);
	}

	@Override
	public OptionalModule<?> getModule()
	{
		return ModuleManager.LETS_DO_BREWERY;
	}

}
