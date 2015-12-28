package com.InfinityRaider.AgriCraft.farming.cropplant;

import com.InfinityRaider.AgriCraft.api.v1.RenderMethod;
import com.InfinityRaider.AgriCraft.renderers.PlantRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CropPlantStem extends CropPlantGeneric {
    private final Block block;

    public CropPlantStem(ItemSeeds seed, Block block) {
        super(seed);
        this.block = block;
    }

    public Block getFruitBlock() {
        return block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderMethod getRenderMethod() {
        return RenderMethod.STEM;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getPlantTexture(int growthStage) {
        //TODO: get vine icon
        return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getFruitIcon() {
        //TODO: get icon from fruit block
        return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderPlantInCrop(WorldRenderer renderer, IBlockAccess world, BlockPos pos, IBlockState state, int growthStage) {
        boolean mature = isMature(world, pos, state);
        Block vine = ((ItemSeeds) getSeed().getItem()).getPlant(null, null).getBlock();
        PlantRenderer.renderStemPlant(renderer, world, pos, getPlantTexture(growthStage), getFruitIcon(), growthStage, vine, mature);
    }

    @Override
    public String getInformation() {
        String name = getSeed().getUnlocalizedName();
        if(name.indexOf('_')>=0) {
            name = name.substring(name.indexOf('_')+1);
        }
        if(name.indexOf('.')>=0) {
            name = name.substring(name.indexOf('.')+1);
        }
        return "agricraft_journal."+name;
    }
}
