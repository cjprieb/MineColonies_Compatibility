package steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.BakingTeachScreen;
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

		var bakerType = ModPlugin.createRecipeType(ModJobs.baker.get());
		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		registration.addRecipeClickArea(StoveTeachScreen.class, 91, 26, 22, 15, bakerType);
		registration.addRecipeClickArea(BakingTeachScreen.class, 91, 26, 22, 15, bakerType);
		registration.addRecipeClickArea(CookingTeachScreen.class, 91, 26, 22, 15, cookassistantType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_BAKERY.isLoaded())
		{
			return;
		}

		var bakerType = ModPlugin.createRecipeType(ModJobs.baker.get());
		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new StoveTeachRecipeTransferHandler(transferHelper, bakerType), bakerType);
		registration.addRecipeTransferHandler(new BakingTeachRecipeTransferHandler(transferHelper, bakerType), bakerType);
		registration.addRecipeTransferHandler(new CookingTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_BAKERY.getModId());
	}

}
