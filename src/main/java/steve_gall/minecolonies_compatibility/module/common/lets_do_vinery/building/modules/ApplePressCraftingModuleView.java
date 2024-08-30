package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.network.ApplePressOpenTeachMessage;

public class ApplePressCraftingModuleView extends CraftingModuleView
{
	public ApplePressCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new ApplePressOpenTeachMessage(this));
	}

}
