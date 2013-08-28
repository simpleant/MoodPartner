package com.antwei.cuit.moodpartner.adapter;

import java.util.List;

import com.antwei.cuit.moodpartner.R;
import com.antwei.cuit.moodpartner.entity.MessageEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {

	private List<MessageEntity> coll;
	private Context ctx;
	private LayoutInflater mInflater;

	public static interface IMsgViewType {
		int ANDROID = 0;
		int USER = 1;
	}

	public ChatAdapter(Context context, List<MessageEntity> coll) {
		ctx = context;
		this.coll = coll;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return coll.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return coll.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		MessageEntity msgEntity = coll.get(position);
		if (msgEntity.getMsgType()) {
			return IMsgViewType.ANDROID;
		} else {
			return IMsgViewType.USER;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MessageEntity msgEntity = coll.get(position);
		boolean isFrom = msgEntity.getMsgType();
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isFrom) {
				convertView = mInflater.inflate(R.layout.chatlist_answer, null);
			} else {
				convertView = mInflater.inflate(R.layout.chatlist_user, null);
			}
			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			viewHolder.tvUserName = (TextView) convertView
					.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_msgcontent);
			viewHolder.isFrom = isFrom;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvSendTime.setText(msgEntity.getDate());
		viewHolder.tvUserName.setText(msgEntity.getName());
		viewHolder.tvContent.setText(msgEntity.getText());
		return convertView;
	}

	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public boolean isFrom = true;
	}

}
