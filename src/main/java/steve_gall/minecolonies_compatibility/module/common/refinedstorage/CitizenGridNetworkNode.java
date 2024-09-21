package steve_gall.minecolonies_compatibility.module.common.refinedstorage;

import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class CitizenGridNetworkNode extends NetworkNode
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("citizen_grid");

	public CitizenGridNetworkNode(Level level, BlockPos pos)
	{
		super(level, pos);
	}

	public CitizenGridNetworkNode(CompoundTag tag, Level level, BlockPos pos)
	{
		this(level, pos);

		this.read(tag);
	}

	@Override
	public int getEnergyUsage()
	{
		return 0;
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

}
