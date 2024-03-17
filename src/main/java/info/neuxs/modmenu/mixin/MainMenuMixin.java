package info.neuxs.modmenu.mixin;

import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.MainMenu;
import finalforeach.cosmicreach.ui.UIElement;
import finalforeach.cosmicreach.world.World;
import info.neuxs.modmenu.ui.ModMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenu.class)
public class MainMenuMixin extends GameState{
    @Inject(method = "create", at = @At("HEAD"))
    private void createInjected(CallbackInfo ci) {
        super.create();
        World.worldLoader.readyToPlay = false;

        UIElement modsButton = new UIElement(-430.0F, -250.0F, 150.0F, 50.0F) {
            public void onClick() {
                super.onClick();
                GameState.switchToGameState(new ModMenu(MainMenuMixin.this));
            }
        };

        modsButton.setText("Mods");
        modsButton.show();
        this.uiElements.add(modsButton);
    }
}