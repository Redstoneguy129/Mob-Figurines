package me.redstoneguy129.mod.mobfigurine;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MFItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MobFigurine.MOD_ID);

    public static final RegistryObject<Item> ENDERMAN_FIGURINE = REGISTER.register("enderman_figurine", () -> new FigurineItem(MFBlocks.ENDERMAN_FIGURINE.get()));
    public static final RegistryObject<Item> CREEPER_FIGURINE = REGISTER.register("creeper_figurine", () -> new FigurineItem(MFBlocks.CREEPER_FIGURINE.get()));
    public static final RegistryObject<Item> ILLAGER_FIGURINE = REGISTER.register("illager_figurine", () -> new FigurineItem(MFBlocks.ILLAGER_FIGURINE.get()));
    public static final RegistryObject<Item> ANCIENT_CREEPER_FIGURINE = REGISTER.register("ancient_creeper_figurine", () -> new FigurineItem(MFBlocks.ANCIENT_CREEPER_FIGURINE.get()));
}
