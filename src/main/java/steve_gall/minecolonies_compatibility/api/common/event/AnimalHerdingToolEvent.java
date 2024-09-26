package steve_gall.minecolonies_compatibility.api.common.event;

import java.util.function.Consumer;

import com.minecolonies.api.util.constant.IToolType;

import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.eventbus.api.Event;

public class AnimalHerdingToolEvent extends Event
{
	private final Animal animal;
	private final Consumer<IToolType> register;

	public AnimalHerdingToolEvent(Animal recipe, Consumer<IToolType> register)
	{
		this.animal = recipe;
		this.register = register;
	}

	public Animal getAnimal()
	{
		return this.animal;
	}

	public void register(IToolType toolType)
	{
		this.register.accept(toolType);
	}

}
