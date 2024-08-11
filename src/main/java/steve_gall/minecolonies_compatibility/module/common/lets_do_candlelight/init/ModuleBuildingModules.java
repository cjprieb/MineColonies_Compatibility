package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module.PanCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module.PanCraftingModuleView;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module.PotCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module.PotCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<PanCraftingModule, PanCraftingModuleView> CHEF_PAN = new BuildingEntry.ModuleProducer<>("chef_lets_do_candlelight_pan", //
			() -> new PanCraftingModule(ModJobs.chef.get()), //
			() -> PanCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<PotCraftingModule, PotCraftingModuleView> CHEF_POT = new BuildingEntry.ModuleProducer<>("chef_lets_do_candlelight_pot", //
			() -> new PotCraftingModule(ModJobs.chef.get()), //
			() -> PotCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
