package me.redstoneguy129.mod.mobfigurine;

import me.redstoneguy129.mod.mobfigurine.structures.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.JunglePyramidPiece;
import net.minecraft.world.gen.feature.structure.JunglePyramidStructure;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Locale;

public class MFStructures {

    public static final Structure<FakeMineshaftConfig> MINESHAFT = (Structure<FakeMineshaftConfig>) register("mineshaft", new FakeMineshaftStructure(FakeMineshaftConfig::deserialize));
    public static final Structure<NoFeatureConfig> JUNGLE_TEMPLE = (Structure<NoFeatureConfig>) register("jungle_temple", new FakeJunglePyramidStructure(NoFeatureConfig::deserialize));
/*
    private static RegistryObject<Feature<?>> register(String name, Feature<?> featureConfigFeature) {
        Registry.<Feature<?>>register(Registry.FEATURE, MobFigurine.MOD_ID+":"+name, featureConfigFeature);
        return REGISTER.register(name, () -> featureConfigFeature);
    }
*/
    private static <C extends IFeatureConfig, F extends Feature<C>> Feature<?> register(String key, F value) {
        return Registry.<Feature<?>>register(Registry.FEATURE, MobFigurine.MOD_ID+":"+key, value);
    }

    /*


   private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
      return (F)(Registry.<Feature<?>>register(Registry.FEATURE, key, value));
   }
     */
    public static final IStructurePieceType MSCORRIDOR = register(FakeMineshaftPieces.Corridor::new, "MSCorridor");
    public static final IStructurePieceType MSCROSSING = register(FakeMineshaftPieces.Cross::new, "MSCrossing");
    public static final IStructurePieceType MSROOM = register(FakeMineshaftPieces.Room::new, "MSRoom");
    public static final IStructurePieceType MSSTAIRS = register(FakeMineshaftPieces.Stairs::new, "MSStairs");
    public static final IStructurePieceType TEJP = register(FakeJunglePyramidPiece::new, "TeJP");

    private static IStructurePieceType register(IStructurePieceType type, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), type);
    }
}
