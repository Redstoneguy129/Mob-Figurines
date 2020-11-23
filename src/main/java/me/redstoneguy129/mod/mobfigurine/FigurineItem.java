package me.redstoneguy129.mod.mobfigurine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class FigurineItem extends BlockItem {
    public FigurineItem(Block block) {
        super(block, new Item.Properties().maxStackSize(16));
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        if(stack.hasTag()) {
            if(stack.getTag().contains("color")) {
                state.with(Figurine.COLOUR, this.getColorByValue(stack.getTag().getInt("color")));
            }
        }
        return super.onBlockPlaced(pos, worldIn, player, stack, state);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        if(context.getItem().hasTag()) {
            if(context.getItem().getTag().contains("color")) {
                return super.placeBlock(context, state.with(Figurine.COLOUR, this.getColorByValue(context.getItem().getTag().getInt("color"))));
            }
        }
        return super.placeBlock(context, state);
    }

    private DyeColor getColorByValue(int color) {
        for(DyeColor dyeColor : DyeColor.values()) {
            if(dyeColor.getColorValue() == color) return dyeColor;
        }
        return DyeColor.GRAY;
    }
}
