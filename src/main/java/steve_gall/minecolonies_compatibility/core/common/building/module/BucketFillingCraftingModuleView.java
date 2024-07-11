package steve_gall.minecolonies_compatibility.core.common.building.module;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.network.message.BucketFillingOpenTeachMessage;

public class BucketFillingCraftingModuleView extends CraftingModuleView
{
	public BucketFillingCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesCompatibility.network().sendToServer(new BucketFillingOpenTeachMessage(this));
	}

}
