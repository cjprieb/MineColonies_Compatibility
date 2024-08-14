package steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
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

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		var sawmillType = ModPlugin.createRecipeType(ModJobs.sawmill.get());
		registration.addRecipeClickArea(CheeseTeachScreen.class, 91, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(CookingTeachScreen.class, 86, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(WoodcuttingTeachScreen.class, 77, 26, 22, 15, sawmillType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_MEADOW.isLoaded())
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
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_MEADOW.getModId());
	}

}
