package steve_gall.minecolonies_compatibility.module.common.ae2.init;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.module.common.ae2.CitizenTerminalPart;

public class ModuleParts
{
	public static final Component REMOVED_TOOLTIP = Component.translatable("block.minecolonies_compatibility.citizen_inventory.tooltip").withStyle(ChatFormatting.GRAY);
	public static final RegistryObject<PartItem<CitizenTerminalPart>> CITIZEN_TERMINAL = createPart("citizen_terminal", CitizenTerminalPart.class, CitizenTerminalPart::new);

	private static <T extends IPart> RegistryObject<PartItem<T>> createPart(String name, Class<T> partClass, Function<IPartItem<T>, T> factory)
	{
		PartModels.registerModels(PartModelsHelper.createModels(partClass));
		return item(name, props -> new PartItem<>(props, partClass, factory)
		{
			public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
			{
				tooltip.add(REMOVED_TOOLTIP);
			}

		});
	}

	private static <T extends Item> RegistryObject<T> item(String name, Function<Item.Properties, T> factory)
	{
		return ModuleItems.REGISTER.register(name, () -> factory.apply(new Item.Properties()));
	}

	public static void init()
	{

	}

	private ModuleParts()
	{

	}

}
