package com.module.candychat.net.view;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.util.UUID;

public class AudioManager {
	private MediaRecorder mMediaRecorder;
	private String mDir;
	private String mCurrentFilePath;
	private static AudioManager mInstance;
	public boolean isPrepared;
	
	private AudioManager(String dir) {
		// TODO Auto-generated constructor stub
		mDir=dir;
	}
	
	

	public interface AudioStateListener
	{

		void wellPrepared();
	}
	
	public AudioStateListener mListener;
	public void setOnAudioStateListener(AudioStateListener listener) {
		mListener=listener;
		Log.d("setOnAudioStateListener", "in");
	}
	

	public static AudioManager getInstance(String dir)
	{
		if(mInstance==null)
		{

			synchronized (AudioManager.class) {
				if(mInstance==null)
					mInstance=new AudioManager(dir);
			}
		}
		
		return mInstance;
	}
	
	public void prepareAudio() {
		Log.d("preparedAudio", "before_try");
		try {
			Log.d("preparedAudio", "In_try");
			isPrepared=false;
			File dir = new File(mDir);

			if (!dir.exists())
				dir.mkdirs();
			String fileName = generateFileName();
			File file = new File(dir, fileName);
			
			
			
			mCurrentFilePath=file.getAbsolutePath();
			
			
			mMediaRecorder = new MediaRecorder();

			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

			mMediaRecorder.setOutputFile(file.getAbsolutePath());

			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);

			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			Log.d("preparedAudio", "before_prepare");

			mMediaRecorder.prepare();
			Log.d("preparedAudio", "before_start");
			mMediaRecorder.start();

			isPrepared=true;
			Log.d("preparedAudio", "before_if");
			if(mListener!=null)
				mListener.wellPrepared();
			Log.d("preparedAudio", "inside_if");
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("preparedAudio", "prepareAudio_exception");
			e.printStackTrace();
		}
		Log.d("preparedAudio", "InsidePreparedAudio_out");
	}

	private String generateFileName() {
		return UUID.randomUUID().toString()+".amr";
	}

	public int getVoiceLevel(int maxLevel) {
		if(isPrepared)
		{
			try {
				//mMediaRecorder.getMaxAmplitude():1-32767
				int voiceLevel=maxLevel*mMediaRecorder.getMaxAmplitude()/32768+1;
				int p=mMediaRecorder.getMaxAmplitude();
				Log.d("getVoiceLevel", "maxlevel=" + p);
				return voiceLevel;
				
			} catch (IllegalStateException e) {}
		}
		return 1;
	}
	
	public void release() {
		Log.d("release", "in");
 		mMediaRecorder.stop();
		mMediaRecorder.release();
		mMediaRecorder=null;
		Log.d("release", "out");
	}
	
	public void cancel() {
		release();
		if(mCurrentFilePath!=null)
		{
			File file=new File(mCurrentFilePath);
			file.delete();
			mCurrentFilePath=null;
		}
		
	}

	public String getCurrentFilePath() {
		return mCurrentFilePath;
	}
}
