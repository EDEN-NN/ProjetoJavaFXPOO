package boundary;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class Boundary {
    private List<IExecutorComandos> list = new ArrayList<>();

    public abstract Pane render();

    public void addExec(IExecutorComandos e) {
        list.add(e);
    }

    public void removeExec(IExecutorComandos e) {
        list.remove(e);
    }

    public void notifyExec(String e) {
        for (IExecutorComandos exec : list) {
            exec.executarComandos(e);
        }
    }

}
