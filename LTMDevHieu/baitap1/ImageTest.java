package baitap1;

public class ImageTest {
	public static void main(String[] args){
		Image i;
		i= new Image(4, 5, "abc.jpg", "hight");
		System. out.println(i.isPortrait());
		Image i1;
		i1=new Image(4,2, "abc.jpg","hight");
		System.out.println(i1.isPortrait());
		
		

		}

}
