public class Dragon extends Cow{

	public Dragon(String name, String image){//Constructor for the Dragon class that extends Cow.
		super(name);
		setImage(image);
	}
	public void setImage(String _image){
		image = _image;
		if(canBreatheFire())
			image += "\nThis dragon can breathe fire.";
		else
			image += "\nThis dragon cannot breathe fire.";
	}
	public boolean canBreatheFire(){//Breath fire is always true for basic dragon type.
		return true;
	}
}
