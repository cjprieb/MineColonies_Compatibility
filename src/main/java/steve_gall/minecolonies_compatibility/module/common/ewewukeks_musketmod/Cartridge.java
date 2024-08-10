package steve_gall.minecolonies_compatibility.module.common.ewewukeks_musketmod;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.MinecoloniesAPIProxy;

import ewewukek.musketmod.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_tweaks.api.common.requestsystem.IDeliverableObject;

public class Cartridge implements IDeliverableObject
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("ewewukeks_musketmod_cartridge");
	public static final Component SHORT_DISPLAY_STRING = Component.translatable(MineColoniesCompatibility.tl("ewewukeks_musketmod_cartridge"));
	public static final Component LONG_DISPLAY_STRING = Component.translatable(MineColoniesCompatibility.tl("ewewukeks_musketmod_cartridge.desc"));

	private static List<ItemStack> CARTRIDGE_EXAMPLES = null;

	private final int minCount;

	public Cartridge(int minCount)
	{
		this.minCount = minCount;
	}

	@Override
	@NotNull
	public ResourceLocation getId()
	{
		return ID;
	}

	public static Cartridge deserialize(@NotNull CompoundTag tag)
	{
		var minCount = tag.getInt("minCount");
		return new Cartridge(minCount);
	}

	public static void serialize(Cartridge request, @NotNull CompoundTag tag)
	{
		tag.putInt("minCount", request.minCount);
	}

	@Override
	@NotNull
	public Component getShortDisplayString()
	{
		return SHORT_DISPLAY_STRING;
	}

	@Override
	@NotNull
	public Component getLongDisplayString()
	{
		return LONG_DISPLAY_STRING;
	}

	@Override
	@NotNull
	public List<ItemStack> getDisplayStacks()
	{
		if (CARTRIDGE_EXAMPLES == null)
		{
			CARTRIDGE_EXAMPLES = MinecoloniesAPIProxy.getInstance().getColonyManager().getCompatibilityManager().getListOfAllItems().stream().filter(this::matches).toList();
		}

		return CARTRIDGE_EXAMPLES;
	}

	@Override
	public Cartridge copyWithCount(int newCount)
	{
		return new Cartridge(this.minCount);
	}

	@Override
	public int getCount()
	{
		return 64;
	}

	@Override
	public int getMinimumCount()
	{
		return this.minCount;
	}

	@Override
	public boolean matches(@NotNull ItemStack stack)
	{
		return stack.getItem() == Items.CARTRIDGE;
	}

}
