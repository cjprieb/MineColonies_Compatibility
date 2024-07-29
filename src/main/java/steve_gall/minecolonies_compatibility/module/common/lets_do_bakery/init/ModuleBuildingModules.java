package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.BakingCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.BakingCraftingModuleView;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.CookingCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.CookingCraftingModuleView;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.StoveCraftingModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module.StoveCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<StoveCraftingModule, StoveCraftingModuleView> BAKER_STOVE = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_stove", //
			() -> new StoveCraftingModule(ModJobs.baker.get()), //
			() -> StoveCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<BakingCraftingModule, BakingCraftingModuleView> BAKER_BAKING = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_baking", //
			() -> new BakingCraftingModule(ModJobs.baker.get()), //
			() -> BakingCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<CookingCraftingModule, CookingCraftingModuleView> COOKASSISTANT_COOKING = new BuildingEntry.ModuleProducer<>("cookassistant_lets_do_bakery_cooking", //
			() -> new CookingCraftingModule(ModJobs.cookassistant.get()), //
			() -> CookingCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
