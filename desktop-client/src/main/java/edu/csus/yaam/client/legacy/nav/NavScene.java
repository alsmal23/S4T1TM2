package edu.csus.yaam.client.legacy.nav;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
@Data
public class NavScene {
    @Delegate
    private final NavAction action;

    private final Node node;

    @SneakyThrows
    @SafeVarargs
    public static NavScene[] scenes(Class<? extends ProjectScene>... types) {
        List<NavScene> scenes = new ArrayList<>();
        for (Class<? extends ProjectScene> type : types) {
            scenes.add(new NavScene(type.getDeclaredAnnotation(NavAction.class), type.newInstance().getScene()));
        }
        return scenes.toArray(new NavScene[scenes.size()]);
    }
}