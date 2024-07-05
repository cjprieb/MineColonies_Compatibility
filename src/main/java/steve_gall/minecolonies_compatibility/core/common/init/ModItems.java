package steve_gall.minecolonies_compatibility.core.common.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.item.RestrictToolItem;

public class ModItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<RestrictToolItem> RESTRICT_TOOL = REGISTER.register("restrict_tool", () -> new RestrictToolItem(new Item.Properties()));

	private ModItems()
	{

	}

}
