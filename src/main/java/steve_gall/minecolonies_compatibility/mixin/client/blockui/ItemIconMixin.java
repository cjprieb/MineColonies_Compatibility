package steve_gall.minecolonies_compatibility.mixin.client.blockui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ldtteam.blockui.controls.ItemIcon;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.core.client.gui.ItemIconExtension;

@Mixin(value = ItemIcon.class, remap = false)
public abstract class ItemIconMixin implements ItemIconExtension
{
	@Unique
	private final List<Component> minecolonies_compatibility$tooltip = new ArrayList<>();

	@Override
	public void minecolonies_compatibility$addTooltip(Component component)
	{
		this.minecolonies_compatibility$tooltip.add(component);
	}

	@Override
	public void minecolonies_compatibility$addTooltip(Collection<? extends Component> component)
	{
		this.minecolonies_compatibility$tooltip.addAll(component);
	}

	@Inject(method = "setItem", remap = false, at = @At(value = "TAIL"), cancellable = true)
	private void setItem(ItemStack stack, CallbackInfo ci)
	{
		this.minecolonies_compatibility$tooltip.clear();
	}

	@Inject(method = "getModifiedItemStackTooltip", remap = false, at = @At(value = "TAIL"), cancellable = true)
	private void getModifiedItemStackTooltip(CallbackInfoReturnable<List<Component>> cir)
	{
		cir.getReturnValue().addAll(1, this.minecolonies_compatibility$tooltip);
	}

}
