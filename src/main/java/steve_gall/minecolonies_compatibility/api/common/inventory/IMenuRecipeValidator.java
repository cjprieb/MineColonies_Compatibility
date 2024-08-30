package steve_gall.minecolonies_compatibility.api.common.inventory;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;

public interface IMenuRecipeValidator<RECIPE>
{
	@NotNull
	List<RECIPE> findAll(@NotNull Container container, @NotNull ServerPlayer player);

	@NotNull
	CompoundTag serialize(@NotNull RECIPE recipe);

	@NotNull
	RECIPE deserialize(@NotNull CompoundTag tag);
}
