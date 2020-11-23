package me.redstoneguy129.mod.mobfigurine.structures;

import com.mojang.datafixers.Dynamic;
import me.redstoneguy129.mod.mobfigurine.MobFigurine;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class FakeJunglePyramidStructure extends ScatteredStructure<NoFeatureConfig> {
    public FakeJunglePyramidStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51489_1_) {
        super(p_i51489_1_);
    }

    public String getStructureName() {
        return MobFigurine.MOD_ID + ":jungle_pyramid";
    }

    public int getSize() {
        return 3;
    }

    public Structure.IStartFactory getStartFactory() {
        return FakeJunglePyramidStructure.Start::new;
    }

    protected int getSeedModifier() {
        return 14357619;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i225807_1_, int p_i225807_2_, int p_i225807_3_, MutableBoundingBox p_i225807_4_, int p_i225807_5_, long p_i225807_6_) {
            super(p_i225807_1_, p_i225807_2_, p_i225807_3_, p_i225807_4_, p_i225807_5_, p_i225807_6_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            FakeJunglePyramidPiece junglepyramidpiece = new FakeJunglePyramidPiece(this.rand, chunkX * 16, chunkZ * 16);
            this.components.add(junglepyramidpiece);
            this.recalculateStructureSize();
        }
    }
}
