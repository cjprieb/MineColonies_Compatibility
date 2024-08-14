package steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.network.CheeseOpenTeachMessage;

public class CheeseCraftingModuleView extends CraftingModuleView
{
	public CheeseCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new CheeseOpenTeachMessage(this));
	}

}
