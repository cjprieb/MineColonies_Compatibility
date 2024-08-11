package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.network.PotOpenTeachMessage;

public class PotCraftingModuleView extends CraftingModuleView
{
	public PotCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new PotOpenTeachMessage(this));
	}

}
