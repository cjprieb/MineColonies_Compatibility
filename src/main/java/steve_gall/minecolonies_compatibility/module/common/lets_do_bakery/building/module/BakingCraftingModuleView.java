package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.network.BakingOpenTeachMessage;

public class BakingCraftingModuleView extends CraftingModuleView
{
	public BakingCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new BakingOpenTeachMessage(this));
	}

}
