package me.redstoneguy129.mod.mobfigurine.structures;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class FakeMineshaftConfig implements IFeatureConfig {
    public final double probability;
    public final FakeMineshaftStructure.Type type;

    public FakeMineshaftConfig(double probability, FakeMineshaftStructure.Type type) {
        this.probability = probability;
        this.type = type;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("probability"), ops.createDouble(this.probability), ops.createString("type"), ops.createString(this.type.getName()))));
    }

    public static <T> FakeMineshaftConfig deserialize(Dynamic<T> data) {
        float f = data.get("probability").asFloat(0.0F);
        FakeMineshaftStructure.Type mineshaftstructure$type = FakeMineshaftStructure.Type.byName(data.get("type").asString(""));
        return new FakeMineshaftConfig(f, mineshaftstructure$type);
    }
}
