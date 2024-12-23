/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.test.recipe.ingredient.client;

import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.test.GameTestException;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.impl.recipe.ingredient.CustomIngredientImpl;
import net.fabricmc.fabric.impl.recipe.ingredient.builtin.ComponentsIngredient;

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
