package steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import steve_gall.minecolonies_compatibility.module.client.jei.AbstractModulePlugin;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.CheeseTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.WoodcuttingTeachScreen;
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
		var sawmillType = ModPlugin.createRecipeType(ModJobs.sawmill.get());
		registration.addRecipeClickArea(CheeseTeachScreen.class, 91, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(CookingTeachScreen.class, 86, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(WoodcuttingTeachScreen.class, 77, 26, 22, 15, sawmillType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		var sawmillType = ModPlugin.createRecipeType(ModJobs.sawmill.get());
		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new CheeseTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
		registration.addRecipeTransferHandler(new CookingTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
		registration.addRecipeTransferHandler(new WoodcuttingTeachRecipeTransferHandler(transferHelper, sawmillType), sawmillType);
	}

	@Override
	public OptionalModule<?> getModule()
	{
		return ModuleManager.LETS_DO_MEADOW;
	}

}
