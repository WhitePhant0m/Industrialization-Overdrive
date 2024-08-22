package dev.wp.ex_extended_industrialization.datagen.server.provider.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.ExEIBlocks;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;

import java.util.Comparator;

public final class BlockTagDatagenProvider extends BlockTagsProvider
{
	public BlockTagDatagenProvider(GatherDataEvent event) {
		super(event.getGenerator().getPackOutput(), event.getLookupProvider(), ExEI.ID, event.getExistingFileHelper());
	}
	
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		for(BlockHolder<?> block : ExEIBlocks.values().stream().sorted(Comparator.comparing((block) -> block.identifier().id())).toList()) {
			for(TagKey<Block> tag : block.tags()) {
				this.tag(tag).add(block.get());
			}
		}
	}
	
	@Override
	public String getName()
	{
		return this.getClass().getSimpleName();
	}
}
