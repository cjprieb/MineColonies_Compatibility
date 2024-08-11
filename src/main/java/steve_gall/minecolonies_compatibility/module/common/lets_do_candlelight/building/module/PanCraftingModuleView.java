package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.network.PanOpenTeachMessage;

public class PanCraftingModuleView extends CraftingModuleView
{
	public PanCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new PanOpenTeachMessage(this));
	}

}
