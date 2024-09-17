package dev.wp.industrial_overdrive;

//import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
//import com.google.common.collect.Maps;
import dev.wp.industrial_overdrive.machines.blockentities.multiblock.MultiProcessingArrayBlockEntity;
//import net.minecraft.resources.ResourceLocation;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.*;

//import java.util.Map;
//import java.util.function.Function;

import static aztech.modern_industrialization.machines.models.MachineCasings.SOLID_TITANIUM;

public class IOMachines {
//    public static void blastFurnaceTiers(BlastFurnaceTiersMIHookContext hook) {
//    }
//
//    public static final class Casings {
//    }
//
//    public static void casings(MachineCasingsMIHookContext hook) {
//    }

//    public static final class RecipeTypes {
//        private static final Map<MachineRecipeType, String> RECIPE_TYPE_NAMES = Maps.newHashMap();
//
//        public static Map<MachineRecipeType, String> getNames() {
//            return RECIPE_TYPE_NAMES;
//        }
//
//        private static MachineRecipeType create(MachineRecipeTypesMIHookContext hook, String englishName, String id, Function<ResourceLocation, MachineRecipeType> creator) {
//            MachineRecipeType recipeType = hook.create(id, creator);
//            RECIPE_TYPE_NAMES.put(recipeType, englishName);
//            return recipeType;
//        }
//
//        private static MachineRecipeType create(MachineRecipeTypesMIHookContext hook, String englishName, String id) {
//            return create(hook, englishName, id, MachineRecipeType::new);
//        }
//    }

//    public static void recipeTypes(MachineRecipeTypesMIHookContext hook) {
//    }

    public static void multiblocks(MultiblockMachinesMIHookContext hook) {
        hook.register(
                "Multi Processing Array", "multi_processing_array", "multi_processing_array",
                SOLID_TITANIUM, true, false, false,
                MultiProcessingArrayBlockEntity::new,
                (__) -> MultiProcessingArrayBlockEntity.registerReiShapes()
        );
    }

//    public static void singleBlockCrafting(SingleBlockCraftingMachinesMIHookContext hook) {
//    }
//
//    public static void singleBlockSpecial(SingleBlockSpecialMachinesMIHookContext hook) {
//    }


}
