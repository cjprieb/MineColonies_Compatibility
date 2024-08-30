package steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.satisfy.candlelight.compat.jei.category.CookingPanCategory;
import net.satisfy.candlelight.compat.jei.category.CookingPotCategory;
import steve_gall.minecolonies_compatibility.module.client.jei.AbstractModulePlugin;
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

		registration.addRecipeClickArea(PanTeachScreen.class, 86, 26, 22, 15, CookingPanCategory.COOKING_PAN);
		registration.addRecipeClickArea(PotTeachScreen.class, 86, 26, 22, 15, CookingPotCategory.COOKING_POT);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new PanTeachRecipeTransferHandler(transferHelper), CookingPanCategory.COOKING_PAN);
		registration.addRecipeTransferHandler(new PotTeachRecipeTransferHandler(transferHelper), CookingPotCategory.COOKING_POT);
	}

	@Override
	public OptionalModule<?> getModule()
	{
		return ModuleManager.LETS_DO_CANDLELIGHT;
	}

}
