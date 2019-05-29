package es.wokis.spacedroid.utils;

public class DataApi {
	
	public int score = 0;
	public int round = -1;	
	
	public void addScore(int q) {
		score += q;
	}
	
	public void removeScore(int q) {
		score -= q;
	}
	
	public void setScore(int q) {
		score = q;
	}
	
	public boolean has(int q) {
		return score >= q;
	}
	
	public void nextRound() {
		round++;
	}
}
