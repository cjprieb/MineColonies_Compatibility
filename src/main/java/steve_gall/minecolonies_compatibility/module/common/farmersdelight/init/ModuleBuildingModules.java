package steve_gall.minecolonies_compatibility.module.common.farmersdelight.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_compatibility.core.common.init.ModToolTypes;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module.CookingCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module.CookingCraftingModuleView;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module.CuttingCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module.CuttingCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<CuttingCraftingModule, CuttingCraftingModuleView> COOKASSISTENT_CUTTING = new BuildingEntry.ModuleProducer<>("cookassistent_farmers_cutting", //
			() -> new CuttingCraftingModule(ModJobs.cookassistant.get(), ModToolTypes.KNIFE.getToolType()), //
			() -> CuttingCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<CookingCraftingModule, CookingCraftingModuleView> COOKASSISTENT_COOKING = new BuildingEntry.ModuleProducer<>("cookassistent_farmers_cooking", //
			() -> new CookingCraftingModule(ModJobs.cookassistant.get()), //
			() -> CookingCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
