package steve_gall.minecolonies_compatibility.mixin.common.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

@Mixin(value = CropBlock.class, remap = true)
public interface CropBlockAccessor
{
	@Invoker(value = "getBaseSeedId", remap = true)
	ItemLike invokeGetBaseSeedId();
}
