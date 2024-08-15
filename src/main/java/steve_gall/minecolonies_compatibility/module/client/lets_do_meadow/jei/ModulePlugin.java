package steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.compat.jei.category.CheesePressCategory;
import net.satisfy.meadow.compat.jei.category.CookingCauldronCategory;
import net.satisfy.meadow.compat.jei.category.WoodCutterCategory;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.CheeseTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.WoodcuttingTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.LETS_DO_MEADOW.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(CheeseTeachScreen.class, 91, 26, 22, 15, CheesePressCategory.CHEESE_PRESS);
		registration.addRecipeClickArea(CookingTeachScreen.class, 86, 26, 22, 15, CookingCauldronCategory.COOKING_CAULDRON);
		registration.addRecipeClickArea(WoodcuttingTeachScreen.class, 77, 26, 22, 15, WoodCutterCategory.WOODCUTTER);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_MEADOW.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new CheeseTeachRecipeTransferHandler(transferHelper), CheesePressCategory.CHEESE_PRESS);
		registration.addRecipeTransferHandler(new CookingTeachRecipeTransferHandler(transferHelper), CookingCauldronCategory.COOKING_CAULDRON);
		registration.addRecipeTransferHandler(new WoodcuttingTeachRecipeTransferHandler(transferHelper), WoodCutterCategory.WOODCUTTER);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_MEADOW.getModId());
	}

}
