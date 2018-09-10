package vo;

public class Pds {

	protected int code;
	protected String filename;
	protected int filesize;
	protected String description;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Pds [code=" + code + ", filename=" + filename + ", filesize=" + filesize + ", description="
				+ description + "]";
	}

}
