package steve_gall.minecolonies_compatibility.module.common.refinedstorage;

import java.util.List;

import javax.annotation.Nullable;

import com.refinedmods.refinedstorage.block.BlockDirection;
import com.refinedmods.refinedstorage.block.NetworkNodeBlock;
import com.refinedmods.refinedstorage.container.factory.BlockEntityMenuProvider;
import com.refinedmods.refinedstorage.util.BlockUtils;
import com.refinedmods.refinedstorage.util.NetworkUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class CitizenGridBlock extends NetworkNodeBlock
{
	public static final Component REMOVED_TOOLTIP = Component.translatable("block.minecolonies_compatibility.citizen_inventory.tooltip").withStyle(ChatFormatting.GRAY);

	public CitizenGridBlock()
	{
		super(BlockUtils.DEFAULT_ROCK_PROPERTIES);
	}

	@Override
	public BlockDirection getDirection()
	{
		return BlockDirection.HORIZONTAL;
	}

	@Override
	public boolean hasConnectedState()
	{
		return true;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new CitizenGridBlockEntity(pos, state);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
	{
		super.appendHoverText(item, level, tooltip, flag);
		
		tooltip.add(REMOVED_TOOLTIP);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
	{
		if (!level.isClientSide)
		{
			return NetworkUtils.attemptModify(level, pos, player, () -> NetworkHooks.openScreen((ServerPlayer) player, //
					new BlockEntityMenuProvider<>(//
							this.getName(), //
							(blockEntity, windowId, inventory, p) -> new CitizenGridContainerMenu((CitizenGridBlockEntity) blockEntity, player, windowId), pos),
					pos));
		}

		return InteractionResult.SUCCESS;
	}

}
