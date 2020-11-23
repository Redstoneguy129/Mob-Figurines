package me.redstoneguy129.mod.mobfigurine;

import me.redstoneguy129.mod.mobfigurine.structures.FakeMineshaftConfig;
import me.redstoneguy129.mod.mobfigurine.structures.FakeMineshaftStructure;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.JungleBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("mobfigurine")
public class MobFigurine {
    public static final String MOD_ID = "mobfigurine";

    private static final ItemGroup itemGroup = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            ItemStack stack = new ItemStack(MFItems.ENDERMAN_FIGURINE.get());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("color", DyeColor.RED.getColorValue());
            stack.setTag(nbt);
            return stack;
        }
        @Override
        public void fill(NonNullList<ItemStack> items) {
            int i = 0;
            for(RegistryObject<Item> item : MFItems.REGISTER.getEntries()) {
                if(item.get() instanceof FigurineItem) {
                    for(DyeColor color : DyeColor.values()) {
                        ItemStack stack = new ItemStack(item.get());
                        CompoundNBT nbt = new CompoundNBT();
                        nbt.putInt("color", color.getColorValue());
                        stack.setTag(nbt);
                        items.add(i, stack);
                        i++;
                    }
                }
            }
            super.fill(items);
        }
    };

    public MobFigurine() {
        MFBlocks.REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        MFItems.REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        new MFStructures();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::common);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client));
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void common(final FMLCommonSetupEvent event) {
        LootConditionManager.registerCondition(new FigurineColorCondition.Serializer());
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(biome.hasStructure(Feature.MINESHAFT)) {
                biome.structures.remove(Feature.MINESHAFT);
                biome.addStructure(MFStructures.MINESHAFT.withConfiguration(new FakeMineshaftConfig(0.004D, FakeMineshaftStructure.Type.NORMAL)));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, MFStructures.MINESHAFT.withConfiguration(new FakeMineshaftConfig(0.004D, FakeMineshaftStructure.Type.NORMAL)));
            }
            if(biome.hasStructure(Feature.JUNGLE_TEMPLE)) {
                biome.structures.remove(Feature.JUNGLE_TEMPLE);
                biome.addStructure(MFStructures.JUNGLE_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, MFStructures.JUNGLE_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
    }

    private void client(final FMLClientSetupEvent event) {
        MFItems.REGISTER.getEntries().forEach(itemRegistryObject -> event.getMinecraftSupplier().get().getItemColors().register(new FigurineColor(), itemRegistryObject.get()));
        MFBlocks.REGISTER.getEntries().forEach(blockRegistryObject -> event.getMinecraftSupplier().get().getBlockColors().register(new FigurineColor(), blockRegistryObject.get()));
    }

    @SubscribeEvent
    public void setFigurineColor(PlayerInteractEvent.RightClickBlock event) {
        if(event.getItemStack().getItem() instanceof DyeItem) {
            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if(block instanceof Figurine && event.getPlayer().isSneaking()) {
                if(event.getWorld().getBlockState(event.getPos()).get(Figurine.COLOUR) != ((DyeItem) event.getItemStack().getItem()).getDyeColor()) {
                    event.getWorld().setBlockState(event.getPos(), event.getWorld().getBlockState(event.getPos()).with(Figurine.COLOUR, ((DyeItem) event.getItemStack().getItem()).getDyeColor()), 10);
                    event.getItemStack().shrink(1);
                }
            }
        }
    }
}
