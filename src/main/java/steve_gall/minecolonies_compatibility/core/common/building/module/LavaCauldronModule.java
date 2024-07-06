package steve_gall.minecolonies_compatibility.core.common.building.module;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IHasRequiredItemsModule;
import com.minecolonies.api.colony.requestsystem.request.IRequest;
import com.minecolonies.api.colony.requestsystem.requestable.IDeliverable;
import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.util.Tuple;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public abstract class LavaCauldronModule extends RestrictableModule implements IHasRequiredItemsModule
{
	@Override
	public Map<Predicate<ItemStack>, Tuple<Integer, Boolean>> getRequiredItemsAndAmount()
	{
		var map = new HashMap<Predicate<ItemStack>, Tuple<Integer, Boolean>>();
		map.put(stack -> stack.is(Items.BUCKET), new Tuple<>(32, true));

		return map;
	}

	@Override
	public Map<ItemStorage, Integer> reservedStacksExcluding(@Nullable IRequest<? extends IDeliverable> excluded)
	{
		return Collections.emptyMap();
	}

}
