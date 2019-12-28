package baitap1;

//represent information about an image
public class Image {
	int width; // in pixels
	int height; // in pixels
	String source; // file name
	String quality;// informal
	Image (int width, int height, String source, String quality){
		this.width= width;
		this.height=height;
		this.source=source;
		this.quality=quality;
	}
	// determines whether the image's height is larger than its width
	// xac dinh chieu cao cua hinh co lon hon chieu rong cua hinh hay khong?
	boolean isPortrait (){
		boolean result = false;
		// Neu chieu cao lon hon chieu rong thi tra ve dung (true)
		if (this.height > this.width)
			result=true;
		return result;
	}
	// computes how many pixels the image contains
	// tinh xem trong hinh chua bao nhieu pixel (tinh dien tich cua hinh)
	int size(){
		int result=0;
		// dien tich cua hinh la bang chieu cao (height) * chieu rong (width)
		result = this.height*this.width;
		return result;
	}
	//determines whether one image contains more pixels than some other image
	// xac dinh xem mot hinh co dien tich (chua nhieu pixels) hon hinh khac
	boolean isLarger (Image i){
		boolean result = false;
		// tinh dien tich cua tung hinh va sau do so sanh
		if (this.size()>i.size())
			result=true;
		return result;
	}
}


