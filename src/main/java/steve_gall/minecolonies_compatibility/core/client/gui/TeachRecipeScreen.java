package steve_gall.minecolonies_compatibility.core.client.gui;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;
import com.minecolonies.api.util.constant.TranslationConstants;
import com.minecolonies.api.util.constant.translation.BaseGameTranslationConstants;
import com.minecolonies.core.Network;
import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;
import com.minecolonies.core.network.messages.server.colony.building.worker.AddRemoveRecipeMessage;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.wrapper.InvWrapper;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_compatibility.core.common.item.ItemHandlerHelper2;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;
import steve_gall.minecolonies_tweaks.core.client.gui.CloseableWindowExtension;
import steve_gall.minecolonies_tweaks.core.common.config.MineColoniesTweaksConfigClient;

public abstract class TeachRecipeScreen<MENU extends TeachRecipeMenu<RECIPE>, RECIPE> extends AbstractContainerScreen<MENU> implements CloseableWindowExtension
{
	private static final Component TEXT_WARNING_MAXIMUM_NUMBER_RECIPES = Component.translatable(TranslationConstants.WARNING_MAXIMUM_NUMBER_RECIPES);
	private static final Component TEXT_DONE = Component.translatable(BaseGameTranslationConstants.BASE_GUI_DONE);

	protected final CraftingModuleView module;

	private Button doneButton;
	private Button closeButton;
	private Screen parent;

	private Component lastError;

	public TeachRecipeScreen(MENU menu, Inventory inventory, Component title)
	{
		super(menu, inventory, title);

		this.module = (CraftingModuleView) menu.getModulePos().getModuleView();
	}

	@Override
	protected void init()
	{
		super.init();

		this.doneButton = new Button(this.leftPos + 1, this.topPos + this.imageHeight + 4, 150, 20, TEXT_DONE, this::onDoneButtonPress);
		this.doneButton.active = false;
		this.lastError = null;
		this.addRenderableWidget(this.doneButton);

		if (MineColoniesTweaksConfigClient.INSTANCE.addReturnButton.get().booleanValue())
		{
			this.closeButton = new Button(this.doneButton.x + this.doneButton.getWidth() + 5, this.doneButton.y, this.doneButton.getHeight(), this.doneButton.getHeight(), Component.literal("X"), this::onClosePress);
			this.addRenderableWidget(this.closeButton);
		}

	}

	@Override
	public void render(PoseStack pose, int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground(pose);

		super.render(pose, mouseX, mouseY, partialTicks);

		if (this.lastError != null)
		{
			var x = this.doneButton.x + (this.doneButton.getWidth() - this.minecraft.font.width(this.lastError)) / 2;
			var y = this.doneButton.y + this.doneButton.getHeight() + 2;
			this.minecraft.font.drawShadow(pose, this.lastError, x, y, 0xFFFF0000);
		}

	}

	@Override
	protected void containerTick()
	{
		super.containerTick();

		var error = this.getError();
		this.doneButton.active = error == null;
		this.lastError = error;
	}

	protected Component getError()
	{
		if (!this.module.canLearn(this.menu.getCraftingType()))
		{
			return TEXT_WARNING_MAXIMUM_NUMBER_RECIPES;
		}

		var recipe = this.menu.getRecipe();

		if (recipe == null)
		{
			return TeachRecipeMenu.TEXT_RECIPE_NOT_FOUND;
		}

		return this.menu.getCurrentError();
	}

	private void onDoneButtonPress(Button button)
	{
		if (this.getError() != null)
		{
			return;
		}

		var recipe = this.menu.getRecipe();

		if (recipe != null)
		{
			var input = ItemHandlerHelper2.unwrap(new InvWrapper(this.menu.getInputContainer()), false).stream().map(ItemStorage::new).toList();
			var storage = this.createRecipeStorage(recipe, input).wrap();
			Network.getNetwork().sendToServer(new AddRemoveRecipeMessage(this.module.getBuildingView(), false, storage, this.module.getProducer().getRuntimeID()));
		}

	}

	protected abstract ICustomizedRecipeStorage createRecipeStorage(RECIPE recipe, List<ItemStorage> input);

	private void onClosePress(Button button)
	{
		this.minecolonies_tweaks$returnOrClose();
	}

	@Override
	public void minecolonies_tweaks$setParent(Screen screen)
	{
		this.parent = screen;
	}

	@Override
	public Screen minecolonies_tweaks$getParent()
	{
		return this.parent;
	}

	public CraftingModuleView getModule()
	{
		return this.module;
	}

}
