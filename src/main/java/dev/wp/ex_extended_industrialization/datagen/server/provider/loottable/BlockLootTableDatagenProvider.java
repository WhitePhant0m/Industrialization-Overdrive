package dev.wp.ex_extended_industrialization.datagen.server.provider.loottable;

import dev.wp.ex_extended_industrialization.ExEIBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;

import java.util.Set;

public final class BlockLootTableDatagenProvider extends BlockLootSubProvider {
	public BlockLootTableDatagenProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.VANILLA_SET, registries);
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ExEIBlocks.values().stream()
				.filter(BlockHolder::hasLootTable)
				.map(BlockHolder::get)
				.toList();
	}
	
	@Override
	protected void generate() {
		for(BlockHolder<?> block : ExEIBlocks.values()) {
			if(!block.hasLootTable()) {
				continue;
			}
			
			this.add(block.get(), block.getLootTableBuilder().apply(this));
		}
	}
}
