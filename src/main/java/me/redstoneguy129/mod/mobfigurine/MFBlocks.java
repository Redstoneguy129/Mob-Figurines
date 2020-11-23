package me.redstoneguy129.mod.mobfigurine;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MFBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MobFigurine.MOD_ID);

    public static final RegistryObject<Figurine> ENDERMAN_FIGURINE = REGISTER.register("enderman_figurine", () -> new Figurine(MFItems.ENDERMAN_FIGURINE, FigurineRarity.RARE));
    public static final RegistryObject<Figurine> CREEPER_FIGURINE = REGISTER.register("creeper_figurine", () -> new Figurine(MFItems.CREEPER_FIGURINE, FigurineRarity.RARE));
    public static final RegistryObject<Figurine> ILLAGER_FIGURINE = REGISTER.register("illager_figurine", () -> new Figurine(MFItems.ILLAGER_FIGURINE, FigurineRarity.RARE));
    public static final RegistryObject<Figurine> ANCIENT_CREEPER_FIGURINE = REGISTER.register("ancient_creeper_figurine", () -> new Figurine(MFItems.ANCIENT_CREEPER_FIGURINE, FigurineRarity.RARE));
}
