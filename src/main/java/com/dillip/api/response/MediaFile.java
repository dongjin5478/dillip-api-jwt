package com.dillip.api.response;

import java.util.Arrays;

public class MediaFile {
	private String fileName;
	private byte[] byteData;
	public MediaFile() {
		super();
	}
	public MediaFile(String fileName, byte[] byteData) {
		super();
		this.fileName = fileName;
		this.byteData = byteData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getByteData() {
		return byteData;
	}
	public void setByteData(byte[] byteData) {
		this.byteData = byteData;
	}
	@Override
	public String toString() {
		return "MediaFile [fileName=" + fileName + ", byteData=" + Arrays.toString(byteData) + "]";
	}
}
