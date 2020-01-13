package twitetr;

public class Word {

	private String word;
	private String changed;
	private int pos;

	public Word (int pos, String word, String changed) {
		this.word=word;
		this.changed=changed;
		this.pos = pos;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}
}
