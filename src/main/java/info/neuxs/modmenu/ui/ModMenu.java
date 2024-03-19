package info.neuxs.modmenu.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.UIElement;
import info.neuxs.modmenu.utils.FabricModGrabber;
import info.neuxs.modmenu.utils.Render;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.awt.*;
import java.io.IOException;

import static info.neuxs.modmenu.utils.FabricModGrabber.*;

public class ModMenu extends GameState {
    GameState previousState;
    private Boolean isSearching = false;
    private SpriteBatch batch = new SpriteBatch();
    private Viewport uiViewport;
    private String selMod = "";

    public ModMenu(final GameState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void create() {
        super.create();

        /* TODO: add mod searching
        UIElement searchBox = new UIElement(-405.0F, -260.0F, 200.0F, 50.0F) {
            public void onClick() {
                super.onClick();

                isSearching = true;
            }
        };
        searchBox.setText("Search Mods");
        searchBox.show();
        this.uiElements.add(searchBox);
         */

        UIElement modDirButton = new UIElement(130.0F, -260.0F, 250.0F, 50.0F) {
            public void onClick() {
                super.onClick();

                try {
                    Desktop.getDesktop().open(getModDir());
                } catch (IOException var2) {
                    var2.printStackTrace();
                }

            }
        };
        modDirButton.setText("Open Mods Directory");
        modDirButton.show();
        this.uiElements.add(modDirButton);

        UIElement returnButton = new UIElement(390.0F, -260.0F, 250.0F, 50.0F) {
            public void onClick() {
                super.onClick();
                GameState.switchToGameState(GameState.MAIN_MENU);
            }
        };
        returnButton.setText("Return to Main Menu");
        returnButton.show();
        this.uiElements.add(returnButton);

        // Side mod buttons
        int modIndex = 0;
        for (ModContainer mod: FabricLoader.getInstance().getAllMods()) {
            UIElement modButton = new UIElement(-405.0F, -270.0F + (modIndex * 60), 250.0F, 50.0F) {
                public void onClick() {
                    super.onClick();
                    selMod = mod.getMetadata().getId();
                }
            };
            modButton.setText(mod.getMetadata().getName());
            modButton.show();
            this.uiElements.add(modButton);

            modIndex ++;
        }
    }

    private void updateModInfo(String name, String authors, String version, String description) {
        FontRenderer.fontTexture.bind(0);
        FontRenderer.drawText(batch, uiViewport, name, -260, -205);
        FontRenderer.drawText(batch, uiViewport, "Author(s): " + authors, -260, -185);
        FontRenderer.drawText(batch, uiViewport, "Version: " + version, -260, -165);
        FontRenderer.drawText(batch, uiViewport, "Description:\n" + description, -260, -145);
    }

    public void render() {
        super.render();

        if (Gdx.input.isKeyJustPressed(111) && (!isSearching)) {
            switchToGameState(this.previousState);
        }

        // Draws the Background of screen
        ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F, true);

        Render.drawLine(250F, 0F, 250F, Gdx.graphics.getHeight(), 2, new Color(255, 255, 255, 255));
        Render.drawLine(250F, 500F, Gdx.graphics.getWidth(), 500F, 2, new Color(255, 255, 255, 255));

        // Mod Info

        if (!selMod.isBlank()) {
            batch.setProjectionMatrix(this.uiCamera.combined);
            batch.begin();
            updateModInfo(grabName(selMod), grabAuthors(selMod), grabVersion(selMod), grabDescription(selMod));
            batch.end();
        }

        // Render Everything to Screen
        Gdx.gl.glEnable(2929);
        Gdx.gl.glDepthFunc(513);
        Gdx.gl.glEnable(2884);
        Gdx.gl.glCullFace(1029);
        Gdx.gl.glEnable(3042);
        Gdx.gl.glBlendFunc(770, 771);
        this.drawUIElements();
    }
}
