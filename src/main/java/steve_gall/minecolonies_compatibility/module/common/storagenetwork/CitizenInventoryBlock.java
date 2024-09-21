package steve_gall.minecolonies_compatibility.module.common.storagenetwork;

import com.lothrazar.library.block.EntityBlockFlib;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class CitizenInventoryBlock extends EntityBlockFlib
{
	public CitizenInventoryBlock()
	{
		super(Block.Properties.of().strength(0.5F).sound(SoundType.STONE));
	}

	@Override
	public RenderShape getRenderShape(BlockState bs)
	{
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new CitizenInventoryBlockEntity(pos, state);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		if (!world.isClientSide())
		{
			if (world.getBlockEntity(pos) instanceof CitizenInventoryBlockEntity blockEntity)
			{
				NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider()
				{
					@Override
					public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player)
					{
						return new CitizenInventoryMenu(windowId, inventory, blockEntity);
					}

					@Override
					public Component getDisplayName()
					{
						return getName();
					}
				}, pos);
			}

		}

		return InteractionResult.SUCCESS;
	}

}
