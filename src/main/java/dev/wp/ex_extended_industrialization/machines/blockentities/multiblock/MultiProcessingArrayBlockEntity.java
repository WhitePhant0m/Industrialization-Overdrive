package dev.wp.ex_extended_industrialization.machines.blockentities.multiblock;


import aztech.modern_industrialization.MI;
import aztech.modern_industrialization.MIBlock;
import aztech.modern_industrialization.compat.rei.machines.ReiMachineRecipes;
import aztech.modern_industrialization.machines.BEP;
import aztech.modern_industrialization.machines.guicomponents.ShapeSelection;
import aztech.modern_industrialization.machines.guicomponents.SlotPanel;
import aztech.modern_industrialization.machines.init.MachineTier;
import aztech.modern_industrialization.machines.models.MachineCasings;
import aztech.modern_industrialization.machines.multiblocks.HatchFlags;
import aztech.modern_industrialization.machines.multiblocks.HatchType;
import aztech.modern_industrialization.machines.multiblocks.ShapeTemplate;
import aztech.modern_industrialization.machines.multiblocks.SimpleMember;
import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import com.google.common.collect.Lists;
import dev.wp.ex_extended_industrialization.ExEIConfig;
import dev.wp.ex_extended_industrialization.ExEITags;
import dev.wp.ex_extended_industrialization.ExEIText;
import dev.wp.ex_extended_industrialization.machines.components.craft.MultiProcessingArrayMachineComponent;
import dev.wp.ex_extended_industrialization.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlot;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.swedz.tesseract.neoforge.compat.mi.component.craft.multiplied.EuCostTransformer;
import net.swedz.tesseract.neoforge.compat.mi.component.craft.multiplied.EuCostTransformers;
import net.swedz.tesseract.neoforge.compat.mi.machine.blockentity.multiblock.multiplied.AbstractElectricMultipliedCraftingMultiblockBlockEntity;
import net.swedz.tesseract.neoforge.compat.mi.machine.multiblock.members.PredicateSimpleMember;

import java.util.List;
import java.util.stream.IntStream;

import static aztech.modern_industrialization.MITooltips.DEFAULT_PARSER;
import static net.swedz.tesseract.neoforge.compat.mi.builtinhook.TesseractMITooltips.EU_COST_TRANSFORMER_PARSER;

public final class MultiProcessingArrayBlockEntity extends AbstractElectricMultipliedCraftingMultiblockBlockEntity {
    private final MultiProcessingArrayMachineComponent machines;

    public MultiProcessingArrayBlockEntity(BEP bep) {
        super(bep, "multi_processing_array", SHAPE_TEMPLATES, MachineTier.LV);

        if(!ExEIConfig.allowUpgradesInMultiProcessingArray) {
            guiComponents.removeIf((component) -> component instanceof SlotPanel.Server);
            this.registerGuiComponent(new SlotPanel.Server(this)
                    .withRedstoneControl(redstoneControl));
        }

        this.machines = new MultiProcessingArrayMachineComponent();
        this.registerComponents(machines);
        this.registerGuiComponent(new MultiProcessingArrayMachineSlot.Server(
                this,
                () -> this.getMachineStackSize(activeShape.getActiveShapeIndex()),
                machines
        ));

        this.registerGuiComponent(new ShapeSelection.Server(
                new ShapeSelection.Behavior() {
                    @Override
                    public void handleClick(int clickedLine, int delta) {
                        int newShapeIndex = Mth.clamp(activeShape.getActiveShapeIndex() + delta, 0, SHAPE_TEMPLATES.length - 1);
                        int newMachineStackSize = MultiProcessingArrayBlockEntity.this.getMachineStackSize(newShapeIndex);
                        if(newMachineStackSize < machines.getMachines().getCount()) {
                            return;
                        }
                        activeShape.incrementShape(MultiProcessingArrayBlockEntity.this, delta);
                    }

                    @Override
                    public int getCurrentIndex(int line) {
                        return activeShape.getActiveShapeIndex();
                    }
                },
                new ShapeSelection.LineInfo(
                        SPLIT,
                        IntStream.range(0, SPLIT).map(this::getMachineStackSize).mapToObj(ExEIText.MULTI_PROCESSING_ARRAY_SIZE::text).toList(),
                        false
                )
        ));
    }

    @Override
    public MachineRecipeType getRecipeType() {
        return machines.getMachineRecipeType();
    }

    @Override
    public int getMaxMultiplier() {
        return machines.getMachineCount();
    }

    @Override
    public EuCostTransformer getEuCostTransformer() {
        return EuCostTransformers.percentage(() -> (float) ExEIConfig.multiProcessingArrayEuCostMultiplier);
    }

    private int getMachineStackSize(int sizeIndex) {
        return (int) (BASE_MACHINES * Math.pow(MULT_MACHINES, sizeIndex));
    }

    @Override
    public List<Component> getTooltips() {
        List<Component> lines = Lists.newArrayList();
        lines.add(DEFAULT_PARSER.parse(ExEIText.MULTI_PROCESSING_ARRAY_RECIPE.text()));
        lines.add(DEFAULT_PARSER.parse(ExEIText.MULTI_PROCESSING_ARRAY_BATCH_SIZE.text()));
        if(ExEIConfig.multiProcessingArrayEuCostMultiplier != 1)
        {
            lines.add(DEFAULT_PARSER.parse(ExEIText.MULTI_PROCESSING_ARRAY_EU_COST_MULTIPLIER.text(EU_COST_TRANSFORMER_PARSER.parse(this.getEuCostTransformer()))));
        }
        return lines;
    }

    private static final int MAX_MACHINES = 64;
    private static final int SPLIT = 4;
    private static final int BASE_MACHINES = 8;
    private static final int MULT_MACHINES = 2;

    private static final ShapeTemplate[] SHAPE_TEMPLATES;

    static {
        SHAPE_TEMPLATES = new ShapeTemplate[SPLIT];

        SimpleMember casing = SimpleMember.forBlock(MIBlock.BLOCK_DEFINITIONS.get(MI.id("solid_titanium_machine_casing")));
        SimpleMember pipe = SimpleMember.forBlock(MIBlock.BLOCK_DEFINITIONS.get(MI.id("titanium_machine_casing_pipe")));
        SimpleMember glass = new PredicateSimpleMember((state) -> state.is(ExEITags.blockCommon("glass_blocks")), Blocks.GLASS);
        HatchFlags front = new HatchFlags.Builder().with(HatchType.ENERGY_INPUT).build();
        HatchFlags top = new HatchFlags.Builder().with(HatchType.ITEM_INPUT, HatchType.FLUID_INPUT).build();
        HatchFlags bottom = new HatchFlags.Builder().with(HatchType.ITEM_OUTPUT, HatchType.FLUID_OUTPUT).build();

        for(int
                i = 0, size = 3, machines = BASE_MACHINES;
                i < SPLIT && machines <= MAX_MACHINES;
                i++, size += 2, machines *= MULT_MACHINES){
            ShapeTemplate.Builder builder = new ShapeTemplate.Builder(MachineCasings.SOLID_TITANIUM);
            for(int z = 0; z < size; z++) {
                boolean isFront = z == 0;
                for(int x = -1; x <= 1; x++) {
                    for(int y = -1; y <= 1; y++) {
                        boolean isTop = y == 1;
                        boolean isBottom = y == -1;
                        boolean isCenter = x == 0 && y == 0;
                        boolean isGlass = x != 0 && y == 0;
                        builder.add(x, y, z, isCenter ? pipe : isGlass ? glass : casing, isFront ? front : isTop ? top : isBottom ? bottom : null);
                    }
                }
            }
            SHAPE_TEMPLATES[i] = builder.build();
        }
    }

    public static void registerReiShapes() {
        int index = 0;
        for(ShapeTemplate shapeTemplate : SHAPE_TEMPLATES) {
            ReiMachineRecipes.registerMultiblockShape("multi_processing_array", shapeTemplate, "" + index);
            index++;
        }
    }
}
