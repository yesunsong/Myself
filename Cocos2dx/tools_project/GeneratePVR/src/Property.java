public class Property {
	public String PixelFormat = "RGBA4444";
	public String DitherMode = "fs-alpha";
	public String Use_alpha = "";

	public Property() {
		this.PixelFormat = "RGBA4444";
		this.DitherMode = "fs-alpha";
		this.Use_alpha = "--premultiply-alpha";
	}
}
