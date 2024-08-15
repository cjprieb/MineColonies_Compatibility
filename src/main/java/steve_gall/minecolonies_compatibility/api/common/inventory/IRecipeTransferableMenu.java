package steve_gall.minecolonies_compatibility.api.common.inventory;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public interface IRecipeTransferableMenu<RECIPE>
{
	@NotNull
	IMenuRecipeValidator<RECIPE> getRecipeValidator();

	void onRecipeTransfer(@NotNull ServerPlayer player, @NotNull RECIPE recipe, @NotNull CompoundTag payload);
}
