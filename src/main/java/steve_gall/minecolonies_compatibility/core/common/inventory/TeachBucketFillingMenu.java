package steve_gall.minecolonies_compatibility.core.common.inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingCraftingType;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.init.ModCraftingTypes;
import steve_gall.minecolonies_compatibility.core.common.init.ModMenuTypes;

public class TeachBucketFillingMenu extends TeachRecipeMenu<BucketFillingRecipeStorage>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int RESULT_X = 80;
	public static final int RESULT_Y = 35;

	public TeachBucketFillingMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModMenuTypes.TEACH_BUCKET_FILLING.get(), windowId, inventory, module);
		this.setup();
	}

	public TeachBucketFillingMenu(int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(ModMenuTypes.TEACH_BUCKET_FILLING.get(), windowId, inventory, buffer);
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachContainer(this, 1);
		this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, 0, RESULT_X, RESULT_Y)));
		this.resultContainer = new TeachContainer(this, 0);

	}

	@Override
	protected IMenuRecipeValidator<BucketFillingRecipeStorage> createRecipeValidator()
	{
		return new IMenuRecipeValidator<>()
		{
			@Override
			public @Nullable BucketFillingRecipeStorage find(ServerPlayer player, Container container)
			{
				return BucketFillingCraftingType.parse(container.getItem(0));
			}

			@Override
			public @NotNull BucketFillingRecipeStorage deserialize(@NotNull CompoundTag tag)
			{
				return BucketFillingRecipeStorage.deserialize(tag);
			}

			public @NotNull CompoundTag serialize(@NotNull BucketFillingRecipeStorage recipe)
			{
				var tag = new CompoundTag();
				BucketFillingRecipeStorage.serialize(tag, recipe);
				return tag;
			}
		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull BucketFillingRecipeStorage recipe, @NotNull CompoundTag payload)
	{
		super.setContainerByTransfer(recipe, payload);

		this.inputContainer.setItem(0, recipe.getFilledBucket());
	}

	@Override
	protected void onRecipeChanged()
	{

	}

	@Override
	public CraftingType getCraftingType()
	{
		return ModCraftingTypes.BUCKET_FILLING.get();
	}

}
