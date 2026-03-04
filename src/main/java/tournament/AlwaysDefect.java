package tournament;

public class AlwaysDefect implements Participant {

	@Override
    public Action chooseAction(GameHistory history) {
        return PrisonerAction.DEFECT;
    }

	@Override
	public void reset() {
		//nothing
		
	}

}
