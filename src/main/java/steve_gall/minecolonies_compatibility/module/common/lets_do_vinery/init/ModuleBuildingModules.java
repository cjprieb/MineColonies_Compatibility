package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.building.module.CheeseCraftingModuleView;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.building.modules.ApplePressCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.building.modules.ApplePressCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<ApplePressCraftingModule, CheeseCraftingModuleView> FARMER_APPLE_PRESS = new BuildingEntry.ModuleProducer<>("farmer_lets_do_vinery_apple_press", //
			() -> new ApplePressCraftingModule(ModJobs.farmer.get()), //
			() -> ApplePressCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
