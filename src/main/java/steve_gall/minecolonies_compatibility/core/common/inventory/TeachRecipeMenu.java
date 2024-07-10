package steve_gall.minecolonies_compatibility.core.common.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.inventory.IItemGhostMenu;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.IRecipeTransferableMenu;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.network.message.TeachRecipeMenuNewResultMessage;

public abstract class TeachRecipeMenu<RECIPE> extends ModuleMenu implements IItemGhostMenu, IRecipeTransferableMenu<RECIPE>
{
	public static final Component TEXT_RECIPE_NOT_FOUND = Component.translatable("minecolonies_compatibility.text.recipe_not_found");

	protected TeachContainer inputContainer;
	protected List<Slot> inputSlots;

	protected Container resultContainer;
	protected List<Slot> resultSlots;

	private IMenuRecipeValidator<RECIPE> recipeValidator;
	protected RECIPE recipe;

	public TeachRecipeMenu(MenuType<?> menuType, int windowId, Inventory inventory, IBuildingModule module)
	{
		super(menuType, windowId, inventory, module);
		this.setup();
	}

	public TeachRecipeMenu(MenuType<?> menuType, int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, inventory, buffer);
		this.setup();
	}

	private void setup()
	{
		this.inputSlots = new ArrayList<>();
		this.resultSlots = new ArrayList<>();

		this.inputContainer = null;
		this.resultContainer = null;

		this.recipeValidator = null;
		this.recipe = null;
	}

	public abstract CraftingType getCraftingType();

	protected abstract IMenuRecipeValidator<RECIPE> createRecipeValidator();

	protected abstract void onRecipeChanged();

	@Override
	public IMenuRecipeValidator<RECIPE> getRecipeValidator()
	{
		if (this.recipeValidator == null)
		{
			this.recipeValidator = this.createRecipeValidator();
		}

		return this.recipeValidator;
	}

	@Override
	public final void onRecipeTransfer(@NotNull RECIPE recipe, @NotNull CompoundTag payload)
	{
		this.setContainerByTransfer(recipe, payload);

		this.setRecipe(recipe);
	}

	protected void setContainerByTransfer(@NotNull RECIPE recipe, @NotNull CompoundTag payload)
	{
		this.inputContainer.clearContent();
	}

	@Override
	public void onGhostAcceptItem(int slotNumber, ItemStack stack, boolean isVirtual)
	{
		if (!isVirtual)
		{
			var slot = this.slots.get(slotNumber);
			this.handleSlotClick(slot, stack);
		}

	}

	@Override
	public void slotsChanged(Container container)
	{
		if (this.inventory.player instanceof ServerPlayer player)
		{
			if (container == this.inputContainer)
			{
				var recipe = this.getRecipeValidator().find(player, container);
				this.setRecipe(recipe);
			}

		}

		super.slotsChanged(container);
	}

	public void setRecipe(RECIPE recipe)
	{
		this.recipe = recipe;
		this.onRecipeChanged();

		if (this.inventory.player instanceof ServerPlayer player)
		{
			var tag = recipe != null ? this.getRecipeValidator().serialize(recipe) : null;
			MineColoniesCompatibility.network().sendToPlayer(new TeachRecipeMenuNewResultMessage(tag), player);
		}

	}

	@Nullable
	public Component getCurrentError()
	{
		return this.recipe != null ? this.getRecipeError(this.recipe) : TEXT_RECIPE_NOT_FOUND;
	}

	@Nullable
	public Component getRecipeError(@NotNull RECIPE recipe)
	{
		return null;
	}

	@Override
	public void clicked(int slotNumber, int clickedButton, ClickType mode, Player player)
	{
		if (0 <= slotNumber && slotNumber < this.slots.size())
		{
			var slot = this.slots.get(slotNumber);

			if (slot.container == this.inputContainer || slot.container == this.resultContainer)
			{
				if (mode == ClickType.PICKUP || mode == ClickType.PICKUP_ALL || mode == ClickType.SWAP)
				{
					this.handleSlotClick(slot, this.getCarried());
				}

				return;
			}

			if (mode == ClickType.QUICK_MOVE)
			{
				return;
			}

		}

		super.clicked(slotNumber, clickedButton, mode, player);
	}

	public void handleSlotClick(Slot slot, ItemStack stack)
	{
		if (slot.container == this.inputContainer)
		{
			this.setSlot(slot, stack);
		}

	}

	protected void setSlot(Slot slot, ItemStack stack)
	{
		if (stack.getCount() > 0)
		{
			var copy = stack.copy();
			copy.setCount(1);
			slot.set(copy);
		}
		else if (slot.getItem().getCount() > 0)
		{
			slot.set(ItemStack.EMPTY);
		}

	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
	{
		return !this.resultSlots.contains(slot) && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slot)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player)
	{
		return true;
	}

	public TeachContainer getInputContainer()
	{
		return this.inputContainer;
	}

	public List<Slot> getCraftSlots()
	{
		return Collections.unmodifiableList(this.inputSlots);
	}

	public Container getResultContainer()
	{
		return this.resultContainer;
	}

	public List<Slot> getResultSlots()
	{
		return Collections.unmodifiableList(this.resultSlots);
	}

	public RECIPE getRecipe()
	{
		return this.recipe;
	}

}
