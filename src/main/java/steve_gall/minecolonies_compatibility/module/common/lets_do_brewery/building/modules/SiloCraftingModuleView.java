package steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.network.SiloOpenTeachMessage;

public class SiloCraftingModuleView extends CraftingModuleView
{
	public SiloCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new SiloOpenTeachMessage(this));
	}

}
