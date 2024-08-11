package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.menu.PanTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.menu.PotTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<PanTeachMenu>> PAN_TEACH = REGISTER.register("lets_do_candlelight_pan_teach", () -> IForgeMenuType.create(PanTeachMenu::new));
	public static final RegistryObject<MenuType<PotTeachMenu>> POT_TEACH = REGISTER.register("lets_do_candlelight_pot_teach", () -> IForgeMenuType.create(PotTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
