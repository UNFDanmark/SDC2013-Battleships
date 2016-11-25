package dk.unf.software.aar2013.gruppe4;

import android.media.SoundPool;
import android.content.Context;
import android.media.AudioManager;

public class sound {

	
	private Context pContext;		// Local copy of app context
	private SoundPool sndPool;		// Our SoundPool instance
	private float rate = 1.0f;		// Playback rate
	private float volume = 1.0f;	// Volume levels for left and right channels
	
	public sound(Context appContext)
	{
	  sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
	  pContext = appContext;
	}
	
	public int load(int sound_id)
	{
		return sndPool.load(pContext, sound_id, 1);
	}
	
	public void play(int sound_id, float volumentest)
	{
		sndPool.play(sound_id, volumentest, volumentest, 1, 0, rate); 	
	}

	
}
