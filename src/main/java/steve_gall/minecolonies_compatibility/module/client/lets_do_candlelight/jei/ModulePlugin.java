package steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.candlelight.compat.jei.category.CookingPanCategory;
import net.satisfy.candlelight.compat.jei.category.CookingPotCategory;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.PanTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.PotTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.LETS_DO_CANDLELIGHT.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(PanTeachScreen.class, 86, 26, 22, 15, CookingPanCategory.COOKING_PAN);
		registration.addRecipeClickArea(PotTeachScreen.class, 86, 26, 22, 15, CookingPotCategory.COOKING_POT);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_CANDLELIGHT.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new PanTeachRecipeTransferHandler(transferHelper), CookingPanCategory.COOKING_PAN);
		registration.addRecipeTransferHandler(new PotTeachRecipeTransferHandler(transferHelper), CookingPotCategory.COOKING_POT);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_CANDLELIGHT.getModId());
	}

}
