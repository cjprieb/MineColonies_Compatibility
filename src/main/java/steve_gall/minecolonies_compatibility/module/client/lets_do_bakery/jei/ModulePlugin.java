package steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bakery.compat.jei.category.BakerStationCategory;
import net.satisfy.bakery.compat.jei.category.CookingPotCategory;
import net.satisfy.bakery.compat.jei.category.CraftingBowlCategory;
import net.satisfy.bakery.compat.jei.category.StoveCategory;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.BakingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.BowlTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.StoveTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.LETS_DO_BAKERY.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(StoveTeachScreen.class, 91, 26, 22, 15, StoveCategory.STOVE);
		registration.addRecipeClickArea(BowlTeachScreen.class, 91, 35, 22, 15, CraftingBowlCategory.DOUGHING);
		registration.addRecipeClickArea(BakingTeachScreen.class, 91, 26, 22, 15, BakerStationCategory.CAKING);
		registration.addRecipeClickArea(CookingTeachScreen.class, 91, 26, 22, 15, CookingPotCategory.COOKING_POT);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_BAKERY.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new StoveTeachRecipeTransferHandler(transferHelper), StoveCategory.STOVE);
		registration.addRecipeTransferHandler(new BowlTeachRecipeTransferHandler(transferHelper), CraftingBowlCategory.DOUGHING);
		registration.addRecipeTransferHandler(new BakingTeachRecipeTransferHandler(transferHelper), BakerStationCategory.CAKING);
		registration.addRecipeTransferHandler(new CookingTeachRecipeTransferHandler(transferHelper), CookingPotCategory.COOKING_POT);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_BAKERY.getModId());
	}

}
