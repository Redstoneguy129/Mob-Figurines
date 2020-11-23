package me.redstoneguy129.mod.mobfigurine;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class FigurineColorCondition implements ILootCondition {
    private final String color;

    private FigurineColorCondition(String color) {
        this.color = color;
    }

    public boolean test(LootContext context) {
        return this.color.equals(context.get(LootParameters.BLOCK_STATE).get(Figurine.COLOUR).getName());
    }

    public static ILootCondition.IBuilder builder(String color) {
        return () -> new FigurineColorCondition(color);
    }

    public static class Serializer extends ILootCondition.AbstractSerializer<FigurineColorCondition> {
        protected Serializer() {
            super(new ResourceLocation(MobFigurine.MOD_ID, "figurine_color"), FigurineColorCondition.class);
        }

        public void serialize(JsonObject json, FigurineColorCondition value, JsonSerializationContext context) {
            json.addProperty("color", value.color);
        }

        public FigurineColorCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return new FigurineColorCondition(JSONUtils.getString(json, "color"));
        }
    }
}
