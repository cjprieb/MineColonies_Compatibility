package steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.jei;

import com.minecolonies.api.colony.jobs.ModJobs;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.client.jei.ModPlugin;
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

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		registration.addRecipeClickArea(PanTeachScreen.class, 86, 26, 22, 15, cookassistantType);
		registration.addRecipeClickArea(PotTeachScreen.class, 86, 26, 22, 15, cookassistantType);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.LETS_DO_CANDLELIGHT.isLoaded())
		{
			return;
		}

		var cookassistantType = ModPlugin.createRecipeType(ModJobs.cookassistant.get());
		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new PanTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
		registration.addRecipeTransferHandler(new PotTeachRecipeTransferHandler(transferHelper, cookassistantType), cookassistantType);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesCompatibility.rl(ModuleManager.LETS_DO_CANDLELIGHT.getModId());
	}

}
