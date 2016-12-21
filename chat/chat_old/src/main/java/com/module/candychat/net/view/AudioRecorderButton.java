package com.module.candychat.net.view;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.module.candychat.net.R;


public class AudioRecorderButton extends Button implements AudioManager.AudioStateListener {

	private static final int STATE_NORMAL=1;
	private static final int STATE_RECORDING=2;
	private static final int STATE_WANT_TO_CANCEL=3;
	private static final int DISTANCE_CANCEL=50;
	private int mCurState=STATE_NORMAL;
	private boolean isRecording = false;
	private DialogManager mDialogManager;
	private AudioManager mAudioManager;
	private float mTime;
	private boolean mReady;
	
	public AudioRecorderButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDialogManager=new DialogManager(getContext());
		String dir= Environment.getExternalStorageDirectory()+"/imooc_recorder_audios";
		mAudioManager=AudioManager.getInstance(dir);
		Log.d("AudioRecorderButton", "before");
		mAudioManager.setOnAudioStateListener(this);
		
		setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				mReady=true;
				Log.d("longclick", "beforeClick");
				mAudioManager.prepareAudio();
				return false;
			}
		});
	}

	public interface AudioFinishRecorderListener {
		void onFinish(float seconds, String filePath);
	}
	
	private AudioFinishRecorderListener mListener;
	
	public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
		mListener=listener;
	}

	private Runnable mGetVoiceLevelRunnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRecording)
			{
				Log.d("mGetVoiceLevelRunnable", "before_try");
				try {
					Thread.sleep(100);
					mTime+=0.1f;
					mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	
	private static final int MSG_AUDIO_PREPARED=0X110;
	private static final int MSG_VOICE_CHANGED=0X111;
	private static final int MSG_DIALOG_DIMISS=0X112;
	private Handler mHandler=new Handler()
	{
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_AUDIO_PREPARED:

				mDialogManager.showRecordingDialog();
				isRecording=true;
				Log.d("handleMessage", "beforeThread");

				new Thread(mGetVoiceLevelRunnable).start();
				Log.d("handleMessage", "afterThread");
				break;
			case MSG_VOICE_CHANGED:
				Log.d("MSG_VOICE_CHANGED", "in");

				mDialogManager.updateVoice(mAudioManager.getVoiceLevel(7));			
				break;
			case MSG_DIALOG_DIMISS:
				mDialogManager.dimissDialog();
	            break;

			default:
				break;
			}
	}
  };

	
	@Override
	public void wellPrepared() {
		// TODO Auto-generated method stub
		Log.d("wellPrepared", "InsideWellPrepared_before");
		mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
		Log.d("wellPrepared", "InsideWellPrepared");
	}

	public AudioRecorderButton(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		// TODO Auto-generated method stub
		int action=event.getAction();
		int x=(int)event.getX();
		int y=(int)event.getY();
		
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			changeState(STATE_RECORDING);
			break;
		case MotionEvent.ACTION_MOVE:
			if(isRecording)
			{
				if(isCancel(x,y))
				{
					changeState(STATE_WANT_TO_CANCEL);
				}else {
					changeState(STATE_RECORDING);
				}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			Log.d("ACTION_UP", "mReady=" + mReady);
			if(!mReady)
			{
				reset();
				return super.onTouchEvent(event);
			}
			if(!isRecording||mTime<0.6f)
			{
				mDialogManager.tooShort();
				mAudioManager.cancel();
				//Log.d("ACTION_UP","up_dissmiss");
				mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS,300);//��ʱ1.3s
				//Log.d("ACTION_UP","up_dissmiss_done");
			}
			else if(mCurState==STATE_RECORDING) //��¼�ƽ���
			{
				Log.d("ACTION_UP", "in1");
				mDialogManager.dimissDialog();
				mAudioManager.release();
				Log.d("ACTION_UP", "out1");
				if(mListener!=null)
				{
					Log.d("ACTION_UP", "mTime=" + mTime);
					mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
				}
	
			}else if (mCurState==STATE_WANT_TO_CANCEL)
			{
				//cancel
				mDialogManager.dimissDialog();
				mAudioManager.cancel();
			}
			reset();
			Log.d("ACTION_UP", "break");
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void reset() {

		isRecording=false;
		mReady=false;
		mTime=0;
		mAudioManager.isPrepared=false;
		changeState(STATE_NORMAL);
	}

	private boolean isCancel(int x, int y) {
		// TODO Auto-generated method stub
		if(x<0||x>getWidth())
			return true;
		if(y<-DISTANCE_CANCEL||y>getHeight()+DISTANCE_CANCEL)
			return true;
		return false;
	}


	private void changeState(int state) {
		// TODO Auto-generated method stub
		if (mCurState!=state) {
			mCurState=state;
			switch (state) {
			case STATE_NORMAL:
				setBackgroundResource(R.drawable.ic_info_voice_icon);
				Log.d("STATE_NORMAL", "break");
				break;
			case STATE_RECORDING:
				Log.d("enentDown", "beforeDown");
				setBackgroundResource(R.drawable.ic_info_voice_icon);
				Log.d("enentDown", "afterDown");
				if(isRecording){
					mDialogManager.recording();
				}
				break;
			case STATE_WANT_TO_CANCEL:
				setBackgroundResource(R.drawable.ic_info_voice_icon);
				mDialogManager.wantToCancel();
				break;

			default:
				break;
			}
		}
	}

}
