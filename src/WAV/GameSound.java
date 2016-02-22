package WAV;
	import java.applet.Applet;
	import java.applet.AudioClip;
	import java.util.HashMap;

	public class GameSound {
		public static GameSound instance;
		
		public static final String MENU_PLAY = "menu";
		public static final String MAIN_START = "plane_start";
		public static final String MAIN_PLAY = "main_play";
		public static final String SHOOTING = "shot";
		public static final String ENEMY_DIE = "chicken_dead";
		public static final String DEFEATED = "myplane_die";
		private HashMap<String, AudioClip> audioMap;

		public GameSound() {
			audioMap = new HashMap<>();
			loadAllAudio();
		}

		public static GameSound getIstance() {
			if (instance == null) {
				instance = new GameSound();
			}

			return instance;
		}

		public void loadAllAudio() {
			putAudio(MENU_PLAY);
			putAudio(MAIN_START);
			putAudio(MAIN_PLAY);
			putAudio(SHOOTING);
			putAudio(ENEMY_DIE);
			putAudio(DEFEATED);
		}

		public void stop() {
			getAudio(MENU_PLAY).stop();
			getAudio(MAIN_START).stop();
			getAudio(MAIN_PLAY).stop();
			getAudio(SHOOTING).stop();
		}

		public void putAudio(String name) {
			AudioClip auClip = Applet.newAudioClip(GameSound.class.getResource(name+ ".wav"));
			audioMap.put(name, auClip);
		}

		public AudioClip getAudio(String name) {
			return audioMap.get(name);
		}
	}

