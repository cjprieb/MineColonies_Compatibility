package steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.building.modules.SiloCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.building.modules.SiloCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<SiloCraftingModule, SiloCraftingModuleView> FARMER_SILO = new BuildingEntry.ModuleProducer<>("farmer_lets_do_brewery_silo", //
			() -> new SiloCraftingModule(ModJobs.farmer.get()), //
			() -> SiloCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
