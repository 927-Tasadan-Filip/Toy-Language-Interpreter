package Repository;

import Model.ProgramState.PrgState;

import java.util.ArrayList;

public class Repo implements IRepo {
    private ArrayList<PrgState> records_prg_states;

    public Repo() {
        records_prg_states = new ArrayList<PrgState>();
    }

    public Repo(PrgState state) {
        this();
        records_prg_states.add(state);
    }

    @Override
    public PrgState getCrtPrg() {
        return records_prg_states.get(0);
    }
}
