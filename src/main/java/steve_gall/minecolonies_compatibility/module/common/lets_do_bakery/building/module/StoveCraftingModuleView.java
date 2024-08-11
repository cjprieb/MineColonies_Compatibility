package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.network.StoveOpenTeachMessage;

public class StoveCraftingModuleView extends CraftingModuleView
{
	public StoveCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new StoveOpenTeachMessage(this));
	}

}
