package com.antwei.cuit.moodpartner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.antwei.cuit.moodpartner.adapter.ChatAdapter;
import com.antwei.cuit.moodpartner.entity.MessageEntity;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity implements OnClickListener {

	private ListView msgListView;
	private ImageButton voice;
	private Button back;
	private ChatAdapter chatAdapter;
	private List<MessageEntity> msgs = new ArrayList<MessageEntity>();
	private RecognizerDialog iatDialog;
	private String userChat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		msgListView = (ListView) findViewById(R.id.ltv_chat);
		voice = (ImageButton) findViewById(R.id.btn_voice);
		back = (Button)findViewById(R.id.btn_back);
		chatAdapter = new ChatAdapter(getBaseContext(), msgs);
		msgListView.setAdapter(chatAdapter);
		voice.setOnClickListener(this);
		back.setOnClickListener(this);
		iatDialog = new RecognizerDialog(ChatActivity.this, "appid=51f7ee36");
		iatDialog.setListener(recognizerListener);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.btn_voice:
			Toast.makeText(getBaseContext(), "voice Touched", Toast.LENGTH_SHORT).show();
			showMyDialog();
			break;
			
		case R.id.btn_back:
			finish();
			break;
		}
	}
	
	private void send(){
		MessageEntity msg = new MessageEntity();
		msg.setName("Ant");
		msg.setDate(getDate());
		msg.setFrom(false);
		msg.setText(userChat);
		msgs.add(msg);
		chatAdapter.notifyDataSetChanged();
		msgListView.setSelection(msgListView.getCount());
		
	}
	
	private void showMyDialog() {
		// TODO Auto-generated method stub
		userChat = "";
		iatDialog.setEngine("sms", null, null);
		iatDialog.setSampleRate(RATE.rate16k);
		iatDialog.show();
	}

	private String getDate(){
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		StringBuffer date = new StringBuffer();
		date.append(year+"-"+month+"-"+day+"-"+hour+"-"+minute);
		return date.toString();
	}
	
	RecognizerDialogListener recognizerListener = new RecognizerDialogListener() {
		
		@Override
		public void onResults(ArrayList<RecognizerResult> results, boolean arg1) {
			// TODO Auto-generated method stub
			StringBuilder builder = new StringBuilder();
			for(RecognizerResult resultChat:results){
				builder.append(resultChat.text);
			}
			userChat += builder.toString();
		}
		
		@Override
		public void onEnd(SpeechError arg0) {
			// TODO Auto-generated method stub
			send();
		}
	};

}
