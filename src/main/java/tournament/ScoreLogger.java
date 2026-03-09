package tournament;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreLogger implements GameObserver {
	private String filePath;
	private FileWriter writer;
	
	public ScoreLogger(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void onMoveMade(MoveEvent event) {
		//not needed, thats for move logger

	}

	@Override
	public void onTournamentEnd(TournamentResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameEnd(GameResult result) {
		try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(result.getP1Name() + " vs " + result.getP2Name() + " | Winner: " + result.getWinner() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
