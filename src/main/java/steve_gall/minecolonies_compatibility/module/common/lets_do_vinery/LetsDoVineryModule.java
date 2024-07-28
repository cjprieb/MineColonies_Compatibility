package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery;

import net.minecraft.world.item.BlockItem;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import satisfyu.vinery.registry.GrapeTypes;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;

public class LetsDoVineryModule extends AbstractModule
{
	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedFruit.register(new AppleLeavesFruit());
			CustomizedFruit.register(new CherryLeavesFruit());

			for (var grapeType : GrapeTypes.GRAPE_TYPE_TYPES)
			{
				if (grapeType.getSeeds() instanceof BlockItem item)
				{
					CustomizedFruit.register(new GrapeFruit(grapeType, item.getBlock()));
				}

			}

		});
	}

}
