
package net.mcreator.tutorial.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.tutorial.TutorialModElements;

import java.util.List;
import java.util.Collections;

@TutorialModElements.ModElement.Tag
public class NuoviBlocchiBlock extends TutorialModElements.ModElement {
	@ObjectHolder("tutorial:nuovi_blocchi")
	public static final Block block = null;
	public NuoviBlocchiBlock(TutorialModElements instance) {
		super(instance, 3);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BlockColorRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new ItemColorRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	private static class BlockColorRegisterHandler {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public void blockColorLoad(ColorHandlerEvent.Block event) {
			event.getBlockColors().register((bs, world, pos, index) -> {
				return world != null && pos != null ? BiomeColors.getWaterColor(world, pos) : -1;
			}, block);
		}
	}

	private static class ItemColorRegisterHandler {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public void itemColorLoad(ColorHandlerEvent.Item event) {
			event.getItemColors().register((stack, index) -> {
				return 3694022;
			}, block);
		}
	}

	public static class CustomBlock extends Block {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0)
					.speedFactor(1.4f).jumpFactor(0.1f).notSolid().setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
			setRegistryName("nuovi_blocchi");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			switch ((Direction) state.get(FACING)) {
				case SOUTH :
				default :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(14, 1, 14, 2, 2, 2), makeCuboidShape(13, 2, 13, 3, 3, 3),
											makeCuboidShape(12, 3, 12, 4, 4, 4), makeCuboidShape(11, 4, 11, 5, 5, 5),
											makeCuboidShape(10, 5, 10, 6, 6, 6), makeCuboidShape(9, 6, 9, 7, 7, 7)),
									VoxelShapes.or(makeCuboidShape(15, 0, 15, 1, 1, 1)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case NORTH :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(2, 1, 2, 14, 2, 14), makeCuboidShape(3, 2, 3, 13, 3, 13),
											makeCuboidShape(4, 3, 4, 12, 4, 12), makeCuboidShape(5, 4, 5, 11, 5, 11),
											makeCuboidShape(6, 5, 6, 10, 6, 10), makeCuboidShape(7, 6, 7, 9, 7, 9)),
									VoxelShapes.or(makeCuboidShape(1, 0, 1, 15, 1, 15)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case EAST :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(14, 1, 2, 2, 2, 14), makeCuboidShape(13, 2, 3, 3, 3, 13),
											makeCuboidShape(12, 3, 4, 4, 4, 12), makeCuboidShape(11, 4, 5, 5, 5, 11),
											makeCuboidShape(10, 5, 6, 6, 6, 10), makeCuboidShape(9, 6, 7, 7, 7, 9)),
									VoxelShapes.or(makeCuboidShape(15, 0, 1, 1, 1, 15)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
				case WEST :
					return VoxelShapes
							.combineAndSimplify(
									VoxelShapes.or(makeCuboidShape(2, 1, 14, 14, 2, 2), makeCuboidShape(3, 2, 13, 13, 3, 3),
											makeCuboidShape(4, 3, 12, 12, 4, 4), makeCuboidShape(5, 4, 11, 11, 5, 5),
											makeCuboidShape(6, 5, 10, 10, 6, 6), makeCuboidShape(7, 6, 9, 9, 7, 7)),
									VoxelShapes.or(makeCuboidShape(1, 0, 15, 15, 1, 1)), IBooleanFunction.ONLY_FIRST)
							.withOffset(offset.x, offset.y, offset.z);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			;
			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
