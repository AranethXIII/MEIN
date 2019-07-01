
public class Speechoptions {
	TextToSpeech tts;
	public Speechoptions() {
		TextToSpeech tts = new TextToSpeech();
		this.tts=tts;
		}

	//Male English
	public void engMale(String s) {
		tts.setVoice("cmu-rms-hsmm");
		tts.speak(s, 2.0f, false, true);
	}
	
	//female english
	public void engFem(String s) {
		TextToSpeech tts = new TextToSpeech();
		tts.setVoice("cmu-slt-hsmm");
		tts.speak(s, 2.0f, false, true);
	}
	
	//female english
	public void engFem2(String s) {
		TextToSpeech tts = new TextToSpeech();
		tts.setVoice("dfki-poppy-hsmm");
		tts.speak(s, 2.0f, false, true);
	}
	//female German
	public void gerFem(String s) {
		TextToSpeech tts = new TextToSpeech();
		tts.setVoice("bits1-hsmm");
		tts.speak(s, 2.0f, false, true);
	}
	//male German
	public void gerMale(String s) {
		TextToSpeech tts = new TextToSpeech();
		tts.setVoice("bits3-hsmm");
		tts.speak(s, 2.0f, false, true);
	}

}
