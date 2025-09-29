package net.lordcambion.mod3rnmod.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    final List<Block> nonOrientableTrapdoor = ImmutableList.of(Blocks.OAK_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR);
    final Map<Block, TexturedModel> texturedModels = ImmutableMap.<Block, TexturedModel>builder()
            .build();

    static final ImmutableMap<BlockFamily.Variant, BiConsumer<ModBlockFamilyProvider, Block>> SHAPE_CONSUMERS = ImmutableMap.<BlockFamily.Variant, BiConsumer<ModBlockFamilyProvider, Block>>builder()
            .put(BlockFamily.Variant.BUTTON, ModBlockFamilyProvider::button)
            .put(BlockFamily.Variant.DOOR, ModBlockFamilyProvider::door)
            .put(BlockFamily.Variant.CHISELED, ModBlockFamilyProvider::fullBlockVariant)
            .put(BlockFamily.Variant.CRACKED, ModBlockFamilyProvider::fullBlockVariant)
            .put(BlockFamily.Variant.CUSTOM_FENCE, ModBlockFamilyProvider::customFence)
            .put(BlockFamily.Variant.FENCE, ModBlockFamilyProvider::fence)
            .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, ModBlockFamilyProvider::customFenceGate)
            .put(BlockFamily.Variant.FENCE_GATE, ModBlockFamilyProvider::fenceGate)
            .put(BlockFamily.Variant.SIGN, ModBlockFamilyProvider::sign)
            .put(BlockFamily.Variant.SLAB, ModBlockFamilyProvider::slab)
            .put(BlockFamily.Variant.STAIRS, ModBlockFamilyProvider::stairs)
            .put(BlockFamily.Variant.PRESSURE_PLATE, ModBlockFamilyProvider::pressurePlate)
            .put(BlockFamily.Variant.TRAPDOOR, ModBlockFamilyProvider::trapdoor)
            .put(BlockFamily.Variant.WALL, ModBlockFamilyProvider::wall)
            .build();


    protected void createDoor(Block pDoorBlock) {
        TextureMapping texturemapping = TextureMapping.door(pDoorBlock);
        MultiVariant multivariant = plainVariant(ModModelTemplates.DOOR_BOTTOM_LEFT.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(ModModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant2 = plainVariant(ModModelTemplates.DOOR_BOTTOM_RIGHT.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant3 = plainVariant(ModModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant4 = plainVariant(ModModelTemplates.DOOR_TOP_LEFT.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant5 = plainVariant(ModModelTemplates.DOOR_TOP_LEFT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant6 = plainVariant(ModModelTemplates.DOOR_TOP_RIGHT.create(pDoorBlock, texturemapping, this.modelOutput));
        MultiVariant multivariant7 = plainVariant(ModModelTemplates.DOOR_TOP_RIGHT_OPEN.create(pDoorBlock, texturemapping, this.modelOutput));
        this.registerSimpleFlatItemModel(pDoorBlock.asItem());
        this.blockStateOutput
                .accept(createDoor(pDoorBlock,
                        multivariant,
                        multivariant1,
                        multivariant2,
                        multivariant3,
                        multivariant4,
                        multivariant5,
                        multivariant6,
                        multivariant7));
    }


    protected static BlockModelDefinitionGenerator createTrapdoor(Block pTrapdoorBlock, MultiVariant pTop, MultiVariant pBottom, MultiVariant pOpen) {
        return MultiVariantGenerator.dispatch(pTrapdoorBlock)
                .with(
                        PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.OPEN)
                                .select(Direction.NORTH, Half.BOTTOM, false, pBottom)
                                .select(Direction.SOUTH, Half.BOTTOM, false, pBottom)
                                .select(Direction.EAST, Half.BOTTOM, false, pBottom)
                                .select(Direction.WEST, Half.BOTTOM, false, pBottom)
                                .select(Direction.NORTH, Half.TOP, false, pTop)
                                .select(Direction.SOUTH, Half.TOP, false, pTop)
                                .select(Direction.EAST, Half.TOP, false, pTop)
                                .select(Direction.WEST, Half.TOP, false, pTop)
                                .select(Direction.NORTH, Half.BOTTOM, true, pOpen)
                                .select(Direction.SOUTH, Half.BOTTOM, true, pOpen.with(Y_ROT_180))
                                .select(Direction.EAST, Half.BOTTOM, true, pOpen.with(Y_ROT_90))
                                .select(Direction.WEST, Half.BOTTOM, true, pOpen.with(Y_ROT_270))
                                .select(Direction.NORTH, Half.TOP, true, pOpen)
                                .select(Direction.SOUTH, Half.TOP, true, pOpen.with(Y_ROT_180))
                                .select(Direction.EAST, Half.TOP, true, pOpen.with(Y_ROT_90))
                                .select(Direction.WEST, Half.TOP, true, pOpen.with(Y_ROT_270))
                );
    }



    public ModBlockModelGenerators(
            Consumer<BlockModelDefinitionGenerator> pBlockStateOutput, ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput
    ) {
        super(pBlockStateOutput, pItemModelOutput, pModelOutput);
    }

    @Override
    public void run() {

        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach(
                p_375984_ -> this.family(p_375984_.getBaseBlock()).generateFor(p_375984_)
        );
//        createTrivialCube(ModBlocks.BEAN_BLOCK.get());
        createTrivialCube(ModBlocks.ARKADIUM_BLOCK.get());
        createTrivialCube(ModBlocks.ARKADIUM_ORE.get());
        createTrivialCube(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get());
        createTrivialCube(ModBlocks.PYRESTONE_ORE.get());
        createTrivialCube(ModBlocks.GLUE_BLOCK.get());

    }
    public class ModBlockFamilyProvider extends BlockFamilyProvider {
        private final TextureMapping mapping;
        private final Map<ModelTemplate, ResourceLocation> models = Maps.newHashMap();
        @Nullable
        private BlockFamily family;
        @Nullable
        private ResourceLocation fullBlock;
        private final Set<Block> skipGeneratingModelsFor = new HashSet<>();
        public ModBlockFamilyProvider(TextureMapping pMapping) {
            super(pMapping);
            this.mapping = pMapping;
        }

        @Override
        public BlockFamilyProvider generateFor(BlockFamily pFamily) {
            this.family = pFamily;
            pFamily.getVariants().forEach((p_375413_, p_375795_) -> {
                if (!this.skipGeneratingModelsFor.contains(p_375795_)) {
                    BiConsumer<ModBlockFamilyProvider, Block> biconsumer = ModBlockModelGenerators.SHAPE_CONSUMERS.get(p_375413_);
                    if (biconsumer != null) {
                        biconsumer.accept(this, p_375795_);
                    }
                }
            });
            return this;
        }

        protected BlockModelGenerators.BlockFamilyProvider door(Block pDoorBlock) {
            ModBlockModelGenerators.this.createDoor(pDoorBlock);
            return this;
        }


        public BlockModelGenerators.BlockFamilyProvider button(Block pButtonBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.BUTTON.create(pButtonBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = ModBlockModelGenerators.plainVariant(
                    ModModelTemplates.BUTTON_PRESSED.create(pButtonBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createButton(pButtonBlock, multivariant, multivariant1));
            ResourceLocation resourcelocation = ModModelTemplates.BUTTON_INVENTORY.create(pButtonBlock, this.mapping, ModBlockModelGenerators.this.modelOutput);
            ModBlockModelGenerators.this.registerSimpleItemModel(pButtonBlock, resourcelocation);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider wall(Block pWallBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.WALL_POST.create(pWallBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.WALL_LOW_SIDE.create(pWallBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.WALL_TALL_SIDE.create(pWallBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createWall(pWallBlock, multivariant, multivariant1, multivariant2));
            ResourceLocation resourcelocation = ModModelTemplates.WALL_INVENTORY.create(pWallBlock, this.mapping, ModBlockModelGenerators.this.modelOutput);
            ModBlockModelGenerators.this.registerSimpleItemModel(pWallBlock, resourcelocation);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider customFence(Block pFenceBlock) {
            TextureMapping texturemapping = TextureMapping.customParticle(pFenceBlock);
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_POST.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_SIDE_NORTH.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_SIDE_EAST.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_SIDE_SOUTH.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant4 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_SIDE_WEST.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput
                    .accept(BlockModelGenerators.createCustomFence(pFenceBlock, multivariant, multivariant1, multivariant2, multivariant3, multivariant4));
            ResourceLocation resourcelocation = ModModelTemplates.CUSTOM_FENCE_INVENTORY.create(pFenceBlock, texturemapping, ModBlockModelGenerators.this.modelOutput);
            ModBlockModelGenerators.this.registerSimpleItemModel(pFenceBlock, resourcelocation);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider fence(Block pFenceBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_POST.create(pFenceBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_SIDE.create(pFenceBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createFence(pFenceBlock, multivariant, multivariant1));
            ResourceLocation resourcelocation = ModModelTemplates.FENCE_INVENTORY.create(pFenceBlock, this.mapping, ModBlockModelGenerators.this.modelOutput);
            ModBlockModelGenerators.this.registerSimpleItemModel(pFenceBlock, resourcelocation);
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider customFenceGate(Block pCustomFenceGateBlock) {
            TextureMapping texturemapping = TextureMapping.customParticle(pCustomFenceGateBlock);
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_GATE_OPEN.create(pCustomFenceGateBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_GATE_CLOSED.create(pCustomFenceGateBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_GATE_WALL_OPEN.create(pCustomFenceGateBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.CUSTOM_FENCE_GATE_WALL_CLOSED.create(pCustomFenceGateBlock, texturemapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput
                    .accept(BlockModelGenerators.createFenceGate(pCustomFenceGateBlock, multivariant, multivariant1, multivariant2, multivariant3, false));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider fenceGate(Block pFenceGateBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_GATE_OPEN.create(pFenceGateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_GATE_CLOSED.create(pFenceGateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_GATE_WALL_OPEN.create(pFenceGateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.FENCE_GATE_WALL_CLOSED.create(pFenceGateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput
                    .accept(BlockModelGenerators.createFenceGate(pFenceGateBlock, multivariant, multivariant1, multivariant2, multivariant3, true));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider pressurePlate(Block pPressurePlateBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(
                    ModModelTemplates.PRESSURE_PLATE_UP.create(pPressurePlateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                    ModModelTemplates.PRESSURE_PLATE_DOWN.create(pPressurePlateBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
            );
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pPressurePlateBlock, multivariant, multivariant1));
            return this;
        }

        public BlockModelGenerators.BlockFamilyProvider sign(Block pSignBlock) {
            if (this.family == null) {
                throw new IllegalStateException("Family not defined");
            } else {
                Block block = this.family.getVariants().get(BlockFamily.Variant.WALL_SIGN);
                MultiVariant multivariant = ModBlockModelGenerators.plainVariant(
                        ModModelTemplates.PARTICLE_ONLY.create(pSignBlock, this.mapping, ModBlockModelGenerators.this.modelOutput)
                );
                ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pSignBlock, multivariant));
                ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, multivariant));
                ModBlockModelGenerators.this.registerSimpleFlatItemModel(pSignBlock.asItem());
                return this;
            }
        }

        public BlockModelGenerators.BlockFamilyProvider slab(Block pSlabBlock) {
            if (this.fullBlock == null) {
                throw new IllegalStateException("Full block not generated yet");
            } else {
                ResourceLocation resourcelocation = this.getOrCreateModel(ModModelTemplates.SLAB_BOTTOM, pSlabBlock);
                MultiVariant multivariant = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModModelTemplates.SLAB_TOP, pSlabBlock));
                ModBlockModelGenerators.this.blockStateOutput
                        .accept(
                                BlockModelGenerators.createSlab(
                                        pSlabBlock,
                                        BlockModelGenerators.plainVariant(resourcelocation),
                                        multivariant,
                                        BlockModelGenerators.plainVariant(this.fullBlock)  // Usa plainVariant invece di variant
                                )
                        );
                ModBlockModelGenerators.this.registerSimpleItemModel(pSlabBlock, resourcelocation);
                return this;
            }
        }

        public BlockModelGenerators.BlockFamilyProvider stairs(Block pStairsBlock) {
            MultiVariant multivariant = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModModelTemplates.STAIRS_INNER, pStairsBlock));
            ResourceLocation resourcelocation = this.getOrCreateModel(ModModelTemplates.STAIRS_STRAIGHT, pStairsBlock);
            MultiVariant multivariant1 = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModModelTemplates.STAIRS_OUTER, pStairsBlock));
            ModBlockModelGenerators.this.blockStateOutput
                    .accept(BlockModelGenerators.createStairs(pStairsBlock, multivariant, BlockModelGenerators.plainVariant(resourcelocation), multivariant1));
            ModBlockModelGenerators.this.registerSimpleItemModel(pStairsBlock, resourcelocation);
            return this;
        }

        protected BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block pBlock) {
            TexturedModel texturedmodel = ModBlockModelGenerators.this.texturedModels.getOrDefault(pBlock, TexturedModel.CUBE.get(pBlock));
            MultiVariant multivariant = BlockModelGenerators.plainVariant(texturedmodel.create(pBlock, ModBlockModelGenerators.this.modelOutput));
            ModBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pBlock, multivariant));
            return this;
        }




        protected void trapdoor(Block pTrapdoorBlock) {
            if (ModBlockModelGenerators.this.nonOrientableTrapdoor.contains(pTrapdoorBlock)) {
                ModBlockModelGenerators.this.createTrapdoor(pTrapdoorBlock);
            } else {
                ModBlockModelGenerators.this.createOrientableTrapdoor(pTrapdoorBlock);
            }
        }
    }

    protected void createOrientableTrapdoor(Block pOrientableTrapdoorBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(pOrientableTrapdoorBlock);
        MultiVariant multivariant = plainVariant(ModModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput));
        ResourceLocation resourcelocation = ModModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput);
        MultiVariant multivariant1 = plainVariant(ModModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(pOrientableTrapdoorBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createOrientableTrapdoor(pOrientableTrapdoorBlock, multivariant, plainVariant(resourcelocation), multivariant1));
        this.registerSimpleItemModel(pOrientableTrapdoorBlock, resourcelocation);
    }

    protected void createTrapdoor(Block pTrapdoorBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(pTrapdoorBlock);
        MultiVariant multivariant = plainVariant(ModModelTemplates.TRAPDOOR_TOP.create(pTrapdoorBlock, texturemapping, this.modelOutput));
        ResourceLocation resourcelocation = ModModelTemplates.TRAPDOOR_BOTTOM.create(pTrapdoorBlock, texturemapping, this.modelOutput);
        MultiVariant multivariant1 = plainVariant(ModModelTemplates.TRAPDOOR_OPEN.create(pTrapdoorBlock, texturemapping, this.modelOutput));
        this.blockStateOutput.accept(createTrapdoor(pTrapdoorBlock, multivariant, plainVariant(resourcelocation), multivariant1));
        this.registerSimpleItemModel(pTrapdoorBlock, resourcelocation);
    }



}