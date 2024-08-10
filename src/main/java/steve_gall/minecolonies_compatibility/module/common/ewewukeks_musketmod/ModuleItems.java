package steve_gall.minecolonies_compatibility.module.common.ewewukeks_musketmod;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class ModuleItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MineColoniesCompatibility.MOD_ID);

	public static final RegistryObject<DummyGun> DUMMY_GUN = REGISTER.register("ewewukeks_musketmod_dummy_gun", DummyGun::new);

	private ModuleItems()
	{

	}

}
