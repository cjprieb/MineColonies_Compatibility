package steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.network.CookingOpenTeachMessage;

public class CookingCraftingModuleView extends CraftingModuleView
{
	public CookingCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new CookingOpenTeachMessage(this));
	}

}
