package me.redstoneguy129.mod.mobfigurine;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

import javax.annotation.Nullable;

public class FigurineColor implements IBlockColor, IItemColor {
    @Override
    public int getColor(BlockState state, @Nullable ILightReader reader, @Nullable BlockPos pos, int tintIndex) {
        return state.get(Figurine.COLOUR).getColorValue();
    }

    @Override
    public int getColor(ItemStack itemStack, int tintIndex) {
        if(itemStack.hasTag() && itemStack.getTag().contains("color")) return itemStack.getTag().getInt("color");
        return DyeColor.GRAY.getColorValue();
    }
}
