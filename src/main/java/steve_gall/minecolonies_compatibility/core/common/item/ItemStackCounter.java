package steve_gall.minecolonies_compatibility.core.common.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

public class ItemStackCounter
{
	private final Object2LongMap<ItemStackKey> counter = new Object2LongOpenHashMap<>();
	private final List<CountChangedListener> listeners = new ArrayList<>();

	public void replace(Iterable<Entry<ItemStackKey>> from)
	{
		var prevKeys = new HashSet<>(this.keySet());

		for (var entry : from)
		{
			var key = entry.getKey();
			this.set(key, entry.getLongValue());
			prevKeys.remove(key);
		}

		prevKeys.forEach(key -> this.set(key, 0L));
	}

	public long get(ItemStackKey key)
	{
		return this.counter.getOrDefault(key, 0L);
	}

	public long set(ItemStackKey key, long count)
	{
		count = Math.max(0, count);
		var old = count <= 0 ? this.counter.removeLong(key) : this.counter.put(key, count);

		if (old != count)
		{
			final var c = count;
			this.listeners.forEach(l -> l.onItemCountChanged(key, old, c));
		}

		return old;
	}

	public long extract(ItemStackKey key, long count)
	{
		var old = this.get(key);
		return this.set(key, old - count);
	}

	public long insert(ItemStackKey key, long count)
	{
		var old = this.get(key);
		return this.set(key, old + count);
	}

	public ObjectSet<ItemStackKey> keySet()
	{
		return this.counter.keySet();
	}

	public ObjectSet<Entry<ItemStackKey>> entrySet()
	{
		return this.counter.object2LongEntrySet();
	}

	public void addListener(CountChangedListener listener)
	{
		this.listeners.add(listener);
	}

	@FunctionalInterface
	public static interface CountChangedListener
	{
		void onItemCountChanged(ItemStackKey key, long oldCount, long newCount);
	}

}
