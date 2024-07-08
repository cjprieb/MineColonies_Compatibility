package steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.crafting.registry.CraftingType;
import com.minecolonies.api.util.constant.IToolType;
import com.minecolonies.api.util.constant.ToolType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.crafting.IngredientHelper;
import steve_gall.minecolonies_compatibility.core.common.inventory.ReadOnlySlotsContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_compatibility.core.common.item.ItemStackHelper;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleCraftingTypes;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleMenuTypes;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public class TeachCuttingMenu extends TeachRecipeMenu<CuttingBoardRecipe>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_SLOTS = 1;
	public static final int CRAFTING_COLS = 1;
	public static final int CRAFTING_X = 14;
	public static final int CRAFTING_Y = 36;

	public static final int RESULT_X = 73;
	public static final int RESULT_Y = 18;
	public static final int RESULT_COLUMNS = 5;

	private final IToolType toolType;

	private List<ChanceResult> results;

	public TeachCuttingMenu(int windowId, Inventory inventory, IBuildingModule module, IToolType toolType)
	{
		super(ModuleMenuTypes.TEACH_CUTTING.get(), windowId, inventory, module);
		this.toolType = toolType;
		this.setup();
	}

	public TeachCuttingMenu(int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.TEACH_CUTTING.get(), windowId, inventory, buffer);
		this.toolType = ToolType.getToolType(buffer.readUtf());
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachInputContainer(this, 1);

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			var col = i % CRAFTING_COLS;
			var row = i / CRAFTING_COLS;
			this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, i, CRAFTING_X + col * SLOT_OFFSET, CRAFTING_Y + row * SLOT_OFFSET)));
		}

		this.results = new ArrayList<>();
		this.resultContainer = new ReadOnlySlotsContainer(this.results::size, i -> this.results.get(i).getStack());
	}

	@Override
	protected IMenuRecipeValidator<CuttingBoardRecipe> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level)
		{
			@Override
			public RecipeType<CuttingBoardRecipe> getRecipeType()
			{
				return ModRecipeTypes.CUTTING.get();
			}

			@Override
			public RecipeWrapper createRecipeContainer(Container container)
			{
				return new RecipeWrapper(new InvWrapper(container));
			}
		};
	}

	@Override
	public void onRecipeTransfer(@NotNull CuttingBoardRecipe recipe, @NotNull CompoundTag payload)
	{
		super.onRecipeTransfer(recipe, payload);

		this.inputSlots.get(0).set(ItemStack.of(payload));
	}

	@Override
	protected void onRecipeChanged()
	{
		var prevSlots = this.resultSlots.size();
		this.results.clear();

		if (this.recipe != null)
		{
			this.results.addAll(this.recipe.getRollableResults());
		}

		var addingSlots = this.results.size() - prevSlots;

		for (var i = 0; i < addingSlots; i++)
		{
			var index = prevSlots + i;
			var xi = index % RESULT_COLUMNS;
			var yi = index / RESULT_COLUMNS;
			var x = RESULT_X + xi * SLOT_OFFSET;
			var y = RESULT_Y + yi * SLOT_OFFSET;
			this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, index, x, y)
			{
				@Override
				public boolean isActive()
				{
					return !this.getItem().isEmpty();
				}
			}));
		}

	}

	@Override
	public boolean testRecipe(CuttingBoardRecipe recipe)
	{
		return ItemStackHelper.isTool(IngredientHelper.getStacks(recipe.getTool()), this.getToolType());
	}

	@Override
	public CraftingType getCraftingType()
	{
		return ModuleCraftingTypes.CUTTING.get();
	}

	public IToolType getToolType()
	{
		return this.toolType;
	}

	public List<ChanceResult> getResults()
	{
		return Collections.unmodifiableList(this.results);
	}

}
