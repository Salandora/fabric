package net.fabricmc.fabric.test.recipe.ingredient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.impl.recipe.ingredient.CustomIngredientImpl;
import net.fabricmc.fabric.impl.recipe.ingredient.builtin.ComponentsIngredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.test.GameTestException;
import net.minecraft.util.Identifier;

public class ClientCustomIngredientSyncTests implements ClientModInitializer {
	/**
	 * The recipe requires a custom ingredient.
	 */
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_WORLD_TICK.register(world -> {
			Identifier recipeId = Identifier.of("fabric-recipe-api-v1-testmod", "test_customingredients_sync");
			ShapelessRecipe recipe = (ShapelessRecipe) world.getRecipeManager().get(recipeId).get().value();
			if (!(recipe.getIngredients().getFirst() instanceof CustomIngredientImpl customIngredient)) {
				throw new GameTestException("Expected the first ingredient to be a CustomIngredientImpl");
			}
			if (!(customIngredient.getCustomIngredient() instanceof ComponentsIngredient)) {
				throw new GameTestException("Expected the custom ingredient to be a ComponentsIngredient");
			}
		});
	}
}
