package steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import steve_gall.minecolonies_compatibility.module.client.jei.AbstractModulePlugin;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
import steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.PanTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.PotTeachScreen;
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

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		registration.addRecipeClickArea(PanTeachScreen.class, 86, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(PotTeachScreen.class, 86, 26, 22, 15, cookassistantType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new PanTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
		registration.addRecipeTransferHandler(new PotTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
	}

	@Override
	public OptionalModule<?> getModule()
	{
		return ModuleManager.LETS_DO_CANDLELIGHT;
	}

}
