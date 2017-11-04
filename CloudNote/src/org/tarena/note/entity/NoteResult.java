package org.tarena.note.entity;

import java.io.Serializable;

public class NoteResult implements Serializable {
    //���صĽ�������Ϣ
	private int status;
    private Object data;
    private String msg;
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "NoteResult [data=" + data + ", msg=" + msg + ", status="
				+ status + "]";
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
    
}
